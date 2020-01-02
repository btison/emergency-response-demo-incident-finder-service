package com.redhat.emergency.response.incident.finder.streams;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.Arrays;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;

import com.redhat.emergency.response.incident.finder.model.Incident;
import com.redhat.emergency.response.incident.finder.model.Mission;
import com.redhat.emergency.response.incident.finder.model.Shelter;
import io.quarkus.kafka.client.serialization.JsonbSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueStore;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class TopologyProducer {

    private static final Logger log = LoggerFactory.getLogger(InteractiveQueries.class);

    private static final String INCIDENT_REPORTED_EVENT = "IncidentReportedEvent";
    private static final String INCIDENT_UPDATED_EVENT = "IncidentUpdatedEvent";

    private static final String MISSION_STARTED_EVENT = "MissionStartedEvent";
    private static final String MISSION_PICKEDUP_EVENT = "MissionPickedUpEvent";
    private static final String MISSION_COMPLETED_EVENT = "MissionCompletedEvent";

    private static final String[] INCIDENT_ACCEPTED_MESSAGE_TYPES = {INCIDENT_REPORTED_EVENT, INCIDENT_UPDATED_EVENT};
    private static final String[] MISSION_ACCEPTED_MESSAGE_TYPES = {MISSION_STARTED_EVENT, MISSION_PICKEDUP_EVENT, MISSION_COMPLETED_EVENT};

    @ConfigProperty(name = "kafka.topic.incident-event")
    String incidentEventTopic;

    @ConfigProperty(name = "kafka.topic.mission-event")
    String missionEventTopic;

    @Produces
    public Topology buildTopology() {

        StreamsBuilder builder = new StreamsBuilder();

        JsonbSerde<Incident> incidentSerde = new JsonbSerde<>(Incident.class);
        JsonbSerde<Mission> missionSerde = new JsonbSerde<>(Mission.class);

        KTable<String, String> incidents = builder.table(incidentEventTopic, Consumed.with(Serdes.String(), Serdes.String()));

        KTable<String, Mission> missions = builder.table(missionEventTopic, Consumed.with(Serdes.String(), Serdes.String()))
                .filter((key, value) -> {
                    try {
                        JsonObject jsonReader = Json.createReader(new StringReader(value)).readObject();
                        String messageType = jsonReader.getString("messageType");
                        if (Arrays.asList(MISSION_ACCEPTED_MESSAGE_TYPES).contains(messageType)) {
                            return true;
                        }
                        log.debug("Message with type '" + messageType + "' is ignored");
                    } catch (Exception e) {
                        log.warn("Unexpected message which is not JSON or without 'messageType' field.");
                    }
                    return false;
                }).mapValues(value -> {
                    JsonObject event = Json.createReader(new StringReader(value)).readObject();
                    JsonObject body = event.getJsonObject("body");
                    try (ByteArrayOutputStream oos = new ByteArrayOutputStream();
                         JsonWriter writer = Json.createWriter(oos)) {
                        writer.writeObject(body);
                        writer.close();
                        oos.flush();
                        byte[] bytes = oos.toByteArray();
                        return missionSerde.deserializer().deserialize(missionEventTopic, bytes);
                    } catch (IOException e) {
                        log.error("Exception while deserializing Mission", e);
                        return null;
                    }
                });

        incidents.filter((key, value) -> {
            try {
                JsonObject jsonReader = Json.createReader(new StringReader(value)).readObject();
                String messageType = jsonReader.getString("messageType");
                if (Arrays.asList(INCIDENT_ACCEPTED_MESSAGE_TYPES).contains(messageType)) {
                    return true;
                }
                log.debug("Message with type '" + messageType + "' is ignored");
            } catch (Exception e) {
                log.warn("Unexpected message which is not JSON or without 'messageType' field.");
            }
            return false;
        }).mapValues(value -> {
            JsonObject event = Json.createReader(new StringReader(value)).readObject();
            JsonObject body = event.getJsonObject("body");
            try (ByteArrayOutputStream oos = new ByteArrayOutputStream();
                 JsonWriter writer = Json.createWriter(oos)) {
                writer.writeObject(body);
                writer.close();
                oos.flush();
                byte[] bytes = oos.toByteArray();
                return incidentSerde.deserializer().deserialize(incidentEventTopic, bytes);
            } catch (IOException e) {
                log.error("Exception while deserializing Incident", e);
                return null;
            }
        }).leftJoin(missions, (incident, mission) -> {
            if (mission == null) {
                return incident;
            }
            incident.setDestinationLat(mission.getDestinationLat());
            incident.setDestinationLon(mission.getDestinationLong());
            incident.setDestinationName(destinationName(mission.getDestinationLat(), mission.getDestinationLong()));
            if (mission.getResponderLocationHistory().isEmpty()) {
                incident.setCurrentPositionLat(incident.getLat());
                incident.setCurrentPositionLon(incident.getLon());
            } else {
                int last = mission.getResponderLocationHistory().size() - 1;
                incident.setCurrentPositionLat(mission.getResponderLocationHistory().get(last).getLat());
                incident.setCurrentPositionLon(mission.getResponderLocationHistory().get(last).getLon());

            }
            return incident;
        }, Materialized.<String, Incident, KeyValueStore<Bytes, byte[]>> as("incidents-store").withKeySerde(Serdes.String()).withValueSerde(incidentSerde));

        return builder.build();
    }

    private String destinationName(BigDecimal lat, BigDecimal lon) {
        return Shelter.shelters().stream()
                .filter(shelter -> shelter.getLat().equals(lat) && shelter.getLon().equals(lon))
                .map(Shelter::getName).findFirst().orElse("");
    }
}

package com.redhat.emergency.response.incident.finder.streams;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.redhat.emergency.response.incident.finder.model.Incident;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.errors.InvalidStateStoreException;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class InteractiveQueries {

    private static final Logger LOG = LoggerFactory.getLogger(InteractiveQueries.class);

    @Inject
    KafkaStreams streams;

    public Incident getIncident(String id) {
        return getIncidentsStore().get(id);
    }

    private ReadOnlyKeyValueStore<String, Incident> getIncidentsStore() {
        while (true) {
            try {
                return streams.store("incidents-store", QueryableStoreTypes.keyValueStore());
            } catch (InvalidStateStoreException e) {
                // ignore, store not ready yet
            }
        }
    }

}

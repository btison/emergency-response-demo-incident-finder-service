package com.redhat.emergency.response.incident.finder.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;

import io.quarkus.kafka.client.serialization.JsonbSerde;
import org.junit.jupiter.api.Test;

public class MissionSerdeTest {

    @Test
    void testDeserialization() {
        String s = "{\"id\":\"0c86bf5a-77c8-4e18-8f27-cd9bbca1a7ce\",\"incidentId\":\"6144b9f1-0174-445d-bfaf-31f747f3536a\"," +
                "\"responderId\":\"8\",\"responderStartLat\":34.2119,\"responderStartLong\":-77.8231," +
                "\"incidentLat\":34.212,\"incidentLong\":-77.8233,\"destinationLat\":34.2461,\"destinationLong\":-77.9519," +
                "\"responderLocationHistory\":[{\"timestamp\":1577975754323,\"lat\":34.2381,\"lon\":-77.8503}," +
                "{\"timestamp\":1577975764187,\"lat\":34.2311,\"lon\":-77.8364}," +
                "{\"timestamp\":1577975774169,\"lat\":34.2194,\"lon\":-77.8326}," +
                "{\"timestamp\":1577975784200,\"lat\":34.2128,\"lon\":-77.8225}," +
                "{\"timestamp\":1577975794186,\"lat\":34.2119,\"lon\":-77.8231}]," +
                "\"status\":\"UPDATED\"," +
                "\"steps\":[{\"lat\":34.2457,\"lon\":-77.8503,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2444,\"lon\":-77.8546,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2413,\"lon\":-77.8566,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2277,\"lon\":-77.8297,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2194,\"lon\":-77.8326,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2137,\"lon\":-77.8224,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2128,\"lon\":-77.8225,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2119,\"lon\":-77.8231,\"destination\":false,\"wayPoint\":true}," +
                "{\"lat\":34.2119,\"lon\":-77.8231,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2137,\"lon\":-77.8224,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2194,\"lon\":-77.8326,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2277,\"lon\":-77.8297,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2551,\"lon\":-77.8709,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2508,\"lon\":-77.9474,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2484,\"lon\":-77.9477,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2467,\"lon\":-77.9488,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2463,\"lon\":-77.9514,\"destination\":true,\"wayPoint\":false}]}";

        JsonbSerde<Mission> missionSerde = new JsonbSerde<>(Mission.class);
        Mission mission = missionSerde.deserializer().deserialize("test", s.getBytes());
        assertNotNull(mission);
        assertEquals( "0c86bf5a-77c8-4e18-8f27-cd9bbca1a7ce", mission.getId());
    }

    @Test
    void testMapFromMessage() {
        String s = "{\"id\":\"94dba155-762a-4f8e-97af-a7733064c53a\",\"messageType\":\"MissionPickedUpEvent\"," +
                "\"invokingService\":\"MissionService\",\"timestamp\":1577975794186," +
                "\"body\":{\"id\":\"0c86bf5a-77c8-4e18-8f27-cd9bbca1a7ce\",\"incidentId\":\"6144b9f1-0174-445d-bfaf-31f747f3536a\"," +
                "\"responderId\":\"8\",\"responderStartLat\":34.2119,\"responderStartLong\":-77.8231," +
                "\"incidentLat\":34.212,\"incidentLong\":-77.8233,\"destinationLat\":34.2461,\"destinationLong\":-77.9519," +
                "\"responderLocationHistory\":[{\"timestamp\":1577975754323,\"lat\":34.2381,\"lon\":-77.8503}," +
                "{\"timestamp\":1577975764187,\"lat\":34.2311,\"lon\":-77.8364}," +
                "{\"timestamp\":1577975774169,\"lat\":34.2194,\"lon\":-77.8326}," +
                "{\"timestamp\":1577975784200,\"lat\":34.2128,\"lon\":-77.8225}," +
                "{\"timestamp\":1577975794186,\"lat\":34.2119,\"lon\":-77.8231}]," +
                "\"status\":\"UPDATED\"," +
                "\"steps\":[{\"lat\":34.2457,\"lon\":-77.8503,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2444,\"lon\":-77.8546,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2413,\"lon\":-77.8566,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2277,\"lon\":-77.8297,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2194,\"lon\":-77.8326,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2137,\"lon\":-77.8224,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2128,\"lon\":-77.8225,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2119,\"lon\":-77.8231,\"destination\":false,\"wayPoint\":true}," +
                "{\"lat\":34.2119,\"lon\":-77.8231,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2137,\"lon\":-77.8224,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2194,\"lon\":-77.8326,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2277,\"lon\":-77.8297,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2551,\"lon\":-77.8709,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2508,\"lon\":-77.9474,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2484,\"lon\":-77.9477,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2467,\"lon\":-77.9488,\"destination\":false,\"wayPoint\":false}," +
                "{\"lat\":34.2463,\"lon\":-77.9514,\"destination\":true,\"wayPoint\":false}]}}";
        JsonObject msg = Json.createReader(new StringReader(s)).readObject();
        JsonObject body = msg.getJsonObject("body");
        try (ByteArrayOutputStream oos = new ByteArrayOutputStream();
             JsonWriter writer = Json.createWriter(oos)) {
            writer.writeObject(body);
            writer.close();
            oos.flush();
            byte[] bytes = oos.toByteArray();
            Mission mission = new JsonbSerde<>(Mission.class).deserializer().deserialize("", bytes);
            assertNotNull(mission);
            assertEquals("0c86bf5a-77c8-4e18-8f27-cd9bbca1a7ce", mission.getId());
        } catch (IOException e) {
            //ignore
        }
    }

}

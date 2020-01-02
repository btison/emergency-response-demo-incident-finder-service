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

public class IncidentSerdeTest {

    @Test
    void testDeserialization() {
        String s = "{\n" +
                "    \"id\": \"8beed52c-55b8-4a4b-880b-5a59b82d228a\",\n" +
                "    \"lat\": 34.1234,\n" +
                "    \"lon\": -77.9876,\n" +
                "    \"numberOfPeople\": 5,\n" +
                "    \"medicalNeeded\": true,\n" +
                "    \"timestamp\": 1577949746678,\n" +
                "    \"victimName\": \"John Foo\",\n" +
                "    \"victimPhoneNumber\": \"111-222-333\",\n" +
                "    \"status\": \"PICKEDUP\"\n" +
                "}";

        JsonbSerde<Incident> incidentSerde = new JsonbSerde<>(Incident.class);
        Incident incident = incidentSerde.deserializer().deserialize("test", s.getBytes());
        assertNotNull(incident);
        assertEquals( "8beed52c-55b8-4a4b-880b-5a59b82d228a", incident.getId());
    }

    @Test
    void testMapFromMessage() {
        String s = "{\n" +
                "  \"messageType\": \"IncidentReportedEvent\",\n" +
                "  \"invokingService\": \"kafka-producer-test-app\",\n" +
                "  \"id\": \"18c69d28-8cf3-405a-a4b0-ec24e80d0bbb\",\n" +
                "  \"timestamp\": 1577963381597,\n" +
                "  \"body\": {\n" +
                "    \"id\": \"8beed52c-55b8-4a4b-880b-5a59b82d228a\",\n" +
                "    \"lat\": 34.1234,\n" +
                "    \"lon\": -77.9876,\n" +
                "    \"numberOfPeople\": 5,\n" +
                "    \"medicalNeeded\": true,\n" +
                "    \"timestamp\": 1577949746678,\n" +
                "    \"victimName\": \"John Foo\",\n" +
                "    \"victimPhoneNumber\": \"111-222-333\",\n" +
                "    \"status\": \"PICKEDUP\"\n" +
                "  }\n" +
                "}";
        JsonObject msg = Json.createReader(new StringReader(s)).readObject();
        JsonObject body = msg.getJsonObject("body");
        try (ByteArrayOutputStream oos = new ByteArrayOutputStream();
             JsonWriter writer = Json.createWriter(oos)) {
            writer.writeObject(body);
            writer.close();
            oos.flush();
            byte[] bytes = oos.toByteArray();
            Incident incident = new JsonbSerde<>(Incident.class).deserializer().deserialize("", bytes);
            assertNotNull(incident);
            assertEquals("8beed52c-55b8-4a4b-880b-5a59b82d228a", incident.getId());
        } catch (IOException e) {
            //ignore
        }
    }


}

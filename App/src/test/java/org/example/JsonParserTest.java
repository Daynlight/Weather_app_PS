package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import kafka.model.Root;
import kafka.model.Sensor;
import kafka.model.SensorType;
import kafka.model.SensorDataValue;
import kafka.model.Location;

import java.util.List;
import java.util.ArrayList;




class JsonParserTest {
  private String json = "[{\"sensordatavalues\":[{\"value\":\"7.49\",\"value_type\":\"temperature\",\"id\":66674518471},{\"value\":\"100463.13\",\"value_type\":\"pressure\",\"id\":66674518473},{\"value\":\"100.00\",\"value_type\":\"humidity\",\"id\":66674518474},{\"value\":101747.52,\"value_type\":\"pressure_at_sealevel\"}],\"sampling_rate\":null,\"sensor\":{\"pin\":\"11\",\"sensor_type\":{\"name\":\"BME280\",\"id\":17,\"manufacturer\":\"Bosch\"},\"id\":44441},\"location\":{\"indoor\":0,\"latitude\":\"52.268\",\"exact_location\":0,\"altitude\":\"104.5\",\"country\":\"PL\",\"id\":30146,\"longitude\":\"20.944\"},\"id\":28685960714,\"timestamp\":\"2026-03-23 23:31:00\"}]";


  @Test
  void getLatitudeFromJson() {
    JsonParser parser = new JsonParser();
    List<Root> roots = parser.getRootsFromApi(json);

    assertNotNull(roots);
    assertFalse(roots.isEmpty());

    double latitude = Double.parseDouble(roots.get(0).getLocation().getLatitude());

    assertEquals(52.268, latitude, 0.001);
  };

  @Test
  void getLongitudeFromJson() {
    JsonParser parser = new JsonParser();
    List<Root> roots = parser.getRootsFromApi(json);

    assertNotNull(roots);
    assertFalse(roots.isEmpty());

    double longitude = Double.parseDouble(roots.get(0).getLocation().getLongitude());
    assertEquals(20.944, longitude, 0.001);
  };

  @Test
  void testInvalidJsonReturnsNull() {
    String invalidJson = "{\"wrong\":\"format\"}";
    JsonParser parser = new JsonParser();
    List<Root> roots = parser.getRootsFromApi(invalidJson);

    assertNull(roots);
  };

};
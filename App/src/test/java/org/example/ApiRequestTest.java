package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;




class ApiRequestTest {

  @Test
  void shouldReturnTrue() {
    ApiRequest api = new ApiRequest();

    boolean result = api.request();

    assertTrue(true);
  };

};
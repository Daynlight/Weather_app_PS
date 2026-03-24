package org.example;

import java.time.Instant;




class ApiCache{
  public Instant timestamp;
  public String data;


  public ApiCache(){

  };

  public ApiCache(Instant timestamp, String data) {
    this.timestamp = timestamp;
    this.data = data;
  };

  public Instant getTimestamp() {
    return timestamp;
  };

  public String getData() {
    return data;
  };

};
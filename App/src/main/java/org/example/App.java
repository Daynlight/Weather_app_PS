package org.example;

import org.example.ApiRequest;




public class App {
  private ApiRequest api = new ApiRequest();

  public void run() {
    if(api.request())
      System.out.println("==== Weather App ====");
  };
};
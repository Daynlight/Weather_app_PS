package org.example;

import org.example.WeatherAppAPI;
import org.example.Terminal;
import org.example.JsonParser;

import java.util.List;
import kafka.model.Root;

import org.fusesource.jansi.Ansi.Color;




class Statistics{
  private JsonParser json = new JsonParser();
  private Terminal terminal = new Terminal();
  private WeatherAppAPI api = new WeatherAppAPI();


  public void print(){
    terminal.printLog("Printing Statistics");
    json.getRootsFromApi(api.get());
    json.getRootsFromApi(api.get());

    // for (Root root : json.getRootsFromApi()) {
    //   if (root.getLocation() != null) {
    //     terminal.print(root.getLocation().getCountry());
    //   };
    // };
  };

};
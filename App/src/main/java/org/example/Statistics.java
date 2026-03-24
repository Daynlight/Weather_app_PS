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
    
    terminal.print("Average Temperature: " + avgTemp());
    terminal.print("High PM 25: " + highPM(25));
  };

  private double avgTemp(){
    double avgTemp = json.getRootsFromApi(api.get()).stream()
      .map(Root::getSensordatavalues)
      .flatMap(List::stream)
      .filter(sd -> sd.getValue_type().equals("temperature"))
      .mapToDouble(sd -> Double.parseDouble(sd.getValue()))
      .average()
      .orElse(0.0);
    
    return avgTemp;
  };

  private long highPM(double value){
    long highPmCount = json.getRootsFromApi(api.get()).stream()
      .map(Root::getSensordatavalues)
      .flatMap(List::stream)
      .filter(sd -> sd.getValue_type().equals("temperature") && 
        sd.getValue().matches("-?\\d+(\\.\\d+)?") && 
        Double.parseDouble(sd.getValue()) > value)
      .count();
      
    return highPmCount;
  };

};
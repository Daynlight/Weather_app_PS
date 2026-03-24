package org.example;

import org.example.WeatherAppAPI;
import org.example.Terminal;
import org.example.JsonParser;
import org.example.StatsData;

import java.util.List;
import java.io.File;
import kafka.model.Root;

import org.fusesource.jansi.Ansi.Color;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;





class Statistics{
  private JsonParser json = new JsonParser();
  private Terminal terminal = new Terminal();
  private WeatherAppAPI api = new WeatherAppAPI();


  public void print(){
    terminal.printLog("Printing Statistics");
    
    terminal.print("Average Temperature: " + avgTemp());
    terminal.print("High Temperature > 25: " + highTemp(25));
    terminal.print("Average humidity: " + avgHumidity());
    terminal.print("High PM > 25: " + highPM(25));
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
  
  private long highTemp(double value) {
    long highTempCount = json.getRootsFromApi(api.get()).stream()
      .map(Root::getSensordatavalues)
      .flatMap(List::stream)
      .filter(sd -> sd.getValue_type().equals("temperature") && 
        sd.getValue().matches("-?\\d+(\\.\\d+)?") && 
        Double.parseDouble(sd.getValue()) > value)
      .count();

    return highTempCount;
  };

  private long highPM(double value){
    long highPmCount = json.getRootsFromApi(api.get()).stream()
      .map(Root::getSensordatavalues)
      .flatMap(List::stream)
      .filter(sd -> sd.getValue_type().equals("P2") && 
        sd.getValue().matches("-?\\d+(\\.\\d+)?") && 
        Double.parseDouble(sd.getValue()) > value)
      .count();
      
    return highPmCount;
  };

  private double avgHumidity() {
    double avgHumid =  json.getRootsFromApi(api.get()).stream()
      .map(Root::getSensordatavalues)
      .flatMap(List::stream)
      .filter(sd -> sd.getValue_type().equals("humidity") && 
        sd.getValue().matches("-?\\d+(\\.\\d+)?"))
      .mapToDouble(sd -> Double.parseDouble(sd.getValue()))
      .average()
      .orElse(0.0);

    return avgHumid;
  };

  public void saveAsXml(String filename) {
    try{
      XmlMapper xmlMapper = new XmlMapper();
      StatsData stats = new StatsData(avgTemp(), highTemp(25), avgHumidity(), highPM(25));
      xmlMapper.writeValue(new File(filename), stats);
      terminal.printLog("Xml is created");
    }
    catch (Exception e){
      terminal.printError("when save to XML: " + e);
    }
  };

};
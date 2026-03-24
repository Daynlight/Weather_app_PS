package org.example;

import org.example.WeatherAppAPI;
import org.example.Terminal;

import java.util.List;
import kafka.model.Root;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;




class JsonParser{
  private WeatherAppAPI api = new WeatherAppAPI();
  private Terminal terminal = new Terminal();
  private static List<Root> roots = null;
  

  public List<Root> getRootsFromApi(){
    if(roots != null) {
      terminal.printLog("Used cached roots");
      return roots;
    };

    try {
      ObjectMapper mapper = new ObjectMapper();
      terminal.printLog("Parsing json to roots");
      roots =  mapper.readValue(api.get(), new TypeReference<List<Root>>() {});
      terminal.printLog("Roots are cached");
    } catch (Exception e) {
      terminal.printError("Parsing Json Error: " + e);
      e.printStackTrace();
    };

    return roots;
  };

};
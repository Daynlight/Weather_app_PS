package org.example;

import org.example.Terminal;

import java.util.List;
import kafka.model.Root;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;




class JsonParser{
  private Terminal terminal = new Terminal();
  private static List<Root> roots = null;
  String json_ref = null;
  

  public List<Root> getRootsFromApi(String json){
    if(roots != null && json_ref == json ) {
      terminal.printLog("Used cached roots");
      return roots;
    };

    try {
      ObjectMapper mapper = new ObjectMapper();
      roots =  mapper.readValue(json, new TypeReference<List<Root>>() {});
      terminal.printLog("Parsing json");
      terminal.printLog("Roots are cached");
      json_ref = json;
    } catch (Exception e) {
      terminal.printError("Parsing Json Error: " + e);
      e.printStackTrace();
    };

    return roots;
  };

};
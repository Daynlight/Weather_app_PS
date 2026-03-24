package org.example;

import org.example.Terminal;
import org.example.JsonParser;

import java.util.List;
import kafka.model.Root;

import org.fusesource.jansi.Ansi.Color;




class Statistics{
  private JsonParser json = new JsonParser();
  private Terminal terminal = new Terminal();


  public void print(){
    terminal.printLog("Printing Statistics");
    json.getRootsFromApi();

    // for (Root root : json.getRootsFromApi()) {
    //   if (root.getLocation() != null) {
    //     terminal.print(root.getLocation().getCountry());
    //   };
    // };
  };

};
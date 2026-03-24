package org.example;

import org.example.Statistics;
import org.example.Terminal;
import org.example.Config;

import org.fusesource.jansi.Ansi.Color;




public class App {
  private Terminal terminal = new Terminal();
  private volatile boolean is_running = true;
  private Statistics statistics = new Statistics();


  public void run() {
    terminal.print("===================", Color.GREEN);
    terminal.print("=== Weather App ===", Color.GREEN);
    terminal.print("===================", Color.GREEN);

    terminal.print("Statistics");
    statistics.print();

    while(is_running) loop();

    stop();
  };

  private void loop(){
    terminal.print("Zapisz jako:");
    terminal.print("P-Pdf J-Json X-xml Q-quit");
    String operation = terminal.getLine();

    if(operation.trim().equalsIgnoreCase("P")) statistics.saveAsPdf("stats.pdf");
    else if(operation.trim().equalsIgnoreCase("J")) statistics.saveAsJson("stats.json");
    else if(operation.trim().equalsIgnoreCase("X")) statistics.saveAsXml("stats.xml");
    else if(operation.trim().equalsIgnoreCase("Q")) is_running = false;
    else terminal.printWarning("Invalid operation: " + operation);
   };

  private void stop(){
    terminal.print("======= Bye =======", Color.GREEN);
  };

  public void flags(String[] args){
    for(String el : args){
      if(el.trim().equals("-d") || 
         el.trim().equals("--debug")) {
        Config.DEBUG = true;
      }
      else if(el.trim().equalsIgnoreCase("--JSON") || 
              el.trim().equalsIgnoreCase("--JS") ||
              el.trim().equalsIgnoreCase("-J")) {
        statistics.saveAsJson("stats.json");
        is_running = false;
      }
      else if(el.trim().equalsIgnoreCase("--XML") || 
              el.trim().equalsIgnoreCase("-X")) {
        statistics.saveAsXml("stats.xml");
        is_running = false;
      }
      else if(el.trim().equalsIgnoreCase("--PDF") || 
              el.trim().equalsIgnoreCase("-P")) {
        statistics.saveAsPdf("stats.pdf");
        is_running = false;
      }
      else 
        terminal.printWarning("Unknown arg: " + el);
    };
  };

};
package org.example;

import org.example.Statistics;
import org.example.Terminal;

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

    if(operation.trim().toUpperCase().equalsIgnoreCase("P")) statistics.saveAsPdf("stats.pdf");
    else if(operation.trim().toUpperCase().equalsIgnoreCase("J")) statistics.saveAsJson("stats.json");
    else if(operation.trim().toUpperCase().equalsIgnoreCase("X")) statistics.saveAsXml("stats.xml");
    else if(operation.trim().toUpperCase().equalsIgnoreCase("Q")) is_running = false;
    else terminal.printWarning("Invalid operation: " + operation);
   };

  private void stop(){
    terminal.print("===================", Color.GREEN);
    terminal.print("======= Bye =======", Color.GREEN);
    terminal.print("===================", Color.GREEN);
  };

};
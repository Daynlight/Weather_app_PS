package org.example;

import org.example.Statistics;
import org.example.Terminal;

import org.fusesource.jansi.Ansi.Color;




public class App {
  private Terminal terminal = new Terminal();
  private volatile boolean is_running = true;
  private Statistics statistics = new Statistics();


  public void run() {
    terminal.print("===================", Color.BLUE);
    terminal.print("=== Weather App ===", Color.BLUE);
    terminal.print("===================", Color.BLUE);

    statistics.print();

    while(is_running) loop();

    stop();
  };


  private void loop(){

  };

  private void stop(){
    terminal.print("===================", Color.BLUE);
    terminal.print("======= Bye =======", Color.BLUE);
    terminal.print("===================", Color.BLUE);
  };

};
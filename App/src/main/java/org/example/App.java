package org.example;

import org.example.ApiRequest;
import org.example.Terminal;

import org.fusesource.jansi.Ansi.Color;




public class App {
  private ApiRequest api = new ApiRequest();
  private Terminal terminal = new Terminal();
  private volatile boolean is_running = true;


  public void run() {
    terminal.print("===================", Color.BLUE);
    terminal.print("=== Weather App ===", Color.BLUE);
    terminal.print("===================", Color.BLUE);
    
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
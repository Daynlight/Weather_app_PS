package org.example;

import org.example.Config;

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;




public class Terminal {
  private static Scanner scanner = new Scanner(System.in);

  public String getLine(){
    return scanner.nextLine();
  };

  public void print(String text){
    System.out.println(
      Ansi.ansi().fg(Color.WHITE).a(text).reset()
    );
  };

  public void print(String text, Color color){
    System.out.println(
      Ansi.ansi().fg(color).a(text).reset()
    );
  };

  public void printLog(String text){
    System.out.println(
      Ansi.ansi().fg(Color.BLUE).a("[LOG] " + text).reset()
    );

    writeToLogFile("[LOG] " + text);
  };

  public void printWarning(String text){
    System.out.println(
      Ansi.ansi().fg(Color.YELLOW).a("[WARNING] " + text).reset()
    );

    writeToLogFile("[WARNING] " + text);
  };

  public void printError(String text){
    System.out.println(
      Ansi.ansi().fg(Color.RED).a("[ERROR] " + text).reset()
    );

    writeToLogFile("[ERROR] " + text);
  };

  private void writeToLogFile(String text){
    File logFile = new File(Config.LOGFILEPATH);
    File parentDir = logFile.getParentFile();

    if (!parentDir.exists()) parentDir.mkdirs();

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(Config.LOGFILEPATH, true))) {
      writer.write(text);
      writer.newLine();
    } catch (IOException e) {
      e.printStackTrace();
    };
  };

};
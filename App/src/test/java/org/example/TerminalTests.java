package org.example;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.*;

import org.fusesource.jansi.Ansi.Color;




class TerminalTest {

  @Test
  void print() {
    Terminal terminal = new Terminal();

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));

    terminal.print("Hello World");

    System.setOut(originalOut);

    String output = outContent.toString().replaceAll("\u001B\\[[;\\d]*m", "");

    assertEquals("Hello World\n", output);
  };

  @Test
  void printRedColor() {
    Terminal terminal = new Terminal();

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));

    terminal.print("Hello World", Color.RED);

    System.setOut(originalOut);
    String output = outContent.toString();

    assertTrue(output.contains("\u001B[31m"), "Output should contain RED ANSI code");
    assertTrue(output.contains("Hello World"), "Output should contain the printed text");
  };

  @Test
  void printLog() {
    Terminal terminal = new Terminal();

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));

    terminal.printLog("Hello World");

    System.setOut(originalOut);
    String output = outContent.toString();

    assertTrue(output.contains("\u001B[34m"), "Output should contain BLUE ANSI code");
    assertTrue(output.contains("[LOG]"), "Output should contain the [LOG]");
    assertTrue(output.contains("Hello World"), "Output should contain the printed text");
  };

  @Test
  void printWarning() {
    Terminal terminal = new Terminal();

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));

    terminal.printWarning("Hello World");

    System.setOut(originalOut);
    String output = outContent.toString();

    assertTrue(output.contains("\u001B[33m"), "Output should contain YELLOW ANSI code");
    assertTrue(output.contains("[WARNING]"), "Output should contain the [WARNING]");
    assertTrue(output.contains("Hello World"), "Output should contain the printed text");
  };

  @Test
  void printError() {
    Terminal terminal = new Terminal();

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outContent));

    terminal.printError("Hello World");

    System.setOut(originalOut);
    String output = outContent.toString();

    assertTrue(output.contains("\u001B[31m"), "Output should contain RED ANSI code");
    assertTrue(output.contains("[ERROR]"), "Output should contain the [ERROR]");
    assertTrue(output.contains("Hello World"), "Output should contain the printed text");
  };

  @Test
  void getLine() {
    String simulatedInput = "Hello World\n";
    ByteArrayInputStream inContent = new ByteArrayInputStream(simulatedInput.getBytes());

    InputStream originalIn = System.in;
    System.setIn(inContent);

    Terminal terminal = new Terminal();
    String line = terminal.getLine();

    assertEquals("Hello World", line, "getLine should return the input line");

    System.setIn(originalIn);
  };

};
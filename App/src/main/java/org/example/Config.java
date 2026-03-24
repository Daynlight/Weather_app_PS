package org.example;

import java.io.File;




public class Config {
  public static final String LOGFILEPATH = System.getProperty("user.home") + File.separator + ".WeatherApp" + File.separator + "terminal.log";
  public static final String REQUESTURL = "https://data.sensor.community/airrohr/v1/filter/box=52.36734243199027,20.819494415027485,52.09692843752652,21.319390572461643";
  public static boolean DEBUG = false;
};
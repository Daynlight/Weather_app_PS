package org.example;

public class StatsData {
  public double avgTemp;
  public long highTemp;
  public double avgHumidity;
  public long highPm;

  public StatsData(double avgTemp, long highTemp, double avgHumidity, long highPm) {
    this.avgTemp = avgTemp;
    this.highTemp = highTemp;
    this.avgHumidity = avgHumidity;
    this.highPm = highPm;
  };
};
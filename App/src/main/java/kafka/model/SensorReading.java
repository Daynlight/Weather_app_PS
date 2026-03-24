package kafka.model;
public class SensorReading {

    Integer sensorId;
    String lat;
    String lon;
    String temperature;
    String humidity;
    String altitude;

    public SensorReading() {
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }
    public void setLon(String lon) {
        this.lon = lon;
    }
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }
    public Integer getSensorId() {
        return sensorId;
    }
    public String getLat() {
        return lat;
    }
    public String getLon() {
        return lon;
    }
    public String getTemperature() {
        return temperature;
    }
    public String getHumidity() {
        return humidity;
    }
    public String getAltitude() {
        return altitude;
    }
}

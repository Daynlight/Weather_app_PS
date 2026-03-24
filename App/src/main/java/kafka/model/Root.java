package kafka.model;
import java.util.ArrayList;
public class Root {
    public Sensor sensor;
    public ArrayList<SensorDataValue> sensordatavalues;
    public Object sampling_rate;
    public long id;
    public String timestamp;
    public Sensor getSensor() {
        return sensor;
    }
    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
    public ArrayList<SensorDataValue> getSensordatavalues() {
        return sensordatavalues;
    }
    public void setSensordatavalues(ArrayList<SensorDataValue> sensordatavalues) {
        this.sensordatavalues = sensordatavalues;
    }
    public Object getSampling_rate() {
        return sampling_rate;
    }
    public void setSampling_rate(Object sampling_rate) {
        this.sampling_rate = sampling_rate;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public Location location;
}

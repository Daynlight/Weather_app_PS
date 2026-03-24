package kafka.model;
public class Sensor {
    public String pin;
    public int id;
    public SensorType sensor_type;
    public String getPin() {
        return pin;
    }
    public void setPin(String pin) {
        this.pin = pin;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public SensorType getSensor_type() {
        return sensor_type;
    }
    public void setSensor_type(SensorType sensor_type) {
        this.sensor_type = sensor_type;
    }
}

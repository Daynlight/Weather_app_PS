package kafka.model;
public class SensorDataValue {
    public String value_type;
    public String value;
    public Object id;
    public SensorDataValue() {
    }
    public SensorDataValue(String value) {
        this.value = value;
    }
    public String getValue_type() {
        return value_type;
    }
    public void setValue_type(String value_type) {
        this.value_type = value_type;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public Object getId() {
        return id;
    }
    public void setId(Object id) {
        this.id = id;
    }
}

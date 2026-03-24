package kafka.model;

public class Location{
    public String latitude;
    public String altitude;
    public int indoor;
    public int id;
    public int exact_location;
    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getAltitude() {
        return altitude;
    }
    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }
    public int getIndoor() {
        return indoor;
    }
    public void setIndoor(int indoor) {
        this.indoor = indoor;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getExact_location() {
        return exact_location;
    }
    public void setExact_location(int exact_location) {
        this.exact_location = exact_location;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public String country;
    public String longitude;
}

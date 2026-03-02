public class Taxis {
    int taxiId;
    String name;
    private String location;
    int isLast;
    public Taxis(int taxiId, String name, String location) {
        this.taxiId = taxiId;
        this.name = name;
        this.setLocation(location);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

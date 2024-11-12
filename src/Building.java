import java.util.Objects;

class Building {
    private String city;
    private String street;
    private int house;
    private int floors;

    public Building(String city, String street, int house, int floors) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.floors = floors;
    }

    public String getCity() {
        return city;
    }

    public int getFloors() {
        return floors;
    }

    @Override
    public String toString() {
        return city + ", " + street + ", дом " + house + ", этажей " + floors;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Building)) return false;
        Building other = (Building) obj;
        return house == other.house &&
                floors == other.floors &&
                city.equals(other.city) &&
                street.equals(other.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, house, floors);
    }
}
package model;

public class City {
    private final String name;
    private final String country;
    private final String sisterName;
    private final String sisterCountry;

    public City(String name, String country, String sisterName, String sisterCountry) {
        this.name = name;
        this.country = country;
        this.sisterName = sisterName;
        this.sisterCountry = sisterCountry;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getSisterName() {
        return sisterName;
    }

    public String getSisterCountry() {
        return sisterCountry;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", sisterName='" + sisterName + '\'' +
                ", sisterCountry='" + sisterCountry + '\'' +
                '}';
    }

}

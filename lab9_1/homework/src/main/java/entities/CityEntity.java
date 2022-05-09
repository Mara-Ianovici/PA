package entities;

import utils.TimeCalculator;

import javax.persistence.*;

@Entity
@EntityListeners({TimeCalculator.class})
@NamedQuery(name="CityEntity.findByName", query = "SELECT e from CityEntity e WHERE e.name =: name")
@Table(name = "cities")
public class CityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "country_name", nullable = false, length = 100)
    private String countryName;

    @Basic
    @Column(name = "name", length = 100)
    private String name;

    @Basic
    @Column(name = "is_capital")
    private Boolean isCapital;

    @Basic
    @Column(name = "latitude")
    private Double latitude;

    @Basic
    @Column(name = "longitude")
    private Double longitude;

    @Basic
    @Column(name = "population")
    private Integer population;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsCapital() {
        return isCapital;
    }

    public void setIsCapital(Boolean isCapital) {
        this.isCapital = isCapital;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
}
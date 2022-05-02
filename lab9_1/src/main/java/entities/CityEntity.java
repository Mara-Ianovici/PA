package entities;

import javax.persistence.*;
import java.util.Objects;

@NamedQuery(name="CityEntity.findByName", query = "SELECT e from CityEntity e WHERE e.name =: name")
@Entity
@Table(name = "city", schema = "public", catalog = "postgres")
public class CityEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "id_country")
    private int idCountry;
    @Basic
    @Column(name = "name")
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCapital() {
        return isCapital;
    }

    public void setCapital(Boolean capital) {
        isCapital = capital;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CityEntity that = (CityEntity) o;

        if (id != that.id) return false;
        if (idCountry != that.idCountry) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(isCapital, that.isCapital)) return false;
        if (!Objects.equals(latitude, that.latitude)) return false;
        if (!Objects.equals(longitude, that.longitude)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idCountry;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (isCapital != null ? isCapital.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        return result;
    }
}

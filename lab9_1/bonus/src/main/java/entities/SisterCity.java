package entities;

import org.dom4j.tree.AbstractEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name="SisterCity.findByName", query = "SELECT e from SisterCity e WHERE e.name =: name")
@Table(name = "sister_cities")
public class SisterCity extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "country", length = 50)
    private String country;

    @Basic
    @Column(name = "name", length = 50)
    private String name;

    @Basic
    @Column(name = "sister_name", length = 50)
    private String sisterName;

    @Basic
    @Column(name = "sister_country", length = 50)
    private String sisterCountry;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSisterName() {
        return sisterName;
    }

    public void setSisterName(String sisterName) {
        this.sisterName = sisterName;
    }

    public String getSisterCountry() {
        return sisterCountry;
    }

    public void setSisterCountry(String sisterCountry) {
        this.sisterCountry = sisterCountry;
    }

    @Override
    public String toString() {
        return "SisterCity{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", name='" + name + '\'' +
                ", sisterName='" + sisterName + '\'' +
                ", sisterCountry='" + sisterCountry + '\'' +
                '}';
    }

    @ManyToMany
    private List<SisterCity> sisterCityList;

    public List<SisterCity> getManyToMany() {
        return sisterCityList;
    }

    public void setManyToMany(List<SisterCity> manyToMany) {
        this.sisterCityList = manyToMany;
    }
}
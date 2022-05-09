package compulsory.entities;

import org.dom4j.tree.AbstractEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name="HcountriesEntity.findByName", query = "SELECT e from HcountriesEntity e WHERE e.name =: name")
@Table(name = "hcountries", schema = "public", catalog = "lab8")
public class HcountriesEntity extends AbstractEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "code")
    private int code;
    @Basic
    @Column(name = "continent")
    private String continent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HcountriesEntity that = (HcountriesEntity) o;

        if (id != that.id) return false;
        if (code != that.code) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (continent != null ? !continent.equals(that.continent) : that.continent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + code;
        result = 31 * result + (continent != null ? continent.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HcountriesEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code=" + code +
                ", continent='" + continent + '\'' +
                '}';
    }

    @OneToMany
    private List<CityEntity> oneToMany;

    public List<CityEntity> getOneToMany() {
        return oneToMany;
    }

    public void setOneToMany(List<CityEntity> oneToMany) {
        this.oneToMany = oneToMany;
    }
}

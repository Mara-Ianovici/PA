import com.github.javafaker.Faker;

import java.util.Arrays;

public class Street {
    private final String name;
    private double length;
    Intersection[] intersection = new Intersection[2];

    public Street(String name, double length, Intersection i1, Intersection i2) {
        this.intersection[0] = i1;
        this.intersection[1] = i2;
        this.name = name;
        this.length = length;
    }

    /**
     * Constructor that uses Faker to name the streets.
     */
    public Street(double length, Intersection i1, Intersection i2) {
        this.intersection[0] = i1;
        this.intersection[1] = i2;
        this.name = getFakerName();
        this.length = length;
    }

    private String getFakerName()
    {
        Faker fakerName = new Faker();
        return fakerName.address().streetName();
    }

    public boolean containsIntersection(Intersection intersection)
    {
        return this.intersection[0] == intersection || this.intersection[1] == intersection;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setIntersection(Intersection[] intersection) {
        this.intersection = intersection;
    }

    public double getLength() {
        return length;
    }

    public Intersection[] getIntersection() {
        return intersection;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Street{" +
                "name='" + name + '\'' +
                ", length=" + length +
                ", intersections=" + Arrays.toString(intersection) +
                "}";
    }
}

import java.util.Arrays;

public class Street {
    private final String name;
    private final double length;
    Intersection[] intersection = new Intersection[2];

    public Street(String name, double length, Intersection i1, Intersection i2) {
        this.intersection[0] = i1;
        this.intersection[1] = i2;
        this.name = name;
        this.length = length;
    }

    public double getLength() {
        return length;
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

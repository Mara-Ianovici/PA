import com.github.javafaker.Faker;

public class Intersection {
    public final String name;

    public Intersection(String name) {
        this.name = name;
    }

    /**
     * Constructor that uses Faker to name the intersections.
     */
    public Intersection(){this.name = getFakerName();}

    private String getFakerName(){
        Faker fakerName = new Faker();
        return fakerName.address().streetName().split(" ")[1];
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

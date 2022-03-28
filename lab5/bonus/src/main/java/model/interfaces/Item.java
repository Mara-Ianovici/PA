package model.interfaces;

import utils.*;
import model.main.ItemType;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Item implements Serializable {
    protected String identifier;
    protected String title;
    protected String location;
    protected Integer year;
    protected String author;
    protected ItemType type;
    private Set<String> conceptSet;

    public Item(String identifier, String title, String location, Integer year, String author, ItemType type) {
        this.identifier = identifier;
        this.title = title;
        this.location = location;
        this.year = year;
        this.author = author;
        this.type = type;

        addConceptSet();
    }

    public Item() {}

    /**
     * Randomly selects from CatalogUtils.conceptList maximum 3 concepts for the current item.
     * The concepts are different because a set is used.
     */
    private void addConceptSet()
    {
        Set<String> randomConcepts = new HashSet<>();
        conceptSet = new HashSet<>();

        for(int index = 0 ; index <= 3 ; index++)
            randomConcepts.add(Utils.conceptList.get(ThreadLocalRandom.current().nextInt(0, 5)));

       conceptSet.addAll(randomConcepts);
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public Set<String> getConceptSet() {
        return conceptSet;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Item ").append(identifier).append(" {");

        for(String concept : conceptSet.stream().toList()){
            sb.append(concept).append(", ");
        }
        sb.append("}");
        return sb.toString();
    }
}

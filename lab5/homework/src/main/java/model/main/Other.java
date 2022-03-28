package model.main;

import model.interfaces.Item;

public class Other extends Item {
    public Other(String identifier, String title, String location, Integer year, String author) {
        super(identifier, title, location, year, author, ItemType.OTHER);
    }
    public Other() {}
}

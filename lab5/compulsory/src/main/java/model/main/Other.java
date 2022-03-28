package model.main;

import model.interfaces.Item;
import model.main.ItemType;

public class Other extends Item {
    public Other(String identifier, String title, String location, Integer year, String author) {
        super(identifier, title, location, year, author, ItemType.OTHER);
    }

}

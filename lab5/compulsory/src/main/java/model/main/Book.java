package model.main;

import model.interfaces.Item;

public class Book extends Item {
    public Book(String identifier, String title, String location, Integer year, String author) {
        super(identifier, title, location, year, author, ItemType.BOOK);
    }
}

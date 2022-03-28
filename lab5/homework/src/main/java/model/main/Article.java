package model.main;

import model.interfaces.Item;

public class Article extends Item {
    public Article(String identifier, String title, String location, Integer year, String author) {
        super(identifier, title, location, year, author, ItemType.ARTICLE);
    }
    public Article() {}
}

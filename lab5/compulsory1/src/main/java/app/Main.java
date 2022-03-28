package app;

import model.main.Article;
import model.main.Book;
import model.main.Catalog;
import model.interfaces.Item;
import utils.CatalogUtils;

import java.io.BufferedReader;
import java.io.File;


public class Main {

    public static void main(String[] args) throws Exception {

        Catalog catalog = new Catalog("catalog1");
        Catalog catalogForLoad;

        Item item1 = new Book("knuth68", "The Art of Computer Programming", "C://Windows//notepad.exe", 1967, "Donald E. Knuth");
        Item item2 = new Book("knuth46", "The Art of Computer Programming", "C://Windows//notepad.exe", 1967, "Donald E. Knuth");

        catalog.addItem(item1);
        catalog.addItem(item2);

        CatalogUtils.save(catalog, "src/main/resources/catalog1.json");

        catalogForLoad = CatalogUtils.load("src/main/resources/catalog1.json");
        System.out.println(catalogForLoad);
    }
}

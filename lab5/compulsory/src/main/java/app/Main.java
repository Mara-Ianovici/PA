package app;

import model.interfaces.Item;
import model.main.Article;
import model.main.Book;
import model.main.Catalog;
import utils.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Catalog catalog1 = new Catalog("catalog1");

        Book item1 = new Book("knuth67", "The Art of Computer Programming", "d:/books/programming/tacp.ps", 1967, "Donald E. Knuth");
        Article item2 = new Article("java17", "The Java Language Specification", "https://docs.oracle.com/javase/specs/jls/se17/html/index.html", 2021, "James Gosling & others");

        catalog1.addItem(item1);
        catalog1.addItem(item2);

        System.out.println(catalog1);

        CatalogUtils.save(catalog1, "target/output.json");

    }
}

package app;

import com.github.javafaker.Faker;
import commands.*;
import model.main.Article;
import model.main.Book;
import model.main.Catalog;
import utils.Utils;


public class Main {
    public static void main(String[] args) throws Exception {

        Catalog catalog = new Catalog("catalog1");

        Faker faker = new Faker();

        AddCommand.addItemToCatalog("add", catalog, new Book(faker.book().title(), "The Art of Computer Programming", "C://Windows//notepad.exe", 1967, "Donald E. Knuth"));
        AddCommand.addItemToCatalog("add", catalog, new Article(faker.book().genre(), "The Art of Computer Programming", "C://Windows//notepad.exe", 1967, "Donald E. Knuth"));
        AddCommand.addItemToCatalog("add", catalog, new Book(faker.book().title(), "The Art of Computer Programming", "C://Windows//notepad.exe", 1967, "Donald E. Knuth"));

        System.out.println(catalog);

        Utils.solver(catalog);
    }
}

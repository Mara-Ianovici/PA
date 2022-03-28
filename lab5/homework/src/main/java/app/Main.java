package app;

import commands.*;
import exceptions.EmptyItemListException;
import exceptions.InvalidCommandException;
import exceptions.InvalidPathException;
import exceptions.RepetitiveItemException;
import model.main.Book;
import model.main.Catalog;
import model.interfaces.Item;


public class Main {

    public static void main(String[] args) throws Exception {

        Catalog catalog;
        Item item = new Book("knuth68", "The Art of Computer Programming", "C://Windows//notepad.exe", 1967, "Donald E. Knuth");

        try {
            catalog = LoadCommand.execute("load", "D:/report/catalog1.json");

            AddCommand.execute("add", catalog, item);

            ListCommand.execute("list", catalog);

            ViewCommand.execute("view", item);

            ReportCommand.reportToHtml("report", catalog, "D:/report/output.html");

            InfoCommand.execute("info", item);
        }
        catch(EmptyItemListException | RepetitiveItemException | InvalidCommandException | InvalidPathException exception){
            System.out.println(exception.getMessage());
        }
    }
}

package commands;

import exceptions.InvalidCommandException;
import model.interfaces.Item;
import model.main.Catalog;

public class ListCommand extends GenericCommand {

    public ListCommand() {
    }

    public static void list(String commandName, Catalog catalog) {
        try {
            if (commandName.compareTo("list") == 0) {
                for (Item item : catalog.getItemList()) {
                    System.out.println(item);
                }
            } else throw new InvalidCommandException("Not a valid command");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

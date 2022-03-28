package commands;

import exceptions.EmptyItemListException;
import exceptions.InvalidCommandException;
import model.interfaces.Item;
import model.main.Catalog;

/**
 * A class that extends Generic Command and implements the command "list".
 */
public class ListCommand extends GenericCommand {

    public ListCommand() {
    }

    /**
     * This method lists all the items in the catalog.
     * It throws custom exceptions if the catalog's itemList is empty or if the command is wrong.
     * @param commandName The name of the command.
     * @param catalog The catalog to list the Items from. (not null)
     */
    public static void execute(String commandName, Catalog catalog) throws InvalidCommandException, EmptyItemListException {
        if (commandName.compareTo("list") != 0)
            throw new InvalidCommandException("Not a valid command");

        if(catalog.getItemList().size() == 0)
            throw new EmptyItemListException("The catalog is empty. Items cannot be listed");

        StringBuilder stringBuilder = new StringBuilder("Catalog " + catalog.getName() + "\n");

        for (Item item : catalog.getItemList())
            stringBuilder.append(item.toString()).append("\n");

        System.out.println(stringBuilder);
    }
}

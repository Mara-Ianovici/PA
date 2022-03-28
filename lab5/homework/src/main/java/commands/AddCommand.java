package commands;

import exceptions.InvalidCommandException;
import exceptions.RepetitiveItemException;
import model.interfaces.Item;
import model.main.Catalog;

/**
 * A class that extends Generic Command and implements the command "add".
 */
public class AddCommand extends GenericCommand {
    public AddCommand() {}

    public static void execute(String commandName, Catalog catalog, Item item) throws InvalidCommandException, RepetitiveItemException{

        if(commandName.compareTo("add") != 0)
            throw new InvalidCommandException("Not a valid command");

        var tempItemList = catalog.getItemList();

        for (Item tempItem : tempItemList)
            if (tempItem.getIdentifier().equals(item.getIdentifier()))
                throw new RepetitiveItemException("The item was already in the catalog and was not added");

        tempItemList.add(item);
        catalog.setItemList(tempItemList);
    }
}

package commands;

import exceptions.InvalidCommandException;
import exceptions.RepetitiveItemException;
import model.interfaces.Item;
import model.main.Catalog;

public class AddCommand extends GenericCommand {
    public AddCommand() {}

    public static void addItemToCatalog(String commandName, Catalog catalog, Item item){
        try{
            if(commandName.compareTo("add") == 0)
            {
                var tempItemList = catalog.getItemList();

                for(Item tempItem:tempItemList)
                    if(tempItem.getIdentifier().equals(item.getIdentifier()))
                        throw new RepetitiveItemException("The item was already in the catalog and was not added");

                tempItemList.add(item);
                catalog.setItemList(tempItemList);
            }
            else throw new InvalidCommandException("Not a valid command");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

package commands;

import exceptions.InvalidCommandException;
import model.interfaces.Item;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * A class that extends Generic Command and implements the command "view".
 */
public class ViewCommand extends GenericCommand {
    public ViewCommand() {
    }

    /**
     * Opens the location from the item.
     * @param commandName The name of the command.
     * @param item The item to open the location from.
     */
    public static void execute(String commandName, Item item) throws InvalidCommandException{
        if (commandName.compareTo("view") != 0)
            throw new InvalidCommandException("Not a valid command");

        Desktop desktop = Desktop.getDesktop();

        File itemFile = new File(item.getLocation());
        try{
            desktop.open(itemFile);
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
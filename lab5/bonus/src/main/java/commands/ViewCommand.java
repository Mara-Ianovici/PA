package commands;

import exceptions.InvalidCommandException;
import model.interfaces.Item;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ViewCommand extends GenericCommand {
    public ViewCommand() {
    }

    public static void openFile(String commandName, Item item) throws IOException {
        try {
            if (commandName.compareTo("view") == 0) {
                Desktop desktop = Desktop.getDesktop();

                File itemFile = new File(item.getLocation());
                desktop.open(itemFile);

            } else throw new InvalidCommandException("Not a valid command");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
package commands;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.InvalidCommandException;
import exceptions.InvalidPathException;
import model.main.Catalog;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

/**
 * A class that extends Generic Command and implements the command "load".
 */
public class LoadCommand extends GenericCommand {
    public LoadCommand() {}

    /**
     * Provides metadata about the specified item.
     * It throws custom exceptions if the path is not valid or if the command is wrong.
     * @param commandName the name of the command executed.
     * @param path the path to the json.
     */
    public static Catalog execute(String commandName, String path) throws InvalidCommandException, InvalidPathException{
        if (commandName.compareTo("load") != 0)
            throw new InvalidCommandException("Not a valid command");

        Catalog catalog = null;
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(path);

        if(!file.exists())
            throw new InvalidPathException("The path " + path + " does not exist.");

        try {
            catalog = mapper.readValue(file, Catalog.class);
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }

        return catalog;
    }
}
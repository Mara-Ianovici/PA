package commands;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.InvalidCommandException;
import exceptions.InvalidPathException;
import model.main.Catalog;
import java.io.File;
import java.io.IOException;

public class LoadCommand extends GenericCommand {
    public LoadCommand() {}

    public static Catalog load(String commandName, String path) throws NullPointerException{
        try {
            if (commandName.compareTo("load") == 0) {
                Catalog catalog = null;
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    File file = new File(path);

                    if(!file.exists())
                        throw new InvalidPathException("The path " + path + " does not exist.");

                    catalog = mapper.readValue(file, Catalog.class);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return catalog;

            } else throw new InvalidCommandException("Not a valid command");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
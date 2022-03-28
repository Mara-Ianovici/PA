package commands;

import exceptions.InvalidCommandException;
import exceptions.InvalidPathException;
import model.interfaces.Item;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * A class that extends Generic Command and implements the command "info".
 */
public class InfoCommand extends GenericCommand {
    public InfoCommand(){}

    /**
     * Provides metadata about the specified item.
     * It throws custom exceptions if the path from the Item is not valid or if the command is wrong.
     * @param commandName the name of the command executed.
     * @param item the item to find metadata about.
     * @throws IOException
     * @throws TikaException
     * @throws SAXException
     */
    public static void execute(String commandName, Item item) throws InvalidCommandException, InvalidPathException{
        if (commandName.compareTo("info") != 0)
            throw new InvalidCommandException("Not a valid command");

        System.out.println("Getting metadata for " + item.getLocation() + ":");

        File file = new File(item.getLocation());

        if(!file.exists())
            throw new InvalidPathException("The path " + item.getLocation() + " does not exist.");

        Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();

        try{
            FileInputStream inputStream = new FileInputStream(file);
            ParseContext context = new ParseContext();

            parser.parse(inputStream, handler, metadata, context);
        }
        catch(SAXException | TikaException | IOException e){
            System.out.println("IO/Tika exceptions");
        }

        System.out.println(handler);

        List<String> metadataNames = Arrays.stream(metadata.names()).toList();

        for (String name : metadataNames) {
            System.out.println(name + "-> " + metadata.get(name));
        }
    }
}

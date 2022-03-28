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
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class InfoCommand extends GenericCommand {
    public InfoCommand(){}

    public static void printMetadata(String commandName, Item item) throws IOException, TikaException, SAXException {
        try {
            if (commandName.compareTo("info") == 0) {
                System.out.println("Getting metadata for " + item.getLocation() + ":");

                File file = new File(item.getLocation());

                if(!file.exists())
                    throw new InvalidPathException("The path " + item.getLocation() + " does not exist.");

                Parser parser = new AutoDetectParser();
                BodyContentHandler handler = new BodyContentHandler();
                Metadata metadata = new Metadata();
                FileInputStream inputStream = new FileInputStream(file);
                ParseContext context = new ParseContext();

                parser.parse(inputStream, handler, metadata, context);
                System.out.println(handler);

                List<String> metadataNames = Arrays.stream(metadata.names()).toList();

                for (String name : metadataNames) {
                    System.out.println(name + "-> " + metadata.get(name));
                }
            } else throw new InvalidCommandException("Not a valid command");
        }
        catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }
}

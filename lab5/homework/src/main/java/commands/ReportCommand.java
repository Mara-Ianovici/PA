package commands;

import exceptions.InvalidCommandException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import model.main.Catalog;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * A class that extends Generic Command and implements the command "report".
 */
public class ReportCommand extends GenericCommand {

    public ReportCommand(){}

    /**
     * Makes a report in html similar to the specified template.
     * @param commandName The name of the command.
     * @param catalog
     * @param outputPath The path of the generated html.
     */
    public static void reportToHtml(String commandName, Catalog catalog, String outputPath) throws InvalidCommandException, TemplateException, IOException{
        if (commandName.compareTo("report") != 0)
            throw new InvalidCommandException("Not a valid command");

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
        File templateDirectory = new File("D:/report");

        try {
            configuration.setDirectoryForTemplateLoading(templateDirectory);
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }

        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Map<String, Object> input = new HashMap<String, Object>();

        input.put("name", catalog.getName());
        input.put("items", catalog.getItemList());

        Template template = configuration.getTemplate("template1.ftl");

        try (Writer fileWriter = new FileWriter(outputPath)) {
            template.process(input, fileWriter);
        }

        System.out.println("The report was successfully generated in " + outputPath);
    }
}
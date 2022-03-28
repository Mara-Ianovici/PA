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

public class ReportCommand extends GenericCommand {//content of the catalog in html

    public ReportCommand(){}

    public static void reportToHtml(String commandName, Catalog catalog, String outputPath) throws IOException, TemplateException {
        try {
            if (commandName.compareTo("report") == 0) {
                Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);

                File templateDirectory = new File("D:/report");
                cfg.setDirectoryForTemplateLoading(templateDirectory);
                cfg.setDefaultEncoding("UTF-8");
                cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

                Map<String, Object> input = new HashMap<String, Object>();

                input.put("name", catalog.getName());
                input.put("items", catalog.getItemList());

                Template template = cfg.getTemplate("template1.ftl");

                try (Writer fileWriter = new FileWriter(outputPath)) {
                    template.process(input, fileWriter);
                }

                System.out.println("The report was successfully generated in " + outputPath);

            } else throw new InvalidCommandException("Not a valid command");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
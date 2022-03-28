package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.main.Catalog;

import java.io.File;
import java.io.IOException;

public class CatalogUtils {
    public static void save(Catalog catalog, String fullPath) throws IOException {
        System.out.println(catalog);
        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        mapper.writeValue(new File(fullPath), catalog);

        System.out.printf("Catalog data is saved in " + fullPath + '\n');
    }

    public static Catalog load(String path) throws NullPointerException, IOException{

        Catalog catalog = null;

        ObjectMapper mapper = new ObjectMapper();
        File file = new File(path);

        if(!file.exists())
            catalog = mapper.readValue(file, Catalog.class);

        return catalog;
    }

}

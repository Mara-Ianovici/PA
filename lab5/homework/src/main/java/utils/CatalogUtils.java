package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.main.Catalog;

import java.io.File;
import java.io.IOException;

public class CatalogUtils {

    public static void save(Catalog catalog, String fullPath) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        mapper.writeValue(new File(fullPath), catalog);
        System.out.printf("Catalog data is saved in " + fullPath + '\n');
    }
}

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private List<Item> itemList;

    public Catalog() {
        this.itemList = new ArrayList<>();
    }

    public void addItem(Item itemToBeAdded) {

        for(var item : itemList)
        {
            if(itemList.contains(itemToBeAdded))
            {
                System.out.println("The item is already in the list and can't be added");
                return;
            }
        }
        itemList.add(itemToBeAdded);
    }

    public void save() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("target/out.json"), this.itemList);
    }

    public void load(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Item[] tmp = objectMapper.readValue(new File(path), Item[].class);

        for (int i = 0; i < tmp.length; i++) {
            this.itemList.add(tmp[i]);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : itemList)
            sb.append(item.getIdentifier() + " " + item.getTitle() + " " + item.getLocation() + " " +
                        item.getYear() + " " + item.getAuthor() + " " + item.getType() + "\n");
        return sb.toString();
    }
}

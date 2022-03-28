package model.main;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import model.interfaces.Item;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    private List<Item> itemList;
    private String name;

    @JsonCreator
    public Catalog(@JsonProperty("name") String name, @JsonProperty("itemList") List<Item> itemList) {
        this.name = name;
        this.itemList = itemList;
    }

    public Catalog(String name) {
        this.name = name;
        itemList = new ArrayList<Item>();
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Catalog{\n");
        for (Item item : itemList)
            sb.append(item.getIdentifier()).append(" ").append(item.getTitle()).append(" ").append(item.getLocation()).
                    append(" ").append(item.getYear()).append(" ").append(item.getAuthor()).append(" ").
                    append(item.getType()).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}

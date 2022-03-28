package model.main;
import model.interfaces.Item;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    private List<Item> itemList;
    private String name;

    public Catalog(List<Item> itemList, String name) {
        this.itemList = itemList;
        this.name = name;
    }

    public Catalog(String name) {
        this.itemList = new ArrayList<Item>();
        this.name = name;
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
            sb.append(item.toString()).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}

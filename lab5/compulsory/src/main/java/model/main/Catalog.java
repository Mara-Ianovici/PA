package model.main;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.interfaces.Item;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Catalog implements Serializable {
    private List<Item> itemList;
    private String name;

    public Catalog(String name) {
        this.name = name;
        this.itemList = new ArrayList<>();
    }

    @JsonCreator
    public Catalog(@JsonProperty("name") String name, @JsonProperty("itemList") List<Item> itemList) {
        this.name = name;
        this.itemList = itemList;
    }

    public void addItem(Item itemToBeAdded) {

        if(itemList.contains(itemToBeAdded))
        {
            System.out.println("The item is already in the list and can't be added");
            return;
        }
        itemList.add(itemToBeAdded);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : itemList)
            sb.append(item.getIdentifier()).append(" ").append(item.getTitle()).append(" ").append(item.getLocation()).
                    append(" ").append(item.getYear()).append(" ").append(item.getAuthor()).append(" ").
                    append(item.getType()).append("\n");
        return sb.toString();
    }
}

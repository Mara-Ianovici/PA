package model.interfaces;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import model.main.Article;
import model.main.Book;
import model.main.Other;
import model.main.ItemType;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Book.class, name = "book"),
        @JsonSubTypes.Type(value = Article.class, name = "article"),
        @JsonSubTypes.Type(value = Other.class, name = "other"),
})

public abstract class Item implements Serializable {
    protected String identifier;
    protected String title;
    protected String location;
    protected Integer year;
    protected String author;
    protected ItemType type;

    @JsonCreator
    public Item(@JsonProperty("id") String identifier, @JsonProperty("title") String title, @JsonProperty("location") String location,
                @JsonProperty("year") Integer year, @JsonProperty("author") String author, @JsonProperty("type") ItemType type) {
        this.identifier = identifier;
        this.title = title;
        this.location = location;
        this.year = year;
        this.author = author;
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }
}

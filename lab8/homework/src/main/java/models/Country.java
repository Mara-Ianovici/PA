package models;

import java.sql.*;

public class Country extends Entity {
    private final String code, continentName;

    public Country(String name, String code, String continentName) {
        super(name);
        this.code = code;
        this.continentName = continentName;
    }

    public String getContinentName() {
        return continentName;
    }

    public String getCode() {
        return code;
    }
}

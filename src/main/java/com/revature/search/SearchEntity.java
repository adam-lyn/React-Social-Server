package com.revature.search;
//
//import javax.persistence.*;
//import java.util.Locale;

public  class SearchEntity implements Searchable {

    private final String type;
    private final String key;
    private final String label;

    public SearchEntity(Searchable result, String type) {
        this.key = result.getKey();
        this.label = result.getLabel();
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public String getLabel() {
        return this.label;
    }

    @Override
    public String getKey() {
        return this.key;
    }
}
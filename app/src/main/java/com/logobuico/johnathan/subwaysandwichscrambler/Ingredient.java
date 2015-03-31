package com.logobuico.johnathan.subwaysandwichscrambler;

import java.io.Serializable;

/**
 * Created by Johnathan on 3/28/2015.
 */
public class Ingredient implements Serializable {
    private long id;
    private String item;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getItem() {
        return item;
    }
    public void setIngredient(String item) {
        this.item = item;
    }
    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return item;
    }
}

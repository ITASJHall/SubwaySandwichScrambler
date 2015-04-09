package com.logobuico.johnathan.subwaysandwichscrambler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * sandwich class to help model a sub sandwich
 * isSerializable so to be saved to the database
 * Created by Johnathan on 3/29/2015.
 */
public class Sandwich implements Serializable {
    private long id;
    private String size;
    private String doubleMeat;
    private ArrayList<Ingredient> meat;
    private Ingredient bread;
    private String bacon;
    private String amountOfCheese;
    private ArrayList<Ingredient> cheese;
    private String toasted;
    private String noVeg;
    private ArrayList<Ingredient> veggies;
    private ArrayList<Ingredient> dressing;
    private ArrayList<Ingredient> seasoning;

// Getters and setters
    public long getId() {
        return id;
    }

    public String getSize() {
        return size;
    }

    public String getDoubleMeat() {
        return doubleMeat;
    }

    public ArrayList<Ingredient> getMeat() {
        return meat;
    }

    public Ingredient getBread() {
        return bread;
    }

    public String getBacon() {
        return bacon;
    }

    public String getAmountOfCheese() {
        return amountOfCheese;
    }

    public ArrayList<Ingredient> getCheese() {
        return cheese;
    }

    public String getToasted() {
        return toasted;
    }

    public String getNoVeg() {
        return noVeg;
    }

    public ArrayList<Ingredient> getVeggies() {
        return veggies;
    }

    public ArrayList<Ingredient> getDressing() {
        return dressing;
    }

    public ArrayList<Ingredient> getSeasoning() {
        return seasoning;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setDoubleMeat(String doubleMeat) {
        this.doubleMeat = doubleMeat;
    }

    public void setMeat(ArrayList<Ingredient> meat) {
        this.meat = meat;
    }

    public void setMeat(Ingredient meat) {
        this.meat.add(meat);
    }

    public void setBread(Ingredient bread) {
        this.bread = bread;
    }

    public void setBacon(String bacon) {
        this.bacon = bacon;
    }

    public void setAmountOfCheese(String amountOfCheese) {
        this.amountOfCheese = amountOfCheese;
    }

    public void setCheese(ArrayList<Ingredient> cheese) {
        this.cheese = cheese;
    }

    public void setCheese(Ingredient cheese) {
        this.cheese.add(cheese);
    }

    public void setToasted(String toasted) {
        this.toasted = toasted;
    }

    public void setNoVeg(String noVeg) {
        this.noVeg = noVeg;
    }

    public void setVeggies(ArrayList<Ingredient> veggies) {
        this.veggies = veggies;
    }

    public void setVeggies(Ingredient veggies) {
        this.veggies.add(veggies);
    }

    public void setDressing(ArrayList<Ingredient> dressing) {
        this.dressing = dressing;
    }

    public void setDressing(Ingredient dressing) {
        this.dressing.add(dressing);
    }

    public void setSeasoning(ArrayList<Ingredient> seasoning) {
        this.seasoning = seasoning;
    }

    public void setSeasoning(Ingredient seasoning) {
        this.seasoning.add(seasoning);
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        String str = getSize() + "\n";
        if (getDoubleMeat() != null)str += getDoubleMeat() + "\n";
        for (int i = 0; i < getMeat().size(); i++) str += getMeat().get(i).getItem() + "\n";
        str += getBread().getItem() + "\n";
        if (getBacon()!=null)str += getBacon() + "\n";
        if (getAmountOfCheese()!=null)str += getAmountOfCheese() + "\n";
        if (getAmountOfCheese() == null) {
            for (int i = 0; i < getCheese().size(); i++)
                str += getCheese().get(i).getItem() + "\n";
        }
        if (getToasted()!=null)str += getToasted() + "\n";
        if (getNoVeg() != null)str += getNoVeg() + "\n";
        if (getNoVeg() == null) {
            for (int i = 0; i < getVeggies().size(); i++)
                str += getVeggies().get(i).getItem() + "\n";
        }
        if (getDressing() != null) {
            for (int i = 0; i < getDressing().size(); i++)
                str += getDressing().get(i).getItem() + "\n";
        }
        if (getSeasoning() !=null) {
            for (int i = 0; i < getSeasoning().size(); i++)
                str += getSeasoning().get(i).getItem() + "\n";
        }

        return str;
    }

}

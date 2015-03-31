package com.logobuico.johnathan.subwaysandwichscrambler;

import java.util.ArrayList;

/**
 * Created by Johnathan on 3/29/2015.
 */
public class Sandwich {
    private long id;
    private String size;
    private String doubleMeat;
    private ArrayList<Ingredient> meat;
    private Ingredient bread;
    private String amountOfCheese;
    private ArrayList<Ingredient> cheese;
    private String toasted;
    private String noVeg;
    private ArrayList<Ingredient> veggies;
    private ArrayList<Ingredient> dressing;
    private ArrayList<Ingredient> seasoning;

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
        String str = getSize() + "\n" +
                "" + getDoubleMeat() + "\n";
        for (int i = 0; i < getMeat().size(); i++) str += getMeat().get(i).getItem() + "\n";
        str += getBread().getItem() + "\n";
        str += getAmountOfCheese() + "\n";
        for (int i = 0; i < getCheese().size(); i++) str += getCheese().get(i).getItem() + "\n";
        str += getToasted() + "\n";
        str += getNoVeg() + "\n";
        for (int i = 0; i < getVeggies().size(); i++) str += getVeggies().get(i).getItem() + "\n";
        for (int i = 0; i < getDressing().size(); i++)
            str += getDressing().get(i).getItem() + "\n";
        for (int i = 0; i < getSeasoning().size(); i++)
            str += getSeasoning().get(i).getItem() + "\n";

        return str;
    }

}

package com.logobuico.johnathan.subwaysandwichscrambler;

import android.util.Log;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Johnathan on 3/28/2015.
 */
public class Randomize {

    public Map getRandomizedIngredients() {
        @SuppressWarnings("unchecked")


        Map<String, ArrayList> ingredients = new HashMap<>();
        ArrayList sizeA = new ArrayList();
        ArrayList baconA = new ArrayList();
        ArrayList doubleMeatA = new ArrayList();
        ArrayList meatA = new ArrayList();
        ArrayList breadA = new ArrayList();
        ArrayList doubleCheeseA = new ArrayList();
        ArrayList cheeseA = new ArrayList();
        ArrayList toastedA = new ArrayList();
        ArrayList noVegA = new ArrayList();
        ArrayList vegA = new ArrayList();
        ArrayList dressingA = new ArrayList();
        ArrayList seasoningsA = new ArrayList();

        int min = 1;
        Random r = new Random();
        int[] ran = new int[14];
        ran[0] = r.nextInt(3 - min) + min; //size
        ran[1] = r.nextInt(21 - min) + min; //double meat
        ran[2] = r.nextInt(21 - min) + min; //add bacon
        ran[3] = r.nextInt(20 - min) + min; //meat
        ran[4] = r.nextInt(13 - min) + min; //number of veg
        ran[5] = r.nextInt(3 - min) + min; //toasted
        ran[6] = r.nextInt(3 - min) + min; //number of dressings
        ran[7] = r.nextInt(9 - min) + min; //bread
        ran[8] = r.nextInt(18 - min) + min; //double cheese
        ran[9] = r.nextInt(5 - min) + min; //seasons
        ran[10] = r.nextInt(12 - min) + min; //veggies
        ran[11] = r.nextInt(10 - min) + min; //dressing
        ran[12] = r.nextInt(5 - min) + min; //cheese
        ran[13] = r.nextInt(3 - min) + min; //number of seasonings

        int[] arrayTwo = new int[14];

        //Size of Sub
        Ingredient size = new Ingredient();
        if (ran[0] == 1) {
            size.setIngredient("6 inch");
        } else {
            size.setIngredient("Footlong");
        }
        sizeA.add(size);
        ingredients.put("Size", sizeA);

        //Adding bacon
        if (ran[2] == 5) {
            Ingredient bacon = new Ingredient();
            bacon.setIngredient("Add Bacon");
            baconA.add(bacon);
            ingredients.put("Bacon", baconA);
        }

        //Adding meat and double meat
        if (ran[1] == 20) {
            Ingredient doubleMeat = new Ingredient();
            doubleMeat.setIngredient("Double Meat");
            doubleMeatA.add(doubleMeat);
            ingredients.put("DoubleMeat", doubleMeatA);
            arrayTwo[3] = r.nextInt(20 - min) + min;
            meatA.add(arrayTwo[3]);
            meatA.add(ran[3]);
            ingredients.put("Meat", meatA);
        } else {
            meatA.add(ran[3]);
            ingredients.put("Meat", meatA);
        }

        //Bread type
        breadA.add(ran[7]);
        ingredients.put("Bread", breadA);

        //adding cheese and double cheese
        if (ran[8] == 1) {
            Ingredient doubleCheese = new Ingredient();
            doubleCheese.setIngredient("Double Cheese");
            doubleCheeseA.add(doubleCheese);
            ingredients.put("DoubleCheese", doubleCheeseA);
            arrayTwo[12] = r.nextInt(5 - min) + min;
            cheeseA.add(arrayTwo[12]);
            cheeseA.add(ran[12]);
            ingredients.put("Cheese", cheeseA);
        } else {
            cheeseA.add(ran[12]);
            ingredients.put("Cheese", cheeseA);
        }

        //Is it Toasted
        if (ran[5] == 1) {
            Ingredient toasted = new Ingredient();
            toasted.setIngredient("Toasted");
            toastedA.add(toasted);
            ingredients.put("Toasted", toastedA);
        }

        //Number of Veggies and adding that Number
        if (ran[4] == 1) {
            Ingredient veg = new Ingredient();
            veg.setIngredient("No Veg");
            noVegA.add(veg);
            ingredients.put("NoVeg", noVegA);
        } else if (ran[4] > 1 && ran[4] != 11) {
            ArrayList<Integer> arrayThree = new ArrayList<Integer>();
            for (int i = 1; i <= 11; i++) arrayThree.add(i);
            vegA.add(ran[10]);
            arrayThree.remove(ran[10]);

            for (int i = 1; i < ran[4]; i++) {
                arrayTwo[10] = r.nextInt(arrayThree.size());
                vegA.add(arrayTwo[10]);
                arrayThree.remove(arrayTwo[10]);
            }

            ingredients.put("Veg", vegA);
        } else if (ran[4] == 11) {
            for (int i = 1; i < 12; i++) {
                vegA.add(i);
            }
            ingredients.put("Veg", vegA);

        }
        //adding dressing(s)
        if (ran[6] == 1) {
            dressingA.add(ran[11]);
            ingredients.put("Dressing", dressingA);
        } else {
            dressingA.add(ran[11]);
            arrayTwo[11] = r.nextInt(10 - min) + min;
            while (arrayTwo[11] == ran[11]) {
                arrayTwo[1] = r.nextInt(10 - min) + min;
            }
            dressingA.add(arrayTwo[11]);
            ingredients.put("Dressing", dressingA);
        }

        //adding seasonings
        if (ran[13] == 1) {
            seasoningsA.add(ran[9]);
            ingredients.put("Seasonings", seasoningsA);
        } else {
            if (ran[9] == 3) {
                seasoningsA.add(ran[9]);
                seasoningsA.add(4);
                ingredients.put("Seasonings", seasoningsA);
            } else {
                seasoningsA.add(ran[9]);
                arrayTwo[9] = r.nextInt(5 - min) + min;
                while (arrayTwo[9] == ran[9]) {
                    arrayTwo[9] = r.nextInt(5 - min) + min;
                }
                seasoningsA.add(arrayTwo[9]);
                ingredients.put("Seasonings", seasoningsA);
            }
        }

        return ingredients;
    }

}

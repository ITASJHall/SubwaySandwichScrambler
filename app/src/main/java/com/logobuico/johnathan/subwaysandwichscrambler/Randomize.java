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

    public Map<String,ArrayList> getRandomizedIngredients() {
        @SuppressWarnings("unchecked")


        Map<String, ArrayList> ingredients = new HashMap<>();
        ArrayList<String> sizeA = new ArrayList<>();
        ArrayList<String> baconA = new ArrayList<>();
        ArrayList<String> doubleMeatA = new ArrayList<>();
        ArrayList<Integer> meatA = new ArrayList<>();
        ArrayList<Integer> breadA = new ArrayList<>();
        ArrayList<String> doubleCheeseA = new ArrayList<>();
        ArrayList<Integer> cheeseA = new ArrayList<>();
        ArrayList<String> toastedA = new ArrayList<>();
        ArrayList<String> noVegA = new ArrayList<>();
        ArrayList<Integer> vegA = new ArrayList<>();
        ArrayList<Integer> dressingA = new ArrayList<>();
        ArrayList<Integer> seasoningsA = new ArrayList<>();

        int min = 1;
        Random r = new Random();
        int[] ran = new int[14];
        ran[0] = r.nextInt(3 - min) + min; //size
        ran[1] = r.nextInt(21 - min) + min; //double meat
        ran[2] = r.nextInt(21 - min) + min; //add bacon
        ran[3] = r.nextInt(20 - min) + min; //meat
        ran[4] = r.nextInt(12 - min) + min; //number of veg
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
        String size;
        if (ran[0] == 1) {
            size = "6 inch";
        } else {
            size="Footlong";
        }
        sizeA.add(size);
        ingredients.put("Size", sizeA);

        //Adding bacon
        if (ran[2] == 5) {
            String bacon;
            bacon="Add Bacon";
            baconA.add(bacon);
            ingredients.put("Bacon", baconA);
        }

        //Adding meat and double meat
        if (ran[1] == 20) {
            String doubleMeat;
            doubleMeat="Double Meat";
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
            String doubleCheese;
            doubleCheese="Double Cheese";
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
           String toasted ;
            toasted="Toasted";
            toastedA.add(toasted);
            ingredients.put("Toasted", toastedA);
        }

        //Number of Veggies and adding that Number
        if (ran[4] == 1) {
           String veg ;
            veg = "No Veg";
            noVegA.add(veg);
            ingredients.put("NoVeg", noVegA);
        } else if (ran[4] > 1 && ran[4] != 11) {
            ArrayList<Integer> arrayThree = new ArrayList<>();
            for (int i = 1; i <= 11; i++)arrayThree.add(i);
            vegA.add(ran[10]);
            int first = ran[10] - 1;
            //Log.d("SubwayTest", "Compare  "+arrayThree.get((ran[10]-1))+" FIRST: "+arrayThree.get(first)+ " ORG: "+ran[10]);
            arrayThree.remove(first);
            //Log.d("SubwayTest", "num Veg "+ran[4]);
            for (int i = 1; i < ran[4]; i++) {
                //Log.d("SubwayTest", "A3 Size before "+arrayThree.size());
                int index = r.nextInt(arrayThree.size());
                //Log.d("SubwayTest", "Veg "+arrayThree.get(index));
                vegA.add(arrayThree.get(index));
                //Log.d("SubwayTest", "Compare "+arrayThree.get(index)+" INDEX: "+ index);
                arrayThree.remove(index);
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
            ArrayList<Integer> arrayThree = new ArrayList<>();
            for (int i = 1; i <= 9; i++)arrayThree.add(i);
            int first = ran[11] - 1;
            arrayThree.remove(first);
            dressingA.add(ran[11]);
            arrayTwo[11] = r.nextInt(arrayThree.size());
            dressingA.add(arrayThree.get(arrayTwo[11]));
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
                ArrayList<Integer> arrayThree = new ArrayList<>();
                for (int i = 1; i <= 4; i++)arrayThree.add(i);
                int first = ran[9] - 1;
                arrayThree.remove(first);
                seasoningsA.add(ran[9]);
                arrayTwo[9] = r.nextInt(arrayThree.size());
                seasoningsA.add(arrayThree.get(arrayTwo[9]));
                ingredients.put("Seasonings", seasoningsA);
            }
        }

        return ingredients;
    }

}

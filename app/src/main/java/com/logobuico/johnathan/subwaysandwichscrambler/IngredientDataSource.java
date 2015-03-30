package com.logobuico.johnathan.subwaysandwichscrambler;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Johnathan on 3/28/2015.
 */
public class IngredientDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = {MySQLiteHelper.ID_COLUMN,
            MySQLiteHelper.COLUMN_NAME};

    public IngredientDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Ingredient getIngredient(int itemId, int id) {
        String table;
        //switch to pull records from the correct table
        switch (itemId) {
            case 1:
                table = MySQLiteHelper.TABLE_BREAD;
                break;
            case 2:
                table = MySQLiteHelper.TABLE_DRESSING;
                break;
            case 3:
                table = MySQLiteHelper.TABLE_CHEESE;
                break;
            case 4:
                table = MySQLiteHelper.TABLE_SEASONING;
                break;
            case 5:
                table = MySQLiteHelper.TABLE_VEGGIE;
                break;
            case 6:
                table = MySQLiteHelper.TABLE_MEAT;
                break;
            default:
                table = MySQLiteHelper.TABLE_SUBS;
        }
        Cursor cursor = database.query(table,
                allColumns, MySQLiteHelper.ID_COLUMN + " = " + id, null, null, null, null);
        cursor.moveToFirst();
        Ingredient entry = cursorToIngredient(cursor);
        cursor.close();
        return entry;
    }

    public Ingredient getSub(int id) {
        Cursor cursor = database.query(MySQLiteHelper.TABLE_SUBS,
                allColumns, MySQLiteHelper.ID_COLUMN + " = " + id, null, null, null, null);
        cursor.moveToFirst();
        Ingredient entry = cursorToIngredient(cursor);
        cursor.close();
        return entry;
    }

    public void saveSub(ArrayAdapter sub){

        for(int i=0;i<sub.getCount();i++) {
            Log.i("SubwayTest",sub.getItem(i).toString());
        }
    }

    public List<Ingredient> getAllIngredients() {
        Randomize randomize = new Randomize();
        Map<String, ArrayList> random = randomize.getRandomizedIngredients();
        List<Ingredient> ingredients = new ArrayList<Ingredient>();

        if (random.containsKey("Size")) {
            Ingredient size = new Ingredient();
            String sizeStr = random.get("Size").toString();
            sizeStr = sizeStr.substring(1,(sizeStr.length()-1));
            size.setIngredient(sizeStr);
            ingredients.add(size);
            Log.i("Subway",""+size.toString());
        }
        if (random.containsKey("DoubleMeat")) {
            Ingredient doubleMeat = new Ingredient();
            String meatStr = random.get("DoubleMeat").toString();
            meatStr = meatStr.substring(1,(meatStr.length()-1));
            doubleMeat.setIngredient(meatStr);
            ingredients.add(doubleMeat);
            Log.i("Subway",""+doubleMeat.toString());
        }
        if (random.containsKey("Meat")) {
            for (int i = 0; i < random.get("Meat").size(); i++) {
                int meat = Integer.parseInt(random.get("Meat").get(i).toString());
                ingredients.add(getIngredient(6, meat));
                Log.i("Subway","meat "+meat);
            }
        }
        if (random.containsKey("Bread")) {
            int bread = Integer.parseInt(random.get("Bread").get(0).toString());
            ingredients.add(getIngredient(1, bread));
            Log.i("Subway","bread "+bread);
        }
        if (random.containsKey("Bacon")) {
            Ingredient bacon = new Ingredient();
            String baconStr = random.get("Bacon").toString();
            baconStr = baconStr.substring(1,(baconStr.length()-1));
            bacon.setIngredient(baconStr);
            ingredients.add(bacon);
            Log.i("Subway",""+bacon.toString());
        }
        if (random.containsKey("DoubleCheese")) {
            Ingredient doubleCheese = new Ingredient();
            String cheeseStr = random.get("DoubleCheese").toString();
            cheeseStr = cheeseStr.substring(1,(cheeseStr.length()-1));
            doubleCheese.setIngredient(cheeseStr);
            ingredients.add(doubleCheese);
            Log.i("Subway",""+doubleCheese.toString());
        }
        if (random.containsKey("Cheese")) {
            for (int i = 0; i < random.get("Cheese").size(); i++) {
                int cheese = Integer.parseInt(random.get("Cheese").get(i).toString());
                ingredients.add(getIngredient(3, cheese));
                Log.i("Subway","cheese "+cheese);
            }
        }
        if (random.containsKey("Toasted")) {
            Ingredient toasted = new Ingredient();
            String toastStr = random.get("Toasted").toString();
            toastStr = toastStr.substring(1,(toastStr.length()-1));
            toasted.setIngredient(toastStr);
            ingredients.add(toasted);
            Log.i("Subway",""+toasted.toString());
        }
        if (random.containsKey("NoVeg")) {
            Ingredient noVeg = new Ingredient();
            String vegStr = random.get("NoVeg").toString();
            vegStr = vegStr.substring(1,(vegStr.length()-1));
            noVeg.setIngredient(vegStr);
            ingredients.add(noVeg);
            Log.i("Subway",""+noVeg.toString());
        }
        if (random.containsKey("Veg")) {
            for (int i = 0; i < random.get("Veg").size(); i++) {
                int veg = Integer.parseInt(random.get("Veg").get(i).toString());
                ingredients.add(getIngredient(5, veg));
                Log.i("Subway","veg "+veg);
            }
        }
        if (random.containsKey("Dressing")) {
            for (int i = 0; i < random.get("Dressing").size(); i++) {
                int dressing = Integer.parseInt(random.get("Dressing").get(i).toString());
                ingredients.add(getIngredient(2, dressing));
                Log.i("Subway","dressing "+dressing);
            }
        }
        if (random.containsKey("Seasonings")) {
            for (int i = 0; i < random.get("Seasonings").size(); i++) {
                int seasonings = Integer.parseInt(random.get("Seasonings").get(i).toString());
                ingredients.add(getIngredient(4, seasonings));
                Log.i("Subway","seasonings "+seasonings);
            }
        }
        return ingredients;
    }

    private Ingredient cursorToIngredient(Cursor cursor) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(cursor.getLong(0));
        ingredient.setIngredient(cursor.getString(1));
        return ingredient;
    }

}

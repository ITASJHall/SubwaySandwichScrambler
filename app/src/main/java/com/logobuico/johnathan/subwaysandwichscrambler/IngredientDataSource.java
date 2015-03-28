package com.logobuico.johnathan.subwaysandwichscrambler;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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

    public List<Ingredient> getAllIngredients() {
        Randomize randomize = new Randomize();
        Map<String, ArrayList> random = randomize.getRandomizedIngredients();
        List<Ingredient> ingredients = new ArrayList<Ingredient>();

        if (random.containsKey("Size")) {
            Ingredient size = new Ingredient();
            size.setIngredient(random.get("Size").get(0).toString());
            ingredients.add(size);
        }
        if (random.containsKey("DoubleMeat")) {
            Ingredient doubleMeat = new Ingredient();
            doubleMeat.setIngredient(random.get("DoubleMeat").get(0).toString());
            ingredients.add(doubleMeat);
        }
        if (random.containsKey("Meat")) {
            for (int i = 0; i < random.get("Meat").size(); i++) {
                int meat = Integer.parseInt(random.get("Meat").get(i).toString());
                ingredients.add(getIngredient(6, meat));
            }
        }
        if (random.containsKey("Bread")) {
            int bread = Integer.parseInt(random.get("Bread").get(0).toString());
            ingredients.add(getIngredient(1, bread));
        }
        if (random.containsKey("Bacon")) {
            Ingredient bacon = new Ingredient();
            bacon.setIngredient(random.get("Bacon").toString());
            ingredients.add(bacon);
        }
        if (random.containsKey("DoubleCheese")) {
            Ingredient doubleCheese = new Ingredient();
            doubleCheese.setIngredient(random.get("DoubleCheese").toString());
            ingredients.add(doubleCheese);
        }
        if (random.containsKey("Cheese")) {
            for (int i = 0; i < random.get("Cheese").size(); i++) {
                int cheese = Integer.parseInt(random.get("Cheese").get(i).toString());
                ingredients.add(getIngredient(3, cheese));
            }
        }
        if (random.containsKey("Toasted")) {
            Ingredient toasted = new Ingredient();
            toasted.setIngredient(random.get("Toast").toString());
            ingredients.add(toasted);
        }
        if (random.containsKey("NoVeg")) {
            Ingredient noVeg = new Ingredient();
            noVeg.setIngredient(random.get("NoVeg").toString());
            ingredients.add(noVeg);
        }
        if (random.containsKey("Veg")) {
            for (int i = 0; i < random.get("Veg").size(); i++) {
                int veg = Integer.parseInt(random.get("Veg").get(i).toString());
                ingredients.add(getIngredient(5, veg));
            }
        }
        if (random.containsKey("Dressing")) {
            for (int i = 0; i < random.get("Dressing").size(); i++) {
                int dressing = Integer.parseInt(random.get("Dressing").get(i).toString());
                ingredients.add(getIngredient(2, dressing));
            }
        }
        if (random.containsKey("Seasoning")) {
            for (int i = 0; i < random.get("Seasonings").size(); i++) {
                int seasonings = Integer.parseInt(random.get("Seasonings").get(i).toString());
                ingredients.add(getIngredient(3, seasonings));
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

package com.logobuico.johnathan.subwaysandwichscrambler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Array;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Class used to query the database and save sandwiches to the database
 * Created by Johnathan on 3/28/2015.
 */
public class IngredientDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = {MySQLiteHelper.ID_COLUMN,
            MySQLiteHelper.COLUMN_NAME};
    private String[] allSubColumns = {MySQLiteHelper.ID_COLUMN,
            MySQLiteHelper.COLUMN_NAME, MySQLiteHelper.COLUMN_COMMENT,
            MySQLiteHelper.COLUMN_RATING,MySQLiteHelper.COLUMN_IMAGE, MySQLiteHelper.COLUMN_SUB};
    private Map<String, ArrayList<Ingredient>> saveSub = new HashMap<>();

    public IngredientDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    /**
     * getting the ingredient String from the database
     * @param itemId type of ingredient
     * @param id ingredient number
     * @return Ingredient
     */
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

    /**
     * Not used yet, but to be used when editing a saved entry
     * @param id sandwich entry id
     * @return sandwich
     */
    public Sandwich getSub(int id) {
        //querying the database
        Cursor cursor = database.query(MySQLiteHelper.TABLE_SUBS,
                allSubColumns, MySQLiteHelper.ID_COLUMN + " = " + id, null, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            //getting sandwich byte[] array and converting that back to a sandwich object
            byte[] subByte = cursor.getBlob(cursor.getColumnIndex(MySQLiteHelper.COLUMN_SUB));
            Sandwich deserializedSub = (Sandwich) Serializer.deserializeObject(subByte);
            cursor.close();
            return deserializedSub;
        } else {
            Sandwich sub = new Sandwich();
            return sub;
        }

    }

    /**
     * getting all saved sub. Used for ViewSavedSubs activity
     * @return arraylist of saved subs
     */
    public ArrayList getAllSubs() {
        //used to hold all subs pull from the database
        ArrayList sandwiches = new ArrayList<>();
        //querying the database
        Cursor cursor = database.query(MySQLiteHelper.TABLE_SUBS,
                allSubColumns, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                //used to hold a single saved entry
                ArrayList sub = new ArrayList<>();
                //pulling the Title,Comment,Rating,Image,and Sub
                String name = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_NAME));
                sub.add(name);
                String message = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_COMMENT));
                sub.add(message);
                Float rating = cursor.getFloat(cursor.getColumnIndex(MySQLiteHelper.COLUMN_RATING));
                sub.add(rating);
                //testing to see if there is an image savved in the entry
                try {
                    byte[] imageByte = cursor.getBlob(cursor.getColumnIndex(MySQLiteHelper.COLUMN_IMAGE));
                    sub.add(imageByte);
                }catch (Exception e){
                    Log.d("SubEntry","No image found for entry");
                }
                //getting sandwich byte[] array and converting that back to a sandwich object
                byte[] subByte = cursor.getBlob(cursor.getColumnIndex(MySQLiteHelper.COLUMN_SUB));
                Sandwich deserializedSub = (Sandwich) Serializer.deserializeObject(subByte);
                sub.add(deserializedSub);
                sandwiches.add(sub);
                cursor.moveToNext();
            }
            cursor.close();
            return sandwiches;
        } else {
            cursor.close();
            return sandwiches;
        }

    }

    /**
     * saving a randomized Sub to the database
     * @param name of the sandwich
     * @param comment on the sandwich
     * @param rating of the sandwich
     * @param image of the sandwich
     * @param isImage was a picture taken
     */
    public void saveSub(String name, String comment, Float rating, Bitmap image, boolean isImage) {
        Sandwich subSandwich = new Sandwich();
        //checking and adding all field of a sub then adding them to the new sandwich object
        //which will be save into the database.
        if (saveSub.containsKey("Size")) {
            String sizeStr = saveSub.get("Size").toString();
            sizeStr = sizeStr.substring(1, (sizeStr.length() - 1));
            subSandwich.setSize(sizeStr);
        }
        if (saveSub.containsKey("DoubleMeat")) {
            String meatStr = saveSub.get("DoubleMeat").toString();
            meatStr = meatStr.substring(1, (meatStr.length() - 1));
            subSandwich.setDoubleMeat(meatStr);
        }
        if (saveSub.containsKey("Meat")) {
            subSandwich.setMeat(saveSub.get("Meat"));
        }
        if (saveSub.containsKey("Bread")) {
            subSandwich.setBread(saveSub.get("Bread").get(0));
        }
        if (saveSub.containsKey("Bacon")) {
            String baconStr = saveSub.get("Bacon").toString();
            baconStr = baconStr.substring(1, (baconStr.length() - 1));
            subSandwich.setBacon(baconStr);
        }
        if (saveSub.containsKey("DoubleCheese")) {
            String cheeseStr = saveSub.get("DoubleCheese").toString();
            cheeseStr = cheeseStr.substring(1, (cheeseStr.length() - 1));
            subSandwich.setAmountOfCheese(cheeseStr);
        }
        if (saveSub.containsKey("Cheese")) {
            subSandwich.setCheese(saveSub.get("Cheese"));
        }
        if (saveSub.containsKey("Toasted")) {
            String toastStr = saveSub.get("Toasted").toString();
            toastStr = toastStr.substring(1, (toastStr.length() - 1));
            subSandwich.setToasted(toastStr);
        }
        if (saveSub.containsKey("NoVeg")) {
            String vegStr = saveSub.get("NoVeg").toString();
            vegStr = vegStr.substring(1, (vegStr.length() - 1));
            subSandwich.setNoVeg(vegStr);
        }
        if (saveSub.containsKey("Veg")) {
            subSandwich.setVeggies(saveSub.get("Veg"));
        }
        if (saveSub.containsKey("Dressing")) {
            subSandwich.setDressing(saveSub.get("Dressing"));
        }
        if (saveSub.containsKey("Seasonings")) {
            subSandwich.setSeasoning(saveSub.get("Seasonings"));
        }
        //converting the sandwich object into a byte[] array
        byte[] subBytes = Serializer.serializeObject(subSandwich);
        //setting the values on the table entry
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NAME, name);
        values.put(MySQLiteHelper.COLUMN_COMMENT, comment);
        values.put(MySQLiteHelper.COLUMN_RATING, rating);
        //a check to see if an image was taken
        if (isImage) {
            //converting the image to a byte[] array
            byte[] imageBytes = Serializer.getBitmapAsByteArray(image);
            values.put(MySQLiteHelper.COLUMN_IMAGE, imageBytes);
        }
        values.put(MySQLiteHelper.COLUMN_SUB, subBytes);
        //saving the sub
        database.insert(MySQLiteHelper.TABLE_SUBS, null, values);


    }

    /**
     * The main method for generating a random sub
     * @return list of all the ingredient in the randomized sandwich
     */
    public List<Ingredient> getAllIngredients() {
        //getting the random sub integer values
        Randomize randomize = new Randomize();
        Map<String, ArrayList> random = randomize.getRandomizedIngredients();
        //array to store all the ingredients
        List<Ingredient> ingredients = new ArrayList<Ingredient>();

        //checking to see if the ingredient key are then calling the getIngedient method to get
        //the String value of the number.
        //it is also saving a duplicate list used for saving the sub

        //getting size
        if (random.containsKey("Size")) {
            ArrayList<Ingredient> sizeA = new ArrayList<>();
            Ingredient size = new Ingredient();
            String sizeStr = random.get("Size").toString();
            sizeStr = sizeStr.substring(1, (sizeStr.length() - 1));
            size.setIngredient(sizeStr);
            ingredients.add(size);
            sizeA.add(size);
            saveSub.put("Size", sizeA);
            Log.i("Subway", "" + size.toString());
        }
        //checking to see if double meat is set
        if (random.containsKey("DoubleMeat")) {
            ArrayList<Ingredient> doubleMeatA = new ArrayList<>();
            Ingredient doubleMeat = new Ingredient();
            String meatStr = random.get("DoubleMeat").toString();
            meatStr = meatStr.substring(1, (meatStr.length() - 1));
            doubleMeat.setIngredient(meatStr);
            ingredients.add(doubleMeat);
            doubleMeatA.add(doubleMeat);
            saveSub.put("DoubleMeat", doubleMeatA);
            Log.i("Subway", "" + doubleMeat.toString());
        }
        //getting meat
        if (random.containsKey("Meat")) {
            ArrayList<Ingredient> meatA = new ArrayList<>();
            for (int i = 0; i < random.get("Meat").size(); i++) {
                int meat = Integer.parseInt(random.get("Meat").get(i).toString());
                ingredients.add(getIngredient(6, meat));
                meatA.add(getIngredient(6, meat));
                Log.i("Subway", "meat " + meat);
            }
            saveSub.put("Meat", meatA);
        }
        //getting the bread
        if (random.containsKey("Bread")) {
            ArrayList<Ingredient> breadA = new ArrayList<>();
            int bread = Integer.parseInt(random.get("Bread").get(0).toString());
            ingredients.add(getIngredient(1, bread));
            breadA.add(getIngredient(1, bread));
            saveSub.put("Bread", breadA);
            Log.i("Subway", "bread " + bread);
        }
        //checking to see if bacon is set
        if (random.containsKey("Bacon")) {
            ArrayList<Ingredient> baconA = new ArrayList<>();
            Ingredient bacon = new Ingredient();
            String baconStr = random.get("Bacon").toString();
            baconStr = baconStr.substring(1, (baconStr.length() - 1));
            bacon.setIngredient(baconStr);
            ingredients.add(bacon);
            baconA.add(bacon);
            saveSub.put("Bacon", baconA);
            Log.i("Subway", "" + bacon.toString());
        }
        //checking to see if double cheese is set
        if (random.containsKey("DoubleCheese")) {
            ArrayList<Ingredient> doubleCheeseA = new ArrayList<>();
            Ingredient doubleCheese = new Ingredient();
            String cheeseStr = random.get("DoubleCheese").toString();
            cheeseStr = cheeseStr.substring(1, (cheeseStr.length() - 1));
            doubleCheese.setIngredient(cheeseStr);
            ingredients.add(doubleCheese);
            doubleCheeseA.add(doubleCheese);
            saveSub.put("DoubleCheese", doubleCheeseA);
            Log.i("Subway", "" + doubleCheese.toString());
        }
        //getting the cheese
        if (random.containsKey("Cheese")) {
            ArrayList<Ingredient> cheeseA = new ArrayList<>();
            for (int i = 0; i < random.get("Cheese").size(); i++) {
                int cheese = Integer.parseInt(random.get("Cheese").get(i).toString());
                ingredients.add(getIngredient(3, cheese));
                cheeseA.add(getIngredient(3, cheese));
                Log.i("Subway", "cheese " + cheese);
            }
            saveSub.put("Cheese", cheeseA);
        }
        //checking to see if toasted is set
        if (random.containsKey("Toasted")) {
            ArrayList<Ingredient> toastedA = new ArrayList<>();
            Ingredient toasted = new Ingredient();
            String toastStr = random.get("Toasted").toString();
            toastStr = toastStr.substring(1, (toastStr.length() - 1));
            toasted.setIngredient(toastStr);
            ingredients.add(toasted);
            toastedA.add(toasted);
            saveSub.put("Toasted", toastedA);
            Log.i("Subway", "" + toasted.toString());
        }
        //checking to see in no veg is set
        if (random.containsKey("NoVeg")) {
            ArrayList<Ingredient> noVegA = new ArrayList<>();
            Ingredient noVeg = new Ingredient();
            String vegStr = random.get("NoVeg").toString();
            vegStr = vegStr.substring(1, (vegStr.length() - 1));
            noVeg.setIngredient(vegStr);
            ingredients.add(noVeg);
            noVegA.add(noVeg);
            saveSub.put("NoVeg", noVegA);
            Log.i("Subway", "" + noVeg.toString());
        }

        //getting the veggies
        if (random.containsKey("Veg")) {
            ArrayList<Ingredient> vegA = new ArrayList<>();
            for (int i = 0; i < random.get("Veg").size(); i++) {
                int veg = Integer.parseInt(random.get("Veg").get(i).toString());
                ingredients.add(getIngredient(5, veg));
                vegA.add(getIngredient(5, veg));
                Log.i("Subway", "veg " + veg);
            }
            saveSub.put("Veg", vegA);
        }
        //getting the dressings
        if (random.containsKey("Dressing")) {
            ArrayList<Ingredient> dressingA = new ArrayList<>();
            for (int i = 0; i < random.get("Dressing").size(); i++) {
                int dressing = Integer.parseInt(random.get("Dressing").get(i).toString());
                ingredients.add(getIngredient(2, dressing));
                dressingA.add(getIngredient(2, dressing));
                Log.i("Subway", "dressing " + dressing);
            }
            saveSub.put("Dressing", dressingA);
        }
        //getting the seasonings
        if (random.containsKey("Seasonings")) {
            ArrayList<Ingredient> seasoningsA = new ArrayList<>();
            for (int i = 0; i < random.get("Seasonings").size(); i++) {
                int seasonings = Integer.parseInt(random.get("Seasonings").get(i).toString());
                ingredients.add(getIngredient(4, seasonings));
                seasoningsA.add(getIngredient(4, seasonings));
                Log.i("Subway", "seasonings " + seasonings);
            }
            saveSub.put("Seasonings", seasoningsA);
        }
        return ingredients;
    }

    /**
     * used for getting the Ingredient String from the database
     * @param cursor
     * @return ingredients
     */
    private Ingredient cursorToIngredient(Cursor cursor) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(cursor.getLong(0));
        ingredient.setIngredient(cursor.getString(1));
        return ingredient;
    }

}

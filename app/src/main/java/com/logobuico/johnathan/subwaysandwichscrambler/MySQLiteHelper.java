package com.logobuico.johnathan.subwaysandwichscrambler;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Johnathan on 3/28/2015.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_BREAD = "breads";
    public static final String TABLE_DRESSING = "dressings";
    public static final String TABLE_CHEESE = "cheese";
    public static final String TABLE_SEASONING = "seasonings";
    public static final String TABLE_VEGGIE = "veggies";
    public static final String TABLE_MEAT = "meats";
    public static final String TABLE_SUBS = "subs";

    public static final String ID_COLUMN = "_id";
    public static final String COLUMN_NAME = "name";

    private static final String DATABASE_NAME = "subway.db";
    private static final int DATABASE_VERSION = 5;

    private static final String DATABASE_CREATE = "create table "
            + TABLE_BREAD + "(" + ID_COLUMN
            + " integer primary key autoincrement, " + COLUMN_NAME
            + " text not null);";

    public static final String CREATE_DRESSING_TABLE = "CREATE TABLE "
            + TABLE_DRESSING + "(" + ID_COLUMN
            + " integer primary key autoincrement, " + COLUMN_NAME
            + " text not null);";

    public static final String CREATE_CHEESE_TABLE = "CREATE TABLE "
            + TABLE_CHEESE + "(" + ID_COLUMN
            + " integer primary key autoincrement, " + COLUMN_NAME
            + " text not null);";

    public static final String CREATE_SEASONING_TABLE = "CREATE TABLE "
            + TABLE_SEASONING + "(" + ID_COLUMN
            + " integer primary key autoincrement, " + COLUMN_NAME
            + " text not null);";

    public static final String CREATE_VEGGIE_TABLE = "CREATE TABLE "
            + TABLE_VEGGIE + "(" + ID_COLUMN
            + " integer primary key autoincrement, " + COLUMN_NAME
            + " text not null);";

    public static final String CREATE_MEAT_TABLE = "CREATE TABLE "
            + TABLE_MEAT + "(" + ID_COLUMN
            + " integer primary key autoincrement, " + COLUMN_NAME
            + " text not null);";

    public static final String CREATE_SUB_TABLE = "CREATE TABLE "
            + TABLE_SUBS + "(" + ID_COLUMN
            + " integer primary key autoincrement, " + COLUMN_NAME
            + " text not null, 'size' text not null, 'meat' text not null, " +
            "'bread' text not null, 'cheese' text, 'toasted' text, 'veggies' text, " +
            "'dressing' text not null, 'seasoning' text not null);";

    public void populateTables(SQLiteDatabase database) {
        ContentValues values = new ContentValues();
        //adding breads
        values.put(MySQLiteHelper.COLUMN_NAME, "White");
        database.insert(MySQLiteHelper.TABLE_BREAD, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Wheat");
        database.insert(MySQLiteHelper.TABLE_BREAD, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Honey Oat");
        database.insert(MySQLiteHelper.TABLE_BREAD, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Italian Herbs & Cheese");
        database.insert(MySQLiteHelper.TABLE_BREAD, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Parmesan Oregano");
        database.insert(MySQLiteHelper.TABLE_BREAD, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Italian");
        database.insert(MySQLiteHelper.TABLE_BREAD, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Flatbread");
        database.insert(MySQLiteHelper.TABLE_BREAD, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Roasted Garlic");
        database.insert(MySQLiteHelper.TABLE_BREAD, null, values);

        //adding dressings
        values.put(MySQLiteHelper.COLUMN_NAME, "Chipotle Southwest");
        database.insert(MySQLiteHelper.TABLE_DRESSING, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Honey Mustard");
        database.insert(MySQLiteHelper.TABLE_DRESSING, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Sweet Onion");
        database.insert(MySQLiteHelper.TABLE_DRESSING, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "House Sauce");
        database.insert(MySQLiteHelper.TABLE_DRESSING, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Mayo");
        database.insert(MySQLiteHelper.TABLE_DRESSING, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Ranch");
        database.insert(MySQLiteHelper.TABLE_DRESSING, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Mustard");
        database.insert(MySQLiteHelper.TABLE_DRESSING, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Sriracha");
        database.insert(MySQLiteHelper.TABLE_DRESSING, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Hot Sauce");
        database.insert(MySQLiteHelper.TABLE_DRESSING, null, values);

        //adding cheeses
        values.put(MySQLiteHelper.COLUMN_NAME, "Natural Cheddar");
        database.insert(MySQLiteHelper.TABLE_CHEESE, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Mozzarella");
        database.insert(MySQLiteHelper.TABLE_CHEESE, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Cheddar");
        database.insert(MySQLiteHelper.TABLE_CHEESE, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Shredded Mozza");
        database.insert(MySQLiteHelper.TABLE_CHEESE, null, values);

        //adding seasonings
        values.put(MySQLiteHelper.COLUMN_NAME, "Salt");
        database.insert(MySQLiteHelper.TABLE_SEASONING, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Pepper");
        database.insert(MySQLiteHelper.TABLE_SEASONING, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Salt & Pepper");
        database.insert(MySQLiteHelper.TABLE_SEASONING, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Parmesan");
        database.insert(MySQLiteHelper.TABLE_SEASONING, null, values);

        //adding veggies
        values.put(MySQLiteHelper.COLUMN_NAME, "Avocado");
        database.insert(MySQLiteHelper.TABLE_VEGGIE, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Banana Peppers");
        database.insert(MySQLiteHelper.TABLE_VEGGIE, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Cucumbers");
        database.insert(MySQLiteHelper.TABLE_VEGGIE, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Green Peppers");
        database.insert(MySQLiteHelper.TABLE_VEGGIE, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Jalapeno Peppers");
        database.insert(MySQLiteHelper.TABLE_VEGGIE, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Lettuce");
        database.insert(MySQLiteHelper.TABLE_VEGGIE, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Onions");
        database.insert(MySQLiteHelper.TABLE_VEGGIE, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Pickles");
        database.insert(MySQLiteHelper.TABLE_VEGGIE, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Olives");
        database.insert(MySQLiteHelper.TABLE_VEGGIE, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Spinach");
        database.insert(MySQLiteHelper.TABLE_VEGGIE, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Tomatoes");
        database.insert(MySQLiteHelper.TABLE_VEGGIE, null, values);

        //adding meats
        values.put(MySQLiteHelper.COLUMN_NAME, "Roasted Chicken");
        database.insert(MySQLiteHelper.TABLE_MEAT, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Salami");
        database.insert(MySQLiteHelper.TABLE_MEAT, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Chicken");
        database.insert(MySQLiteHelper.TABLE_MEAT, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Teriyaki Chicken");
        database.insert(MySQLiteHelper.TABLE_MEAT, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Cold Cut");
        database.insert(MySQLiteHelper.TABLE_MEAT, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Egg");
        database.insert(MySQLiteHelper.TABLE_MEAT, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Ham");
        database.insert(MySQLiteHelper.TABLE_MEAT, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Meatball");
        database.insert(MySQLiteHelper.TABLE_MEAT, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Roast Beef");
        database.insert(MySQLiteHelper.TABLE_MEAT, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Steak");
        database.insert(MySQLiteHelper.TABLE_MEAT, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Sausage");
        database.insert(MySQLiteHelper.TABLE_MEAT, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Seafood");
        database.insert(MySQLiteHelper.TABLE_MEAT, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Tuna");
        database.insert(MySQLiteHelper.TABLE_MEAT, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Turkey");
        database.insert(MySQLiteHelper.TABLE_MEAT, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Pepperoni");
        database.insert(MySQLiteHelper.TABLE_MEAT, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Veggie");
        database.insert(MySQLiteHelper.TABLE_MEAT, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Cheese");
        database.insert(MySQLiteHelper.TABLE_MEAT, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Falafel");
        database.insert(MySQLiteHelper.TABLE_MEAT, null, values);
        values.put(MySQLiteHelper.COLUMN_NAME, "Spicy Italian");
        database.insert(MySQLiteHelper.TABLE_MEAT, null, values);

    }

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
        database.execSQL(CREATE_DRESSING_TABLE);
        database.execSQL(CREATE_CHEESE_TABLE);
        database.execSQL(CREATE_SEASONING_TABLE);
        database.execSQL(CREATE_VEGGIE_TABLE);
        database.execSQL(CREATE_MEAT_TABLE);
        database.execSQL(CREATE_SUB_TABLE);
        Log.i("Subway","Database Table "+CREATE_SUB_TABLE);
        populateTables(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BREAD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRESSING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHEESE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SEASONING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VEGGIE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEAT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBS);
        onCreate(db);
    }
}

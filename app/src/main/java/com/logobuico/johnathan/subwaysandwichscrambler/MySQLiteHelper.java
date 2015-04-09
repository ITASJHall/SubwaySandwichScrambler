package com.logobuico.johnathan.subwaysandwichscrambler;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Creation of the database and inserts
 * Created by Johnathan on 3/28/2015.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    //Table names
    public static final String TABLE_BREAD = "breads";
    public static final String TABLE_DRESSING = "dressings";
    public static final String TABLE_CHEESE = "cheese";
    public static final String TABLE_SEASONING = "seasonings";
    public static final String TABLE_VEGGIE = "veggies";
    public static final String TABLE_MEAT = "meats";
    public static final String TABLE_SUBS = "subs";

    //column names
    public static final String ID_COLUMN = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SUB = "sub";
    public static final String COLUMN_COMMENT = "comment";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_IMAGE = "image";

    //database name and version
    private static final String DATABASE_NAME = "subway.db";
    private static final int DATABASE_VERSION = 18;

    //creating the tables sql statments for each of the tables in the database
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
    //BLOB type is used to hole byte[] arrays
    public static final String CREATE_SUB_TABLE = "CREATE TABLE "
            + TABLE_SUBS + "(" + ID_COLUMN
            + " integer primary key autoincrement, " + COLUMN_NAME
            + " text not null, " + COLUMN_COMMENT
            + " text, " + COLUMN_RATING
            + " FLOA not null, " + COLUMN_IMAGE
            + " BLOB, " +  COLUMN_SUB
            + " BLOB not null )";

    /**
     * populating the tables with predefined values
     * TODO:Have these values be populated from a XML file or pulled from a remote database
     * @param database
     */
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

    /**
     * calling the tables sql statments and creating the tables
     * @param database
     */
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
        database.execSQL(CREATE_DRESSING_TABLE);
        database.execSQL(CREATE_CHEESE_TABLE);
        database.execSQL(CREATE_SEASONING_TABLE);
        database.execSQL(CREATE_VEGGIE_TABLE);
        database.execSQL(CREATE_MEAT_TABLE);
        database.execSQL(CREATE_SUB_TABLE);
        populateTables(database);
    }

    /**
     * Upgrading the database
     * @param db
     * @param oldVersion
     * @param newVersion
     */
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
        //to be removed once the subs table is finialized, save subs table should not be removed on update
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBS);

        onCreate(db);
    }
}

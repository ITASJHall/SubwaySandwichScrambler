package com.logobuico.johnathan.subwaysandwichscrambler;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Johnathan on 3/28/2015.
 */
public class IngredientDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.ID_COLUMN,
            MySQLiteHelper.COLUMN_NAME };

    public IngredientDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Ingredient getIngredient(int itemId, int id){
        String table;
        //switch to pull records from the correct table
        switch (itemId) {
            case 1: table = MySQLiteHelper.TABLE_BREAD;
                break;
            case 2: table = MySQLiteHelper.TABLE_DRESSING;
                break;
            case 3: table = MySQLiteHelper.TABLE_CHEESE;
                break;
            case 4: table = MySQLiteHelper.TABLE_SEASONING;
                break;
            case 5: table = MySQLiteHelper.TABLE_VEGGIE;
                break;
            case 6: table = MySQLiteHelper.TABLE_MEAT;
                break;
            default:table = MySQLiteHelper.TABLE_SUBS;
        }
        Cursor cursor = database.query(table,
                allColumns, MySQLiteHelper.ID_COLUMN + " = " + id , null, null, null, null);
        cursor.moveToFirst();
        Ingredient entry = cursorToIngredient(cursor);
        cursor.close();
        return entry;

    }

    private Ingredient cursorToIngredient(Cursor cursor) {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(cursor.getLong(0));
        ingredient.setIngredient(cursor.getString(1));
        return ingredient;
    }


}

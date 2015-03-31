package com.logobuico.johnathan.subwaysandwichscrambler;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.List;


public class MainActivity extends ListActivity {
    private IngredientDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datasource = new IngredientDataSource(this);

        ArrayAdapter<Ingredient> adapter = new ArrayAdapter<Ingredient>(this,
                android.R.layout.simple_list_item_1);
        setListAdapter(adapter);

    }

    public void onClick(View view){
       @SuppressWarnings("unchecked")
       ArrayAdapter<Ingredient> adapter = (ArrayAdapter<Ingredient>) getListAdapter();
       datasource.open();
        switch (view.getId()) {
            case R.id.random:
                adapter.clear();
                List<Ingredient> values = datasource.getAllIngredients();
                adapter = new ArrayAdapter<Ingredient>(this, android.R.layout.simple_expandable_list_item_1, values);
                setListAdapter(adapter);
                break;
            case R.id.save:
                if (getListAdapter().getCount() > 0) {
                    datasource.saveSub();
                    Log.i("SavedSub"," "+datasource.getSub(2));
                }
                break;
        }
       datasource.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }
}

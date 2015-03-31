package com.logobuico.johnathan.subwaysandwichscrambler;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends ListActivity  {
    private IngredientDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


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
                    final AlertDialog.Builder alert = new AlertDialog.Builder(this);
                    alert.setTitle(R.string.save_title);
                    LayoutInflater factory = LayoutInflater.from(this);
                    final View textEntryView = factory.inflate(R.layout.text_entry, null);
                    final EditText input1 = (EditText) textEntryView.findViewById(R.id.EditText1);
                    final EditText input2 = (EditText) textEntryView.findViewById(R.id.EditText2);
                    final RatingBar input3 = (RatingBar) textEntryView.findViewById(R.id.ratingBar);
                    LayerDrawable stars = (LayerDrawable)input3.getProgressDrawable();
                    stars.getDrawable(1).setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                    stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
                    stars.getDrawable(0).setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_ATOP);


                    alert.setView(textEntryView);
                    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            //datasource.open();
                            String name = input1.getText().toString().trim();
                            String comment = input2.getText().toString().trim();
                            Log.i("SubRating",""+input3.getRating());
                           // datasource.saveSub(name);
                            String save = "Saved";
                            Toast.makeText(getApplicationContext(), save, Toast.LENGTH_SHORT).show();
                        }
                    });
                    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.cancel();
                        }
                    });
                    alert.show();
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

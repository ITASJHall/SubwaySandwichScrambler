package com.logobuico.johnathan.subwaysandwichscrambler;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends ListActivity  {
    private IngredientDataSource datasource;
    private int saved;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Bitmap bitMap;
    ImageView imageView;
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
                saved = 0;
                adapter.clear();
                List<Ingredient> values = datasource.getAllIngredients();
                adapter = new ArrayAdapter<Ingredient>(this, android.R.layout.simple_list_item_1, values);
                setListAdapter(adapter);
                break;
            case R.id.save:
                if (getListAdapter().getCount() > 0){
                    if(saved != getListAdapter().hashCode()) {

                        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
                        //setting the title for the dialog box
                        alert.setTitle(R.string.save_title);
                        //setting the layout for the dialog box
                        LayoutInflater factory = LayoutInflater.from(this);
                        final View textEntryView = factory.inflate(R.layout.text_entry, null);
                        //input for name
                        final EditText input1 = (EditText) textEntryView.findViewById(R.id.EditText1);
                        //input for comments
                        final EditText input2 = (EditText) textEntryView.findViewById(R.id.EditText2);
                        //rating input
                        final RatingBar input3 = (RatingBar) textEntryView.findViewById(R.id.ratingBar);
                        //setting colour of rating stars
                        LayerDrawable stars = (LayerDrawable)input3.getProgressDrawable();
                        stars.getDrawable(1).setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
                        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
                        stars.getDrawable(0).setColorFilter(Color.LTGRAY, PorterDuff.Mode.SRC_ATOP);


                        imageView = (ImageView) textEntryView.findViewById(R.id.subPic);
                        //setting the view
                        alert.setView(textEntryView);

                        alert.setNeutralButton("Picture", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        });
                        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                if (input1.getText().toString().isEmpty()){
                                    dialog.dismiss();
                                    Toast.makeText(getApplicationContext(),"Invalid Name entry", Toast.LENGTH_LONG).show();
                                }else {
                                    datasource.open();
                                    String name = input1.getText().toString().trim();
                                    String comment = input2.getText().toString().trim();
                                    Float rating = input3.getRating();
                                    datasource.saveSub(name, comment, rating);
                                    saved = getListAdapter().hashCode();
                                    String save = "Saved";
                                    Toast.makeText(getApplicationContext(), save, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.cancel();
                            }
                        });

                        final AlertDialog dialog = alert.create();
                        dialog.show();
                        dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                Boolean wantToCloseDialog = false;
                                //Do stuff, possibly set wantToCloseDialog to true then...
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);

                                if(wantToCloseDialog)dialog.dismiss();
                                //else dialog stays open. Make sure you have an obvious way to close the dialog especially if you set cancellable to false.
                            }
                        });



                    }else{
                        Toast.makeText(getApplicationContext(),"Entry Already Saved", Toast.LENGTH_LONG).show();
                    }
                }

                break;
            case R.id.viewSaved:
                Intent viewIntent = new Intent(getApplicationContext(),ViewSavedSubs.class);
                startActivity(viewIntent);
                break;
        }
       datasource.close();
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode== RESULT_OK && intent != null){
            // get bundle
            Bundle extras = intent.getExtras();
            // get bitmap
            bitMap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(bitMap);

        }
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

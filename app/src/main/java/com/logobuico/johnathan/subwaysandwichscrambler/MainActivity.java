package com.logobuico.johnathan.subwaysandwichscrambler;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.provider.MediaStore;
import android.provider.Settings;
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

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.util.List;


public class MainActivity extends ListActivity  {
    //database connection
    private IngredientDataSource datasource;
    //used to check if the sub is saved
    private int saved;
    //used for geting the image from the camera
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Bitmap bitMap;
    ImageView imageView;


    // Main onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializing the facebook SDK
        FacebookSdk.sdkInitialize(getApplicationContext());
        //setting the database connection
        datasource = new IngredientDataSource(this);

        //setting the adapter using to hole the random list of ingredients
        ArrayAdapter<Ingredient> adapter = new ArrayAdapter<Ingredient>(this,
                android.R.layout.simple_list_item_1);
        setListAdapter(adapter);

    }

    //onClick for each button
    public void onClick(View view){
       @SuppressWarnings("unchecked")
       ArrayAdapter<Ingredient> adapter = (ArrayAdapter<Ingredient>) getListAdapter();
        //opening database
       datasource.open();

        //switch for managing the buttons
        switch (view.getId()) {
            //random button
            case R.id.random:
                //setting isSaved to NO
                saved = 0;
                //clearing the previous randomization
                adapter.clear();
                //getting the new random sub
                List<Ingredient> values = datasource.getAllIngredients();
                //inflating the list with the random sub
                adapter = new ArrayAdapter<Ingredient>(this, android.R.layout.simple_list_item_1, values);
                setListAdapter(adapter);
                break;
            //save button
            case R.id.save:
                //making sure that there is a sub to save
                if (getListAdapter().getCount() > 0){
                    //checking to see if the saved has just been saved
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

                        //input for the picture
                        imageView = (ImageView) textEntryView.findViewById(R.id.subPic);
                        //setting the view
                        alert.setView(textEntryView);
                        //creating the picture button
                        alert.setNeutralButton("Picture", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        });
                        //OK button that saves the sub to the database
                        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                //making sure that the Name field isSet
                                if (input1.getText().toString().isEmpty()){
                                    dialog.dismiss();
                                    Toast.makeText(getApplicationContext(),"Invalid Name entry", Toast.LENGTH_LONG).show();
                                    //saving sub
                                }else {
                                    //opening the database
                                    datasource.open();
                                    //setting the fields
                                    String name = input1.getText().toString().trim();
                                    String comment = input2.getText().toString().trim();
                                    Float rating = input3.getRating();
                                    Bitmap image;
                                    Boolean isImage;
                                    //checking to see if an image was taken
                                    if (imageView.getDrawable()!=null) {
                                        image = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                                        isImage = true;
                                    }else{
                                        image = Bitmap.createBitmap(1,1, Bitmap.Config.ARGB_8888);
                                        isImage = false;
                                    }
                                    //passing the info off to to a method that saves them to the database
                                    datasource.saveSub(name, comment, rating, image, isImage);
                                    //recyling the image
                                    image.recycle();
                                    //setting the isSaved to a hash code so that the user doesn't saved the sub again
                                    saved = getListAdapter().hashCode();
                                    //creating a toast to notify that the sub has been saved
                                    String save = "Saved";
                                    Toast.makeText(getApplicationContext(), save, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        //cancel button
                        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.cancel();
                            }
                        });

                        //showing the dialog
                        final AlertDialog dialog = alert.create();
                        dialog.show();
                        //setting the
                        dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                Boolean wantToCloseDialog = false;
                                //Do stuff, possibly set wantToCloseDialog to true then...
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

                                if(wantToCloseDialog)dialog.dismiss();
                                //else dialog stays open. Make sure you have an obvious way to close the dialog especially if you set cancellable to false.
                            }
                        });



                    }else{
                        Toast.makeText(getApplicationContext(),"Entry Already Saved", Toast.LENGTH_LONG).show();
                    }
                }

                break;
            //view saved button
            case R.id.viewSaved:
                Intent viewIntent = new Intent(getApplicationContext(),ViewSavedSubs.class);
                startActivity(viewIntent);
                break;
        }
        //closing the database
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
        // Inflate the menu; this adds items to the action bar if it is present
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

        // Logs 'install' and 'app activate' App Events to facebook.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();

        // Logs 'app deactivate' App Event to facebook.
        AppEventsLogger.deactivateApp(this);
    }
}

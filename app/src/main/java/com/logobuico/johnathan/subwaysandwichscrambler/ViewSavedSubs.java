package com.logobuico.johnathan.subwaysandwichscrambler;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ExpandableListActivity;
import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleExpandableListAdapter;

import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.widget.ShareButton;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class ViewSavedSubs extends Activity {

    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, String> listDataChild;
    private HashMap<String, Bitmap> listImageChild;
    private HashMap<String, Float> listRateHeader;
    private HashMap<String, ShareOpenGraphContent> listShareHeader;

    private IngredientDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_saved_subs);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.Elist);

        // preparing list data
        prepareListData();

        listAdapter = new com.logobuico.johnathan.subwaysandwichscrambler.ExpandableListAdapter(this, listDataHeader, listRateHeader, listDataChild, listImageChild, listShareHeader);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        // ListView button = getListView();
        // button.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //     @Override
        //     public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {

        //           Object sandwich = adapter.getItemAtPosition(position);


        //      }
        //  });

    }

    private void prepareListData() {
        datasource = new IngredientDataSource(this);
        datasource.open();
        ArrayList<ArrayList> values = datasource.getAllSubs();
        datasource.close();

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, String>();
        listRateHeader = new HashMap<String, Float>();
        listImageChild = new HashMap<String, Bitmap>();
        listShareHeader = new HashMap<String, ShareOpenGraphContent>();

        // Adding child data
        for (int i = 0; i < values.size(); i++) {
            listDataHeader.add("Name:  " + values.get(i).get(0).toString() + "\nComment:  " + values.get(i).get(1).toString());
        }
        for (int i = 0; i < listDataHeader.size(); i++) {
            listRateHeader.put(listDataHeader.get(i), Float.parseFloat(values.get(i).get(2).toString())); // Header, Child data
        }

        for (int i = 0; i < listDataHeader.size(); i++) {
            try {
                byte[] imageData = (byte[]) values.get(i).get(3);
                ByteArrayInputStream imageStream = new ByteArrayInputStream(imageData);
                Bitmap theImage = BitmapFactory.decodeStream(imageStream);
                listImageChild.put(listDataHeader.get(i), theImage); // Header, Child data
            } catch (Exception e) {
                Log.i("SavedSub", "No picture with entry");
            }
        }
        for (int i = 0; i < listDataHeader.size(); i++) {

            ShareOpenGraphObject object = new ShareOpenGraphObject.Builder()
                    .putString("og:type", "subscrambler:sandwich")
                    .putString("og:title", values.get(i).get(0).toString())
                    .putString("og:description", values.get(i).get(4).toString())
                    .build();
            ShareOpenGraphAction action = new ShareOpenGraphAction.Builder()
                    .setActionType("subscrambler:eat")
                    .putObject("subscrambler:sandwich", object)
                    .build();
            ShareOpenGraphContent content = new ShareOpenGraphContent.Builder()
                    .setPreviewPropertyName("subscrambler:sandwich")
                    .setAction(action)
                    .build();
            listShareHeader.put(listDataHeader.get(i), content);
        }

        for (int i = 0; i < listDataHeader.size(); i++) {
            listDataChild.put(listDataHeader.get(i), values.get(i).get(4).toString()); // Header, Child data
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_saved_subs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        // if (id == R.id.action_share) {

        //    return true;
        // }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }
}

package com.logobuico.johnathan.subwaysandwichscrambler;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleExpandableListAdapter;

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

    private IngredientDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_saved_subs);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.Elist);

        // preparing list data
        prepareListData();

        listAdapter = new com.logobuico.johnathan.subwaysandwichscrambler.ExpandableListAdapter(this,listDataHeader,listDataChild);

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

        // Adding child data
        for (int i=0;i<values.size();i++){
            listDataHeader.add(values.get(i).get(0).toString() + "\n" +values.get(i).get(1).toString());
        }
        Log.i("SubPopulate",""+listDataHeader.size());
        for (int i=0;i<listDataHeader.size();i++){
            listDataChild.put(listDataHeader.get(i), values.get(i).get(2).toString()); // Header, Child data
            Log.i("SubPopulate",values.get(i).get(2).toString());
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }
}

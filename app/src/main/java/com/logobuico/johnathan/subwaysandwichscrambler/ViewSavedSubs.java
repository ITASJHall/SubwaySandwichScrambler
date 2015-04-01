package com.logobuico.johnathan.subwaysandwichscrambler;

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


public class ViewSavedSubs extends ExpandableListActivity {
    private static final String PARENT_KEY = "pKey";
    private static final String CHILD_KEY = "cKey";

    private ExpandableListAdapter mAdapter;

    private IngredientDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_saved_subs);

        datasource = new IngredientDataSource(this);
        datasource.open();
        List<Sandwich> values = datasource.getAllSubs();
      ///  Log.d("SubList", values.toString());
       // ArrayAdapter<Sandwich> adapter = new ArrayAdapter<Sandwich>(this, android.R.layout.simple_expandable_list_item_1,values);
        //setListAdapter(adapter);
        datasource.close();

        ExpandableListView elv = (ExpandableListView) findViewById(android.R.id.list);

        List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
        List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();

        Map<String, String> curGroupMap = new HashMap<String, String>();
        groupData.add(curGroupMap);
        curGroupMap.put(PARENT_KEY, "Hello");
        curGroupMap.put(CHILD_KEY, "First Order System Response");

        List<Map<String, String>> children = new ArrayList<Map<String, String>>();

        Map<String, String> curChildMap = new HashMap<String, String>();
        children.add(curChildMap);
        for (int i=0; i<values.size();i++) {
            curChildMap.put(CHILD_KEY, values.get(i).toString());
        }
       // curChildMap.put(CHILD_KEY, "Second Order System");

        childData.add(children);

        // Set up our adapter
        mAdapter = new SimpleExpandableListAdapter(this, groupData,
                android.R.layout.simple_expandable_list_item_1, new String[] {
                PARENT_KEY, CHILD_KEY }, new int[] {
                android.R.id.text1, android.R.id.text2 }, childData,
                android.R.layout.simple_expandable_list_item_2, new String[] {
                PARENT_KEY, CHILD_KEY }, new int[] {
                android.R.id.text1, android.R.id.text2 });

        elv.setAdapter(mAdapter);

       // ListView button = getListView();
       // button.setOnItemClickListener(new AdapterView.OnItemClickListener() {
       //     @Override
       //     public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {

         //           Object sandwich = adapter.getItemAtPosition(position);


      //      }
      //  });

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

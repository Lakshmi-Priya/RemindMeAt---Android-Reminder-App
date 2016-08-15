package com.asu.remind.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.asu.remind.R;
import com.asu.remind.adapter.ListingsAdapter;
import com.asu.remind.model.ListingsModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ListingsModel> list;
    private RecyclerView rvListings;
    private ListingsAdapter adapter;
    private ListingsModel model;
    private LinearLayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_listing);

        rvListings = (RecyclerView) findViewById(R.id.events);

        list = new ArrayList<ListingsModel>();

        model = new ListingsModel();
        model.setEvent("Just a test, mate. Just a simple test of RecyclerView. See how this looks. Green and green");
        model.setTime("13.23");
        model.setDate("15-8-2016");

        list.add(model);

        model = new ListingsModel();
        model.setEvent("Just ne more test. this is second test. yes, a second test to test the testing of the test. Now I don't knwo what am I writing.");
        model.setTime("13.27");
        model.setDate("15-8-2016");

        list.add(model);

        model = new ListingsModel();
        model.setEvent("yest one more test. ha ha ha ha. now don't laugh too much. this is not anything to laugh at.");
        model.setTime("13.29");
        model.setDate("15-8-2016");

        list.add(model);


        model = new ListingsModel();
        model.setEvent("this just no duplicate data. u got it ?");
        model.setTime("13.30");
        model.setDate("15-8-2016");

        list.add(model);

        adapter = new ListingsAdapter(list);
        rvListings.setAdapter(adapter);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        rvListings.setLayoutManager(mLayoutManager);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.add) {
            startActivity(new Intent(getBaseContext(), AddEvent.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

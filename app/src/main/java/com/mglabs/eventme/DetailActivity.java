package com.mglabs.eventme;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mglabs.eventme.model.Event;
import com.mglabs.eventme.model.EventStore;

import static com.mglabs.eventme.EventAdapter.EVENT_ID_EXTRA;
import static com.mglabs.eventme.EventAdapter.EVENT_IMAGE_EXTRA;

public class DetailActivity extends AppCompatActivity {

    int mEventID;
    TextView mTitleDetail;
    TextView mDescrDetail;
    AppBarLayout mAppBar;

    //SharedPreferences variables
    private String sharedPrefFile = "com.mglabs.eventme.mysharedpreferences";
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        mAppBar = findViewById(R.id.app_bar);

        mDescrDetail = findViewById(R.id.info_detail);
        mTitleDetail = findViewById(R.id.title_detail);

        // Retrieve ID of the single event from Intent
        EventStore store = new EventStore(this);
        Intent intentDetail = getIntent();
        mEventID = intentDetail.getIntExtra(EVENT_ID_EXTRA, 0);

        //Retrieve the event from the store
        Event event = store.getEventByID(mEventID);

        if (event == null) {
            mTitleDetail.setText(R.string.event_not_found);
            return;
        }

        // Display the rest of event's info
        mTitleDetail.setText(event.getTitle());
        mDescrDetail.setText(event.getInfo());

        mAppBar.setBackgroundResource(intentDetail.getIntExtra(EVENT_IMAGE_EXTRA, 0));

        //region Shared Preferences
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        // FAB TO ADD PREFS
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab.setSelected(true);
                addToPrefs(view);
            }
        });
        //endregion

    }


    public void addToPrefs(View view) {
        //get an Editor
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(EVENT_ID_EXTRA, mEventID);
        preferencesEditor.apply();

        int ID = mPreferences.getInt(EVENT_ID_EXTRA, 0);
        Toast.makeText(this, String.valueOf(ID), Toast.LENGTH_LONG).show();
    }
}

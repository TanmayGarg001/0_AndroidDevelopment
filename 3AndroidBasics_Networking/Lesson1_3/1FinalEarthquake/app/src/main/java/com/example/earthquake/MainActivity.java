package com.example.earthquake;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    private EarthQuakeAdapter mAdapter;
    //https://www.youtube.com/watch?v=qk5F6Bxqhr4&list=PLWz5rJ2EKKc9CBxr3BVjPTPoDPLdPIFCE&index=2
    //JSON = JavaScript Object Notation [parsing is done in order to make a java object from simple text in json similar to Integer.parseInt(String s)]
    //JSON is an open standard file format and data interchange format that uses human-readable text to store and transmit data objects consisting of
    //attribute–value pairs and arrays. It is a common data format with diverse uses in electronic data interchange, including that of web applications with servers.
    //https://classroom.udacity.com/courses/ud843/lessons/1335cf7d-bb4f-48c6-8503-f14b127d2abc/concepts/1c68bec9-f1f7-4654-9d6b-c29332ded407

    //API is the acronym for Application Programming  Interface, which is a software intermediary that allows two applications to talk to each other.
    //Each time you use an app like Facebook, send an instant message, or check the weather on your phone, you're using an API.
    //In android studio parsing is done using JSONObject class.It uses format similar to tree structure.Example:-
    //https://classroom.udacity.com/courses/ud843/lessons/1335cf7d-bb4f-48c6-8503-f14b127d2abc/concepts/85a11c0c-3acd-42e7-b49b-8623d17686b9
    //So in layman words, it's the app way of saying that if you want to build something new using some of our data/functionality we can work it together.
    //It's like a programming partnership.

    //Link for USGS [United States Geological Survey website: https://earthquake.usgs.gov/fdsnws/event/1/
    //Link for csv files : https://earthquake.usgs.gov/earthquakes/feed/v1.0/csv.php
    //Here the time is represented in UNIX timestamp which is defined as number of seconds after the date Jan1 1970.
    //Try doing stuff with Json URL like sort or set min parameters etc.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.earthquake_list);

        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new EarthQuakeAdapter(this, 0, new ArrayList<>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected earthquake.
        earthquakeListView.setOnItemClickListener((parent, view, position, id) -> {
            EarthQuake currentEarthquake = mAdapter.getItem(position);
            // Convert the String URL into a URI object (to pass into the Intent constructor)
            Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());
            // Create a new intent to view the earthquake URI
            Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
            // Send the intent to launch a new activity
            startActivity(websiteIntent);
        });

        EarthquakeAsyncTask earthquakeAsyncTask = new EarthquakeAsyncTask();
        earthquakeAsyncTask.execute();
    }

    @SuppressLint("StaticFieldLeak")
    private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<EarthQuake>> {

        /**
         * This method runs on a background thread and performs the network request.
         * We should not update the UI from a background thread, so we return a list of
         * {@link EarthQuake}s as the result.
         */
        @Override
        protected List<EarthQuake> doInBackground(String... urls) {
            // Don't perform the request if there are no URLs, or the first URL is null.
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            return QueryUtils.fetchEarthquakeData(urls[0]);
        }

        /**
         * This method runs on the main UI thread after the background work has been
         * completed. This method receives as input, the return value from the doInBackground()
         * method. First we clear out the adapter, to get rid of earthquake data from a previous
         * query to USGS. Then we update the adapter with the new list of earthquakes,
         * which will trigger the ListView to re-populate its list items.
         */
        @Override
        protected void onPostExecute(List<EarthQuake> data) {
            // Clear the adapter of previous earthquake data
            mAdapter.clear();

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            // data set. This will trigger the ListView to update.
            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
    }
}
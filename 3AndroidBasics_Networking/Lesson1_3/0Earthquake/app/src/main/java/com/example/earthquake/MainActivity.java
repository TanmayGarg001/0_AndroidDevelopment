package com.example.earthquake;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
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
        ArrayList<EarthQuake> earthquakes = QueryUtils.extractEarthquakes();

        ListView listView = findViewById(R.id.earthquake_list);
        EarthQuakeAdapter arrayAdapter = new EarthQuakeAdapter(this, R.layout.list_view, earthquakes);
        listView.setAdapter(arrayAdapter);
    }

}
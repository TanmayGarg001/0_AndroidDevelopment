package com.tanmaygg2001.layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //using linear layout in constraint layout coz that's what you do in general results in stacking of items side by side .
    //We can keep adding more and more linear layouts horizontal or vertical depending upon our requirement.
    //Linear layout weight assigns items how much percentage they will be given inside parent
    //relative layout is kinda useless
    //linear is somewhat used in legacy apps
    //But you should always use constraint layouts


    //Activity and LifeCycle of Android
    //Activity is one screen.If you have 3 activities it means you have to design 3 screen app.
    //ACTIVITY is NEW SCREEN sometimes it might look to you that you clicked something and you are in new activity but that's not true its the same activity but just some
    //advanced tinkering.
    //Activity is like lets say gallery app redirects you to whats-app if you want to send some pic or vid to someone. The screen changes so boom... new activity!

    //Android manifest.xml = deals with internet access,storage etc. permissions from the user.Describes essential info about your app to the
    //android build tools, Android OS and google play.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //*****ANDROID ACTIVITY LIFE CYCLE*****
    //to navigate the transitions bw stages of the activity lifecycle, Activity class provides a core of six callbacks.
    //when activity is launched onCreate() method is launched inside which we call setContentView(R.layout.activity_main); which
    //tells the app to display the xml file to the UI.
    //after that onStart() function when the activity is about to come in front of the user this is called.
    //and just after that onResume() is called which deals with most of the things that we implement and core functionality of app is inside this.

    //If another activity is called the previous activity gets paused and onPause() method is ran. Sow we implement that stuff in this which
    //we want to run when our app looses focus (on pause just pauses the app it can update UI and run in the background)
    //If activity is no longer visible it stops and onStop() method is ran.
    // Example:- if we open a another app and our app might be killed by android OS depending on the priority to free the resources and it again starts running from onCreate().
    // or call onRestart() method which evokes onStart() after user comes back to our app.

    //If we remove the app from the background by ourselves then onDestroy() function is ran which shut downs the app.
    //It is not necessary to implement all these call backs in your app but you should have somewhat idea of how android activity life cycle works.


}
package com.tanmaygg2001.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //View group objects have view groups or views inside it. Example:- linear layout,relative layout,constraint layout.
    //VG=ViewGroup,V=Views
    //Inside VG we can have more VGs and Vs.
    //And inside Vgs we can have more Vs.
    //Example : packet ke andar chips daal skte hai but chips ke andar packets nahi.

    //This whole hierarchy can be avoided by using constraint layout!


    public void sendNow(View view) {
        //Make toast to serve the user i.e. to  do give data or some o/p to user when the button is clicked
        Toast.makeText(this, "Sending message ... ", Toast.LENGTH_SHORT).show();
        //makeText shows some stuff in the app when button is clicked
        //We can also do stuff like if button is pressed maneuver the user to some other activity(another screen for example) or to execute some code or algorithm.
    }

    public void receiveNow(View view) {
        Toast.makeText(this, "Incoming message ... ", Toast.LENGTH_SHORT).show();
    }

    public void deleteMessage(View view) {
        Toast.makeText(this, "Message deleted !", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //basic java code
    }

}
package com.example.languide;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {//https://developer.android.com/guide/components/activities/activity-lifecycle

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView numbers = findViewById(R.id.numbers);
        numbers.setOnClickListener(v -> {
            Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);//explicit Intent
            startActivity(numbersIntent);
//            Toast.makeText(MainActivity.this,"Numbers Activity",Toast.LENGTH_SHORT).show();
        });
        TextView familyMembers = findViewById(R.id.family_members);
        familyMembers.setOnClickListener(v -> {
            Intent familyMembersIntent = new Intent(MainActivity.this, FamilyMembersActivity.class);
            startActivity(familyMembersIntent);
        });
        TextView colors = findViewById(R.id.colors);
        colors.setOnClickListener(v -> {
            Intent colorsIntent = new Intent(MainActivity.this, ColorsActivity.class);
            startActivity(colorsIntent);
        });
        TextView phrases = findViewById(R.id.phrases);
        phrases.setOnClickListener(v -> {
            Intent phrasesIntent = new Intent(MainActivity.this, PhrasesActivity.class);
            startActivity(phrasesIntent);
        });

    }

}
package com.example.android.courtcounter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private int scoreA = 0;
    private int scoreB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void teamA3(View view) {
        scoreA = scoreA + 3;
        displayTeamAScore(scoreA);
    }

    public void teamB3(View view) {
        scoreB = scoreB + 3;
        displayTeamBScore(scoreB);
    }

    public void teamA2(View view) {
        scoreA = scoreA + 2;
        displayTeamAScore(scoreA);
    }

    public void teamB2(View view) {
        scoreB = scoreB + 2;
        displayTeamBScore(scoreB);
    }

    public void teamA1(View view) {
        scoreA = scoreA + 1;
        displayTeamAScore(scoreA);
    }

    public void teamB1(View view) {
        scoreB = scoreB + 1;
        displayTeamBScore(scoreB);
    }

    public void reset(View view) {
        scoreA = 0;
        scoreB = 0;
        displayTeamAScore(scoreA);
        displayTeamBScore(scoreB);
    }

    @SuppressLint("SetTextI18n")
    private void displayTeamAScore(int number) {
        TextView teamA = findViewById(R.id.teamAScore);
        teamA.setText(Integer.toString(number));
    }

    @SuppressLint("SetTextI18n")
    private void displayTeamBScore(int number) {
        TextView teamB = findViewById(R.id.teamBScore);
        teamB.setText(Integer.toString(number));
    }
}
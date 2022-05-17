package com.example.musicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static int time = 0;

    //State diagram of MediaPlayer:
    //https://developer.android.com/reference/android/media/MediaPlayer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.song);
        Button play = findViewById(R.id.playButton);
        play.setOnClickListener(v -> mediaPlayer.start());

        Button pause = findViewById(R.id.pauseButton);
        pause.setOnClickListener(v -> mediaPlayer.pause());

        Button forward = findViewById(R.id.forwardButton);
        forward.setOnClickListener(v -> {
            time = time + 5000;
            mediaPlayer.seekTo(time);
        });
        mediaPlayer.setOnCompletionListener(mp -> Toast.makeText(MainActivity.this, "I'm done!", Toast.LENGTH_SHORT).show());
        mediaPlayer.release();
    }



}
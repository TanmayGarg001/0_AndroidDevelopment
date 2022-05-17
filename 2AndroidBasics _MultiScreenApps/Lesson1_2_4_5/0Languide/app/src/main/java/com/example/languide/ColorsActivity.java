package com.example.languide;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private final MediaPlayer.OnCompletionListener onCompletionListener = mp -> releaseMediaPlayer();
    //Audio Focus is important, because it focuses on audio playbacks that are more important than the others for example whilst playing music in spotify
    //an important notification pops up or calls come then we have to pause the song and play the notification's/call-tone audio and when that is completed we play the song.
    //https://developer.android.com/reference/android/media/AudioManager#requestAudioFocus(android.media.AudioManager.OnAudioFocusChangeListener,%20int,%20int)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        ArrayList<Word> colorsList = new ArrayList<>();
        colorsList.add(new Word("लाल", "Red", R.drawable.color_red, R.raw.color_red));
        colorsList.add(new Word("हरा", "Green", R.drawable.color_green, R.raw.color_green));
        colorsList.add(new Word("भूरा", "Brown", R.drawable.color_brown, R.raw.color_brown));
        colorsList.add(new Word("काला", "Black", R.drawable.color_black, R.raw.color_black));
        colorsList.add(new Word("धूसर", "Gray", R.drawable.color_gray, R.raw.color_gray));
        colorsList.add(new Word("सफेद", "White", R.drawable.color_white, R.raw.color_white));
        colorsList.add(new Word("धूल भरा पीला", "Dusty yellow", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        colorsList.add(new Word("सरसों पीली", "Mustard yellow", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        WordAdapter colorAdapter = new WordAdapter(this, colorsList);
        ListView listView = findViewById(R.id.colorsActivityXML);
        listView.setAdapter(colorAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Word word = colorsList.get(position);
            Log.v("ColorsActivity", "Status : " + word);
            mediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getAudioId());
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(onCompletionListener);
        });
    }

    @Override
    protected void onStop() {//if activity is stopped or user moves to other whilst sound is playing, this will release the resources.
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

}
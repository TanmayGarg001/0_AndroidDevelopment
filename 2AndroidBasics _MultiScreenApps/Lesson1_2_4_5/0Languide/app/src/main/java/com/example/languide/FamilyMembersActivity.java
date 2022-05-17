package com.example.languide;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class FamilyMembersActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private final MediaPlayer.OnCompletionListener onCompletionListener = mp -> releaseMediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_members);

        ArrayList<Word> familyList = new ArrayList<>();
        familyList.add(new Word("मां", "Mother", R.drawable.family_mother, R.raw.family_mother));
        familyList.add(new Word("पिता", "Father", R.drawable.family_father, R.raw.family_father));
        familyList.add(new Word("दादी मां", "Grandmother", R.drawable.family_grandmother, R.raw.family_grandmother));
        familyList.add(new Word("दादा", "Grandfather", R.drawable.family_grandfather, R.raw.family_grandfather));
        familyList.add(new Word("बेटी", "Daughter", R.drawable.family_daughter, R.raw.family_daughter));
        familyList.add(new Word("बेटा", "Son", R.drawable.family_son, R.raw.family_son));
        familyList.add(new Word("छोटी बहन", "Younger sister", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        familyList.add(new Word("छोटा भाई", "Younger brother", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        familyList.add(new Word("बड़ी बहन", "Older sister", R.drawable.family_older_sister, R.raw.family_older_sister));
        familyList.add(new Word("बड़े भाई", "Older brother", R.drawable.family_older_brother, R.raw.family_older_brother));

        WordAdapter familyAdapter = new WordAdapter(this, familyList);
        ListView listView = findViewById(R.id.familyMembersActivityXML);
        listView.setAdapter(familyAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Word word = familyList.get(position);
            Log.v("FamilyMembersActivity", "Status : " + word);
            mediaPlayer = MediaPlayer.create(FamilyMembersActivity.this, word.getAudioId());
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
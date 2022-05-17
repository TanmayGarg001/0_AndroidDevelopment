package com.example.languide;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private final MediaPlayer.OnCompletionListener onCompletionListener = mp -> releaseMediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);
        ArrayList<Word> phrasesList = new ArrayList<>();
        phrasesList.add(new Word("मैं अभी आ रहा हूँ", "Just coming", R.raw.phrase_are_you_coming));
        phrasesList.add(new Word("बहुत अच्छा", "Very well", R.raw.phrase_come_here));
        phrasesList.add(new Word("अच्छी बात है", "Very good", R.raw.phrase_how_are_you_feeling));
        phrasesList.add(new Word("जैसी आपकी मर्जी", "As you please", R.raw.phrase_im_coming));
        phrasesList.add(new Word("और कुछ ?", "Anything else ?", R.raw.phrase_im_feeling_good));
        phrasesList.add(new Word("बस, रहने दो !", "That’s enough !", R.raw.phrase_lets_go));
        phrasesList.add(new Word("इस सम्मान के लिए धन्यबाद", "Thanks for this honour", R.raw.phrase_my_name_is));
        phrasesList.add(new Word("अच्छा", "O.K", R.raw.phrase_what_is_your_name));
        phrasesList.add(new Word("क्यों नहीं ?", "Why not", R.raw.phrase_where_are_you_going));
        phrasesList.add(new Word("थोडा-सा भी नहीं", "Not a bit", R.raw.phrase_yes_im_coming));
        phrasesList.add(new Word("ध्यान रखना", "Take care", R.raw.phrase_are_you_coming));

        WordAdapter phrasesAdapter = new WordAdapter(this, phrasesList);
        ListView listView = findViewById(R.id.phrasesActivityXML);
        listView.setAdapter(phrasesAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Word word = phrasesList.get(position);
            Log.v("PhrasesActivity", "Status : " + word);
            mediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getAudioId());
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
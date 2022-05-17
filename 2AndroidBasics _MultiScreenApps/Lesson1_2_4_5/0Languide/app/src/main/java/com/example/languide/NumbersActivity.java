package com.example.languide;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private final MediaPlayer.OnCompletionListener onCompletionListener = mp -> releaseMediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        ArrayList<Word> wordArrayList = new ArrayList<>();
        wordArrayList.add(new Word("एक(१)", "One (1)", R.drawable.number_one, R.raw.number_one));
        wordArrayList.add(new Word("दो(२)", "Two (2)", R.drawable.number_two, R.raw.number_two));
        wordArrayList.add(new Word("तीन(३)", "Three (3)", R.drawable.number_three, R.raw.number_three));
        wordArrayList.add(new Word("चार(४)", "Four (4)", R.drawable.number_four, R.raw.number_four));
        wordArrayList.add(new Word("पाँच(५)", "Five (5)", R.drawable.number_five, R.raw.number_five));
        wordArrayList.add(new Word("छह(६)", "Six (6)", R.drawable.number_six, R.raw.number_six));
        wordArrayList.add(new Word("सात(७)", "Seven (7)", R.drawable.number_seven, R.raw.number_seven));
        wordArrayList.add(new Word("आठ(८)", "Eight (8)", R.drawable.number_eight, R.raw.number_eight));
        wordArrayList.add(new Word("नौ(९)", "Nine (9)", R.drawable.number_nine, R.raw.number_nine));
        wordArrayList.add(new Word("दस(१०)", "Ten (10)", R.drawable.number_ten, R.raw.number_ten));
        // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,numbersList);
        // The adapter knows how to create layouts for each item in the list, using the simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single TextView, which the adapter will set to display a single word.
        //Adapters are very powerful as they adapt to change in layout
        //In order to implement our own layout style we need to override the getView method from ArrayAdapter, we can do this by creating our own class like ArrayAdapter.
        WordAdapter wordAdapter = new WordAdapter(this, wordArrayList);
        ListView listView = findViewById(R.id.numbersActivityXML);
        listView.setAdapter(wordAdapter);
        //We can make custom layouts for this but then we have to make new xml file, a new java class that implements the ArrayAdapter etc.
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Word word = wordAdapter.getItem(position);
            Log.v("NumbersActivity", "Status : " + word);
            mediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioId());
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
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }

}

//Alternate way of manually adding list items
//        LinearLayout root_view = findViewById(R.id.root_view_numbers);
//
//        for (int i = 0; i < listOfWords.size(); i++) {
//            TextView textView = new TextView(this, null, 0, R.style.list_item);//android framework provides layouts for this
//            textView.setText(listOfWords.get(i));
//            root_view.addView(textView);
//        }
//
package com.example.languide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {
    public WordAdapter(@NonNull Context context, @NonNull List<Word> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //check if an existing view is being reused, else inflate the view
        Word currentWord = getItem(position);//gets the data item associated with the specified position in the data set.
        View listView = convertView;//we make view which is same as convertView
        //at start of activity View is null so,
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);//layout inflater inflates(fills) the view (XML)
        }
        ImageView imageView = listView.findViewById(R.id.list_item_visualImage);
        if (currentWord.hasImage()) {
            imageView.setImageResource(currentWord.getResourceId());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }

        TextView hindi = listView.findViewById(R.id.list_item_hindiTranslation);
        hindi.setText(currentWord.getHindiTranslation());
        TextView english = listView.findViewById(R.id.list_item_engTranslation);
        english.setText(currentWord.getEngTranslation());
        return listView;//this is added as a child to adapter
    }
}

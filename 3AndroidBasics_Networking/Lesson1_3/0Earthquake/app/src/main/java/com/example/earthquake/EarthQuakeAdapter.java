package com.example.earthquake;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthQuakeAdapter extends ArrayAdapter<EarthQuake> {
    private static final String LOCATION_SEPARATOR = " of ";

    public EarthQuakeAdapter(@NonNull Context context, int resource, @NonNull List<EarthQuake> objects) {
        super(context, resource, objects);
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView = convertView;
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_view, parent, false);
        }
        EarthQuake earthQuake = getItem(position);

        TextView magScale = listView.findViewById(R.id.magScale);
        String magToDisplay = formatMagnitude(earthQuake.getMagnitude());
        magScale.setText(magToDisplay);

        GradientDrawable magnitudeCircle = (GradientDrawable) magScale.getBackground();
        int magnitudeColor = getMagColor(earthQuake.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        String originalLocation = earthQuake.getLocation();
        String locationOffset;
        String primaryLocation;
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        TextView primaryLocationView = listView.findViewById(R.id.primaryLocation);
        primaryLocationView.setText(primaryLocation);

        TextView locationOffsetView = listView.findViewById(R.id.locationOffset);
        locationOffsetView.setText(locationOffset);


        Date date = new Date(earthQuake.getTimeInMillis());
        TextView dateOfEq = listView.findViewById(R.id.dateOfEarthquake);
        String formattedDate = formatDate(date);
        dateOfEq.setText(formattedDate);

        TextView timeView = listView.findViewById(R.id.timeOfEarthquake);
        String formattedTime = formatTime(date);
        timeView.setText(formattedTime);

        return listView;
    }

    private String formatMagnitude(double magnitude) {
        @SuppressLint("SimpleDateFormat") DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return decimalFormat.format(magnitude);
    }

    private String formatDate(Date dateObject) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private int getMagColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

}

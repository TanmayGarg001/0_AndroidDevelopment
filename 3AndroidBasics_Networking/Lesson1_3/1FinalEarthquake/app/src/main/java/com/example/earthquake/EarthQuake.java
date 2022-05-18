package com.example.earthquake;

public class EarthQuake {

    private final double magnitude;
    private final String location;
    private final long timeInMillis;
    private final String url;

    public EarthQuake(double magnitude, String location, long timeInMillis, String url) {
        this.location = location;
        this.magnitude = magnitude;
        this.timeInMillis = timeInMillis;
        this.url = url;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public long getTimeInMillis() {
        return timeInMillis;
    }

    public String getUrl() {
        return url;
    }


}

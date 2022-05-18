package com.example.earthquake;

public class EarthQuake {

    private final double magnitude;
    private final String location;
    private final long timeInMillis;

    public EarthQuake(double magnitude, String location, long timeInMillis) {
        this.location = location;
        this.magnitude = magnitude;
        this.timeInMillis = timeInMillis;
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

}

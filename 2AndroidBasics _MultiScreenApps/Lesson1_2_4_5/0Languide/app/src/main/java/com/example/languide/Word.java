package com.example.languide;

import androidx.annotation.NonNull;

public class Word {

    private String hindiTranslation;
    private String engTranslation;
    private int resourceId = NO_IMAGE;
    private int audioId;
    private static final int NO_IMAGE = -1;

    public Word(String hindiTranslation, String engTranslation, int resourceId, int audioId) {
        this.hindiTranslation = hindiTranslation;
        this.engTranslation = engTranslation;
        this.resourceId = resourceId;
        this.audioId = audioId;
    }

    public Word(String hindiTranslation, String engTranslation, int audioId) {
        this.hindiTranslation = hindiTranslation;
        this.engTranslation = engTranslation;
        this.audioId = audioId;
    }


    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getAudioId() {
        return audioId;
    }

    public void setAudioId(int audioId) {
        this.audioId = audioId;
    }

    public String getEngTranslation() {
        return engTranslation;
    }

    public String getHindiTranslation() {
        return hindiTranslation;
    }

    public String setEngTranslation(String engTranslation) {
        return this.engTranslation = engTranslation;
    }

    public String setHindiTranslation(String hindiTranslation) {
        return this.hindiTranslation = hindiTranslation;
    }

    public boolean hasImage() {
        return resourceId != NO_IMAGE;
    }

    @NonNull
    @Override
    public String toString() {
        return "Word{" +
                "hindiTranslation='" + hindiTranslation + '\'' +
                ", engTranslation='" + engTranslation + '\'' +
                ", resourceId=" + resourceId +
                ", audioId=" + audioId +
                '}';
    }
}

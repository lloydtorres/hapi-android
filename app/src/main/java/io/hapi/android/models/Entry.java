package io.hapi.android.models;

import java.util.Date;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Entry extends RealmObject {
    private String imageUri;
    private Date date;
    private RealmList<Question> questions;
    private Emotions emotion;

    public Entry(String imageUri, Date date, RealmList<Question> questions, Emotions emotion) {
        this.imageUri = imageUri;
        this.date = date;
        this.questions = questions;
        this.emotion = emotion;
    }
    public Entry(){

    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public RealmList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(RealmList<Question> questions) {
        this.questions = questions;
    }

    public Emotions getEmotion() {
        return emotion;
    }

    public void setEmotion(Emotions emotion) {
        this.emotion = emotion;
    }
}



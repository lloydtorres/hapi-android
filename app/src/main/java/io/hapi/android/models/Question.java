package io.hapi.android.models;

import java.util.Date;

import io.realm.RealmObject;

public class Question extends RealmObject{
    private  String question;
    private  boolean isBinaryResponse;
    private String response;
    private boolean binaryResponse;
    private Date dateTaken;

    public Question(){

    }

    public Date getDateTaken() {
        return dateTaken;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setIsBinaryResponse(boolean isBinaryResponse) {
        this.isBinaryResponse = isBinaryResponse;
    }

    public void setDateTaken(Date dateTaken) {
        this.dateTaken = dateTaken;
    }

    public Question(String question, boolean isBinaryResponse) {
        this.question = question;
        this.isBinaryResponse = isBinaryResponse;
    }

    public void setResponse(String mResponse) {
        this.response = mResponse;
    }

    public void setBinaryResponse(boolean mBinaryResponse) {
        this.binaryResponse = mBinaryResponse;
    }

    public String getQuestion() {
        return question;
    }

    public boolean isBinaryResponse() {
        return isBinaryResponse;
    }

    public String getResponse() {
        return response;
    }

    public boolean getBinaryResponse() {
        return binaryResponse;
    }

}
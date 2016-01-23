package io.hapi.android.models;

import java.util.Date;

public class Question {
    private final String mQuestion;
    private final boolean mIsBinaryResponse;
    private String mResponse;
    private boolean mBinaryResponse;
    private Date mDateTaken;

    public Date getDateTaken() {
        return mDateTaken;
    }

    public void setDateTaken(Date dateTaken) {
        this.mDateTaken = dateTaken;
    }

    public Question(String question, boolean isBinaryResponse) {
        mQuestion = question;
        mIsBinaryResponse = isBinaryResponse;
    }

    public void setResponse(String mResponse) {
        this.mResponse = mResponse;
    }

    public void setBinaryResponse(boolean mBinaryResponse) {
        this.mBinaryResponse = mBinaryResponse;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public boolean isBinaryResponse() {
        return mIsBinaryResponse;
    }

    public String getResponse() {
        return mResponse;
    }

    public boolean getBinaryResponse() {
        return mBinaryResponse;
    }
}
package com.teamtreehouse.pomodoro.model;

public class Attempt {
    private String mMessage;
    private int mRemainingSeconds;
    private AttemptKind mKind;

    //  kind is in uppercase
    public Attempt(AttemptKind kind, String message) {
        mKind = kind;
        mMessage = message; // user input
        mRemainingSeconds = kind.getTotalSeconds();
    }

    public AttemptKind getKind() {
        return mKind;
    }

    public String getMessage() {
        return mMessage;
    }

    public int getRemainingSeconds() {
        return mRemainingSeconds;
    }

    public void setMessage(String message) {
        mMessage = message;
    }
}

package com.teamtreehouse.pomodoro.model;

public enum AttemptKind {
    FOCUS(25 * 60, "Focus time"), // caller
    BREAK(5 * 60, "Break time");

    private int mTotalSeconds; // field
    private String mDisplayName;

    AttemptKind(int totalSeconds, String displayName) { // setter
        mTotalSeconds = totalSeconds;
        mDisplayName = displayName;
    }

    public int getTotalSeconds() {
        return mTotalSeconds;
    } // getter

    public String getDisplayName() {
        return mDisplayName;
    }
}

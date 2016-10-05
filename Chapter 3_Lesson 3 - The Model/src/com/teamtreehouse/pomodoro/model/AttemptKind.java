package com.teamtreehouse.pomodoro.model;

public enum AttemptKind {
//    calls the constructor
//    caps on lets people know they are constants
    FOCUS(25 * 60),
    BREAK(5 * 60);
//    if it was BREAK or FOCUS without arguments
//  they would call the default argumentless constructor

    private int mTotalSeconds;

    AttemptKind(int totalSeconds) {
        mTotalSeconds = totalSeconds;
    }

    public int getTotalSeconds() {
        return mTotalSeconds;
    }
}

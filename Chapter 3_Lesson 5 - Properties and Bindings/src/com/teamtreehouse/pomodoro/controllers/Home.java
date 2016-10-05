package com.teamtreehouse.pomodoro.controllers;

import com.teamtreehouse.pomodoro.model.Attempt;
import com.teamtreehouse.pomodoro.model.AttemptKind;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Home {
    @FXML
    private VBox container;

    @FXML
    private Label title;

    private Attempt mCurrentAttempt;
    private StringProperty mTimerText;
    // StringProperty is abstract

    // creates timer text and sets the default timer
    public Home() {
        // SimpleStringProperty extends StringProperty
        mTimerText = new SimpleStringProperty();
        setTimerText(0);
    }

    // getter (for javafx file)
    public String getTimerText() {
        return mTimerText.get();
    }

    // the new part special to javafx
    public StringProperty timerTextProperty() {
        return mTimerText;
    }

    // setter: sets the timer through ref var
    public void setTimerText(String timerText) {
        mTimerText.set(timerText);
    }
//    *javafx has getter, setter and a special javafx property object.
//    if we didn't pass integer to the setTimerText method
//    above 4 methods would be standard to reference
//    label in fxml with time id.
//    by using binding method we don't use the id name of "time" on fxml*

    // *overrides the setTimerText method*
    public void setTimerText(int remainingSeconds) {
        int minutes = remainingSeconds / 60;
        int seconds = remainingSeconds % 60;
        setTimerText(String.format("%02d:%02d", minutes, seconds));
    }

    // initialized with user input
    private void prepareAttempt(AttemptKind kind) {
        clearAttemptStyles();
        mCurrentAttempt = new Attempt(kind, "");
        addAttemptStyle(kind);
        title.setText(kind.getDisplayName());
        setTimerText(mCurrentAttempt.getRemainingSeconds());
    }

    private void addAttemptStyle(AttemptKind kind) {
        container.getStyleClass().add(kind.toString().toLowerCase());
    }

    private void clearAttemptStyles() {
        for (AttemptKind kind : AttemptKind.values()) {
            container.getStyleClass().remove(kind.toString().toLowerCase());
        }
    }

    public void DEBUG(ActionEvent actionEvent) {
        System.out.println("HI MOM");
    }
}

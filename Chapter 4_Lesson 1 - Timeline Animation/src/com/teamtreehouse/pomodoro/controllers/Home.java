package com.teamtreehouse.pomodoro.controllers;

import com.teamtreehouse.pomodoro.model.Attempt;
import com.teamtreehouse.pomodoro.model.AttemptKind;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class Home {
    @FXML
    private VBox container;

    @FXML
    private Label title;

    private Attempt mCurrentAttempt;
    private StringProperty mTimerText;
    private Timeline mTimeline;
//    timeline extends animation

    public Home() {
//      constructor otomatik olarak çağırılıyor fxml'in controllerına eklenince (?)
        mTimerText = new SimpleStringProperty(); // label name gibi düşünülebilir
        setTimerText(0); // initial value için
    }

    public String getTimerText() {
        return mTimerText.get();
    }

    public StringProperty timerTextProperty() {
        return mTimerText;
    }

    public void setTimerText(String timerText) {
        mTimerText.set(timerText);
    }

    public void setTimerText(int remainingSeconds) {
        int minutes = remainingSeconds / 60;
        int seconds = remainingSeconds % 60;
        setTimerText(String.format("%02d:%02d", minutes, seconds));
//        *fxml binding yerine 1. metod kullanılsaydı:
//        yukarıda,
//        "@FXML private Label time"
//        olarak tanımlandıktan sonra bu kısımda,
//        "time.setText(timerText);"
//        denecekti (ve aradaki gereksiz metodlar vs atılacaktı)*
    }

    private void prepareAttempt(AttemptKind kind) {
        clearAttemptStyles();
        mCurrentAttempt = new Attempt(kind, "");
        addAttemptStyle(kind);
        title.setText(kind.getDisplayName());
        // zamanın geri sayımda başlangıçta 25 olarak gözükmesi için:
        setTimerText(mCurrentAttempt.getRemainingSeconds());
        // TODO:csd This is creating multiple timelines we need to fix this!
        mTimeline = new Timeline();
//        setCycleCount specifies the no of cycles that the frames would be applied
//        (bu sayede looplamaya gerek kalmıyor)
        mTimeline.setCycleCount(kind.getTotalSeconds());
//        Duration.seconds(1): her 1 saniyede 1 değişim olur
//        mCurrentAttempt.tick(): zamanın azalma miktarını belirler
//        setTimerText(mCurrentAttempt.getRemainingSeconds()):
//          azalan zamanın labelda gözükmesi sağlanır
//        keyframe is a property that returns a list on our timeline
//        we add a new keyframe with args stating that how long does the frame run for and what does it do
        mTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {
            mCurrentAttempt.tick();
            setTimerText(mCurrentAttempt.getRemainingSeconds());
        }));
        // mTimeline.play(); could be added here (?)
    }

//    starts the animation
    public void playTimer() {
        mTimeline.play();
    }

//    şimdilik kullanılmadı
    public void pauseTimer() {
        mTimeline.pause();
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

    public void handleRestart(ActionEvent actionEvent) {
        prepareAttempt(AttemptKind.FOCUS);
        playTimer();
    }
}

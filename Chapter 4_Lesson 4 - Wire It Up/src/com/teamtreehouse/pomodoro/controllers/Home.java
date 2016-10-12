package com.teamtreehouse.pomodoro.controllers;

import com.teamtreehouse.pomodoro.model.Attempt;
import com.teamtreehouse.pomodoro.model.AttemptKind;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

public class Home {
    private final AudioClip mApplause;
    @FXML
    private VBox container;
//    ***fxml'deki container id ile hem start ve pause butonlarının değişimi sağlanıyor
//  (bu sayede farklı event handlingler elde ediliyor) hem de background color değişiyor
//  (ikisi de class eklenerek css aracılığıyla oluyor)***

    @FXML
    private Label title;

    @FXML
    private TextArea message;

    private Attempt mCurrentAttempt;
    private StringProperty mTimerText; // timeline'ı ayarlar ve formatlar
    private Timeline mTimeline; // timeline ı aktif hale getirir

    public Home() {
        mTimerText = new SimpleStringProperty();
        setTimerText(0);
        mApplause = new AudioClip(getClass().getResource("/sounds/applause.mp3").toExternalForm());
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
    }

    private void prepareAttempt(AttemptKind kind) {
        reset();
        mCurrentAttempt = new Attempt(kind, "");
        addAttemptStyle(kind);
        title.setText(kind.getDisplayName());
        setTimerText(mCurrentAttempt.getRemainingSeconds());
        mTimeline = new Timeline();
        mTimeline.setCycleCount(kind.getTotalSeconds());
//        *her 1 saniyede bir, kalan zaman kadar döngü olacak biçimde kalan zaman 1 saniye
//      azalır (tick() ile) ve panelde gösterilir*
        mTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {
            mCurrentAttempt.tick();
            setTimerText(mCurrentAttempt.getRemainingSeconds());
        }));
        mTimeline.setOnFinished(e -> {
            saveCurrentAttempt();
            mApplause.play();
            prepareAttempt(mCurrentAttempt.getKind() == AttemptKind.FOCUS ?
                    AttemptKind.BREAK : AttemptKind.FOCUS);
        });
    }

    private void saveCurrentAttempt() {
        mCurrentAttempt.setMessage(message.getText());
        mCurrentAttempt.save();
    }

    private void reset() {
        clearAttemptStyles();
//        timeline birden fazla kez çalıştırılırsa önceki objectleri silmek için
        if (mTimeline != null && mTimeline.getStatus() == Animation.Status.RUNNING) {
            mTimeline.stop();
        }
    }

    public void playTimer() {
//        hem pause butonu hem de play butonu etkiler
        container.getStyleClass().add("playing"); // *pause icon gelir*
        mTimeline.play();
    }

    public void pauseTimer() {
//        hem pause butonu hem de play butonu etkiler
        container.getStyleClass().remove("playing"); // *play icon geri gelir*
        mTimeline.pause();
    }

    private void addAttemptStyle(AttemptKind kind) {
        container.getStyleClass().add(kind.toString().toLowerCase());
    }

    private void clearAttemptStyles() {
        // replaces the play/resume icon with pause icon when time is finished
        container.getStyleClass().remove("playing"); // *play icon geri gelir*

//        *önceki stylelar silinerek css'de yazılan son break style'ın focus'u override etmesi engelleniyor*
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

    public void handlePlay(ActionEvent actionEvent) {
        if (mTimeline == null) {
            handleRestart(actionEvent);
        } else {
            playTimer();
        }
    }

    public void handlePause(ActionEvent actionEvent) {
        pauseTimer();
    }
}

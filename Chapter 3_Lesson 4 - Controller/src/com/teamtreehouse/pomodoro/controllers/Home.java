package com.teamtreehouse.pomodoro.controllers;

import com.teamtreehouse.pomodoro.model.Attempt;
import com.teamtreehouse.pomodoro.model.AttemptKind;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Home {
    @FXML
    private VBox container;
    // *we style the container based on the state (focus, break) we are in*

    @FXML
    private Label title;

    private Attempt mCurrentAttempt;

//    kind is either FOCUS or BREAK
    private void prepareAttempt(AttemptKind kind) {
        clearAttemptStyles(); // clears previous styles
        mCurrentAttempt = new Attempt(kind, ""); // *calls method of Attempt*
        addAttemptStyle(kind); // adds new styles based on attempt kind
        title.setText(kind.getDisplayName());
//        more dynamic than this alternative:
//        title.setText(kind.toString().toLowerCase());

    }

//    *a common approach to handling design changes is to add and remove a css class
//    getStyleClass might be multiple classes
//    container.getStyleClass returns a list that is why we can use add and remove methods
//    since kind is in uppercase and of type AttemptKind we use toString().toLowerCase()*
    private void addAttemptStyle(AttemptKind kind) {
        container.getStyleClass().add(kind.toString().toLowerCase());
    }

    private void clearAttemptStyles() {

        for (AttemptKind kind : AttemptKind.values()) { // loops enum
            container.getStyleClass().remove(kind.toString().toLowerCase());
        }
    }

    public void DEBUG(ActionEvent actionEvent) {
        System.out.println("HI MOM");
    }
}

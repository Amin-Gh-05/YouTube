package org.project.youtube.Client.Controller;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class ShortController {

    private void playClickEffect(Button button) {
        // animation class
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setDuration(Duration.millis(100));
        scaleTransition.setNode(button);

        // set scales
        scaleTransition.setByX(0.1);
        scaleTransition.setByY(0.1);
        scaleTransition.setAutoReverse(true);

        scaleTransition.setCycleCount(2);
        scaleTransition.play();
    }

    public void nextBtnAction(ActionEvent actionEvent) {
    }
    public void nextBtnBorderPaneMouseClicked(MouseEvent mouseEvent) {
    }


    public void previousBtnAction(ActionEvent actionEvent) {
    }
    public void previousBtnBorderPaneMouseClicked(MouseEvent mouseEvent) {
    }


    public void likeBtnAction(ActionEvent actionEvent) {
    }
    public void likeBtnBorderPaneMouseClicked(MouseEvent mouseEvent) {
    }


    public void dislikeBtnAction(ActionEvent actionEvent) {
    }
    public void dislikeBtnBorderPaneMouseClicked(MouseEvent mouseEvent) {
    }


    public void commentsBtnAction(ActionEvent actionEvent) {
    }
    public void commentsBtnBorderPaneMouseClicked(MouseEvent mouseEvent) {
    }


    public void saveToPLBtnAction(ActionEvent actionEvent) {
    }
    public void saveToPLBtnBorderPaneMouseClicked(MouseEvent mouseEvent) {
    }

}

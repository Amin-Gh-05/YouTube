package org.project.youtube.Client.Controller;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class ShortPlayerController {
    @FXML
    private Button playBtn;
    @FXML
    private Button muteBtn;


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

    public void playBtnAction(ActionEvent actionEvent) {
        playClickEffect(playBtn);
    }

    public void playBtnMouseClicked(MouseEvent mouseEvent) {
        playClickEffect(playBtn);
    }

    public void muteBtnAction(ActionEvent actionEvent) {
        playClickEffect(muteBtn);
    }
}

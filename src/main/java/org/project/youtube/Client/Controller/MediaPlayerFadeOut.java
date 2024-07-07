package org.project.youtube.Client.Controller;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MediaPlayerFadeOut implements Runnable {
    @FXML
    private VBox vBox;
    public MediaPlayerFadeOut(VBox vBox){
        this.vBox = vBox;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1500);
                if (MediaPlayerController.c) {
                    Platform.runLater(this::hide);
                }
            }
        }
        catch (InterruptedException e) {
        }
    }

    public void hide() {
        FadeTransition ft = new FadeTransition(Duration.millis(600), vBox);
        ft.setFromValue(vBox.getOpacity());
        ft.setToValue(0.0);
        ft.play();
    }
}
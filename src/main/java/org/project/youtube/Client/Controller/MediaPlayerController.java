package org.project.youtube.Client.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaView;
import org.project.youtube.Client.Model.Video;

public class MediaPlayerController {
    Video video;

    @FXML
    private BorderPane borderPane;

    @FXML
    private VBox controllersVBox;

    @FXML
    private Button fullscreenBtn;

    @FXML
    private MediaView mediaView;

    @FXML
    private Button miniPlayerBtn;

    @FXML
    private Button muteBtn;

    @FXML
    private Button nextBtn;

    @FXML
    private Button playBtn;

    @FXML
    private Button rateIncrementBtn;

    @FXML
    private Slider rateSlider;

    @FXML
    private Button teaterModeBtn;

    @FXML
    private Label timeLbl;

    @FXML
    private Slider videoSlider;

    @FXML
    private Slider volumeSlider;

    @FXML
    public void initialize() {
        
    }
    @FXML
    void fullscreenBtnAction(ActionEvent event) {

    }

    @FXML
    void muteBtnAction(ActionEvent event) {

    }

    @FXML
    void nextBtnAction(ActionEvent event) {

    }

    @FXML
    void playBtnAction(ActionEvent event) {

    }

    @FXML
    void rateIncrementBtnAction(ActionEvent event) {

    }

    @FXML
    void teaterModeBtnAction(ActionEvent event) {

    }

    public void showVBox(MouseEvent mouseEvent) {
    }

    public void hideVBox(MouseEvent mouseEvent) {
    }
}

package org.project.youtube.Client.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.net.URL;
import java.util.ResourceBundle;

public class MediaPlayerController implements Initializable {

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


    private String path;
    private MediaPlayer mediaPlayer;
    public static Thread fadeOutThread;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Media media = new Media(path);
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);

        // TODO Use The Media Player Container instead of scene
//        Scene scene = mediaView.getScene();
//        mediaView.fitWidthProperty().bind(scene.widthProperty());
//        mediaView.fitHeightProperty().bind(scene.heightProperty());

        borderPane.prefWidthProperty().bind(mediaView.fitWidthProperty());
        borderPane.prefHeightProperty().bind(mediaView.fitHeightProperty());

        mediaPlayer.setVolume(1);
        mediaPlayer.setBalance(0);

        rateSlider.valueProperty().addListener((observable, oldValue, newValue) -> mediaPlayer.setRate((double)newValue));
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> mediaPlayer.setVolume((double)newValue));
        // TODO <seek>
    }

    public void setPath(String path) {
        this.path = path;
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

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
import javafx.util.Duration;

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


    private Media media;
    private MediaPlayer mediaPlayer;
    public static Thread fadeOutThread;
    private double lastVolume;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO Use The Media Player Container instead of scene
//        Scene scene = mediaView.getScene();
//        mediaView.fitWidthProperty().bind(scene.widthProperty());
//        mediaView.fitHeightProperty().bind(scene.heightProperty());

        borderPane.prefWidthProperty().bind(mediaView.fitWidthProperty());
        borderPane.prefHeightProperty().bind(mediaView.fitHeightProperty());

        mediaPlayer.setVolume(1);
        mediaPlayer.setBalance(0);

        rateSlider.valueProperty().addListener((observable, oldValue, newValue) -> mediaPlayer.setRate((double)newValue));
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double newVal = (double)newValue;
            double oldVal = (double)oldValue;
            mediaPlayer.setVolume(newVal);
            if (newVal == 0.0) {
                muteShape();
            }
            else if (oldVal == 0.0) {
                unMuteShape();
            }
        });

        mediaPlayer.setOnReady(() -> {
            Duration totalDuration = media.getDuration();
            videoSlider.setMax(totalDuration.toSeconds());
        });

        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> videoSlider.setValue(newValue.toSeconds()));

        lastVolume = 1.0;
    }

    public void setPath(String path) {
        media = new Media(path);
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
    }

    @FXML
    void muteBtnAction(ActionEvent event) {
        if (mediaPlayer.getVolume() > 0) {
            lastVolume = mediaPlayer.getVolume();
            mediaPlayer.setVolume(0);
            volumeSlider.setValue(0);
            muteShape();
        }
        else {
            mediaPlayer.setVolume(lastVolume);
            volumeSlider.setValue(lastVolume);
            unMuteShape();
        }
    }

    private void unMuteShape() {
        muteBtn.setStyle("-fx-shape: \"&quot;M8,21 L12,21 L17,26 L17,10 L12,15 L8,15 L8,21 Z M19,14 L19,22 C20.48,21.32 21.5,19.77 21.5,18 C21.5,16.26 20.48,14.74 19,14 ZM19,11.29 C21.89,12.15 24,14.83 24,18 C24,21.17 21.89,23.85 19,24.71 L19,26.77 C23.01,25.86 26,22.28 26,18 C26,13.72 23.01,10.14 19,9.23 L19,11.29 Z&quot;\"");
    }

    void muteShape() {
        muteBtn.setStyle("-fx-shape: \"m 21.48,17.98 c 0,-1.77 -1.02,-3.29 -2.5,-4.03 v 2.21 l 2.45,2.45 c .03,-0.2 .05,-0.41 .05,-0.63 z m 2.5,0 c 0,.94 -0.2,1.82 -0.54,2.64 l 1.51,1.51 c .66,-1.24 1.03,-2.65 1.03,-4.15 0,-4.28 -2.99,-7.86 -7,-8.76 v 2.05 c 2.89,.86 5,3.54 5,6.71 z M 9.25,8.98 l -1.27,1.26 4.72,4.73 H 7.98 v 6 H 11.98 l 5,5 v -6.73 l 4.25,4.25 c -0.67,.52 -1.42,.93 -2.25,1.18 v 2.06 c 1.38,-0.31 2.63,-0.95 3.69,-1.81 l 2.04,2.05 1.27,-1.27 -9,-9 -7.72,-7.72 z m 7.72,.99 -2.09,2.08 2.09,2.09 V 9.98 z\"");
    }

    @FXML
    void nextBtnAction(ActionEvent event) {
        // TODO
    }

    @FXML
    void playBtnAction(ActionEvent event) {
        if (mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
            playBtn.setStyle("-fx-shape : \"M 12,26 18.5,22 18.5,14 12,10 z M 18.5,22 25,18 25,18 18.5,14 z\"");
            mediaPlayer.pause();
        }
        else {
            playBtn.setStyle("-fx-shape : \"M 12,26 16,26 16,10 12,10 z M 21,26 25,26 25,10 21,10 z\"");
            mediaPlayer.play();
        }
    }

    @FXML
    void rateIncrementBtnAction(ActionEvent event) {
        double rate = mediaPlayer.getRate();
        if (rate < 8) {
            rate++;
            mediaPlayer.setRate(rate);
        }
    }

    @FXML
    void fullscreenBtnAction(ActionEvent event) {
        // TODO
    }

    @FXML
    void teaterModeBtnAction(ActionEvent event) {
        // TODO
    }

    public void miniPlayerBtnAction(ActionEvent actionEvent) {
        // TODO
    }

    public void showVBox(MouseEvent mouseEvent) {
        if (fadeOutThread != null && !fadeOutThread.isInterrupted()){
            fadeOutThread.interrupt();
        }
        controllersVBox.setOpacity(1);
    }

    public void hideVBox(MouseEvent mouseEvent) {
        fadeOutThread = new Thread(new MediaPlayerFadeOut(controllersVBox));
        fadeOutThread.start();
    }

    public void videoSliderSeek(MouseEvent mouseEvent) {
        mediaPlayer.seek(Duration.seconds(videoSlider.getValue()));
    }
}

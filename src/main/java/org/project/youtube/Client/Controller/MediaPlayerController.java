package org.project.youtube.Client.Controller;

import javafx.animation.ScaleTransition;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import org.project.youtube.Client.Model.History;

import java.util.UUID;

public class MediaPlayerController {

    public static Thread fadeOutThread;
    static boolean c;

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
    private Button playBtn;
    @FXML
    private Button rateIncrementBtn;
    @FXML
    private Button teaterModeBtn;
    @FXML
    private Label timeLbl;
    @FXML
    private Slider videoSlider;
    @FXML
    private HBox volumeHBox;
    @FXML
    private HBox rateIncrementHBox;
    private Slider rateSlider;
    private Slider volumeSlider;
    private String path;
    private UUID videoID;
    private Media media;
    private MediaPlayer mediaPlayer;
    private double lastVolume;
    private String totalTimeStr;
    private AnchorPane pane;
    private int a;


    public void setPane(AnchorPane pane) {
        this.pane = pane;
    }

    public void setPath(String path, UUID videoID) {
        this.path = path;
        this.videoID = videoID;
        media = new Media(path);
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.setStartTime(Duration.seconds(History.getHistory(videoID)));
    }

    public void initBindings() {

        mediaPlayer.setOnReady(() -> {
            Duration totalDuration = media.getDuration();
            totalTimeStr = getTime(totalDuration);
            videoSlider.setMax(totalDuration.toSeconds());
        });


        a = 0;
        mediaPlayer.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            if (a == 80) {
                History.updateHistory(videoID, (int) newValue.toSeconds());
                a = 0;
            }
            videoSlider.setValue(newValue.toSeconds());
            a++;
        });

        timeLbl.textProperty().bind(Bindings.createStringBinding(() -> getTime(mediaPlayer.getCurrentTime()) + " / " + totalTimeStr, mediaPlayer.currentTimeProperty()));

        lastVolume = 1.0;
    }

    private String getTime(Duration time) {
        int h = (int) time.toHours();
        int m = (int) time.toMinutes();
        int s = (int) time.toSeconds();

        if (h > 59) h %= 60;
        if (m > 59) m %= 60;
        if (s > 59) s %= 60;

        if (h > 0) return String.format("%d:%02d:%02d", h, m, s);
        else return String.format("%02d:%02d", m, s);
    }

    private void createVolumeSlider() {
        Slider slider = new Slider();
        slider.setPrefWidth(70);
        slider.setMin(0);
        slider.setMax(1);
        slider.setValue(mediaPlayer.getVolume());
        slider.setBlockIncrement(0.1);
        slider.setMajorTickUnit(0.2);
        slider.setMinorTickCount(1);

        this.volumeSlider = slider;

        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double newVal = (double) newValue;
            double oldVal = (double) oldValue;
            mediaPlayer.setVolume(newVal);
            if (newVal == 0.0) {
                muteShape();
            } else if (oldVal == 0.0) {
                unMuteShape();
            }
        });

        volumeHBox.getChildren().add(slider);
    }

    private void createRateSlider() {
        Slider slider = new Slider();
        slider.setPrefWidth(70);
        slider.setMin(1);
        slider.setMax(8);
        slider.setValue(mediaPlayer.getRate());
        slider.setBlockIncrement(0.5);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(3);

        this.rateSlider = slider;

        rateSlider.valueProperty().addListener((observable, oldValue, newValue) -> mediaPlayer.setRate((double) newValue));

        rateIncrementHBox.getChildren().add(slider);
    }

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

    @FXML
    void muteBtnAction() {
        playClickEffect(muteBtn);
        if (mediaPlayer.getVolume() > 0) {
            lastVolume = mediaPlayer.getVolume();
            mediaPlayer.setVolume(0);
            if (volumeSlider != null) {
                volumeSlider.setValue(0);
            }
            muteShape();
        } else {
            mediaPlayer.setVolume(lastVolume);
            if (volumeSlider != null) {
                volumeSlider.setValue(lastVolume);
            }
            unMuteShape();
        }
    }

    private void unMuteShape() {
        muteBtn.setStyle("-fx-shape: \"M8,21 L12,21 L17,26 L17,10 L12,15 L8,15 L8,21 Z M19,14 L19,22 C20.48,21.32 21.5,19.77 21.5,18 C21.5,16.26 20.48,14.74 19,14 ZM19,11.29 C21.89,12.15 24,14.83 24,18 C24,21.17 21.89,23.85 19,24.71 L19,26.77 C23.01,25.86 26,22.28 26,18 C26,13.72 23.01,10.14 19,9.23 L19,11.29 Z\"");
    }

    void muteShape() {
        muteBtn.setStyle("-fx-shape: \"m 21.48,17.98 c 0,-1.77 -1.02,-3.29 -2.5,-4.03 v 2.21 l 2.45,2.45 c .03,-0.2 .05,-0.41 .05,-0.63 z m 2.5,0 c 0,.94 -0.2,1.82 -0.54,2.64 l 1.51,1.51 c .66,-1.24 1.03,-2.65 1.03,-4.15 0,-4.28 -2.99,-7.86 -7,-8.76 v 2.05 c 2.89,.86 5,3.54 5,6.71 z M 9.25,8.98 l -1.27,1.26 4.72,4.73 H 7.98 v 6 H 11.98 l 5,5 v -6.73 l 4.25,4.25 c -0.67,.52 -1.42,.93 -2.25,1.18 v 2.06 c 1.38,-0.31 2.63,-0.95 3.69,-1.81 l 2.04,2.05 1.27,-1.27 -9,-9 -7.72,-7.72 z m 7.72,.99 -2.09,2.08 2.09,2.09 V 9.98 z\"");
    }

    @FXML
    void playBtnAction() {
        playClickEffect(playBtn);

        if (media == null || mediaPlayer == null) {
            setPath(path, videoID);
            initBindings();
        }

        if (!mediaPlayer.getStatus().equals(MediaPlayer.Status.READY) && !mediaPlayer.getStatus().equals(MediaPlayer.Status.STOPPED) && !mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
            setPath(path, videoID);
            initBindings();
        }

        if (mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING)) {
            playBtn.setStyle("-fx-shape : \"M 12,26 18.5,22 18.5,14 12,10 z M 18.5,22 25,18 25,18 18.5,14 z\"");
            mediaPlayer.pause();
        } else {
            playBtn.setStyle("-fx-shape : \"M 12,26 16,26 16,10 12,10 z M 21,26 25,26 25,10 21,10 z\"");
            mediaPlayer.play();
        }
    }

    @FXML
    void rateIncrementBtnAction() {
        playClickEffect(rateIncrementBtn);
        double rate = mediaPlayer.getRate();
        if (rate < 8) {
            rate++;
            mediaPlayer.setRate(rate);
            if (rateSlider != null) {
                rateSlider.setValue(rate);
            }
        }
    }

    @FXML
    void fullscreenBtnAction() {
        playClickEffect(fullscreenBtn);
        // TODO
    }

    @FXML
    void teaterModeBtnAction() {
        playClickEffect(teaterModeBtn);
        // TODO
    }

    public void miniPlayerBtnAction() {
        playClickEffect(miniPlayerBtn);
        // TODO
    }

    public void showVBox() {
        if (fadeOutThread != null && !fadeOutThread.isInterrupted()) {
            fadeOutThread.interrupt();
        }
        controllersVBox.setOpacity(1);
        c = false;
    }

    public void hideVBox() {
        fadeOutThread = new Thread(new MediaPlayerFadeOut(controllersVBox));
        fadeOutThread.start();
        c = true;
    }

    public void videoSliderSeek() {
        mediaPlayer.seek(Duration.seconds(videoSlider.getValue()));
    }

    public void volumeHBoxEntered() {
        createVolumeSlider();
    }

    public void volumeHBoxExited() {
        volumeHBox.getChildren().remove(1);
        volumeSlider = null;
    }

    public void rateIncrementHBoxEnter() {
        createRateSlider();
    }

    public void rateIncrementHBoxExit() {
        rateIncrementHBox.getChildren().remove(1);
        rateSlider = null;
    }

    public AnchorPane getPane() {
        return pane;
    }
}

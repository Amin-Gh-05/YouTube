package org.project.youtube.Client.Controller;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.project.youtube.Client.Main;
import org.project.youtube.Client.Model.Network.Request;
import org.project.youtube.Client.Model.Short;

import java.io.IOException;

public class ShortController {
    Short shortVideo;

    @FXML
    private Button commentsBtn;

    @FXML
    private BorderPane commentsBtnBorderPane;

    @FXML
    private Button dislikeBtn;

    @FXML
    private BorderPane dislikeBtnBorderPane;

    @FXML
    private Button likeBtn;

    @FXML
    private BorderPane likeBtnBorderPane;

    @FXML
    private HBox mainHBox;

    @FXML
    private Button nextBtn;

    @FXML
    private Button previousBtn;

    @FXML
    private Button saveToPLBtn;

    @FXML
    private Pane playerPane;


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

    public void loadPlayer() throws IOException {
        FXMLLoader shortPlayerLoader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/short-player.fxml"));
        BorderPane shortPlayer = shortPlayerLoader.load();
        playerPane.getChildren().add(shortPlayer);

        ShortPlayerController shortPlayerController = shortPlayerLoader.getController();
        shortPlayerController.setPane(playerPane);
        shortPlayerController.setPath("file:///" + Main.CASH_PATH + "/" + shortVideo.getId().toString() + ".mp4");
        shortPlayerController.initBindings();

        shortPlayerController.setHandle(shortVideo.getShortHandle());
        shortPlayerController.setTitle(shortVideo.getTitle());
        shortPlayerController.setProfileImage(shortVideo.getShortHandle());
        if (Request.isSubscribed(MainController.user.getYid().toString(), shortVideo.getShortHandle())) {
            shortPlayerController.disableSubscribeBtn();
        }
    }

    public void nextBtnAction(ActionEvent actionEvent) {
        playClickEffect(nextBtn);
    }
    public void nextBtnBorderPaneMouseClicked(MouseEvent mouseEvent) {
    }


    public void previousBtnAction(ActionEvent actionEvent) {
        playClickEffect(previousBtn);
    }
    public void previousBtnBorderPaneMouseClicked(MouseEvent mouseEvent) {
    }


    public void likeBtnAction(ActionEvent actionEvent) {
        playClickEffect(likeBtn);
    }
    public void likeBtnBorderPaneMouseClicked(MouseEvent mouseEvent) {
    }


    public void dislikeBtnAction(ActionEvent actionEvent) {
        playClickEffect(dislikeBtn);
    }
    public void dislikeBtnBorderPaneMouseClicked(MouseEvent mouseEvent) {
    }


    public void commentsBtnAction(ActionEvent actionEvent) {
        playClickEffect(commentsBtn);
    }
    public void commentsBtnBorderPaneMouseClicked(MouseEvent mouseEvent) {
    }


    public void saveToPLBtnAction(ActionEvent actionEvent) {
        playClickEffect(saveToPLBtn);
    }
    public void saveToPLBtnBorderPaneMouseClicked(MouseEvent mouseEvent) {
    }

}

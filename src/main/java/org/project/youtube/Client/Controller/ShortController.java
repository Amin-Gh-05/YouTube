package org.project.youtube.Client.Controller;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class ShortController {
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

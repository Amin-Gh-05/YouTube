package org.project.youtube.Client.Controller;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class MainController {
    public static Stage mainStage;

    @FXML
    private Label channelLabel;

    @FXML
    private Label historyLabel;

    @FXML
    private Label homeLabel;

    @FXML
    private Label laterLabel;

    @FXML
    private Label likedLabel;

    @FXML
    private ImageView mainLogo;

    @FXML
    private Button moreButton;

    @FXML
    private Label playlistsLabel;

    @FXML
    private TextField searchBox;

    @FXML
    private Button searchButton;

    @FXML
    private Label shortsLabel;

    @FXML
    private Button signInButton;

    @FXML
    private Label subLabel;

    @FXML
    private Separator topSeparator;

    @FXML
    private Label videosLabel;

    @FXML
    private HBox homeBox;

    @FXML
    private HBox shortsBox;

    @FXML
    private HBox subsBox;

    @FXML
    private HBox channelBox;

    @FXML
    private HBox historyBox;

    @FXML
    private HBox playlistsBox;

    @FXML
    private HBox videosBox;

    @FXML
    private HBox latersBox;

    @FXML
    private HBox likedBox;

    @FXML
    private VBox sideBar;

    @FXML
    private Label youLabel;

    private boolean slideBar = true;

    @FXML
    void loadChannel(MouseEvent event) {

    }

    @FXML
    void loadHistory(MouseEvent event) {

    }

    @FXML
    void loadHome(ActionEvent event) {

    }

    @FXML
    void loadLaters(MouseEvent event) {

    }

    @FXML
    void loadLikes(MouseEvent event) {

    }

    @FXML
    void loadPlaylists(MouseEvent event) {

    }

    @FXML
    void loadShorts(MouseEvent event) {

    }

    @FXML
    void loadSubs(ActionEvent event) {

    }

    @FXML
    void loadVideos(MouseEvent event) {

    }

    @FXML
    void refreshAll(MouseEvent event) {

    }

    @FXML
    void searchAll(ActionEvent event) {

    }

    @FXML
    void signIn(ActionEvent event) throws IOException {
        // get current stage
        mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // load fxml of login page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/login-view.fxml"));
        Parent root = loader.load();
        // create a new stage and show it
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        // hide this page and open the sign in panel
        mainStage.hide();
        stage.show();

        System.out.println("| redirect to login panel");
    }

    @FXML
    void slideSidebar(ActionEvent event) {
        playClickEffect(moreButton);

        if (slideBar) {
            slideBar = false;

            // remove children
            sideBar.getChildren().remove(3, 11);
            subsBox.getChildren().remove(1);
            shortsBox.getChildren().remove(1);
            homeBox.getChildren().remove(1);
        } else {
            slideBar = true;

            // add children
            homeBox.getChildren().add(homeLabel);
            shortsBox.getChildren().add(shortsLabel);
            subsBox.getChildren().add(subLabel);
            sideBar.getChildren().addAll(topSeparator, youLabel, channelBox, historyBox, playlistsBox, videosBox, latersBox, likedBox);
        }
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
}
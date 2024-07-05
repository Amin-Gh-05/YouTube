package org.project.youtube.Client.Controller;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.project.youtube.Client.Model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public static User user;
    static Stage mainStage;

    // ------------------------------ HEADER ------------------------------
    @FXML
    private Button moreButton;

    @FXML
    private TextField searchBox;

    @FXML
    private Button searchButton;

    @FXML
    private Button createButton;

    @FXML
    private Button signInButton;

    // ------------------------------ SIDE ------------------------------

    @FXML
    private VBox sideBar;

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
    private HBox settingsBox;

    @FXML
    private HBox helpBox;

    @FXML
    private Label homeLabel;

    @FXML
    private Label shortsLabel;

    @FXML
    private Label subLabel;

    @FXML
    private Label channelLabel;

    @FXML
    private Label historyLabel;

    @FXML
    private Label playlistsLabel;

    @FXML
    private Label videosLabel;

    @FXML
    private Label laterLabel;

    @FXML
    private Label likedLabel;

    @FXML
    private Label settingsLabel;

    @FXML
    private Label helpLabel;

    @FXML
    private Separator topSeparator;

    @FXML
    private Separator downSeparator;

    @FXML
    private Label youLabel;

    private boolean slideBar = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void refreshAll() {

    }

    @FXML
    void slideSidebar() {
        playClickEffect(moreButton);

        if (slideBar) {
            slideBar = false;

            // remove children of sideBox
            sideBar.getChildren().remove(3, 6);
            sideBar.getChildren().remove(4, 9);
            sideBar.getChildren().remove(5);

            // remove child of HBoxes
            subsBox.getChildren().remove(1);
            shortsBox.getChildren().remove(1);
            homeBox.getChildren().remove(1);
            historyBox.getChildren().remove(1);
            settingsBox.getChildren().remove(1);
        } else {
            slideBar = true;

            // add child of HBoxes
            homeBox.getChildren().add(homeLabel);
            shortsBox.getChildren().add(shortsLabel);
            subsBox.getChildren().add(subLabel);
            historyBox.getChildren().add(historyLabel);
            settingsBox.getChildren().add(settingsLabel);

            // add children of sideBox
            sideBar.getChildren().add(3, topSeparator);
            sideBar.getChildren().add(4, youLabel);
            sideBar.getChildren().add(5, channelBox);
            sideBar.getChildren().add(7, playlistsBox);
            sideBar.getChildren().add(8, videosBox);
            sideBar.getChildren().add(9, latersBox);
            sideBar.getChildren().add(10, likedBox);
            sideBar.getChildren().add(11, downSeparator);
            sideBar.getChildren().add(13, helpBox);
        }
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
    void searchAll() {

    }

    @FXML
    void loadStudio(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/studio-view.fxml"));
        Parent root = loader.load();

        // create a new scene and set scene of stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        System.out.println("| redirect to studio panel");
    }

    @FXML
    void loadHome() {

    }

    @FXML
    void loadShorts() {

    }

    @FXML
    void loadSubs() {

    }

    @FXML
    void loadChannel() {

    }

    @FXML
    void loadHistory() {

    }

    @FXML
    void loadPlaylists() {

    }

    @FXML
    void loadVideos() {

    }

    @FXML
    void loadLaters() {

    }

    @FXML
    void loadLikes() {

    }

    @FXML
    void loadSettings() {

    }

    @FXML
    void loadHelp() {

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
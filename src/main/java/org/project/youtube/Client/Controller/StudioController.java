package org.project.youtube.Client.Controller;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import org.project.youtube.Client.Model.Channel;


import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class StudioController implements Initializable {
    static Channel channel;

    // ------------------------------ HEADER ------------------------------

    @FXML
    private Button moreButton;

    @FXML
    private TextField searchBox;

    @FXML
    private Button profileView;

    // ------------------------------ SIDE ------------------------------

    @FXML
    private VBox sideBar;

    @FXML
    private AnchorPane mainPanel;

    @FXML
    private Circle profilePic;

    @FXML
    private Label nameLabel;

    @FXML
    private HBox dashboardBox;

    @FXML
    private HBox contentBox;

    @FXML
    private HBox settingsBox;

    @FXML
    private HBox returnBox;

    @FXML
    private Label dashboardLabel;

    @FXML
    private Label contentLabel;

    @FXML
    private Label settingsLabel;

    @FXML
    private Label returnLabel;

    private boolean slideBar = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set image for profile viewer
        ImagePattern imagePattern = new ImagePattern(new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                "/org/project/youtube/Client/images/profile-background.png"))));
        profilePic.setFill(imagePattern);
        profilePic.setEffect(new DropShadow(10, Color.BLACK));

        // set name of channel
        nameLabel.setText("test");
    }

    @FXML
    void slideSidebar(ActionEvent event) {
        playClickEffect(moreButton);

        if (slideBar) {
            slideBar = false;

            profilePic.setRadius(25);
            sideBar.getChildren().remove(1);
            // remove child of sideBoxes
            dashboardBox.getChildren().remove(1);
            contentBox.getChildren().remove(1);
            settingsBox.getChildren().remove(1);
            returnBox.getChildren().remove(1);
        } else {
            slideBar = true;

            profilePic.setRadius(103);
            sideBar.getChildren().add(1, nameLabel);
            // add child of sideBoxes
            dashboardBox.getChildren().add(dashboardLabel);
            contentBox.getChildren().add(contentLabel);
            settingsBox.getChildren().add(settingsLabel);
            returnBox.getChildren().add(returnLabel);
        }
    }

    @FXML
    void refreshAll(MouseEvent event) {

    }

    @FXML
    void searchChannel(ActionEvent event) {

    }

    @FXML
    void createContent(ActionEvent event) {

    }

    @FXML
    void loadHelp(ActionEvent event) {

    }

    @FXML
    void loadProfile(ActionEvent event) {

    }

    @FXML
    void loadDashboard(ActionEvent event) {

    }

    @FXML
    void loadContent(ActionEvent event) {

    }

    @FXML
    void loadSettings(ActionEvent event) {

    }

    @FXML
    void loadMain(ActionEvent event) {

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

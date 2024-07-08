package org.project.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import org.project.youtube.Client.Model.Channel;
import org.project.youtube.Client.Model.Network.Request;

import java.io.IOException;

public class ChannelController {
    Channel channel;

    MainController controller;

    @FXML
    private ImageView bannerImage;

    @FXML
    private AnchorPane infoPanel;

    @FXML
    private Circle logoImage;

    @FXML
    private Label nameLabel;

    @FXML
    private ContextMenu changeMenu;

    @FXML
    private Label dateLabel;

    @FXML
    private Label handleLabel;

    @FXML
    private Label subsLabel;

    @FXML
    private Label viewLabel;

    @FXML
    private Button submitButton;

    @FXML
    private Label descriptionLabel;

    @FXML
    private FlowPane videosPanel;

    @FXML
    private FlowPane shortsPanel;

    @FXML
    private VBox playlistsPanel;

    @FXML
    private TextField nameField;

    @FXML
    private TextArea descriptionArea;

    @FXML
    void editChannel() {
        // get info from old name
        String oldName = nameLabel.getText();
        double nameLayoutX = nameLabel.getLayoutX();
        double nameLayoutY = nameLabel.getLayoutY();
        double nameWidth = nameLabel.getWidth();
        double nameHeight = nameLabel.getHeight();
        infoPanel.getChildren().remove(nameLabel);

        // get info from old description
        String oldDescription = descriptionLabel.getText();
        double descriptionLayoutX = descriptionLabel.getLayoutX();
        double descriptionLayoutY = descriptionLabel.getLayoutY();
        double descriptionWidth = descriptionLabel.getWidth();
        double descriptionHeight = descriptionLabel.getHeight();
        infoPanel.getChildren().remove(descriptionLabel);

        // add a text field to get new value
        nameField = new TextField(oldName);
        nameField.setLayoutX(nameLayoutX);
        nameField.setLayoutY(nameLayoutY);
        nameField.setPrefWidth(nameWidth);
        nameField.setPrefHeight(nameHeight);
        infoPanel.getChildren().add(1, nameField);

        // add a text area to get new value
        descriptionArea = new TextArea(oldDescription);
        descriptionArea.setLayoutX(descriptionLayoutX);
        descriptionArea.setLayoutY(descriptionLayoutY);
        descriptionArea.setPrefWidth(descriptionWidth);
        descriptionArea.setPrefHeight(descriptionHeight);
        infoPanel.getChildren().add(4, descriptionArea);

        changeMenu.hide();
        submitButton.setVisible(true);
    }

    @FXML
    void submitChanges() {
        Channel channel = MainController.channel;
        // get info from fields
        channel.setName(nameField.getText());
        channel.setDescription(descriptionArea.getText());
        try {
            Request.updateChannel(channel);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        nameLabel.setText(nameField.getText());
        descriptionLabel.setText(descriptionArea.getText());

        infoPanel.getChildren().remove(nameField);
        infoPanel.getChildren().remove(descriptionArea);
        infoPanel.getChildren().add(1, nameLabel);
        infoPanel.getChildren().add(4, descriptionLabel);

        submitButton.setVisible(false);
        System.out.println("| channel was successfully updated");
    }

    public ImageView getBannerImage() {
        return bannerImage;
    }

    public AnchorPane getInfoPanel() {
        return infoPanel;
    }

    public Circle getLogoImage() {
        return logoImage;
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public ContextMenu getChangeMenu() {
        return changeMenu;
    }

    public Label getDateLabel() {
        return dateLabel;
    }

    public Label getSubsLabel() {
        return subsLabel;
    }

    public Label getHandleLabel() {
        return handleLabel;
    }

    public Label getViewLabel() {
        return viewLabel;
    }

    public Button getSubmitButton() {
        return submitButton;
    }

    public Label getDescriptionLabel() {
        return descriptionLabel;
    }

    public FlowPane getVideosPanel() {
        return videosPanel;
    }

    public FlowPane getShortsPanel() {
        return shortsPanel;
    }

    public VBox getPlaylistsPanel() {
        return playlistsPanel;
    }
}

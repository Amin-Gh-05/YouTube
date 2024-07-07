package org.project.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

public class PlaylistController {
    MainController controller;

    @FXML
    private ImageView playlistImage;

    @FXML
    private Label nameLabel;

    @FXML
    private ContextMenu changeMenu;

    @FXML
    private Label handleLabel;

    @FXML
    private Label isPublicLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Button submitButton;

    @FXML
    private FlowPane videosPanel;

    @FXML
    private FlowPane shortsPanel;

    @FXML
    void editPlaylist() {

    }

    @FXML
    void deletePlaylist() {

    }

    @FXML
    void submitChanges() {

    }

    public ImageView getPlaylistImage() {
        return playlistImage;
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public ContextMenu getChangeMenu() {
        return changeMenu;
    }

    public Label getHandleLabel() {
        return handleLabel;
    }

    public Label getIsPublicLabel() {
        return isPublicLabel;
    }

    public Label getDescriptionLabel() {
        return descriptionLabel;
    }

    public Button getSubmitButton() {
        return submitButton;
    }

    public FlowPane getVideosPanel() {
        return videosPanel;
    }

    public FlowPane getShortsPanel() {
        return shortsPanel;
    }
}

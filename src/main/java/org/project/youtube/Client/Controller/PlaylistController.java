package org.project.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.project.youtube.Client.Model.Network.Request;
import org.project.youtube.Client.Model.Playlist;

import java.io.IOException;


public class PlaylistController {
    Playlist playlist;

    MainController controller;

    @FXML
    private HBox infoPanel;

    @FXML
    private VBox namePanel;

    @FXML
    private ImageView playlistImage;

    @FXML
    private Label nameLabel;

    @FXML
    private ContextMenu changeMenu;

    @FXML
    private MenuItem editItem;

    @FXML
    private MenuItem deleteItem;

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
    private TextField nameField;

    @FXML
    private ChoiceBox<String> isPublicChoice;

    @FXML
    private TextArea descriptionArea;

    @FXML
    void openPlaylist() throws IOException {
        Node node = controller.loadFullPlaylist(playlist);
        controller.getMainPanel().getChildren().clear();
        controller.getMainPanel().getChildren().add(node);
        System.out.println("| redirect to playlist page");
    }

    @FXML
    void editPlaylist() {
        // get info from old name
        String oldName = nameLabel.getText();
        namePanel.getChildren().remove(nameLabel);

        // remove old label
        namePanel.getChildren().remove(isPublicLabel);

        // get info from old description
        String oldDescription = descriptionLabel.getText();
        double descriptionWidth = descriptionLabel.getWidth();
        double descriptionHeight = descriptionLabel.getHeight();
        infoPanel.getChildren().remove(descriptionLabel);

        // add a text field to get new value
        nameField = new TextField(oldName);
        namePanel.getChildren().addFirst(nameField);

        // add a choice box to get new value
        isPublicChoice = new ChoiceBox<>();
        isPublicChoice.getItems().addAll("private", "public");
        namePanel.getChildren().add(2, isPublicChoice);

        // add a text area to get new value
        descriptionArea = new TextArea(oldDescription);
        descriptionArea.setPrefWidth(descriptionWidth);
        descriptionArea.setPrefHeight(descriptionHeight);
        infoPanel.getChildren().add(3, descriptionArea);

        changeMenu.hide();
        editItem.setDisable(true);
        submitButton.setVisible(true);
    }

    @FXML
    void deletePlaylist() throws IOException {
        Request.deletePlaylist(playlist);
        System.out.println("| playlist deleted");
    }

    @FXML
    void submitChanges() {
        playlist.setName(nameField.getText());
        playlist.setPublic(isPublicChoice.getValue().equals("public"));
        playlist.setDescription(descriptionArea.getText());
        try {
            Request.updatePL(playlist);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        nameLabel.setText(nameField.getText());
        isPublicLabel.setText(isPublicChoice.getValue());
        descriptionLabel.setText(descriptionArea.getText());

        namePanel.getChildren().remove(nameField);
        namePanel.getChildren().remove(isPublicChoice);
        infoPanel.getChildren().remove(descriptionArea);
        namePanel.getChildren().addFirst(nameLabel);
        namePanel.getChildren().add(2, isPublicLabel);
        infoPanel.getChildren().add(3, descriptionLabel);

        submitButton.setVisible(false);
        editItem.setDisable(false);
        System.out.println("| playlist was successfully updated");
    }

    public HBox getInfoPanel() {
        return infoPanel;
    }

    public ImageView getPlaylistImage() {
        return playlistImage;
    }

    public Label getNameLabel() {
        return nameLabel;
    }

    public MenuItem getEditItem() {
        return editItem;
    }

    public MenuItem getDeleteItem() {
        return deleteItem;
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

    public FlowPane getVideosPanel() {
        return videosPanel;
    }

    public FlowPane getShortsPanel() {
        return shortsPanel;
    }
}

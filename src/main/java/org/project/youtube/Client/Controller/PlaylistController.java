package org.project.youtube.Client.Controller;

import javafx.fxml.FXML;
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
    private Label handleLabel;

    @FXML
    private Label isPublicLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private FlowPane videosPanel;

    @FXML
    private FlowPane shortsPanel;
}

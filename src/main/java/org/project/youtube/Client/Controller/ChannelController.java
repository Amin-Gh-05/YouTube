package org.project.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class ChannelController {

    @FXML
    private ImageView bannerImage;

    @FXML
    private Circle logoImage;

    @FXML
    private Label nameLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label handleLabel;

    @FXML
    private Label subsLabel;

    @FXML
    private Label viewLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private FlowPane videosPanel;

    @FXML
    private FlowPane shortsPanel;

    @FXML
    private VBox playlistsPanel;
}

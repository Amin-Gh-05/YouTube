package org.project.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import org.project.youtube.Client.Model.Video;

public class ShortThumbnailController {
    Video video;

    MainController controller;

    @FXML
    private ImageView thumbnailImage;

    @FXML
    private Circle profileImage;

    @FXML
    private Label titleLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label viewsLabel;

    @FXML
    void loadVideo() {

    }

    public ImageView getThumbnailImage() {
        return thumbnailImage;
    }

    public Circle getProfileImage() {
        return profileImage;
    }

    public Label getTitleLabel() {
        return titleLabel;
    }

    public Label getDateLabel() {
        return dateLabel;
    }

    public Label getViewsLabel() {
        return viewsLabel;
    }
}

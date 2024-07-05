package org.project.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import org.project.youtube.Client.Model.Video;


public class ThumbnailController {
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

    public void setThumbnailImage(ImageView thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    public Circle getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Circle profileImage) {
        this.profileImage = profileImage;
    }

    public Label getTitleLabel() {
        return titleLabel;
    }

    public void setTitleLabel(Label titleLabel) {
        this.titleLabel = titleLabel;
    }

    public Label getDateLabel() {
        return dateLabel;
    }

    public void setDateLabel(Label dateLabel) {
        this.dateLabel = dateLabel;
    }

    public Label getViewsLabel() {
        return viewsLabel;
    }

    public void setViewsLabel(Label viewsLabel) {
        this.viewsLabel = viewsLabel;
    }
}

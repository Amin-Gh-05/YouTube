package org.project.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.project.youtube.Client.Model.Short;
import org.project.youtube.Client.Model.Video;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import static org.project.youtube.Client.Model.Network.Request.getChannel;


public class ThumbnailController {
    Video video;
    Short aShort;

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

    public void init() throws IOException {
        Image thumbnail = new Image(new ByteArrayInputStream(video.getThumbnail()));
        thumbnailImage.setImage(thumbnail);
        Image logo = new Image(new ByteArrayInputStream(getChannel(video.getVideoHandle()).getLogo()));
        profileImage.setFill(new ImagePattern(logo));
        titleLabel.setText(video.getTitle());
        viewsLabel.setText(String.valueOf(video.getViews()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd mmmm yyyy");
        dateLabel.setText(video.getCreatedDateTime().format(formatter));
    }



}

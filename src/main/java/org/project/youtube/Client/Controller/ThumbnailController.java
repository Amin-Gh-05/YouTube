package org.project.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import org.project.youtube.Client.Model.Video;

import java.net.URL;
import java.util.ResourceBundle;

public class ThumbnailController implements Initializable {
    Video video;

    @FXML
    private Label dateLabel;

    @FXML
    private Circle profileImage;

    @FXML
    private ImageView thumbnailImage;

    @FXML
    private Label titleLabel;

    @FXML
    private Label viewsLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void loadVideo() {

    }
}

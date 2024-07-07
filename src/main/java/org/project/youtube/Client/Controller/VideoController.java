package org.project.youtube.Client.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.project.youtube.Client.Model.Video;

import java.io.IOException;

public class VideoController {
    Video video;
    @FXML
    private Label dateCreatedLabel;

    @FXML
    private Label description;

    @FXML
    private Label dislikeCount;

    @FXML
    private Label dislikeCount11;

    @FXML
    private ImageView dislikeImage;

    @FXML
    private Label likeCount;

    @FXML
    private ImageView likeImage;

    @FXML
    private VBox mainVideoBox;

    @FXML
    private Pane mediaPlayerPane;

    @FXML
    private Button moreComments;

    @FXML
    private Button moreVideos;

    @FXML
    private VBox thumbnailBox;

    @FXML
    private Label titleLabel;

    @FXML
    private Label viewCount;

    @FXML
    void likeVideo(MouseEvent event) {

    }

    @FXML
    void loadComments(ActionEvent event) {

    }

    @FXML
    void loadThumbnails(ActionEvent event) {

    }

    public void init()  {


    }


    public void loadPlayer() throws IOException {
        FXMLLoader mediaPlayerLoader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/media-player.fxml"));
        AnchorPane mediaPlayer = mediaPlayerLoader.load();
        MediaPlayerController mediaPlayerController = mediaPlayerLoader.getController();
        mediaPlayerPane.getChildren().add(mediaPlayer);

        mediaPlayerController.setPath("file:///C:/YouTube/c.mp4");
        mediaPlayerController.setPane(mediaPlayerPane);
        mediaPlayerController.init();
    }

}
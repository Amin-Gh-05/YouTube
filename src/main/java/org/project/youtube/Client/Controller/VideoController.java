package org.project.youtube.Client.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.project.youtube.Client.Model.Video;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.project.youtube.Client.Model.Network.Request.getRandomVideos;

public class VideoController {
    Video video;
    static int commentPNT;
    static int thumbnailPNT;

    // ------------------------ VIDEO ------------------------
    @FXML
    private VBox mainVideoBox;

    @FXML
    private Pane mediaPlayerPane;

    @FXML
    private Label likeCount, viewCount, titleLabel, dateCreatedLabel, description;

    @FXML
    private Button likeImage, dislikeImage;

    // ---------------------- THUMBNAILS ----------------------
    @FXML
    private Button moreVideos;

    @FXML
    private VBox thumbnailBox;

    //  ---------------------- COMMENTS -----------------------
    @FXML
    private Button moreComments;


    @FXML
    void likeVideo(ActionEvent event) {

    }

    @FXML
    void dislikeVideo(ActionEvent event) {

    }

    @FXML
    void loadComments(ActionEvent event) throws IOException {
        mainVideoBox.getChildren().remove(moreComments);
        FXMLLoader commentLoader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/comment-view.fxml"));
        AnchorPane comment = commentLoader.load();
        CommentController commentController = commentLoader.getController();
        for(int i = 0; i < 10; i++)
        {
            commentController.comment = video.getComments().get(commentPNT + i);
            mediaPlayerPane.getChildren().add(comment);
        }
        commentPNT += 10;
        mainVideoBox.getChildren().add(moreComments);
    }

    @FXML
    void loadThumbnails(ActionEvent event) throws IOException {
        /* TODO loading first 10 Thumbnail
        List<Video> randomVideo = getRandomVideos();
        thumbnailBox.getChildren().remove(moreVideos);
        FXMLLoader thumbnailLoader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/video-thumbnail.fxml"));
        AnchorPane thumbnail = thumbnailLoader.load();
        ThumbnailController thumbnailController = thumbnailLoader.getController();
        for(int i = 0; i < 10; i++)
        {
            thumbnailController.video = randomVideo.get(thumbnailPNT + i);
            mediaPlayerPane.getChildren().add(thumbnail);
        }
        thumbnailPNT += 10;
        thumbnailBox.getChildren().add(moreVideos);
        */
    }

    public void init() throws IOException {
        likeCount.setText(String.valueOf(video.getLikes()));
        viewCount.setText(String.valueOf(video.getViews()));
        titleLabel.setText(video.getTitle());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd mmmm yyyy");
        dateCreatedLabel.setText(video.getCreatedDateTime().format(formatter));
        description.setText(video.getDescription());

        // loading first 10 comments
        mainVideoBox.getChildren().remove(moreComments);
        FXMLLoader commentLoader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/comment-view.fxml"));
        AnchorPane comment = commentLoader.load();
        CommentController commentController = commentLoader.getController();
        for(commentPNT = 0; commentPNT < 10; commentPNT++)
        {
            commentController.comment = video.getComments().get(commentPNT);
            mediaPlayerPane.getChildren().add(comment);
        }
        mainVideoBox.getChildren().add(moreComments);

        /* TODO loading first 10 Thumbnail
        List<Video> randomVideo = getRandomVideos();
        thumbnailBox.getChildren().remove(moreVideos);
        FXMLLoader thumbnailLoader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/video-thumbnail.fxml"));
        AnchorPane thumbnail = thumbnailLoader.load();
        ThumbnailController thumbnailController = thumbnailLoader.getController();
        for(thumbnailPNT = 0; thumbnailPNT < 10; thumbnailPNT++)
        {
            thumbnailController.video = randomVideo.get(thumbnailPNT);
            mediaPlayerPane.getChildren().add(thumbnail);
        }
        thumbnailBox.getChildren().add(moreVideos);
        */

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

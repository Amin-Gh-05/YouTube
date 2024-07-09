package org.project.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import org.controlsfx.control.Notifications;
import org.project.youtube.Client.Main;
import org.project.youtube.Client.Model.Comment;
import org.project.youtube.Client.Model.Network.Request;
import org.project.youtube.Client.Model.User;
import org.project.youtube.Client.Model.Video;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.project.youtube.Client.Model.Network.Request.getLatestVideos;

public class VideoController {
    static int commentPNT;
    static int thumbnailPNT;

    Video video;

    // ------------------------ VIDEO ------------------------

    @FXML
    private VBox mainVideoBox;

    @FXML
    private Pane mediaPlayerPane;

    @FXML
    private Label likeCount, viewCount, titleLabel, dateCreatedLabel, description;

    @FXML
    private Button likeImage, dislikeImage, saveButton, reportButton;

    // ---------------------- THUMBNAILS ----------------------

    @FXML
    private Button moreVideos;

    @FXML
    private VBox thumbnailBox;

    //  ---------------------- COMMENTS -----------------------

    @FXML
    private Button moreComments;

    @FXML
    void reportAlert() {
        Notifications.create().title("Guidance").text("Here is a free country. No objection!").showInformation();
    }

    @FXML
    void saveToPlayList() {

    }

    @FXML
    void likeVideo() {

    }

    @FXML
    void dislikeVideo() {

    }

    @FXML
    void loadComments() throws IOException {
        mainVideoBox.getChildren().remove(moreComments);

        FXMLLoader commentLoader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/comment-view.fxml"));
        AnchorPane comment = commentLoader.load();
        CommentController commentController = commentLoader.getController();

        for (int i = 0; i < 10; i++) {
            commentController.comment = video.getComments().get(commentPNT + i);
            mediaPlayerPane.getChildren().add(comment);
        }
        commentPNT += 10;

        mainVideoBox.getChildren().add(moreComments);
    }

    @FXML
    void loadThumbnails() throws IOException {
        List<Video> randomVideo = getLatestVideos();
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

    }

    public void init() throws IOException {
        likeCount.setText(String.valueOf(video.getLikes()));
        viewCount.setText(String.valueOf(video.getViews()));
        titleLabel.setText(video.getTitle());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        dateCreatedLabel.setText(video.getCreatedDateTime().format(formatter));
        description.setText(video.getDescription());

        // loading first 10 comments
        mainVideoBox.getChildren().remove(moreComments);
        FXMLLoader commentLoader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/comment-view.fxml"));
        AnchorPane comment = commentLoader.load();
        CommentController commentController = commentLoader.getController();
        for (commentPNT = 0; commentPNT < 10; commentPNT++) {
            commentController.comment = video.getComments().get(commentPNT);
            mainVideoBox.getChildren().add(comment);
//            commentController.init();
        }
        mainVideoBox.getChildren().add(moreComments);

        List<Video> randomVideo = getLatestVideos();
        thumbnailBox.getChildren().remove(moreVideos);
        FXMLLoader thumbnailLoader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/video-thumbnail.fxml"));
        AnchorPane thumbnail = thumbnailLoader.load();
        ThumbnailController thumbnailController = thumbnailLoader.getController();
        for (thumbnailPNT = 0; thumbnailPNT < 10; thumbnailPNT++) {
            thumbnailController.video = randomVideo.get(thumbnailPNT);
            thumbnailController.init();
            mediaPlayerPane.getChildren().add(thumbnail);
        }
        thumbnailBox.getChildren().add(moreVideos);
    }

    public void loadPlayer() throws IOException {
        FXMLLoader mediaPlayerLoader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/media-player.fxml"));
        AnchorPane mediaPlayer = mediaPlayerLoader.load();
        mediaPlayerPane.getChildren().add(mediaPlayer);

        MediaPlayerController mediaPlayerController = mediaPlayerLoader.getController();
        mediaPlayerController.setPane(mediaPlayerPane);
        mediaPlayerController.setPath("file:///" + Main.CASH_PATH + "/" + video.getId().toString() + ".mp4");
        mediaPlayerController.initBindings();
    }

    private Node loadComment(Comment comment) throws IOException {
        User user = Request.getUser(comment.getWriterYID());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/comment-view.fxml"));
        Node node = loader.load();
        CommentController commentController = loader.getController();

        // set attributes
        commentController.comment = comment;
        commentController.videoController = this;
        try {
            commentController.getProfilePic().setFill(new ImagePattern(new Image(new ByteArrayInputStream(user.getProfilePic()))));
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            commentController.getProfilePic().setFill(new ImagePattern(new Image("/org/project/youtube/Client/images/profile-sample.png")));
        }
        commentController.getUsernameLabel().setText(user.getUsername());
        commentController.getDateLabel().setText(comment.getCreatedDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        commentController.getTextLabel().setText(comment.getComment());
        commentController.getLikesLabel().setText(String.valueOf(comment.getLike()));

        return node;
    }

    public VBox getMainVideoBox() {
        return mainVideoBox;
    }
}

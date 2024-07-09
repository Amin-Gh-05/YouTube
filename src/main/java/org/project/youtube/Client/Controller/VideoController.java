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
import org.project.youtube.Client.Model.Channel;
import org.project.youtube.Client.Model.Comment;
import org.project.youtube.Client.Model.Network.Request;
import org.project.youtube.Client.Model.User;
import org.project.youtube.Client.Model.Video;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.project.youtube.Client.Model.Network.Request.getRandomVideos;

public class VideoController {
    static int commentPNT;

    Video video;

    MainController controller;

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
    void likeVideo() throws IOException {
        boolean canLike = Request.likeVideo("L", MainController.user, video);

        if (canLike) {
            dislikeImage.setDisable(true);
            System.out.println("| video was liked");
            likeCount.setText(Integer.toString(Integer.parseInt(likeCount.getText()) + 1));
        } else {
            dislikeImage.setDisable(false);
            Request.unLikeVideo("L", MainController.user, video);
            System.out.println("| video was unliked");
            likeCount.setText(Integer.toString(Integer.parseInt(likeCount.getText()) - 1));
        }
    }

    @FXML
    void dislikeVideo() throws IOException {
        boolean canLike = Request.likeVideo("D", MainController.user, video);

        if (canLike) {
            likeImage.setDisable(true);
            System.out.println("| video was disliked");
            likeCount.setText(Integer.toString(Integer.parseInt(likeCount.getText()) - 1));
        } else {
            likeImage.setDisable(false);
            Request.unLikeVideo("D", MainController.user, video);
            System.out.println("| video was unDisliked");
            likeCount.setText(Integer.toString(Integer.parseInt(likeCount.getText()) + 1));
        }
    }

    @FXML
    void loadComments() throws IOException {
        mainVideoBox.getChildren().remove(moreComments);

        try {
            for (int i = 0; i < 10; i++) {
                mainVideoBox.getChildren().add(loadComment(video.getComments().get(commentPNT + i)));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("| no more comments");
            moreComments.setDisable(true);
        } finally {
            commentPNT += 10;
        }

        mainVideoBox.getChildren().add(moreComments);
    }

    @FXML
    void loadThumbnails() throws IOException {
        List<Video> videos = getRandomVideos();
        for (Video video : videos) {
            thumbnailBox.getChildren().add(loadThumbnail(video));
        }

        System.out.println("| thumbnails loaded");
    }


    public void init() throws IOException {
        likeCount.setText(String.valueOf(video.getLikes()));
        viewCount.setText(String.valueOf(video.getViews()));
        titleLabel.setText(video.getTitle());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        dateCreatedLabel.setText(video.getCreatedDateTime().format(formatter));
        description.setText(video.getDescription());

        // loading first 10 comments
        commentPNT = 0;
        loadComments();

        // loading 10 thumbnails
        loadThumbnails();

        loadPlayer();
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
            commentController.getProfilePic().setFill(new ImagePattern(new Image("/org/project/youtube/Client/images/sample-profile.png")));
        }
        commentController.getUsernameLabel().setText(user.getUsername());
        commentController.getDateLabel().setText(comment.getCreatedDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        commentController.getTextLabel().setText(comment.getComment());
        commentController.getLikesLabel().setText(String.valueOf(comment.getLike()));
        if (!comment.getWriterYID().equals(MainController.user.getYid())) {
            commentController.getEditItem().setDisable(true);
            commentController.getDeleteItem().setDisable(true);
        }
        return node;
    }

    private Node loadThumbnail(Video video) throws IOException {
        Channel videoChannel = Request.getChannel(video.getVideoHandle());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/video-thumbnail.fxml"));
        Node node = loader.load();
        ThumbnailController thumbnailController = loader.getController();

        // set attributes
        thumbnailController.video = video;
        thumbnailController.controller = this.controller;
        try {
            thumbnailController.getThumbnailImage().setImage(new Image(new ByteArrayInputStream(video.getThumbnail())));
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            thumbnailController.getThumbnailImage().setImage(new Image("/org/project/youtube/Client/images/sample-thumbnail.png"));
        }
        try {
            thumbnailController.getProfileImage().setFill(new ImagePattern(new Image(new ByteArrayInputStream(videoChannel.getLogo()))));
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            thumbnailController.getProfileImage().setFill(new ImagePattern(new Image("/org/project/youtube/Client/images/sample-profile.png")));
        }
        thumbnailController.getTitleLabel().setText(video.getTitle());
        thumbnailController.getDateLabel().setText(video.getCreatedDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        thumbnailController.getViewsLabel().setText(String.valueOf(video.getViews()));

        return node;
    }

    public VBox getMainVideoBox() {
        return mainVideoBox;
    }
}

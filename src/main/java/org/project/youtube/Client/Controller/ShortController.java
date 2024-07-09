package org.project.youtube.Client.Controller;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import org.project.youtube.Client.Main;
import org.project.youtube.Client.Model.Comment;
import org.project.youtube.Client.Model.Network.Request;
import org.project.youtube.Client.Model.Short;
import org.project.youtube.Client.Model.User;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ShortController {
    Short shortVideo;
    List<Short> shortVideoList;

    @FXML
    private Button commentsBtn;

    @FXML
    private BorderPane commentsBtnBorderPane;

    @FXML
    private Button dislikeBtn;

    @FXML
    private BorderPane dislikeBtnBorderPane;

    @FXML
    private Button likeBtn;

    @FXML
    private BorderPane likeBtnBorderPane;

    @FXML
    private HBox mainHBox;

    @FXML
    private Button nextBtn;

    @FXML
    private Button previousBtn;

    @FXML
    private Button saveToPLBtn;

    @FXML
    private Pane playerPane;

    @FXML
    private ScrollPane commentsScrollPane;

    @FXML
    private FlowPane commentsFlowPane;

    public void setShortVideo(Short shortVideo) {
        this.shortVideo = shortVideo;
    }

    public void setShortVideoList(List<Short> shortVideoList) {
        this.shortVideoList = shortVideoList;
    }

    private void playClickEffect(Button button) {
        // animation class
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setDuration(Duration.millis(100));
        scaleTransition.setNode(button);

        // set scales
        scaleTransition.setByX(0.1);
        scaleTransition.setByY(0.1);
        scaleTransition.setAutoReverse(true);

        scaleTransition.setCycleCount(2);
        scaleTransition.play();
    }

    public void loadPlayer() throws IOException {
        FXMLLoader shortPlayerLoader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/short-player.fxml"));
        BorderPane shortPlayer = shortPlayerLoader.load();
        playerPane.getChildren().add(shortPlayer);

        ShortPlayerController shortPlayerController = shortPlayerLoader.getController();
        shortPlayerController.setPane(playerPane);
        shortPlayerController.setPath("file:///" + Main.CASH_PATH + "/" + shortVideo.getId().toString() + ".mp4");
        shortPlayerController.initBindings();

        shortPlayerController.setHandle(shortVideo.getShortHandle());
        shortPlayerController.setTitle(shortVideo.getTitle());
        shortPlayerController.setProfileImage(shortVideo.getShortHandle());
        if (Request.isSubscribed(MainController.user.getYid().toString(), shortVideo.getShortHandle())) {
            shortPlayerController.disableSubscribeBtn();
        }
    }

    //TODO
    public void nextBtnAction(ActionEvent actionEvent) {
        playClickEffect(nextBtn);
    }
    public void nextBtnBorderPaneMouseClicked(MouseEvent mouseEvent) {
    }


    //TODO
    public void previousBtnAction(ActionEvent actionEvent) {
        playClickEffect(previousBtn);
    }
    public void previousBtnBorderPaneMouseClicked(MouseEvent mouseEvent) {
    }

    private void like() throws IOException {
        boolean canLike = Request.likeShort("L", MainController.user, shortVideo);

        if (canLike) {
            dislikeBtn.setDisable(true);
            System.out.println("| short was liked");
        }
        else {
            dislikeBtn.setDisable(false);
            Request.unLikeShort("L", MainController.user, shortVideo);
            System.out.println("| short was unliked");
        }
    }
    public void likeBtnAction(ActionEvent actionEvent) throws IOException {
        playClickEffect(likeBtn);
        like();
    }
    public void likeBtnBorderPaneMouseClicked(MouseEvent mouseEvent) throws IOException {
        like();
    }

    private void dislike() throws IOException {
        boolean canLike = Request.likeShort("D", MainController.user, shortVideo);

        if (canLike) {
            likeBtn.setDisable(true);
            System.out.println("| short was disliked");
        }
        else {
            likeBtn.setDisable(false);
            Request.unLikeShort("D", MainController.user, shortVideo);
            System.out.println("| short was unDisliked");
        }
    }
    public void dislikeBtnAction(ActionEvent actionEvent) throws IOException {
        playClickEffect(dislikeBtn);
        dislike();
    }
    public void dislikeBtnBorderPaneMouseClicked(MouseEvent mouseEvent) throws IOException {
        dislike();
    }

    private Node loadComment(Comment comment) throws IOException {
        User user = Request.getUser(comment.getWriterYID());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/comment-view.fxml"));
        Node node = loader.load();
        CommentController commentController = loader.getController();

        // set attributes
        commentController.comment = comment;
        commentController.shortController = this;
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
    void loadComments() throws IOException {
        commentsScrollPane.setPrefWidth(305);

        // TODO new comment

        List<Comment> commentList = shortVideo.getComments();
        for (Comment comment : commentList) {
            commentsFlowPane.getChildren().add(loadComment(comment));
        }
    }
    public void commentsBtnAction(ActionEvent actionEvent) throws IOException {
        playClickEffect(commentsBtn);
        loadComments();
    }
    public void commentsBtnBorderPaneMouseClicked(MouseEvent mouseEvent) throws IOException {
        loadComments();
    }


    //TODO
    public void saveToPLBtnAction(ActionEvent actionEvent) {
        playClickEffect(saveToPLBtn);
    }
    public void saveToPLBtnBorderPaneMouseClicked(MouseEvent mouseEvent) {
    }

}

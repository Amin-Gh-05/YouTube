package org.project.youtube.Client.Controller;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
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
    int shortNumber = 0;
    private boolean commentsEnabled = false;

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


    public void loadPlayer() throws IOException {
        for (int i = 0; i < shortVideoList.size(); i++) {
            if (shortVideoList.get(i).getId().toString().equals(shortVideo.getId().toString())) {
                shortNumber = i;
            }
        }

        if (shortNumber == 0) {
            previousBtn.setDisable(true);
        }

        FXMLLoader shortPlayerLoader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/short-player.fxml"));
        BorderPane shortPlayer = shortPlayerLoader.load();
        playerPane.getChildren().clear();
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


    private void nextBtnClick() throws IOException {
        shortNumber++;
        if (previousBtn.isDisable()) {
            previousBtn.setDisable(false);
        }

        loadPlayer();
    }
    public void nextBtnAction(ActionEvent actionEvent) throws IOException {
        playClickEffect(nextBtn);
        nextBtnClick();
    }
    public void nextBtnBorderPaneMouseClicked(MouseEvent mouseEvent) throws IOException {
        nextBtnClick();
    }

    private void previousBtnClick() throws IOException {
        shortNumber--;
        if (shortNumber == 0) {
            previousBtn.setDisable(true);
        }

        loadPlayer();
    }
    public void previousBtnAction(ActionEvent actionEvent) throws IOException {
        playClickEffect(previousBtn);
        previousBtnClick();
    }
    public void previousBtnBorderPaneMouseClicked(MouseEvent mouseEvent) throws IOException {
        previousBtnClick();
    }


    private void like() throws IOException {
        boolean canLike = Request.likeShort("L", MainController.user, shortVideoList.get(shortNumber));

        if (canLike) {
            dislikeBtn.setDisable(true);
            System.out.println("| short was liked");
        }
        else {
            dislikeBtn.setDisable(false);
            Request.unLikeShort("L", MainController.user, shortVideoList.get(shortNumber));
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
        boolean canLike = Request.likeShort("D", MainController.user, shortVideoList.get(shortNumber));

        if (canLike) {
            likeBtn.setDisable(true);
            System.out.println("| short was disliked");
        }
        else {
            likeBtn.setDisable(false);
            Request.unLikeShort("D", MainController.user, shortVideoList.get(shortNumber));
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
        if (!commentsEnabled) {
            nextBtn.setDisable(true);
            previousBtn.setDisable(true);

            commentsScrollPane.setPrefWidth(305);

            // TODO new comment

            List<Comment> commentList = shortVideoList.get(shortNumber).getComments();
            for (Comment comment : commentList) {
                commentsFlowPane.getChildren().add(loadComment(comment));
            }

            commentsEnabled = true;
        }
        else {
            nextBtn.setDisable(false);
            previousBtn.setDisable(false);

            commentsScrollPane.setPrefWidth(3);
            commentsFlowPane.getChildren().clear();

            commentsEnabled = false;
        }
    }
    public void commentsBtnAction(ActionEvent actionEvent) throws IOException {
        playClickEffect(commentsBtn);
        loadComments();
    }
    public void commentsBtnBorderPaneMouseClicked(MouseEvent mouseEvent) throws IOException {
        loadComments();
    }


    private void saveToPlaylist() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/addToPL-view.fxml"));
        DialogPane addToPL = loader.load();
        AddToPLController controller = loader.getController();
        controller.shortVideo = this.shortVideo;

        Dialog adder = new Dialog();
        adder.setDialogPane(addToPL);
    }
    public void saveToPLBtnAction(ActionEvent actionEvent) throws IOException {
        playClickEffect(saveToPLBtn);
        saveToPlaylist();
    }

    public void saveToPLBtnBorderPaneMouseClicked(MouseEvent mouseEvent) throws IOException {
        saveToPlaylist();
    }

}

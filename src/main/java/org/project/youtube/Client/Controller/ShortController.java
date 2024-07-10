package org.project.youtube.Client.Controller;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.project.youtube.Client.Main;
import org.project.youtube.Client.Model.Comment;
import org.project.youtube.Client.Model.Network.Request;
import org.project.youtube.Client.Model.Short;
import org.project.youtube.Client.Model.User;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import static org.project.youtube.Client.Model.Network.Request.createVideoComment;
import static org.project.youtube.Client.Model.Network.Request.unLikeShort;

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
    private BorderPane saveToPLBtnBorderPane;

    @FXML
    private BorderPane previousBtnBorderPane;

    @FXML
    private BorderPane nextBtnBorderPane;

    @FXML
    private Pane playerPane;

    @FXML
    private ScrollPane commentsScrollPane;

    @FXML
    private FlowPane commentsFlowPane;

    @FXML
    private Label likeCnt, viewCnt;


    public void loadPlayer() throws IOException {
        for (int i = 0; i < shortVideoList.size(); i++) {
            if (shortVideoList.get(i).getId().toString().equals(shortVideo.getId().toString())) {
                shortNumber = i;
            }
        }
        if(MainController.user == null) {
            likeBtn.setDisable(true);
            likeBtnBorderPane.setDisable(true);
            dislikeBtn.setDisable(true);
            dislikeBtnBorderPane.setDisable(true);
            saveToPLBtn.setDisable(true);
            saveToPLBtnBorderPane.setDisable(true);
        }

        likeCnt.setText(String.valueOf(shortVideoList.get(shortNumber).getLikes()));
        viewCnt.setText("view: "+ String.valueOf(shortVideoList.get(shortNumber).getViews()));


        if (shortNumber == 0) {
            previousBtn.setDisable(true);
            previousBtnBorderPane.setDisable(true);
        }

        if (shortVideoList.size() == 1) {
            previousBtn.setDisable(true);
            previousBtnBorderPane.setDisable(true);
            nextBtn.setDisable(true);
            nextBtnBorderPane.setDisable(true);
        }

        FXMLLoader shortPlayerLoader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/short-player.fxml"));
        AnchorPane shortPlayer = shortPlayerLoader.load();
        playerPane.getChildren().clear();
        playerPane.getChildren().add(shortPlayer);

        ShortPlayerController shortPlayerController = shortPlayerLoader.getController();
        shortPlayerController.setPane(playerPane);
        shortPlayerController.setPath("file:///" + Main.CASH_PATH + "/" + shortVideoList.get(shortNumber).getId().toString() + ".mp4", shortVideoList.get(shortNumber).getId());
        shortPlayerController.initBindings();

        shortPlayerController.setHandle(shortVideoList.get(shortNumber).getShortHandle());
        shortPlayerController.setTitle(shortVideoList.get(shortNumber).getTitle());
        shortPlayerController.setProfileImage(shortVideoList.get(shortNumber).getShortHandle());
        if (MainController.user != null) {
            if (Request.isSubscribed(MainController.user.getYid().toString(), shortVideoList.get(shortNumber).getShortHandle())) {
                shortPlayerController.disableSubscribeBtn();
            }
        }
        else {
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
            previousBtnBorderPane.setDisable(false);
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
            previousBtnBorderPane.setDisable(true);
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
            dislikeBtnBorderPane.setDisable(true);
            System.out.println("| short was liked");
        }
        else {
            dislikeBtn.setDisable(false);
            dislikeBtnBorderPane.setDisable(false);
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
            likeBtnBorderPane.setDisable(true);
            System.out.println("| short was disliked");
        }
        else {
            likeBtn.setDisable(false);
            likeBtnBorderPane.setDisable(false);
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
            nextBtnBorderPane.setDisable(true);
            previousBtn.setDisable(true);
            previousBtnBorderPane.setDisable(true);

            commentsScrollPane.setPrefWidth(305);

            Label cmt = new Label("Comments");
            cmt.setFont(new Font(20));
            commentsFlowPane.getChildren().add(cmt);

            TextField commentSection = new TextField();
            commentSection.setPromptText("Write your comment...");
            commentSection.setAlignment(Pos.TOP_LEFT);
            commentSection.setFont(new Font(16));
            commentSection.setPrefHeight(70);
            commentSection.setPrefWidth(240);

            Button postComment = new Button();
            postComment.getStyleClass().add("actionButton");
            postComment.setText("Post");
            postComment.setOnAction(event -> {
                if(commentSection.getText() != null){
                    Comment comment = new Comment(UUID.randomUUID(), shortVideoList.get(shortNumber).getId(), MainController.user.getYid(), commentSection.getText(), 0, String.valueOf(LocalDate.now()));
                    try {
                        Request.createShortComment(comment);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            HBox newCmt = new HBox(commentSection);
            newCmt.setSpacing(10);
            newCmt.getChildren().add(postComment);

            if(MainController.user == null) {
                newCmt.setDisable(true);
            }

            List<Comment> commentList = shortVideoList.get(shortNumber).getComments();
            for (Comment comment : commentList) {
                commentsFlowPane.getChildren().add(loadComment(comment));
            }

            commentsEnabled = true;
        }
        else {
            nextBtn.setDisable(false);
            nextBtnBorderPane.setDisable(false);
            previousBtn.setDisable(false);
            previousBtnBorderPane.setDisable(false);

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
        controller.shortVideo = this.shortVideoList.get(shortNumber);

        Dialog adder = new Dialog();
        adder.setDialogPane(addToPL);
        adder.show();
    }
    public void saveToPLBtnAction(ActionEvent actionEvent) throws IOException {
        playClickEffect(saveToPLBtn);
        saveToPlaylist();
    }

    public void saveToPLBtnBorderPaneMouseClicked(MouseEvent mouseEvent) throws IOException {
        saveToPlaylist();
    }

}

package org.project.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import org.project.youtube.Client.Model.Comment;
import org.project.youtube.Client.Model.Network.Request;

import java.io.IOException;

public class CommentController {
    Comment comment;

    VideoController videoController;
    ShortController shortController;

    @FXML
    private AnchorPane mainPanel;

    @FXML
    private Circle profilePic;

    @FXML
    private Label usernameLabel;

    @FXML
    private ContextMenu changeMenu;

    @FXML
    private MenuItem editItem;

    @FXML
    private MenuItem deleteItem;

    @FXML
    private Label dateLabel;

    @FXML
    private Label textLabel;

    @FXML
    private Label likesLabel;

    @FXML
    private Button submitButton;

    @FXML
    private Button likeBtn;

    @FXML
    private Button dislikeBtn;

    @FXML
    private TextArea textArea;

    @FXML
    void likeComment() throws IOException {
        if (videoController != null) {
            boolean canLike = Request.likeVideoComment("L", MainController.user, comment);

            if (canLike) {
                dislikeBtn.setDisable(true);
            }
            else {
                dislikeBtn.setDisable(false);
                Request.unLikeVideoComment("L", MainController.user, comment);
            }

            System.out.println("| comment was liked");
            return;
        }

        if (shortController != null) {
            boolean canLike = Request.likeShortComment("L", MainController.user, comment);

            if (canLike) {
                dislikeBtn.setDisable(true);
            }
            else {
                dislikeBtn.setDisable(false);
                Request.unLikeShortComment("L", MainController.user, comment);
            }

            System.out.println("| comment was liked");
            return;
        }

        System.out.println("| comment like was failed");
    }

    @FXML
    void dislikeComment() throws IOException {
        if (videoController != null) {
            boolean canDislike = Request.likeVideoComment("D", MainController.user, comment);

            if (canDislike) {
                likeBtn.setDisable(true);
            }
            else {
                likeBtn.setDisable(false);
                Request.unLikeVideoComment("D", MainController.user, comment);
            }

            System.out.println("| comment was disliked");
            return;
        }

        if (shortController != null) {
            boolean canDislike = Request.likeShortComment("D", MainController.user, comment);

            if (canDislike) {
                likeBtn.setDisable(true);
            }
            else {
                likeBtn.setDisable(false);
                Request.unLikeShortComment("D", MainController.user, comment);
            }

            System.out.println("| comment was disliked");
            return;
        }

        System.out.println("| comment dislike was failed");
    }

    @FXML
    void editComment() {
        // get info from old text
        String oldText = textLabel.getText();
        double textLayoutX = textLabel.getLayoutX();
        double textLayoutY = textLabel.getLayoutY();
        double textWidth = textLabel.getWidth();
        double textHeight = textLabel.getHeight();
        mainPanel.getChildren().remove(textLabel);

        // add a text area to get new value
        textArea = new TextArea(oldText);
        textArea.setLayoutX(textLayoutX);
        textArea.setLayoutY(textLayoutY);
        textArea.setPrefWidth(textWidth);
        textArea.setPrefHeight(textHeight);
        mainPanel.getChildren().add(2, textArea);

        changeMenu.hide();
        editItem.setDisable(true);
        submitButton.setVisible(true);
    }

    @FXML
    void deleteComment() throws IOException {
        if (videoController != null) {
            Request.deleteVideoComment(comment);
            System.out.println("| comment was deleted from video");
            return;
        }

        if (shortController != null) {
            Request.deleteShortComment(comment);
            System.out.println("| comment was deleted from short");
            return;
        }

        System.out.println("| comment deletion failed");
    }

    @FXML
    void submitChanges() {
        if (videoController != null) {
            comment.setComment(textArea.getText());
            try {
                Request.updateVideoComment(comment);
                System.out.println("| comment was updated");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            textArea.setText(textArea.getText());
            mainPanel.getChildren().remove(textArea);
            mainPanel.getChildren().add(2, textLabel);

            editItem.setDisable(false);
            submitButton.setVisible(false);
            return;
        }

        if (shortController != null) {
            comment.setComment(textArea.getText());
            try {
                Request.updateShortComment(comment);
                System.out.println("| comment was updated");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            textArea.setText(textArea.getText());
            mainPanel.getChildren().remove(textArea);
            mainPanel.getChildren().add(2, textLabel);

            editItem.setDisable(false);
            submitButton.setVisible(false);
            return;
        }

        System.out.println("| comment update failed");
    }

    public Circle getProfilePic() {
        return profilePic;
    }

    public Label getUsernameLabel() {
        return usernameLabel;
    }

    public MenuItem getEditItem() {
        return editItem;
    }

    public MenuItem getDeleteItem() {
        return deleteItem;
    }

    public Label getDateLabel() {
        return dateLabel;
    }

    public Label getTextLabel() {
        return textLabel;
    }

    public Label getLikesLabel() {
        return likesLabel;
    }
}

package org.project.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.project.youtube.Client.Model.Comment;
import org.project.youtube.Client.Model.Network.Request;
import org.project.youtube.Client.Model.User;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CommentController {
    Comment comment;

    @FXML
    private Label dateLabel;

    @FXML
    private Circle profilePic;

    @FXML
    private Label textLabel;

    @FXML
    private Label likesLabel;

    @FXML
    private ContextMenu changeMenu;

    @FXML
    private Label usernameLabel;

    @FXML
    private Button submitButton;

    @FXML
    void likeComment() {

    }

    @FXML
    void dislikeComment() {

    }

    @FXML
    void replyComment() {

    }

    @FXML
    void editComment() {

    }

    @FXML
    void deleteComment() {

    }

    @FXML
    void submitChanges() {

    }

    public void init() throws IOException {
        // TODO set username and profile pic
        User writer = Request.getUser(comment.getWriterYID());
        usernameLabel.setText(writer.getUsername());
        Image img = new Image(new ByteArrayInputStream(writer.getProfilePic()));
        profilePic.setFill(new ImagePattern(img));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        dateLabel.setText(comment.getCreatedDateTime().format(formatter));
        likesLabel.setText(String.valueOf(comment.getLike()));

    }
}

package org.project.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import org.project.youtube.Client.Model.Comment;

import java.net.URL;
import java.util.ResourceBundle;

public class CommentController implements Initializable {
    static Comment comment;

    @FXML
    private Label dateLabel;

    @FXML
    private Circle profilePic;

    @FXML
    private Label textLabel;

    @FXML
    private Label usernameLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void likeComment() {

    }

    @FXML
    void dislikeComment() {

    }

    @FXML
    void replyComment() {

    }
}

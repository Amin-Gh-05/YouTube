package org.project.youtube.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SignupController {

    @FXML
    private Button SignUpButton;

    @FXML
    private TextField emailAddress;

    @FXML
    private PasswordField passWord;

    @FXML
    private Button signInButton;

    @FXML
    private TextField userName;

    @FXML
    void signIn(ActionEvent event) throws IOException {
        // change scene to login panel
        Stage signupStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Stage loginStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/project/youtube/login-view.fxml")));
        Scene scene = new Scene(root);
        loginStage.setScene(scene);

        System.out.println("| redirect to login panel");

        loginStage.show();
        signupStage.close();
    }

    @FXML
    void signUp(ActionEvent event) {

    }
}
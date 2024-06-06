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

public class LoginController {

    @FXML
    private Button SignUpButton;

    @FXML
    private PasswordField passWord;

    @FXML
    private Button signInButton;

    @FXML
    private TextField userName;

    @FXML
    private Button returnButton;

    @FXML
    void signIn(ActionEvent event) {

    }

    @FXML
    void signUp(ActionEvent event) throws IOException {
        // get current stage
        Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // run a new stage
        Stage signupStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/project/youtube/signup-view.fxml")));
        Scene scene = new Scene(root);
        signupStage.setScene(scene);

        signupStage.show();
        loginStage.close();

        System.out.println("| redirect to signup panel");
    }

    @FXML
    void turnBack(ActionEvent event) {
        // get current stage
        Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // restore the main page
        loginStage.close();
        MainController.mainStage.show();

        System.out.println("| redirect to main panel");
    }
}

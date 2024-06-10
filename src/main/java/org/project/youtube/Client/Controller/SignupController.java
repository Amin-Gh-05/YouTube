package org.project.youtube.Client.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private Button returnButton;

    @FXML
    void signIn(ActionEvent event) throws IOException {
        // get current stage
        Stage signupStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // run a new stage and replace the page
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
        if (!checkUsername(userName.getText())) {
            System.out.println("| username not valid");
            usernameAlert();
            return;
        }
        if (!checkEmail(emailAddress.getText())) {
            System.out.println("| email not valid");
            emailAlert();
            return;
        }
        if (!checkPassword(passWord.getText())) {
            System.out.println("| password not valid");
            passwordAlert();
            return;
        }
        //TODO
    }

    @FXML
    void turnBack(ActionEvent event) {
        // get current stage
        Stage signupStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // restore the main page
        signupStage.close();
        MainController.mainStage.show();

        System.out.println("| redirect to main panel");
    }

    private boolean checkUsername(String username) {
        // check if username is already used
        if (findUsername(username)) {
            System.out.println("| username already exists");
            return false;
        }
        // regex pattern of username
        Pattern pattern = Pattern.compile("(?=.{6,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])");
        Matcher matcher = pattern.matcher(username);
        return matcher.find();
    }

    private boolean checkEmail(String email) {
        // check if email is already taken
        if (findEmail(email)) {
            System.out.println("| email already exists");
            return false;
        }
        // regex pattern of email
        Pattern pattern = Pattern.compile("[\\w-.]+@[\\w-.]+\\.[\\w-]{2,4}");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    private boolean checkPassword(String password) {
        // regex pattern of password
        Pattern pattern = Pattern.compile("(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    private boolean findUsername(String username) {
        //TODO
        return false;
    }

    private boolean findEmail(String email) {
        //TODO
        return false;
    }

    private void usernameAlert() {
        // show alert for invalid username
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Username");
        alert.setHeaderText("Please enter a valid username");
        alert.setContentText("A valid username should be unique with 6-20 characters including numbers and letters, _ and .");
        if (alert.showAndWait().get() == ButtonType.OK) {
            userName.clear();
            System.out.println("| try again with new username");
        }
    }

    private void emailAlert() {
        // show alert for invalid email
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Email");
        alert.setHeaderText("Please enter a valid email");
        alert.setContentText("A valid email should be unique and consistent with the email address");
        if (alert.showAndWait().get() == ButtonType.OK) {
            emailAddress.clear();
            System.out.println("| try again with new email");
        }
    }

    private void passwordAlert() {
        // show alert for invalid password
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Password");
        alert.setHeaderText("Please enter a valid password");
        alert.setContentText("A valid password should be 8-20 characters including number and letter or special characters");
        if (alert.showAndWait().get() == ButtonType.OK) {
            passWord.clear();
            System.out.println("| try again with new password");
        }
    }
}
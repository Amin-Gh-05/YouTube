package org.project.youtube.Client.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;
import org.project.youtube.Client.Main;
import org.project.youtube.Client.Model.Network.Request;
import org.project.youtube.Client.Model.User;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    void signIn(ActionEvent event) throws IOException {
        int usernameInt = checkUsername(userName.getText());
        if (usernameInt == 0) {
            return;
        }
        if (!checkPassword(passWord.getText())) {
            return;
        }
        User user = Request.login(usernameInt, userName.getText(), DigestUtils.sha256Hex(passWord.getText()));
        Main.setUser(user);

        // get current stage
        Stage signupStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // restore the main page
        signupStage.close();
        MainController.mainStage.show();

        System.out.println("| redirect to main panel");
    }

    @FXML
    void signUp(ActionEvent event) throws IOException {
        // get current stage
        Stage loginStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // run a new stage
        Stage signupStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/project/youtube/Client/signup-view.fxml")));
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

    private boolean findUsername(String username) throws IOException {
        return Request.findUsername(username);
    }

    private boolean findEmail(String email) throws IOException {
        return Request.findEmail(email);
    }

    private int checkUsername(String username) throws IOException {
        // regex pattern of username
        Pattern pattern = Pattern.compile("(?=.{6,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])");
        Matcher matcher = pattern.matcher(username);
        // regex pattern of email
        Pattern pattern2 = Pattern.compile("[\\w-.]+@[\\w-.]+\\.[\\w-]{2,4}");
        Matcher matcher2 = pattern2.matcher(username);

        //username
        if (matcher.find()) {
            if (!findUsername(username)) {
                usernameAlert(false);
                return 0;
            }
            return 1;
        }
        //email
        if (matcher2.find()) {
            if (!findEmail(username)) {
                usernameAlert(false);
                return 0;
            }
            return 2;
        }

        usernameAlert(true);
        return 0;
    }

    private boolean checkPassword(String password) {
        // regex pattern of password
        Pattern pattern = Pattern.compile("(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}");
        Matcher matcher = pattern.matcher(password);
        if (!matcher.find()){
            passwordAlert();
            return false;
        }
        return true;
    }

    private void usernameAlert(boolean a) {
        // show alert for invalid username
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (a){
            alert.setTitle("Invalid Username or Email");
        }
        else {
            alert.setTitle("Username or Email Does not Exist");
        }
        alert.setHeaderText("Please enter a valid username");
        alert.setContentText("A valid username should be unique with 6-20 characters including numbers and letters, _ and .\nA valid email should be unique and consistent with the email address");
        if (alert.showAndWait().get() == ButtonType.OK) {
            userName.clear();
            System.out.println("| try again with new username");
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

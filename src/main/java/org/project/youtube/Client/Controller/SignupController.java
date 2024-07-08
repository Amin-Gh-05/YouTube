package org.project.youtube.Client.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.codec.digest.DigestUtils;
import org.project.youtube.Client.Model.Channel;
import org.project.youtube.Client.Model.Network.Request;
import org.project.youtube.Client.Model.Playlist;
import org.project.youtube.Client.Model.User;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupController {

    @FXML
    private TextField emailAddress;

    @FXML
    private PasswordField passWord;

    @FXML
    private TextField userName;

    @FXML
    void signIn(ActionEvent event) throws IOException {
        // get current stage
        Stage signupStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // run a new stage and replace the page
        Stage loginStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/project/youtube/Client/login-view.fxml")));
        Scene scene = new Scene(root);
        loginStage.setScene(scene);

        System.out.println("| redirect to login panel");

        loginStage.show();
        signupStage.close();
    }

    @FXML
    void signUp(ActionEvent event) throws IOException {
        if (!checkUsername(userName.getText())) {
            return;
        }
        if (!checkEmail(emailAddress.getText())) {
            return;
        }
        if (!checkPassword(passWord.getText())) {
            return;
        }

        User user = Request.signup(userName.getText(), emailAddress.getText(), DigestUtils.sha256Hex(passWord.getText()));

        // create a personal channel for user
        Channel channel = new Channel(user.getHandle(), user.getFirstName() + " " + user.getLastName(), user.getYid(),
                null, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")),
                null, null, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        Request.createChannel(channel, user);

        // create watch-laters playlist
        Playlist watchLaters = new Playlist(new ArrayList<>(), UUID.randomUUID(), "Watch Laters", channel.getHandle(),
                "Here comes the videos you marked to watch later", new ArrayList<>(), null);
        Request.createPlaylist(watchLaters);
        Request.addPlaylistToChannel(watchLaters, channel);

        // create liked-videos playlist
        Playlist likedVideos = new Playlist(new ArrayList<>(), UUID.randomUUID(), "Liked Videos", channel.getHandle(),
                "Here comes the videos you have liked", new ArrayList<>(), null);
        Request.createPlaylist(likedVideos);
        Request.addPlaylistToChannel(likedVideos, channel);

        MainController.user = user;
        MainController.channel = channel;
        turnBack(event);
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

    private boolean checkUsername(String username) throws IOException {
        // regex pattern of username
        Pattern pattern = Pattern.compile("(?=.{6,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])");
        Matcher matcher = pattern.matcher(username);

        if (!matcher.find()) {
            usernameAlert(true);
            return false;
        }

        // check if username is already used
        if (findUsername(username)) {
            usernameAlert(false);
            return false;
        }

        return true;
    }

    private boolean checkEmail(String email) throws IOException {
        // regex pattern of email
        Pattern pattern = Pattern.compile("[\\w-.]+@[\\w-.]+\\.[\\w-]{2,4}");
        Matcher matcher = pattern.matcher(email);

        if (!matcher.find()) {
            emailAlert(true);
            return false;
        }

        // check if email is already taken
        if (findEmail(email)) {
            emailAlert(false);
            return false;
        }

        return true;
    }

    private boolean checkPassword(String password) {
        // regex pattern of password
        Pattern pattern = Pattern.compile("(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}");
        Matcher matcher = pattern.matcher(password);
        if (!matcher.find()) {
            passwordAlert();
            return false;
        }
        return true;
    }

    private boolean findUsername(String username) throws IOException {
        return Request.findUsername(username);
    }

    private boolean findEmail(String email) throws IOException {
        return Request.findEmail(email);
    }

    private void usernameAlert(boolean a) {
        // show alert for invalid username
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (a) {
            alert.setTitle("Invalid Username");
        } else {
            alert.setTitle("Username Already Exists");
        }
        alert.setHeaderText("Please enter a valid username");
        alert.setContentText("A valid username should be unique with 6-20 characters including numbers and letters, _ and .");
        if (alert.showAndWait().get() == ButtonType.OK) {
            userName.clear();
            System.out.println("| try again with new username");
        }
    }

    private void emailAlert(boolean a) {
        // show alert for invalid email
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (a) {
            alert.setTitle("Invalid Email");
        } else {
            alert.setTitle("Email Already Exists");
        }
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
package org.project.youtube.Client.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.project.youtube.Client.Main;
import org.project.youtube.Client.Model.User;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Objects;

public class ProfileController {
    private static User user;

    @FXML
    private Button PreSign;

    @FXML
    private Label DOBField;

    @FXML
    private Button ManageChannels;

    @FXML
    private Button editProfile;

    @FXML
    private Label emailField;

    @FXML
    private Label genderField;

    @FXML
    private HBox hyperlinks;

    @FXML
    private Label lastnameField;

    @FXML
    private Label nameField;

    @FXML
    private Circle profile;

    @FXML
    private Label regionField;

    @FXML
    private Label usernameField;

    public void initialize() throws IOException {
        user = MainController.getUser();

        usernameField.setText(user.getUsername());

        if(!user.isPremium())
            PreSign.setVisible(false);

        if(user.getProfilePic() == null){

        }
        else{
            Image img = new Image(new ByteArrayInputStream(user.getProfilePic()));
            profile.setFill(new ImagePattern(img));
        }

        nameField.setText(user.getFirstName());
        lastnameField.setText(user.getLastName());
        emailField.setText(user.getEmail());
        DOBField.setText(user.getDateOfBirth().toString());
        genderField.setText(user.getGender());
        regionField.setText(user.getRegion());
    }

    //Changing scenes
    public void switchToManageChannels(ActionEvent e) throws IOException{
        // TODO
    }

    public void switchToEditProfile(ActionEvent e) throws IOException{
        // TODO
    }

    public ProfileController(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("profile-view.fxml"));
        stage.setTitle("Profile setting");
        stage.setScene(new Scene(root, 1220, 740));
        stage.setResizable(false);
        stage.show();
    }
}

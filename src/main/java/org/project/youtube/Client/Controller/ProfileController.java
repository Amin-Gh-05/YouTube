package org.project.youtube.Client.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.project.youtube.Client.Model.User;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProfileController {
    public static List<String> countries = new ArrayList<>();
    static User user;

    @FXML
    private Button preSign;

    @FXML
    private Label DOBField, MOBField, YOBField;

    @FXML
    private Button manageChannels;

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
    private ChoiceBox<String> regionBox;

    @FXML
    private Label usernameField;

    @FXML
    private TextField nameEditor, lastNameEditor, emailEditor;

    @FXML
    private ImageView setChanges;

    @FXML
    private CheckBox maleBox, femaleBox;

    @FXML
    private DatePicker datePicker;

    public void initialize() throws IOException {
        user = MainController.user;

        usernameField.setText(user.getUsername());

        if (!user.isPremium())
            preSign.setVisible(false);

        if (user.getProfilePic() == null) {
            Image img = new Image("images\\profile-circle.svg");
            profile.setFill(new ImagePattern(img));
        } else {
            Image img = new Image(new ByteArrayInputStream(user.getProfilePic()));
            profile.setFill(new ImagePattern(img));
        }

        nameField.setText(user.getFirstName());
        lastnameField.setText(user.getLastName());
        emailField.setText(user.getEmail());
        String dob = String.valueOf(user.getDateOfBirth());
        MOBField.setText(dob.substring(5, 6));
        DOBField.setText(dob.substring(8, 9));
        YOBField.setText(dob.substring(0, 3));
        genderField.setText(user.getGender());
        regionField.setText(user.getRegion());

        String[] locales = Locale.getISOCountries();
        for (String countryCode : locales) {
            Locale obj = new Locale("", countryCode);
            countries.add(obj.getDisplayCountry());
        }
        regionBox.getItems().addAll(countries);

        // building links


    }

    // changing scenes
    public void switchToManageChannels(ActionEvent e) throws IOException {

    }

    public void switchToEditProfile(ActionEvent e) throws IOException {

    }

    public void makeChanges(ActionEvent e) throws IOException {

    }
}

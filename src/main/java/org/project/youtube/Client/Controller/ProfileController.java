package org.project.youtube.Client.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.project.youtube.Client.Main;
import org.project.youtube.Client.Model.User;
import org.controlsfx.control.Notifications;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class ProfileController {
    static User user;
    public static List<String> countries = new ArrayList<>();

    // ------------------------ HEADER ------------------------
    @FXML
    private Label usernameField;

    @FXML
    private Label DJField, MJField, YJField;

    @FXML
    private Button manageChannels;

    @FXML
    private Button editProfile;

    @FXML
    private ImageView makeChanges;

    @FXML
    private Button preSign;

    @FXML
    private Circle profile;

    // -------------------- PERSONAL INFO ---------------------
    @FXML
    private Label DOBField, MOBField, YOBField;

    @FXML
    private Label nameField, lastnameField, emailField, genderField, regionField;

    // ------------------------ EDITOR ------------------------
    @FXML
    private DatePicker datePicker;

    @FXML
    private ImageView usernameAlert, nameAlert, lastnameAlert, emailAlert, genderAlert;

    @FXML
    private TextField usernameEditor, nameEditor, lastnameEditor, emailEditor;

    @FXML
    private CheckBox femaleBox, maleBox;

    @FXML
    private ChoiceBox<String> regionBox;

    public void initialize() throws IOException {
        user = Main.getUser();

        //initializing header info
        usernameField.setText(user.getUsername());
        if(!user.isPremium())
            preSign.setVisible(false);

        if(user.getProfilePic() == null){
            Image img = new Image("images\\profile-circle.svg");
            profile.setFill(new ImagePattern(img));
        }
        else{
            Image img = new Image(new ByteArrayInputStream(user.getProfilePic()));
            profile.setFill(new ImagePattern(img));
        }
        String dj = String.valueOf(user.getJoinedDate());
        MJField.setText(dj.substring(5, 6));
        DJField.setText(dj.substring(8, 9));
        YJField.setText(dj.substring(0, 3));

        // initializing personal info
        nameField.setText(user.getFirstName());
        lastnameField.setText(user.getLastName());
        emailField.setText(user.getEmail());
        String dob = String.valueOf(user.getDateOfBirth());
        MOBField.setText(dob.substring(5, 6));
        DOBField.setText(dob.substring(8, 9));
        YOBField.setText(dob.substring(0, 3));
        genderField.setText(user.getGender());
        regionField.setText(user.getRegion());

        // creating region list
        String[] locales = Locale.getISOCountries();
        for (String countryCode : locales) {
            Locale obj = new Locale("", countryCode);
            countries.add(obj.getDisplayCountry());
        }
        regionBox.getItems().addAll(countries);

    }

    //profile editor functions
    public void switchToEditProfile(ActionEvent e) throws IOException{
        editProfile.setDisable(true);
        makeChanges.setVisible(true);
        usernameField.setVisible(false);
        manageChannels.setVisible(false);
        genderField.setVisible(false);
        regionField.setVisible(false);
        nameEditor.setVisible(true);
        nameEditor.setText(nameField.getText());
        lastnameEditor.setVisible(true);
        lastnameEditor.setText(lastnameField.getText());
        emailEditor.setVisible(true);
        emailEditor.setText(emailField.getText());
        datePicker.setVisible(true);
        datePicker.setValue(LocalDate.of(Integer.parseInt(YOBField.getText()), Integer.parseInt(MOBField.getText()), Integer.parseInt(DOBField.getText())));
        femaleBox.setVisible(true);
        maleBox.setVisible(true);
        if(genderField.getText().equals("female")){
            femaleBox.setSelected(true);
        }
        else if(genderField.getText().equals("male")){
            maleBox.setSelected(true);
        }
        regionBox.setVisible(true);
        regionBox.setValue(regionField.getText());
    }

    public void makeChanges(ActionEvent e) throws IOException{

    }

    //Changing scenes
    public void switchToManageChannels(ActionEvent e) throws IOException{
        // TODO
    }


}

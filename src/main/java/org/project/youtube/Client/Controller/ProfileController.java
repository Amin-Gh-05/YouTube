package org.project.youtube.Client.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.controlsfx.control.Notifications;
import org.project.youtube.Client.Model.Channel;
import org.project.youtube.Client.Model.Network.Request;
import org.project.youtube.Client.Model.User;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ProfileController {
    public static List<String> countries = new ArrayList<>();

    // ------------------------ LINKS ------------------------

    static User user;

    List<ImageView> logos = new ArrayList<>();
    List<Hyperlink> urls = new ArrayList<>();

    // ------------------------ HEADER ------------------------

    @FXML
    private Label usernameField;

    @FXML
    private Label DJField, MJField, YJField;

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

    @FXML
    private VBox linksBox;

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
        user = MainController.user;

        // initializing header info
        usernameField.setText(user.getUsername());
        if (!user.isPremium())
            preSign.setVisible(false);

        if (user.getProfilePic() == null) {
            Image img = new Image("/org/project/youtube/Client/images/sample-profile.png");
            profile.setFill(new ImagePattern(img));
        } else {
            Image img = new Image(new ByteArrayInputStream(user.getProfilePic()));
            profile.setFill(new ImagePattern(img));
        }
        LocalDate dj = user.getJoinedDate();
        MJField.setText(dj.format(DateTimeFormatter.ofPattern("MM")));
        DJField.setText(dj.format(DateTimeFormatter.ofPattern("dd")));
        YJField.setText(dj.format(DateTimeFormatter.ofPattern("yyyy")));

        // initializing personal info

        lastnameField.setText(user.getLastName());
        emailField.setText(user.getEmail());
        LocalDate dob = user.getDateOfBirth();
        if (dob != null) {
            MOBField.setText(dob.format(DateTimeFormatter.ofPattern("MM")));
            DOBField.setText(dob.format(DateTimeFormatter.ofPattern("dd")));
            YOBField.setText(dob.format(DateTimeFormatter.ofPattern("yyyy")));
        }
        genderField.setText(user.getGender());
        regionField.setText(user.getRegion());

        // creating region list
        String[] locales = Locale.getISOCountries();
        for (String countryCode : locales) {
            Locale obj = new Locale("", countryCode);
            countries.add(obj.getDisplayCountry());
        }
        regionBox.getItems().addAll(countries);

        // initializing hyperlinks ===============================
        logos.add(new ImageView(new Image("/org/project/youtube/Client/images/website.png")));
        logos.add(new ImageView(new Image("/org/project/youtube/Client/images/facebook.png")));
        logos.add(new ImageView(new Image("/org/project/youtube/Client/images/instagram.png")));
        logos.add(new ImageView(new Image("/org/project/youtube/Client/images/twitter.png")));
        logos.add(new ImageView(new Image("/org/project/youtube/Client/images/telegram.png")));
        logos.add(new ImageView(new Image("/org/project/youtube/Client/images/tiktok.png")));
        logos.add(new ImageView(new Image("/org/project/youtube/Client/images/discord.png")));
        logos.add(new ImageView(new Image("/org/project/youtube/Client/images/linkedin.png")));
        logos.add(new ImageView(new Image("/org/project/youtube/Client/images/reddit.png")));

        Channel ch = Request.getChannel(user.getHandle());
        urls.add(new Hyperlink(ch.getWebsite()));
        urls.add(new Hyperlink(ch.getFacebook()));
        urls.add(new Hyperlink(ch.getInstagram()));
        urls.add(new Hyperlink(ch.getX()));
        urls.add(new Hyperlink(ch.getTelegram()));
        urls.add(new Hyperlink(ch.getTiktok()));
        urls.add(new Hyperlink(ch.getDiscord()));
        urls.add(new Hyperlink(ch.getLinkedin()));
        urls.add(new Hyperlink(ch.getReddit()));

        // indexes: 0-website  1-facebook  2-instagram  3-X  4-telegram  5-tiktok  6-discord  7-linkedin  8-reddit
        for (int i = 0; i < 9; i++) {
            HBox hbox = new HBox();
            hbox.getStyleClass().add("linkHBoxes");

            logos.get(i).setFitHeight(30);
            logos.get(i).setFitWidth(30);
            hbox.getChildren().add(logos.get(i));

            urls.get(i).getStyleClass().add("hyperlink");
            hbox.getChildren().add(urls.get(i));

            linksBox.getChildren().add(hbox);
        }
    }

    @FXML
    void returnHome(ActionEvent event) {
        // get current stage
        Stage profileStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // restore the main page
        profileStage.close();
        MainController.mainStage.show();

        System.out.println("| redirect to main panel");
    }

    // profile editor functions

    @FXML
    void switchToEditProfile() {
        // change button statements
        editProfile.setDisable(true);
        makeChanges.setVisible(true);

        // invisible labels
        genderField.setVisible(false);

        // visible editors
        usernameEditor.setVisible(true);
        usernameEditor.setText(usernameField.getText());

        nameEditor.setVisible(true);
        nameEditor.setText(nameField.getText());

        lastnameEditor.setVisible(true);
        lastnameEditor.setText(lastnameField.getText());

        emailEditor.setVisible(true);
        emailEditor.setText(emailField.getText());

        datePicker.setVisible(true);

        femaleBox.setVisible(true);
        maleBox.setVisible(true);

        regionBox.setVisible(true);
        regionBox.setValue(regionField.getText());

    }

    // checking for errors and setting user information

    @FXML
    void CheckChanges() throws IOException {
        usernameAlert.setVisible(false);
        nameAlert.setVisible(false);
        lastnameAlert.setVisible(false);
        emailAlert.setVisible(false);
        genderAlert.setVisible(false);
        if (!checkErrors()) {
            setInformation();
        }

    }

    public boolean checkErrors() {
        boolean flag = false;
        if (usernameEditor.getText().isEmpty()) {
            flag = true;
            usernameAlert.setVisible(true);
            Notifications.create().title("Error").text("Username field can't be empty.").showError();
        }
        if (nameEditor.getText().isEmpty()) {
            flag = true;
            nameAlert.setVisible(true);
            Notifications.create().title("Error").text("Name field can't be empty.").showError();
        }

        if (lastnameEditor.getText().isEmpty()) {
            flag = true;
            lastnameAlert.setVisible(true);
            Notifications.create().title("Error").text("Last name field can't be empty.").showError();
        }
        if (emailEditor.getText().isEmpty()) {
            flag = true;
            emailAlert.setVisible(true);
            Notifications.create().title("Error").text("Email field can't be empty.").showError();
        }
        if ((maleBox.isSelected() && femaleBox.isSelected()) || (!maleBox.isSelected() && !femaleBox.isSelected())) {
            flag = true;
            genderAlert.setVisible(true);
            Notifications.create().title("Error").text("Please select only one gender. We live in Iran.").showError();
        }

        if (!checkRegex("[\\w-.]+@[\\w-.]+\\.[\\w-]{2,4}", emailEditor.getText())) {
            flag = true;
            emailAlert.setVisible(true);
            Notifications.create().title("Error").text("Enter a valid Email.").showError();
        }
        return flag;
    }

    public boolean checkRegex(String reg, String url) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(url);

        return matcher.find();
    }

    public void setInformation() throws IOException {
        usernameField.setText(usernameEditor.getText());
        user.setUsername(usernameEditor.getText());
        usernameEditor.setVisible(false);

        nameField.setText(nameEditor.getText());
        user.setFirstName(nameEditor.getText());
        nameEditor.setVisible(false);

        lastnameField.setText(lastnameEditor.getText());
        user.setLastName(lastnameEditor.getText());
        lastnameEditor.setVisible(false);

        emailField.setText(emailEditor.getText());
        user.setEmail(emailEditor.getText());
        emailEditor.setVisible(false);

        LocalDate dob = datePicker.getValue();
        if (dob != null) {
            MOBField.setText(dob.format(DateTimeFormatter.ofPattern("MM")));
            DOBField.setText(dob.format(DateTimeFormatter.ofPattern("dd")));
            YOBField.setText(dob.format(DateTimeFormatter.ofPattern("yyyy")));
        }
        datePicker.setVisible(false);

        if (maleBox.isSelected()) {
            user.setGender("male");
            genderField.setText("male");
        } else {
            user.setGender("female");
            genderField.setText("female");
        }
        maleBox.setVisible(false);
        femaleBox.setVisible(false);
        genderField.setVisible(true);

        regionField.setText(regionBox.getValue());
        user.setRegion(regionBox.getValue());
        regionBox.setVisible(false);

        makeChanges.setVisible(false);
        Request.updateUser(user);
        System.out.println("| user was updated");
        editProfile.setDisable(false);
    }

    private File pickFile() {
        // show the file picker dialog
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an Image");

        // file extension filers
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        return fileChooser.showOpenDialog(new Stage());
    }

    @FXML
    void editProfilePic() throws IOException {
        File file = pickFile();
        if (file != null) {
            user.setProfilePic(FileUtils.readFileToByteArray(file));
        }
        Request.updateUser(user);
        System.out.println("| profile picture was updated");
    }
}

package org.project.youtube.Client.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.project.youtube.Client.Model.Channel;
import org.project.youtube.Client.Model.User;
import org.w3c.dom.events.MouseEvent;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.project.youtube.Client.Model.Network.Request.getChannel;
import static org.project.youtube.Client.Model.Network.Request.updateUser;

public class ProfileController {
    public static List<String> countries = new ArrayList<>();
    static User user;

    MainController controller;

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

    @FXML
    private ImageView editProfileButton;

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

    // ------------------------ LINKS ------------------------
    public static Image alertSign = new Image("/org/project/youtube/Client/images/important.png");

    List<HBox> linkBoxes = new ArrayList<>();
    List<ImageView> alertSigns = new ArrayList<>();
    List<ImageView> logos = new ArrayList<>();
    List<Hyperlink> urls = new ArrayList<>();
    List<TextField> urlEditor = new ArrayList<>();

    public void initialize() throws IOException {
        user = MainController.user;

        //initializing header info
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

        Channel ch = getChannel(user.getHandle());
        urls.add(new Hyperlink(ch.getWebsite()));
        urls.add(new Hyperlink(ch.getFacebook()));
        urls.add(new Hyperlink(ch.getInstagram()));
        urls.add(new Hyperlink(ch.getX()));
        urls.add(new Hyperlink(ch.getTelegram()));
        urls.add(new Hyperlink(ch.getTiktok()));
        urls.add(new Hyperlink(ch.getDiscord()));
        urls.add(new Hyperlink(ch.getLinkedin()));
        urls.add(new Hyperlink(ch.getReddit()));

        for(int i = 0; i < 9; i++){ // indexes: 0-website  1-facebook  2-instagram  3-X  4-telegram  5-tiktok  6-discord  7-linkedin  8-reddit
            HBox hbox = new HBox();
            hbox.getStylesheets().add(Objects.requireNonNull(getClass().getResource("profile-view.css")).toExternalForm());
            hbox.getStyleClass().add("linkHBoxes");

            ImageView alert = new ImageView(alertSign);
            alert.setVisible(false);
            alert.setFitHeight(30);
            alertSigns.add(alert);
            hbox.getChildren().add(alert);

            logos.get(i).setFitHeight(30);
            hbox.getChildren().add(logos.get(i));

            urls.get(i).getStylesheets().add(Objects.requireNonNull(getClass().getResource("profile-view.css")).toExternalForm());
            urls.get(i).getStyleClass().add("hyperlink");
            hbox.getChildren().add(urls.get(i));

            linkBoxes.add(hbox);
            if(urls.get(i).getText() != null)
                linksBox.getChildren().add(hbox);

            urlEditor.get(i).getStylesheets().add(Objects.requireNonNull(getClass().getResource("profile-view.css")).toExternalForm());
            urlEditor.get(i).getStyleClass().add("hyperlink");
        }
    }

    // profile editor functions
    public void switchToEditProfile(ActionEvent e) throws IOException {
        // change button statements
        editProfile.setDisable(true);
        makeChanges.setVisible(true);

        // invisible labels
        usernameField.setVisible(false);
        manageChannels.setVisible(false);
        genderField.setVisible(false);
        regionField.setVisible(false);

        // visible editors
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
        if (genderField.getText().equals("female")) {
            femaleBox.setSelected(true);
        } else if (genderField.getText().equals("male")) {
            maleBox.setSelected(true);
        }
        regionBox.setVisible(true);
        regionBox.setValue(regionField.getText());

        // link fields
        linksBox.getChildren().clear();
        for(int i = 0; i < 9; i++) {
            linkBoxes.get(i).getChildren().removeLast();
            urlEditor.get(i).setText(urls.get(i).getText());
            linkBoxes.get(i).getChildren().add(urlEditor.get(i));
        }
        linksBox.getChildren().addAll(linkBoxes);
    }

    //checking for errors and setting user information
    @FXML
    void makeChanges(ActionEvent e) throws IOException {
        for(HBox hbox: linkBoxes) {
            hbox.getChildren().getFirst().setVisible(false);
        }
        if(!checkErrors()) {
            setInformation();
        }
    }

    public boolean checkErrors(){
        boolean flag = false;

        return flag;
    }

    public boolean checkRegex(String reg, String url){
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(url);

        if (!matcher.find()) {
            return false;
        }

        return true;
    }

    public void setInformation(){

    }

    @FXML
    void editProfilePic(MouseEvent event) throws IOException {
        File file = pickFile();
        if (file != null) {
            user.setProfilePic(FileUtils.readFileToByteArray(file));
        }
        updateUser(user);
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

        File file = fileChooser.showOpenDialog(new Stage());
        return file;
    }



    // changing scenes
    public void switchToManageChannels(ActionEvent e) throws IOException {
        // TODO
    }

    @FXML
    void editProfilePic() {

    }
}

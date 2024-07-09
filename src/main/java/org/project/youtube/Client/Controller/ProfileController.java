package org.project.youtube.Client.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import org.project.youtube.Client.Model.Channel;
import org.project.youtube.Client.Model.User;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.project.youtube.Client.Model.Network.Request.getChannel;

public class ProfileController {
    public static List<String> countries = new ArrayList<>();
    static User user;

    public static HBox webBox = new HBox();
    public static HBox fbBox = new HBox();
    public static HBox igBox = new HBox();
    public static HBox xBox = new HBox();
    public static HBox tgBox = new HBox();
    public static HBox tiktokBox = new HBox();
    public static HBox discordBox = new HBox();
    public static HBox InBox = new HBox();
    public static HBox redditBox = new HBox();

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
        user = MainController.user;

        //initializing header info
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

        // Creating hyperlinks
        Channel ch = getChannel(user.getHandle());
        Font font = new Font("System", 15);
        //webBox
        Image webimg = new Image("images/icons8-website-50.png");
        ImageView webView = new ImageView(webimg);
        Hyperlink webUrl = new Hyperlink(ch.getWebsite());
        webUrl.setFont(font);
        webBox.getChildren().add(webView);
        webBox.getChildren().add(webUrl);

        //fbBox
        Image fbimg = new Image("icons8-facebook-48.png");
        ImageView fbView = new ImageView(fbimg);
        Hyperlink fbUrl = new Hyperlink(ch.getFacebook());
        fbUrl.setFont(font);
        fbBox.getChildren().add(fbView);
        fbBox.getChildren().add(fbUrl);

        //igBox
        Image igimg = new Image("images/icons8-insta-48.png");
        ImageView igView = new ImageView(igimg);
        Hyperlink igUrl = new Hyperlink(ch.getInstagram());
        igUrl.setFont(font);
        igBox.getChildren().add(igView);
        igBox.getChildren().add(igUrl);

        //xBox = new HBox();
        Image ximg = new Image("images/icons8-twitterx-50.png");
        ImageView xView = new ImageView(ximg);
        Hyperlink xUrl = new Hyperlink(ch.getX());
        xUrl.setFont(font);
        xBox.getChildren().add(xView);
        xBox.getChildren().add(xUrl);

        //tgBox
        Image tgimg = new Image("images/icons8-telegram-48.png");
        ImageView tgView = new ImageView(tgimg);
        Hyperlink tgUrl = new Hyperlink(ch.getTelegram());
        tgUrl.setFont(font);
        tgBox.getChildren().add(tgView);
        tgBox.getChildren().add(tgUrl);

        //tiktokBox
        Image tiktokimg = new Image("images/icons8-tiktok-48.png");
        ImageView tiktokView = new ImageView(tiktokimg);
        Hyperlink tiktokUrl = new Hyperlink(ch.getTiktok());
        tiktokUrl.setFont(font);
        tiktokBox.getChildren().add(tiktokView);
        tiktokBox.getChildren().add(tiktokUrl);

        //discordBox
        Image discordimg = new Image("images/Discord.png");
        ImageView discordView = new ImageView(discordimg);
        Hyperlink discordUrl = new Hyperlink(ch.getDiscord());
        discordUrl.setFont(font);
        discordBox.getChildren().add(discordView);
        discordBox.getChildren().add(discordUrl);

        //InBox
        Image Inimg = new Image("images/icons8-linkedin-48.png");
        ImageView InView = new ImageView(Inimg);
        Hyperlink InUrl = new Hyperlink(ch.getLinkedin());
        InUrl.setFont(font);
        InBox.getChildren().add(InView);
        InBox.getChildren().add(InUrl);

        //redditBox
        Image redditimg = new Image("images/reddit.png");
        ImageView redditView = new ImageView(redditimg);
        Hyperlink redditUrl = new Hyperlink(ch.getReddit());
        redditUrl.setFont(font);
        redditBox.getChildren().add(redditView);
        redditBox.getChildren().add(redditUrl);
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
    }

    //checking for errors and setting user information
    public void makeChanges(ActionEvent e) throws IOException {

    }



    // changing scenes
    public void switchToManageChannels(ActionEvent e) throws IOException {
        // TODO
    }


}

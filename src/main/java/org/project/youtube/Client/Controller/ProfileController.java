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
    static Font font = new Font("System", 15);

    private static HBox webBox = new HBox();
    private static Hyperlink webUrl = new Hyperlink();
    private static TextField webEditor = new TextField();

    private static HBox fbBox = new HBox();
    private static Hyperlink fbUrl = new Hyperlink();
    private static TextField fbEditor = new TextField();

    private static HBox igBox = new HBox();
    private static Hyperlink igUrl = new Hyperlink();
    private static TextField igEditor = new TextField();

    private static HBox xBox = new HBox();
    private static Hyperlink xUrl = new Hyperlink();
    private static TextField xEditor = new TextField();

    private static HBox tgBox = new HBox();
    private static Hyperlink tgUrl = new Hyperlink();
    private static TextField tgEditor = new TextField();

    private static HBox tiktokBox = new HBox();
    private static Hyperlink tiktokUrl = new Hyperlink();
    private static TextField tiktokEditor = new TextField();

    private static HBox discordBox = new HBox();
    private static Hyperlink discordUrl = new Hyperlink();
    private static TextField discordEditor = new TextField();

    private static HBox InBox = new HBox();
    private static Hyperlink InUrl = new Hyperlink();
    private static TextField InEditor = new TextField();

    private static HBox redditBox = new HBox();
    private static Hyperlink redditUrl = new Hyperlink();
    private static TextField redditEditor = new TextField();





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
        //webBox
        Image webimg = new Image("images/website.png");
        ImageView webView = new ImageView(webimg);
        webView.setFitHeight(30);
        webUrl = new Hyperlink(ch.getWebsite());
        webUrl.setStyle("profile-view: hyperlink");
        webBox.getChildren().add(webView);
        webBox.getChildren().add(webUrl);
        if(webUrl != null)
            linksBox.getChildren().add(webBox);

        //fbBox
        Image fbimg = new Image("facebook.png");
        ImageView fbView = new ImageView(fbimg);
        fbView.setFitHeight(30);
        fbUrl = new Hyperlink(ch.getFacebook());
        fbUrl.setStyle("profile-view: hyperlink");
        fbBox.getChildren().add(fbView);
        fbBox.getChildren().add(fbUrl);
        if(fbUrl != null)
            linksBox.getChildren().add(fbBox);

        //igBox
        Image igimg = new Image("images/instagram.png");
        ImageView igView = new ImageView(igimg);
        igView.setFitHeight(30);
        Hyperlink igUrl = new Hyperlink(ch.getInstagram());
        igUrl.setStyle("profile-view: hyperlink");
        igBox.getChildren().add(igView);
        igBox.getChildren().add(igUrl);
        if(igUrl != null)
            linksBox.getChildren().add(igBox);

        //xBox = new HBox();
        Image ximg = new Image("images/twitter.png");
        ImageView xView = new ImageView(ximg);
        xView.setFitHeight(30);
        Hyperlink xUrl = new Hyperlink(ch.getX());
        xUrl.setStyle("profile-view: hyperlink");
        xBox.getChildren().add(xView);
        xBox.getChildren().add(xUrl);
        if(xUrl != null)
            linksBox.getChildren().add(xBox);

        //tgBox
        Image tgimg = new Image("images/telegram.png");
        ImageView tgView = new ImageView(tgimg);
        tgView.setFitHeight(30);
        Hyperlink tgUrl = new Hyperlink(ch.getTelegram());
        tgUrl.setStyle("profile-view: hyperlink");
        tgBox.getChildren().add(tgView);
        tgBox.getChildren().add(tgUrl);
        if(tgUrl != null)
            linksBox.getChildren().add(tgBox);

        //tiktokBox
        Image tiktokimg = new Image("images/tiktok.png");
        ImageView tiktokView = new ImageView(tiktokimg);
        tiktokView.setFitHeight(30);
        Hyperlink tiktokUrl = new Hyperlink(ch.getTiktok());
        tiktokUrl.setStyle("profile-view: hyperlink");
        tiktokBox.getChildren().add(tiktokView);
        tiktokBox.getChildren().add(tiktokUrl);
        if(tiktokUrl != null)
            linksBox.getChildren().add(tiktokBox);

        //discordBox
        Image discordimg = new Image("images/discord.png");
        ImageView discordView = new ImageView(discordimg);
        discordView.setFitHeight(30);
        Hyperlink discordUrl = new Hyperlink(ch.getDiscord());
        discordUrl.setStyle("profile-view: hyperlink");
        discordBox.getChildren().add(discordView);
        discordBox.getChildren().add(discordUrl);
        if(discordUrl != null)
            linksBox.getChildren().add(discordBox);


        //InBox
        Image Inimg = new Image("images/linkedin.png");
        ImageView InView = new ImageView(Inimg);
        InView.setFitHeight(30);
        Hyperlink InUrl = new Hyperlink(ch.getLinkedin());
        InUrl.setStyle("profile-view: hyperlink");
        InBox.getChildren().add(InView);
        InBox.getChildren().add(InUrl);
        if(InUrl != null)
            linksBox.getChildren().add(InBox);

        //redditBox
        Image redditimg = new Image("images/reddit.png");
        ImageView redditView = new ImageView(redditimg);
        redditView.setFitHeight(30);
        Hyperlink redditUrl = new Hyperlink(ch.getReddit());
        redditUrl.setStyle("profile-view: hyperlink");
        redditBox.getChildren().add(redditView);
        redditBox.getChildren().add(redditUrl);
        if(redditUrl != null)
            linksBox.getChildren().add(redditBox);

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
        webBox.getChildren().remove(webUrl);
        webEditor.setText(webUrl.getText());
        webEditor.setFont(font);


    }

    //checking for errors and setting user information
    public void makeChanges(ActionEvent e) throws IOException {

    }



    // changing scenes
    public void switchToManageChannels(ActionEvent e) throws IOException {
        // TODO
    }


}

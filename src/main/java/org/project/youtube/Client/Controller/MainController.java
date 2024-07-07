package org.project.youtube.Client.Controller;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.project.youtube.Client.Model.Short;
import org.project.youtube.Client.Model.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class MainController implements Initializable {
    public static User user;
    public static Channel channel;

    static Stage mainStage;

    @FXML
    private FlowPane mainPanel;

    // ------------------------------ HEADER ------------------------------

    @FXML
    private HBox headerPanel;

    @FXML
    private Button moreButton;

    @FXML
    private TextField searchBox;

    @FXML
    private Button createButton;

    @FXML
    private Button signInButton;

    @FXML
    private Button profileButton;

    // ------------------------------ SIDE ------------------------------

    @FXML
    private VBox sideBar;

    @FXML
    private HBox homeBox;

    @FXML
    private HBox shortsBox;

    @FXML
    private HBox subsBox;

    @FXML
    private HBox channelBox;

    @FXML
    private HBox historyBox;

    @FXML
    private HBox playlistsBox;

    @FXML
    private HBox videosBox;

    @FXML
    private HBox latersBox;

    @FXML
    private HBox likedBox;

    @FXML
    private HBox settingsBox;

    @FXML
    private HBox helpBox;

    @FXML
    private Label homeLabel;

    @FXML
    private Label shortsLabel;

    @FXML
    private Label subLabel;

    @FXML
    private Label historyLabel;

    @FXML
    private Label settingsLabel;

    @FXML
    private Separator topSeparator;

    @FXML
    private Separator downSeparator;

    @FXML
    private Label youLabel;

    private boolean slideBar = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //refreshAll();
    }

    @FXML
    void refreshAll() {
        if (user == null) {
            createButton.setDisable(true);

            if (headerPanel.getChildren().contains(profileButton)) {
                headerPanel.getChildren().remove(profileButton);
                headerPanel.getChildren().add(signInButton);
            }
        } else {
            createButton.setDisable(false);

            if (headerPanel.getChildren().contains(signInButton)) {
                headerPanel.getChildren().remove(signInButton);
                headerPanel.getChildren().add(profileButton);
            }
        }

        searchBox.clear();
        loadHome();

        System.out.println("| main panel refreshed");
    }

    @FXML
    void slideSidebar() {
        playClickEffect(moreButton);

        if (slideBar) {
            slideBar = false;

            // remove children of sideBox
            sideBar.getChildren().remove(3, 6);
            sideBar.getChildren().remove(4, 9);
            sideBar.getChildren().remove(5);

            // remove child of HBoxes
            subsBox.getChildren().remove(1);
            shortsBox.getChildren().remove(1);
            homeBox.getChildren().remove(1);
            historyBox.getChildren().remove(1);
            settingsBox.getChildren().remove(1);
        } else {
            slideBar = true;

            // add child of HBoxes
            homeBox.getChildren().add(homeLabel);
            shortsBox.getChildren().add(shortsLabel);
            subsBox.getChildren().add(subLabel);
            historyBox.getChildren().add(historyLabel);
            settingsBox.getChildren().add(settingsLabel);

            // add children of sideBox
            sideBar.getChildren().add(3, topSeparator);
            sideBar.getChildren().add(4, youLabel);
            sideBar.getChildren().add(5, channelBox);
            sideBar.getChildren().add(7, playlistsBox);
            sideBar.getChildren().add(8, videosBox);
            sideBar.getChildren().add(9, latersBox);
            sideBar.getChildren().add(10, likedBox);
            sideBar.getChildren().add(11, downSeparator);
            sideBar.getChildren().add(13, helpBox);
        }
    }

    @FXML
    void signIn(ActionEvent event) throws IOException {
        // get current stage
        mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // load fxml of login page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/login-view.fxml"));
        Parent root = loader.load();

        // create a new stage and show it
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();

        // hide this page and open the sign in panel
        mainStage.hide();
        stage.show();

        System.out.println("| redirect to login panel");
    }

    @FXML
    void searchAll() {

    }

    @FXML
    void loadStudio(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/studio-view.fxml"));
        Parent root = loader.load();

        // create a new scene and set scene of stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        System.out.println("| redirect to studio panel");
    }

    @FXML
    void loadHome() {
        List<Video> videos = getHomeVideos(user);
        mainPanel.getChildren().clear();

        if (videos != null) {
            for (Video video : videos) {
                mainPanel.getChildren().add(loadThumbnail(video));
            }
        }
    }

    @FXML
    void loadShorts() {

    }

    @FXML
    void loadSubs() {

    }

    @FXML
    void loadChannel() {

    }

    @FXML
    void loadHistory() {

    }

    @FXML
    void loadPlaylists() {

    }

    @FXML
    void loadVideos() {

    }

    @FXML
    void loadLaters() {

    }

    @FXML
    void loadLikes() {

    }

    @FXML
    void loadSettings() {
        Notifications.create().title("Guidance").text("Everything's already set!").showInformation();
    }

    @FXML
    void loadHelp() {
        Notifications.create().title("Guidance").text("We're all helpless brother!").showInformation();
    }

    private List<Video> getHomeVideos(User user) {
        if (user == null) {
            return loadRandomVideos();
        }

        // todo: load videos by trending and subscriptions
        return null;
    }

    private List<Video> loadRandomVideos() {
        // todo: load videos for unsigned home
        return null;
    }

    private void playClickEffect(Button button) {
        // animation class
        ScaleTransition scaleTransition = new ScaleTransition();
        scaleTransition.setDuration(Duration.millis(100));
        scaleTransition.setNode(button);

        // set scales
        scaleTransition.setByX(0.1);
        scaleTransition.setByY(0.1);
        scaleTransition.setAutoReverse(true);

        scaleTransition.setCycleCount(2);
        scaleTransition.play();
    }

    private Node loadThumbnail(Video video) {
        // todo: load thumbnail and set attributes
        return null;
    }

    private Node loadThumbnail(Short shortVideo) {
        // todo: load thumbnail and set attributes
        return null;
    }

    private Node loadPlaylist(Playlist playlist) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/playlist-view.fxml"));
        PlaylistController playlistController = loader.getController();

        // set attributes
        playlistController.controller = this;
        playlistController.getPlaylistImage().setImage(new Image(new ByteArrayInputStream(playlist.getImage())));
        playlistController.getNameLabel().setText(playlist.getName());
        playlistController.getHandleLabel().setText(playlist.getChannelHandle());
        playlistController.getIsPublicLabel().setText(String.valueOf(playlist.isPublic()));
        playlistController.getDescriptionLabel().setText(playlist.getDescription());

        playlistController.getSubmitButton().setVisible(false);
        if (!playlist.getChannelHandle().equals(channel.getHandle())) {
            playlistController.getChangeMenu().hide();
        }
        // fill videos panel
        if (!playlist.getVideos().isEmpty()) {
            for (Video video : playlist.getVideos()) {
                playlistController.getVideosPanel().getChildren().add(loadThumbnail(video));
            }
        }
        // fill shorts panel
        if (!playlist.getShorts().isEmpty()) {
            for (Short shortVideo : playlist.getShorts()) {
                playlistController.getShortsPanel().getChildren().add(loadThumbnail(shortVideo));
            }
        }

        return loader.load();
    }

    private Node loadChannel(Channel channel) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/channel-view.fxml"));
        ChannelController channelController = loader.getController();

        // set attributes
        channelController.controller = this;
        channelController.getBannerImage().setImage(new Image(new ByteArrayInputStream(channel.getBanner())));
        channelController.getLogoImage().setFill(new ImagePattern(new Image(new ByteArrayInputStream(channel.getLogo()))));
        channelController.getNameLabel().setText(channel.getName());
        channelController.getDateLabel().setText(channel.getCreatedDateTime().toString());
        channelController.getHandleLabel().setText(channel.getHandle());
        channelController.getSubsLabel().setText(String.valueOf(channel.getSubscribers()));
        channelController.getViewLabel().setText(String.valueOf(channel.getViews()));
        channelController.getDescriptionLabel().setText(channel.getDescription());

        channelController.getSubmitButton().setVisible(false);
        if (!channel.getOwnerYID().equals(user.getYid())) {
            channelController.getChangeMenu().hide();
        }
        // fill videos panel
        if (!channel.getVideos().isEmpty()) {
            for (Video video : channel.getVideos()) {
                channelController.getVideosPanel().getChildren().add(loadThumbnail(video));
            }
        }
        // fill shorts panel
        if (!channel.getShorts().isEmpty()) {
            for (Short shortVideo : channel.getShorts()) {
                channelController.getShortsPanel().getChildren().add(loadThumbnail(shortVideo));
            }
        }
        // fill playlists panel
        if (!channel.getPlaylists().isEmpty()) {
            for (Playlist playlist : channel.getPlaylists()) {
                channelController.getPlaylistsPanel().getChildren().add(loadPlaylist(playlist));
            }
        }

        return loader.load();
    }

    public void fakeBtnAction(ActionEvent actionEvent) throws IOException {
        Video video = new Video(UUID.randomUUID(), "AAAAAA", "ABCHAJASLSJSAL", 1059, LocalDate.now().toString(), 100, null, false, null, null, " ", 1000);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/video-view.fxml"));
        VideoController videoController = loader.getController();
        videoController.video = video;
        Parent root = loader.load();

        mainPanel.getChildren().add(root);
    }
}
package org.project.youtube.Client.Controller;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.project.youtube.Client.Model.Network.Request;
import org.project.youtube.Client.Model.Short;
import org.project.youtube.Client.Model.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class MainController implements Initializable {
    public static User user;
    public static Channel channel;
    public static MainController mainController;

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
    private HBox logoutBox;

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
    private Label logoutLabel;

    @FXML
    private Separator topSeparator;

    @FXML
    private Separator downSeparator;

    @FXML
    private Label youLabel;

    private boolean slideBar = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mainController = this;

        // load profile button to use later
        profileButton = new Button("Profile");
        profileButton.setOnAction(event -> {
            try {
                loadProfile(event);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
        profileButton.getStyleClass().add("actionButton");
        profileButton.setPrefSize(60, 35);
        profileButton.setMinSize(60, 35);
        profileButton.setMaxSize(60, 35);

        // refresh panel
        try {
            refreshAll();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void refreshAll() throws IOException {
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
            logoutBox.getChildren().remove(1);
        } else {
            slideBar = true;

            // add child of HBoxes
            homeBox.getChildren().add(homeLabel);
            shortsBox.getChildren().add(shortsLabel);
            subsBox.getChildren().add(subLabel);
            historyBox.getChildren().add(historyLabel);
            settingsBox.getChildren().add(settingsLabel);
            logoutBox.getChildren().add(logoutLabel);

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
    void searchAll() throws IOException {
        if (searchBox.getText().length() > 1) {
            // load fxml of search page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/search-view.fxml"));
            TabPane tabPane = loader.load();
            SearchController searchController = loader.getController();
            searchController.load(searchBox.getText());
            mainPanel.getChildren().clear();
            mainPanel.getChildren().add(tabPane);
        }
    }

    @FXML
    void loadStudio(ActionEvent event) throws IOException {
        if (user == null) {
            System.out.println("| studio not available");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/studio-view.fxml"));
        Parent root = loader.load();

        // create a new scene and set scene of stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        System.out.println("| redirect to studio panel");
    }

    @FXML
    void loadProfile(ActionEvent event) throws IOException {
        if (user == null) {
            System.out.println("| profile not available");
            return;
        }

        // get current stage
        mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // load fxml of profile page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/profile-view.fxml"));
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

        System.out.println("| redirect to profile panel");
    }

    @FXML
    void loadHome() throws IOException {
        List<Video> videos = getHomeVideos(user);
        mainPanel.getChildren().clear();

        if (videos != null) {
            for (Video video : videos) {
                mainPanel.getChildren().add(loadThumbnail(video));
            }
        }
    }

    private List<Video> getHomeVideos(User user) throws IOException {
        if (user == null) {
            return loadLatestVideos();
        }

        return Request.getHomeVideos(user);
    }

    private List<Video> loadLatestVideos() throws IOException {
        return Request.getLatestVideos();
    }

    @FXML
    void loadShorts() throws IOException {
        List<Short> shorts = getHomeShorts(user);
        mainPanel.getChildren().clear();

        if (shorts != null) {
            for (Short aShort : shorts) {
                mainPanel.getChildren().add(loadThumbnail(aShort, shorts));
            }
        }
    }

    private List<Short> getHomeShorts(User user) throws IOException {
        if (user == null) {
            return loadLatestShorts();
        }

        return Request.getHomeShorts(user);
    }

    private List<Short> loadLatestShorts() throws IOException {
        return Request.getLatestShorts();
    }

    @FXML
    void loadSubs() throws IOException {
        if (user == null) {
            System.out.println("| subs not available");
            return;
        }

        List<Channel> subscriptions = Request.getSubscribedChannels(user);
        mainPanel.getChildren().clear();

        for (Channel channel : subscriptions) {
            Node node = loadMinChannel(channel);
            mainPanel.getChildren().add(node);
        }
    }

    @FXML
    void loadChannel() throws IOException {
        if (user == null) {
            System.out.println("| channel not available");
            return;
        }

        Node node = loadFullChannel(channel);

        if (node != null) {
            mainPanel.getChildren().clear();
            mainPanel.getChildren().add(node);
        } else {
            System.out.println("| redirect to channel failed");
        }
    }

    @FXML
    void loadHistory() {

    }

    @FXML
    void loadPlaylists() throws IOException {
        if (user == null) {
            System.out.println("| playlists not available");
            return;
        }

        // configure HBox
        HBox createPlaylist = new HBox();
        createPlaylist.setPrefWidth(mainPanel.getWidth());
        createPlaylist.setAlignment(Pos.CENTER);
        createPlaylist.setSpacing(10);
        // configure children of create panel
        Label createLabel = new Label("Create a new playlist");
        TextField nameField = new TextField();
        Button addButton = new Button("Create");
        addButton.setOnAction(event -> {
            try {
                createPlaylist(nameField.getText());
                nameField.clear();
                loadPlaylists();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
        // add elements to HBox
        createPlaylist.getChildren().addAll(createLabel, nameField, addButton);

        List<Playlist> playlists = Request.getPLs(channel.getHandle());
        mainPanel.getChildren().clear();

        for (Playlist playlist : playlists) {
            Node node = loadMinPlaylist(playlist);
            mainPanel.getChildren().add(node);
        }
    }

    @FXML
    void loadVideos() throws IOException, InterruptedException {
        if (user == null) {
            System.out.println("| videos not available");
            return;
        }

        mainPanel.getChildren().clear();
        for (Video video : channel.getVideos()) {
            Video video1 = Request.getVideo(video.getId());
            mainPanel.getChildren().add(loadThumbnail(video));
        }
    }

    @FXML
    void loadLaters() throws IOException {
        if (user == null) {
            System.out.println("| watch-laters not available");
            return;
        }

        Playlist laters = Request.getWatchLaterPlaylist(channel.getHandle());
        Node node = loadFullPlaylist(laters);

        if (node != null) {
            mainPanel.getChildren().clear();
            mainPanel.getChildren().add(node);
            System.out.println("| redirect to watch later playlist");
        } else {
            System.out.println("| redirect to watch-laters failed");
        }
    }

    @FXML
    void loadLikes() throws IOException {
        if (user == null) {
            System.out.println("| liked-videos not available");
            return;
        }

        Playlist liked = Request.getLikedVideosPlaylist(channel.getHandle());
        Node node = loadFullPlaylist(liked);

        if (node != null) {
            mainPanel.getChildren().clear();
            mainPanel.getChildren().add(node);
            System.out.println("| redirect to liked-videos playlist");
        } else {
            System.out.println("| redirect to liked-videos failed");
        }
    }

    @FXML
    void loadSettings() {
        Notifications.create().title("Guidance").text("Everything's already set!").showInformation();
    }

    @FXML
    void loadHelp() {
        Notifications.create().title("Guidance").text("We're all helpless brother!").showInformation();
    }

    @FXML
    void logOut() throws IOException {
        MainController.user = null;
        MainController.channel = null;
        StudioController.channel = null;

        refreshAll();
        Notifications.create().title("Logout").text("You're logged out").showInformation();
        System.out.println("| user logged out");
    }

    private void createPlaylist(String playlistName) throws IOException {
        Playlist playlist = new Playlist(new ArrayList<>(), UUID.randomUUID(), playlistName, channel.getHandle(),
                null, new ArrayList<>(), null);
        Request.createPlaylist(playlist);
        System.out.println("| playlist created");
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

    Node loadThumbnail(Video video) throws IOException {
        Channel videoChannel = Request.getChannel(video.getVideoHandle());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/video-thumbnail.fxml"));
        Node node = loader.load();
        ThumbnailController thumbnailController = loader.getController();

        // set attributes
        thumbnailController.video = video;
        thumbnailController.controller = this;
        try {
            thumbnailController.getThumbnailImage().setImage(new Image(new ByteArrayInputStream(video.getThumbnail())));
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            thumbnailController.getThumbnailImage().setImage(new Image("/org/project/youtube/Client/images/sample-thumbnail.png"));
        }
        try {
            thumbnailController.getProfileImage().setFill(new ImagePattern(new Image(new ByteArrayInputStream(videoChannel.getLogo()))));
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            thumbnailController.getProfileImage().setFill(new ImagePattern(new Image("/org/project/youtube/Client/images/sample-profile.png")));
        }
        thumbnailController.getTitleLabel().setText(video.getTitle());
        thumbnailController.getDateLabel().setText(video.getCreatedDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        thumbnailController.getViewsLabel().setText(String.valueOf(video.getViews()));

        return node;
    }

    Node loadThumbnail(Short shortVideo, List<Short> shortList) throws IOException {
        Channel shortChannel = Request.getChannel(shortVideo.getShortHandle());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/short-thumbnail.fxml"));
        Node node = loader.load();
        ThumbnailController thumbnailController = loader.getController();

        // set attributes
        thumbnailController.aShort = shortVideo;
        thumbnailController.shortList = shortList;
        thumbnailController.controller = this;
        try {
            thumbnailController.getThumbnailImage().setImage(new Image(new ByteArrayInputStream(shortVideo.getThumbnail())));
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            thumbnailController.getThumbnailImage().setImage(new Image("/org/project/youtube/Client/images/sample-thumbnail.png"));
        }
        try {
            thumbnailController.getProfileImage().setFill(new ImagePattern(new Image(new ByteArrayInputStream(shortChannel.getLogo()))));
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            thumbnailController.getProfileImage().setFill(new ImagePattern(new Image("/org/project/youtube/Client/images/sample-profile.png")));
        }
        thumbnailController.getTitleLabel().setText(shortVideo.getTitle());
        thumbnailController.getDateLabel().setText(shortVideo.getCreatedDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        thumbnailController.getViewsLabel().setText(String.valueOf(shortVideo.getViews()));

        return node;
    }

    Node loadMinPlaylist(Playlist playlist) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/playlist-view.fxml"));
        loader.load();
        PlaylistController playlistController = loader.getController();

        // set attributes
        playlistController.playlist = playlist;
        playlistController.controller = this;
        try {
            playlistController.getPlaylistImage().setImage(new Image(new ByteArrayInputStream(playlist.getImage())));
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            playlistController.getPlaylistImage().setImage(new Image("/org/project/youtube/Client/images/sample-playlist.png"));
        }
        playlistController.getNameLabel().setText(playlist.getName());
        playlistController.getHandleLabel().setText(playlist.getChannelHandle());
        playlistController.getIsPublicLabel().setText(playlist.isPublic() ? "public" : "private");
        playlistController.getDescriptionLabel().setText(playlist.getDescription());
        if (!playlist.getChannelHandle().equals(channel.getHandle())) {
            playlistController.getEditItem().setDisable(true);
            playlistController.getDeleteItem().setDisable(true);
        }

        return playlistController.getInfoPanel();
    }

    Node loadFullPlaylist(Playlist playlist) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/playlist-view.fxml"));
        Node node = loader.load();
        PlaylistController playlistController = loader.getController();

        // set attributes
        playlistController.playlist = playlist;
        playlistController.controller = this;
        try {
            playlistController.getPlaylistImage().setImage(new Image(new ByteArrayInputStream(playlist.getImage())));
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            playlistController.getPlaylistImage().setImage(new Image("/org/project/youtube/Client/images/sample-playlist.png"));
        }
        playlistController.getNameLabel().setText(playlist.getName());
        playlistController.getHandleLabel().setText(playlist.getChannelHandle());
        playlistController.getIsPublicLabel().setText(playlist.isPublic() ? "public" : "private");
        playlistController.getDescriptionLabel().setText(playlist.getDescription());
        if (!playlist.getChannelHandle().equals(channel.getHandle())) {
            playlistController.getEditItem().setDisable(true);
            playlistController.getDeleteItem().setDisable(true);
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
                playlistController.getShortsPanel().getChildren().add(loadThumbnail(shortVideo, playlist.getShorts()));
            }
        }

        return node;
    }

    Node loadMinChannel(Channel channel) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/channel-view.fxml"));
        loader.load();
        ChannelController channelController = loader.getController();

        // set attributes
        channelController.channel = channel;
        channelController.controller = this;
        try {
            channelController.getLogoImage().setFill(new ImagePattern(new Image(new ByteArrayInputStream(channel.getLogo()))));
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            channelController.getLogoImage().setFill(new ImagePattern(new Image("/org/project/youtube/Client/images/sample-profile.png")));
        }
        channelController.getNameLabel().setText(channel.getName());
        channelController.getDateLabel().setText(channel.getCreatedDateTime().format(DateTimeFormatter.ofPattern("yyyy MM dd")));
        channelController.getHandleLabel().setText(channel.getHandle());
        channelController.getSubsLabel().setText(String.valueOf(channel.getSubscribers()));
        channelController.getViewLabel().setText(String.valueOf(channel.getViews()));
        channelController.getDescriptionLabel().setText(channel.getDescription());
        if (!MainController.user.getYid().equals(channel.getOwnerYID())) {
            channelController.getEditItem().setDisable(true);
        }
        if (Request.isSubscribed(user.getYid().toString(), channel.getHandle()) || channel.getHandle().equals(MainController.channel.getHandle())) {
            channelController.getSubscribeButton().setDisable(true);
        }

        return channelController.getInfoPanel();
    }

    Node loadFullChannel(Channel channel) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/channel-view.fxml"));
        Node node = loader.load();
        ChannelController channelController = loader.getController();

        // set attributes
        channelController.channel = channel;
        channelController.controller = this;
        try {
            channelController.getBannerImage().setImage(new Image(new ByteArrayInputStream(channel.getBanner())));
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            channelController.getBannerImage().setImage(new Image("/org/project/youtube/Client/images/sample-banner.png"));
        }
        try {
            channelController.getLogoImage().setFill(new ImagePattern(new Image(new ByteArrayInputStream(channel.getLogo()))));
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            channelController.getLogoImage().setFill(new ImagePattern(new Image("/org/project/youtube/Client/images/sample-profile.png")));
        }
        channelController.getNameLabel().setText(channel.getName());
        channelController.getDateLabel().setText(channel.getCreatedDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        channelController.getHandleLabel().setText(channel.getHandle());
        channelController.getSubsLabel().setText(String.valueOf(channel.getSubscribers()));
        channelController.getViewLabel().setText(String.valueOf(channel.getViews()));
        channelController.getDescriptionLabel().setText(channel.getDescription());
        if (!MainController.user.getYid().equals(channel.getOwnerYID())) {
            channelController.getEditItem().setDisable(true);
        }
        if (Request.isSubscribed(user.getYid().toString(), channel.getHandle()) || channel.getHandle().equals(MainController.channel.getHandle())) {
            channelController.getSubscribeButton().setDisable(true);
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
                channelController.getShortsPanel().getChildren().add(loadThumbnail(shortVideo, channel.getShorts()));
            }
        }
        // fill playlists panel
        if (!channel.getPlaylists().isEmpty()) {
            for (Playlist playlist : channel.getPlaylists()) {
                channelController.getPlaylistsPanel().getChildren().add(loadMinPlaylist(playlist));
            }
        }

        return node;
    }

    public FlowPane getMainPanel() {
        return mainPanel;
    }
}
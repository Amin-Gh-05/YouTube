package org.project.youtube.Client.Controller;

import javafx.animation.ScaleTransition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.io.FileUtils;
import org.controlsfx.control.Notifications;
import org.project.youtube.Client.Model.Channel;
import org.project.youtube.Client.Model.Network.Request;
import org.project.youtube.Client.Model.Video;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class StudioController implements Initializable {
    public static Channel channel;

    // ------------------------------ HEADER ------------------------------

    @FXML
    private Button moreButton;

    @FXML
    private Button createButton;

    // ------------------------------ SIDE ------------------------------

    @FXML
    private StackPane mainPanel;

    @FXML
    private VBox sideBar;

    @FXML
    private Circle profilePic;

    @FXML
    private Label nameLabel;

    @FXML
    private HBox dashboardBox;

    @FXML
    private HBox contentBox;

    @FXML
    private HBox settingsBox;

    @FXML
    private HBox returnBox;

    @FXML
    private Label dashboardLabel;

    @FXML
    private Label contentLabel;

    @FXML
    private Label settingsLabel;

    @FXML
    private Label returnLabel;

    private boolean slideBar = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        channel = MainController.channel;

        try {
            refreshAll();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void slideSidebar() {
        playClickEffect(moreButton);

        if (slideBar) {
            slideBar = false;

            profilePic.setRadius(25);
            sideBar.getChildren().remove(1);
            // remove child of sideBoxes
            dashboardBox.getChildren().remove(1);
            contentBox.getChildren().remove(1);
            settingsBox.getChildren().remove(1);
            returnBox.getChildren().remove(1);
        } else {
            slideBar = true;

            profilePic.setRadius(103);
            sideBar.getChildren().add(1, nameLabel);
            // add child of sideBoxes
            dashboardBox.getChildren().add(dashboardLabel);
            contentBox.getChildren().add(contentLabel);
            settingsBox.getChildren().add(settingsLabel);
            returnBox.getChildren().add(returnLabel);
        }
    }

    @FXML
    void refreshAll() throws IOException {
        // set image for profile viewer
        ImagePattern imagePattern;
        try {
            imagePattern = new ImagePattern(new Image(new ByteArrayInputStream(channel.getLogo())));
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            imagePattern = new ImagePattern(new Image("/org/project/youtube/Client/images/profile-sample.png"));
        }
        profilePic.setFill(imagePattern);
        profilePic.setEffect(new DropShadow(10, Color.BLACK));

        // set name of channel
        nameLabel.setText(channel.getName());

        mainPanel.getChildren().clear();
        loadDashboard();
    }

    @FXML
    void changeProfile() throws IOException {
        File picture = pickFile();
        if (picture != null) {
            channel.setLogo(FileUtils.readFileToByteArray(picture));
            Request.updateChannel(channel);
            System.out.println("| logo image was successfully updated");
        }

        refreshAll();
    }

    @FXML
    void createContent() throws IOException {
        playClickEffect(createButton);

        // remove the previously loaded child
        if (!mainPanel.getChildren().isEmpty()) {
            mainPanel.getChildren().removeFirst();
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/upload-view.fxml"));
        StackPane pane = loader.load();
        UploadController uploadController = loader.getController();
        uploadController.controller = this;
        mainPanel.getChildren().add(pane);
    }

    @FXML
    void loadHelp() {
        Notifications.create().title("Guidance").text("Call 911").showInformation();
    }

    @FXML
    void loadProfile() {

    }

    @FXML
    void loadDashboard() throws IOException {
        // remove the previously loaded child
        mainPanel.getChildren().clear();

        Node node = loadDashboard(channel);
        mainPanel.getChildren().add(node);
    }

    @FXML
    void loadContent() throws IOException {
        // remove the previously loaded child
        mainPanel.getChildren().clear();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/content-view.fxml"));
        AnchorPane pane = loader.load();
        mainPanel.getChildren().add(pane);
    }

    @FXML
    void loadSettings() {
        Notifications.create().title("Guidance").text("You mean you're not glad with settings?").showInformation();
    }

    @FXML
    void loadMain(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/main-view.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public StackPane getMainPanel() {
        return mainPanel;
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

    private Node loadDashboard(Channel channel) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/dashboard-view.fxml"));
        Node node = loader.load();
        DashboardController dashboardController = loader.getController();

        // set attributes
        dashboardController.controller = this;
        dashboardController.getSubCount().setText(String.valueOf(channel.getSubscribers()));
        dashboardController.getViewCount().setText(String.valueOf(channel.getViews()));
        dashboardController.getVideosCount().setText(String.valueOf(channel.getVideos().size()));
        dashboardController.getTopVideos().getItems().addAll(getTopVideos(channel));

        return node;
    }

    private String[] getTopVideos(Channel channel) {
        List<Video> topVideos = channel.getVideos();
        topVideos.sort(Comparator.comparing(Video::getTitle).reversed());

        int size = topVideos.size();
        String[] videos;
        // fill array
        if (size < 10) {
            videos = new String[size];
            for (int i = 0; i < size; i++) {
                videos[i] = topVideos.get(i).getTitle() + " " + topVideos.get(i).getViews();
            }
        } else {
            videos = new String[10];
            for (int i = 0; i < 10; i++) {
                videos[i] = topVideos.get(i).getTitle() + " " + topVideos.get(i).getViews();
            }
        }

        return videos;
    }

    private File pickFile() {
        // show the file picker dialog
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a Video File");

        // file extension filers
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Video Files", "*.mp4"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            System.out.println("| file selected: " + file.getAbsolutePath());
            return file;
        } else {
            System.out.println("| file not selected");
            return null;
        }
    }
}

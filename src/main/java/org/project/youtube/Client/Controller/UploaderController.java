package org.project.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.project.youtube.Client.Model.Channel;
import org.project.youtube.Client.Model.Network.Request;
import org.project.youtube.Client.Model.Short;
import org.project.youtube.Client.Model.Video;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class UploaderController implements Initializable {
    static File file;
    static boolean isShort;
    StudioController controller;
    private byte[] thumbnailImage;

    @FXML
    private TextField titleText;

    @FXML
    private TextArea descriptionText;

    @FXML
    private CheckBox adultOnlyCheck;

    @FXML
    private TextField tagsText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (isShort) {
            descriptionText.setDisable(true);
        }
    }

    @FXML
    void cancelButton() throws IOException {
        file = null;
        isShort = false;

        // return to previously loaded node
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/upload-view.fxml"));
        Node node = loader.load();

        controller.getMainPanel().getChildren().removeFirst();
        controller.getMainPanel().getChildren().add(node);
    }

    @FXML
    void pickThumbnail() throws IOException {
        File file = pickFile();
        if (file != null) {
            thumbnailImage = FileUtils.readFileToByteArray(file);
        }
    }

    @FXML
    void uploadVideo() throws IOException, InterruptedException {
        if (isShort) {
            Short shortVideo = new Short(UUID.randomUUID(), titleText.getText(),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")),
                    adultOnlyCheck.isSelected(), new ArrayList<>(List.of(tagsText.getText().split(" "))),
                    thumbnailImage, StudioController.channel.getHandle());

            Request.createShort(shortVideo, file.getAbsolutePath());
            System.out.println("| short upload complete");

            Channel updatedChannel = Request.getChannel(shortVideo.getShortHandle());
            MainController.channel = updatedChannel;
            StudioController.channel = updatedChannel;

            cancelButton();
        } else {
            Video video = new Video(UUID.randomUUID(), titleText.getText(), descriptionText.getText(),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")),
                    adultOnlyCheck.isSelected(), new ArrayList<>(List.of(tagsText.getText().split(" "))),
                    thumbnailImage, StudioController.channel.getHandle());

            Request.createVideo(video, file.getAbsolutePath());
            System.out.println("| video upload complete");

            Channel updatedChannel = Request.getChannel(video.getVideoHandle());
            MainController.channel = updatedChannel;
            StudioController.channel = updatedChannel;
            cancelButton();
        }
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

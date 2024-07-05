package org.project.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.bytedeco.ffmpeg.avformat.AVFormatContext;
import org.bytedeco.ffmpeg.avutil.AVDictionary;
import org.bytedeco.ffmpeg.global.avformat;
import org.project.youtube.Client.Model.Video;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UploaderController {
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
    void uploadVideo() throws IOException {
        Video video = new Video(UUID.randomUUID(), titleText.getText(), descriptionText.getText(), videoLength(file),
                LocalDateTime.now().toString(), adultOnlyCheck.isSelected(), new ArrayList<>(List.of(tagsText.getText().split(" "))),
                thumbnailImage, StudioController.channel.getHandle());

        // todo: send video through api and add it to database
    }

    private File pickFile() {
        // show the file picker dialog
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a Video File");

        // file extension filers
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.mkv", "*.m4a", "*.m4v"),
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

    private int videoLength(File file) {
        String filePath = file.getAbsolutePath();

        AVFormatContext formatContext = avformat.avformat_alloc_context();
        if (avformat.avformat_open_input(formatContext, filePath, null, null) != 0) {
            throw new RuntimeException("Could not open video file: " + filePath);
        }

        // retrieve stream information
        if (avformat.avformat_find_stream_info(formatContext, (AVDictionary) null) < 0) {
            throw new RuntimeException("Could not retrieve stream information.");
        }

        // get the duration in seconds
        long durationInMicroseconds = formatContext.duration(); // Duration in microseconds
        double durationInSeconds = durationInMicroseconds / 1_000_000.0;

        return (int) durationInSeconds;
    }
}

package org.project.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class UploadController {
    StudioController controller;

    @FXML
    void pickShort() throws IOException {
        File file = pickFile();
        if (file != null) {
            UploaderController.file = file;
            UploaderController.isShort = true;
        }

        // load uploader panel
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/uploader-view.fxml"));
        UploaderController uploaderController = loader.getController();
        uploaderController.controller = this.controller;
        Node node = loader.load();

        controller.getMainPanel().getChildren().removeFirst();
        controller.getMainPanel().getChildren().add(node);
    }

    @FXML
    void pickVideo() throws IOException {
        File file = pickFile();
        if (file != null) {
            UploaderController.file = file;
            UploaderController.isShort = false;
        }

        // load uploader panel
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/uploader-view.fxml"));
        UploaderController uploaderController = loader.getController();
        uploaderController.controller = this.controller;
        Node node = loader.load();

        controller.getMainPanel().getChildren().removeFirst();
        controller.getMainPanel().getChildren().add(node);
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
}

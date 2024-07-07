package org.project.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;


public class DashboardController {
    StudioController controller;

    @FXML
    private Label subCount;

    @FXML
    private ListView<String> topVideos;

    @FXML
    private Label viewCount;

    @FXML
    private Label watchTime;

    @FXML
    void createContent() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/upload-view.fxml"));
        UploadController uploadController = loader.getController();

        // set studio as controller of upload
        uploadController.controller = controller;

        Node node = loader.load();
        controller.getMainPanel().getChildren().clear();
        controller.getMainPanel().getChildren().add(node);
    }
}

package org.project.youtube.Client.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ProfileController {

    public ProfileController(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("profile-view.fxml"));
        stage.setTitle("Profile setting");
        stage.setScene(new Scene(root, 1220, 740));
        stage.show();
    }
}

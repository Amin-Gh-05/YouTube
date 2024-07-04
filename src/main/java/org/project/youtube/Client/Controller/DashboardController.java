package org.project.youtube.Client.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.project.youtube.Client.Model.Channel;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    static Channel channel;

    @FXML
    private Label subCount;

    @FXML
    private ListView<?> topVideos;

    @FXML
    private Label viewCount;

    @FXML
    private Label watchTime;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void createContent(ActionEvent event) {

    }
}

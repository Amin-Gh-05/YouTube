package org.project.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import org.project.youtube.Client.Model.Channel;
import org.project.youtube.Client.Model.Playlist;
import org.project.youtube.Client.Model.Short;
import org.project.youtube.Client.Model.Video;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ContentController implements Initializable {
    static Channel channel;

    @FXML
    private VBox playlistColumn;

    @FXML
    private VBox shortColumn;

    @FXML
    private VBox shortCountColumn;

    @FXML
    private VBox shortDateColumn;

    @FXML
    private VBox shortLikeColumn;

    @FXML
    private VBox shortRestColumn;

    @FXML
    private VBox shortViewColumn;

    @FXML
    private VBox videoColumn;

    @FXML
    private VBox videoCountColumn;

    @FXML
    private VBox videoDateColumn;

    @FXML
    private VBox videoLikeColumn;

    @FXML
    private VBox videoRestColumn;

    @FXML
    private VBox videoViewColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void loadVideos(List<Video> videos) {

    }

    private void loadShorts(List<Short> shorts) {

    }

    private void loadPlaylists(List<Playlist> playlists) {

    }

    private void loadVideoContent(Video video) {

    }

    private void loadShortContent(Short shortVideo) {

    }

    private void loadPlaylistContent(Playlist playlist) {

    }
}

package org.project.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.project.youtube.Client.Model.Playlist;
import org.project.youtube.Client.Model.Short;
import org.project.youtube.Client.Model.Video;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ContentController implements Initializable {

    @FXML
    private VBox playlistColumn;

    @FXML
    private VBox videoCountColumn;

    @FXML
    private VBox shortCountColumn;

    @FXML
    private VBox shortColumn;

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
        for (Video video : videos) {
            loadVideoContent(video);
        }

        System.out.println("| videos were loaded");
    }

    private void loadShorts(List<Short> shorts) {
        for (Short shortVideo : shorts) {
            loadShortContent(shortVideo);
        }

        System.out.println("| shorts were loaded");
    }

    private void loadPlaylists(List<Playlist> playlists) {
        for (Playlist playlist : playlists) {
            loadPlaylistContent(playlist);
        }

        System.out.println("| playlists were loaded");
    }

    private void loadVideoContent(Video video) {
        // add title
        Label videoTitle = new Label(video.getTitle());
        videoColumn.getChildren().add(videoTitle);
        // add restrictions
        Label videoRest = new Label(String.valueOf(video.isAgeRestricted()));
        videoRestColumn.getChildren().add(videoRest);
        // add date
        Label videoDate = new Label(video.getCreatedDateTime().toString().substring(0, 10));
        videoDateColumn.getChildren().add(videoDate);
        // add views
        Label videoViews = new Label(String.valueOf(video.getViews()));
        videoViewColumn.getChildren().add(videoViews);
        // add likes
        Label videoLike = new Label(String.valueOf(video.getLikes()));
        videoLikeColumn.getChildren().add(videoLike);
    }

    private void loadShortContent(Short shortVideo) {
        // add title
        Label shortTitle = new Label(shortVideo.getTitle());
        shortColumn.getChildren().add(shortTitle);
        // add restrictions
        Label shortRest = new Label(String.valueOf(shortVideo.isAgeRestricted()));
        shortRestColumn.getChildren().add(shortRest);
        // add date
        Label shortDate = new Label(shortVideo.getCreatedDateTime().toString().substring(0, 10));
        shortDateColumn.getChildren().add(shortDate);
        // add views
        Label shortViews = new Label(String.valueOf(shortVideo.getViews()));
        shortViewColumn.getChildren().add(shortViews);
        // add likes
        Label shortLike = new Label(String.valueOf(shortVideo.getLikes()));
        shortLikeColumn.getChildren().add(shortLike);
    }

    private void loadPlaylistContent(Playlist playlist) {
        // add title
        Label playlistTitle = new Label(playlist.getName());
        playlistColumn.getChildren().add(playlistTitle);
        // add videos count
        Label playlistVideos = new Label(String.valueOf(playlist.getVideos().size()));
        videoCountColumn.getChildren().add(playlistVideos);
        // add shorts count
        Label playlistShorts = new Label(String.valueOf(playlist.getShorts().size()));
        shortCountColumn.getChildren().add(playlistShorts);
    }
}

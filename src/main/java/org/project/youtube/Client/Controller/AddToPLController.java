package org.project.youtube.Client.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import org.project.youtube.Client.Model.Channel;
import org.project.youtube.Client.Model.Playlist;
import org.project.youtube.Client.Model.Short;
import org.project.youtube.Client.Model.Video;

import java.io.IOException;

import static org.project.youtube.Client.Model.Network.Request.*;

public class AddToPLController {
    Channel channel;
    Video video;
    Short shortVideo;

    @FXML
    private Button addButton;

    @FXML
    private ChoiceBox<String> plChoiceBox;

    public void initialize() throws IOException {
         channel = getChannel(MainController.user.getHandle());
         for(Playlist playlist : channel.getPlaylists()){
             plChoiceBox.getItems().add(playlist.getName());
         }
    }

    @FXML
    void addToPL(ActionEvent event) throws IOException {
        String name = plChoiceBox.getValue();
        if(name != null){
            if (video != null) {
                for (Playlist playlist : channel.getPlaylists()) {
                    if (playlist.getName().equals(name)) {
                        addVideoToPlaylist(playlist, video);
                    }
                }
            }
            else if (shortVideo != null) {
                for (Playlist playlist : channel.getPlaylists()) {
                    if (playlist.getName().equals(name)) {
                        addShortToPlaylist(playlist, shortVideo);
                    }
                }
            }
        }
    }

}

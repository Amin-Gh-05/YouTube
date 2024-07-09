package org.project.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.project.youtube.Client.Main;
import org.project.youtube.Client.Model.Short;
import org.project.youtube.Client.Model.Video;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.project.youtube.Client.Model.Network.Request.*;


public class ThumbnailController {
    Video video;
    Short aShort;
    List<Short> shortList;

    MainController controller;

    @FXML
    private ImageView thumbnailImage;

    @FXML
    private Circle profileImage;

    @FXML
    private Label titleLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label viewsLabel;

    @FXML
    void loadVideo() throws IOException, InterruptedException {
        FXMLLoader loader;

        if (video != null) {
            if (!(new File(Main.CASH_PATH + "/" + video.getId().toString() + ".mp4").exists())) {
                Video video1 = getVideo(video.getId());
            }

            loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/video-view.fxml"));
            Node node = loader.load();
            VideoController videoController = loader.getController();

            videoController.video = video;
            videoController.controller = controller;
            videoController.init();

            controller.getMainPanel().getChildren().clear();
            controller.getMainPanel().getChildren().add(node);

            System.out.println("| redirect to video page");
            return;
        }

        if (aShort != null) {
            if (!(new File(Main.CASH_PATH + "/" + aShort.getId().toString() + ".mp4").exists())) {
                getShort(aShort.getId());
            }

            loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/short-view.fxml"));
            Node node = loader.load();
            ShortController shortController = loader.getController();

            shortController.shortVideo = aShort;
            shortController.shortVideoList = shortList;
            shortController.loadPlayer();

            controller.getMainPanel().getChildren().clear();
            controller.getMainPanel().getChildren().add(node);

            System.out.println("| redirect to short page");
            return;
        }

        System.out.println("| redirect to video/short page failed");
    }

    public ImageView getThumbnailImage() {
        return thumbnailImage;
    }

    public Circle getProfileImage() {
        return profileImage;
    }

    public Label getTitleLabel() {
        return titleLabel;
    }

    public Label getDateLabel() {
        return dateLabel;
    }

    public Label getViewsLabel() {
        return viewsLabel;
    }

//    public void init() throws IOException {
//        Image thumbnail = new Image(new ByteArrayInputStream(video.getThumbnail()));
//        thumbnailImage.setImage(thumbnail);
//        Image logo = new Image(new ByteArrayInputStream(getChannel(video.getVideoHandle()).getLogo()));
//        profileImage.setFill(new ImagePattern(logo));
//        titleLabel.setText(video.getTitle());
//        viewsLabel.setText(String.valueOf(video.getViews()));
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
//        dateLabel.setText(video.getCreatedDateTime().format(formatter));
//    }
}

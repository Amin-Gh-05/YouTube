package org.project.youtube.Client.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.ImagePattern;
import org.project.youtube.Client.Main;
import org.project.youtube.Client.Model.Channel;
import org.project.youtube.Client.Model.Network.Request;
import org.project.youtube.Client.Model.Short;
import org.project.youtube.Client.Model.Video;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SearchController {
    @FXML
    private FlowPane channelsFlowPane;

    @FXML
    private FlowPane shortsFlowPane;

    @FXML
    private FlowPane videosFlowPane;

    private String title;

    public void load(String title) throws IOException {
        this.title = title;
        loadVideos();
        loadShorts();
        loadChannels();
    }

    private void loadVideos() throws IOException {
        List<Video> videoList = Request.searchVideos(title);
        for (Video video : videoList) {
            videosFlowPane.getChildren().add(loadThumbnail(video));
        }
    }

    private void loadShorts() throws IOException {
        List<Short> shortList = Request.searchShorts(title);
        for (Short shorVideo : shortList) {
            shortsFlowPane.getChildren().add(loadThumbnail(shorVideo));
        }
    }

    private void loadChannels() throws IOException {
        List<Channel> channelList = Request.searchChannels(title);
        for (Channel channel : channelList) {
            channelsFlowPane.getChildren().add(loadMinChannel(channel));
        }
    }

    private Node loadMinChannel(Channel channel) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/channel-view.fxml"));
        loader.load();
        ChannelController channelController = loader.getController();

        // set attributes
        channelController.channel = channel;
        channelController.controller = Main.mainController;
        try {
            channelController.getLogoImage().setFill(new ImagePattern(new Image(new ByteArrayInputStream(channel.getLogo()))));
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            channelController.getLogoImage().setFill(new ImagePattern(new Image("/org/project/youtube/Client/images/profile-sample.png")));
        }
        channelController.getNameLabel().setText(channel.getName());
        channelController.getDateLabel().setText(channel.getCreatedDateTime().format(DateTimeFormatter.ofPattern("yyyy MM dd")));
        channelController.getHandleLabel().setText(channel.getHandle());
        channelController.getSubsLabel().setText(String.valueOf(channel.getSubscribers()));
        channelController.getViewLabel().setText(String.valueOf(channel.getViews()));
        channelController.getDescriptionLabel().setText(channel.getDescription());
        if (!MainController.user.getYid().equals(channel.getOwnerYID())) {
            channelController.getEditItem().setDisable(true);
        }
        if (Request.isSubscribed(MainController.user.getYid().toString(), channel.getHandle())) {
            channelController.getSubscribeButton().setDisable(true);
        }

        return channelController.getInfoPanel();
    }

    private Node loadThumbnail(Video video) throws IOException {
        Channel videoChannel = Request.getChannel(video.getVideoHandle());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/video-thumbnail.fxml"));
        Node node = loader.load();
        ThumbnailController thumbnailController = loader.getController();

        // set attributes
        thumbnailController.video = video;
        thumbnailController.controller = Main.mainController;
        try {
            thumbnailController.getThumbnailImage().setImage(new Image(new ByteArrayInputStream(video.getThumbnail())));
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            thumbnailController.getThumbnailImage().setImage(new Image("/org/project/youtube/Client/images/sample-thumbnail.png"));
        }
        try {
            thumbnailController.getProfileImage().setFill(new ImagePattern(new Image(new ByteArrayInputStream(videoChannel.getLogo()))));
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            thumbnailController.getProfileImage().setFill(new ImagePattern(new Image("/org/project/youtube/Client/images/profile-sample.png")));
        }
        thumbnailController.getTitleLabel().setText(video.getTitle());
        thumbnailController.getDateLabel().setText(video.getCreatedDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        thumbnailController.getViewsLabel().setText(String.valueOf(video.getViews()));

        return node;
    }

    private Node loadThumbnail(Short shortVideo) throws IOException {
        Channel shortChannel = Request.getChannel(shortVideo.getShortHandle());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/Client/short-thumbnail.fxml"));
        Node node = loader.load();
        ThumbnailController thumbnailController = loader.getController();

        // set attributes
        thumbnailController.aShort = shortVideo;
        thumbnailController.controller = Main.mainController;
        try {
            thumbnailController.getThumbnailImage().setImage(new Image(new ByteArrayInputStream(shortVideo.getThumbnail())));
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            thumbnailController.getThumbnailImage().setImage(new Image("/org/project/youtube/Client/images/sample-thumbnail.png"));
        }
        try {
            thumbnailController.getProfileImage().setFill(new ImagePattern(new Image(new ByteArrayInputStream(shortChannel.getLogo()))));
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            thumbnailController.getProfileImage().setFill(new ImagePattern(new Image("/org/project/youtube/Client/images/profile-sample.png")));
        }
        thumbnailController.getTitleLabel().setText(shortVideo.getTitle());
        thumbnailController.getDateLabel().setText(shortVideo.getCreatedDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        thumbnailController.getViewsLabel().setText(String.valueOf(shortVideo.getViews()));

        return node;
    }
}

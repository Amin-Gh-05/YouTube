package org.project.youtube.Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.project.youtube.Client.Model.Network.Client;
import org.project.youtube.Client.Model.Network.Downloader;

import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

public class Main extends Application {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 5431;
    private static final int SERVER_FILE_TRANSFER_PORT = 5430;

    //public static Client client;
    //public static Downloader downloader;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/org/project/youtube/Client/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("YouTube");
        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/org/project/youtube/Client/images/icon.png"))));
        stage.setMaxWidth(1920);
        stage.setMaxHeight(1080);
        stage.setMinWidth(960);
        stage.setMinHeight(540);
        stage.show();
    }


    @Override
    public void init() throws IOException {
        //client = new Client(new Socket(SERVER_IP, SERVER_PORT));
        // TODO Downloader
        //Client.socket = new Socket(SERVER_IP, SERVER_PORT);
        //Client.run();
    }

    public static void main(String[] args) {
        launch();
    }
}
package org.project.youtube.Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.project.youtube.Client.Model.Network.Client;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

public class Main extends Application {
    // IP address of the server to connect to
    private static final String SERVER_IP = "localhost";
    // Port of the server to connect to
    private static final int SERVER_PORT = 5432;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("YouTube");
        stage.setScene(scene);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/org/project/youtube/images/icon.png"))));
        stage.setMaxWidth(1920);
        stage.setMaxHeight(1080);
        stage.setMinWidth(960);
        stage.setMinHeight(540);
        stage.show();
    }

    @Override
    public void init() throws IOException {
        Client client = new Client(new Socket(SERVER_IP, SERVER_PORT));
    }

    public static void main(String[] args) {
        launch();
    }
}
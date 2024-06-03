package org.project.youtube;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
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

    public static void main(String[] args) {
        launch();
    }
}
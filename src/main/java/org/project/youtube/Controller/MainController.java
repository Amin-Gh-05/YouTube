package org.project.youtube.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private ImageView mainLogo;

    @FXML
    private TextField searchBox;

    @FXML
    private Button searchButton;

    @FXML
    private VBox sideBarBox;

    @FXML
    private Button signInButton;

    @FXML
    static Stage mainStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void refreshAll(MouseEvent event) {

    }

    @FXML
    void searchAll(ActionEvent event) {

    }

    @FXML
    void signIn(ActionEvent event) throws IOException {
        // get current stage
        mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // load fxml of login page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/project/youtube/login-view.fxml"));
        Parent root = loader.load();
        // create a new stage and show it
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        // hide this page and open the sign in panel
        mainStage.hide();
        stage.show();

        System.out.println("| redirect to login panel");
    }
}
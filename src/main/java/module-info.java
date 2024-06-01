module org.project.youtube {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.project.youtube to javafx.fxml;
    exports org.project.youtube;
}
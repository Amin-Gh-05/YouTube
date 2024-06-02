module org.project.youtube {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.project.youtube to javafx.fxml;
    exports org.project.youtube;
    exports org.project.youtube.Controller;
    opens org.project.youtube.Controller to javafx.fxml;
}
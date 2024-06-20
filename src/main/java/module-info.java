module org.project.youtube {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires org.json;
    requires org.apache.commons.codec;
    requires java.desktop;


    exports org.project.youtube.Client.Controller;
    opens org.project.youtube.Client.Controller to javafx.fxml;
    exports org.project.youtube.Client;
    opens org.project.youtube.Client to javafx.fxml;
    exports org.project.youtube.Client.Model.Network;
    opens org.project.youtube.Client.Model.Network to javafx.fxml;
}
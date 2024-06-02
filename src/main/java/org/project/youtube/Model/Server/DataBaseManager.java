package org.project.youtube.Model.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseManager {
    private static final String JDBC_URL = DataBaseConfig.getUrl();
    private static final String USERNAME = DataBaseConfig.getUser();
    private static final String PASSWORD = DataBaseConfig.getPassword();

    private static Connection connect() {
        try {
            return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
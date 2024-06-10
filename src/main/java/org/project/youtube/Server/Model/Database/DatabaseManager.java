package org.project.youtube.Server.Model.Database;

import org.project.youtube.Client.Model.YID;
import org.project.youtube.Server.Model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String JDBC_URL = DatabaseConfig.getUrl();
    private static final String USERNAME = DatabaseConfig.getUser();
    private static final String PASSWORD = DatabaseConfig.getPassword();

    private static Connection connect() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    public static void createUser(User user) {
        try (Connection conn = connect()) {
            String userQuery = "INSERT INTO users (yid, username, email, password) VALUES (?, ?, ?, ?)";
            PreparedStatement userPrepStat = conn.prepareStatement(userQuery);
            userPrepStat.setString(1, YID.randomYID().toString());
            userPrepStat.setString(2, user.getUsername());
            userPrepStat.setString(3, user.getEmail());
            userPrepStat.setString(4, user.getPassword());
            userPrepStat.executeUpdate();
            userPrepStat.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

package org.project.youtube.Model.Server;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {
    private static final Properties prop = new Properties();

    static {
        try (InputStream in = DatabaseConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (in == null) {
                System.out.println("No config file found");
            }
            // load config from db.properties
            prop.load(in);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getUrl() {
        return prop.getProperty("db.url");
    }

    public static String getUser() {
        return prop.getProperty("db.username");
    }

    public static String getPassword() {
        return prop.getProperty("db.password");
    }
}
package org.project.youtube.Server.Model.Database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.project.youtube.Server.Model.*;
import org.project.youtube.Server.Model.Short;

import java.sql.SQLException;
import java.util.UUID;

public class ClientService {

    public static String login(JSONObject data) throws SQLException {
        int usernameInt = Integer.parseInt(data.getString("usernameInt"));
        String username = data.getString("username");
        String password = data.getString("password");

        User user = null;
        if (usernameInt == 1) {
            user = DatabaseManager.readUserByUsername(username, password);
        }
        if (usernameInt == 2) {
            user = DatabaseManager.readUserByEmail(username, password);
        }

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        return gson.toJson(user);
    }

    public static String signup(JSONObject data) throws SQLException {
        String username = data.getString("username");
        String email = data.getString("email");
        String password = data.getString("password");

        YID yid = YID.randomYID();
        while (DatabaseManager.findYid(yid.toString())) {
            yid = YID.randomYID();
        }

        User newUser = new User(YID.randomYID(), username, email, password);

        DatabaseManager.createUser(newUser);

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        return gson.toJson(newUser);
    }

    public static boolean findUsername(JSONObject data) throws SQLException {
        return DatabaseManager.findUsername(data.getString("username"));
    }

    public static boolean findEmail(JSONObject data) throws SQLException {
        return DatabaseManager.findEmail(data.getString("email"));
    }

    public static String getVideo(JSONObject data) throws SQLException {
        UUID id = UUID.fromString(data.getString("ID"));
        Video video = DatabaseManager.readVideo(id);

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        return gson.toJson(video);
    }
    public static String getShort(JSONObject data) throws SQLException {
        UUID id = UUID.fromString(data.getString("ID"));
        Short shortt = DatabaseManager.readShort(id);

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        return gson.toJson(shortt);
    }
    public static String getChannel(JSONObject data) throws SQLException {
        Channel channel = DatabaseManager.readChannel(data.getString("ID"));

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        return gson.toJson(channel);
    }
    public static String getPL(JSONObject data) throws SQLException {
        UUID id = UUID.fromString(data.getString("ID"));
        Playlist playlist = DatabaseManager.readPlaylist(id);

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        return gson.toJson(playlist);
    }

}

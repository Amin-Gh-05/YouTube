package org.project.youtube.Server.Model.Database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.project.youtube.Server.Model.User;
import org.project.youtube.Server.Model.YID;

import java.sql.SQLException;

public class ClientService {

    public static String login(JSONObject data){

        return "";
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
}

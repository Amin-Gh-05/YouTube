package org.project.youtube.Server.Model.Database;

import org.json.JSONObject;

public class ClientService {
    public static String login(JSONObject data){

        return "";
    }
    public static String signup(JSONObject data){
        String username = data.getString("username");
        String email = data.getString("email");
        String password = data.getString("password");

        return "";
    }

    public static String findUsername(JSONObject data) {
        String username = data.getString("username");

    }

    public static String findEmail(JSONObject data) {
        String email = data.getString("email");
    }
}

package org.project.youtube.Client.Controller;

import org.json.JSONObject;

public class Request {
    public static void login(String username, String password){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "login");

        JSONObject data = new JSONObject();
        data.put("usernameEmail", username); // username or email
        data.put("password", password); // hashed password

        jsonObject.put("reqData", data);

    }
}

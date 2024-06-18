package org.project.youtube.Client.Model.Network;

import org.json.JSONObject;

import java.io.IOException;

public class Request {

    public static void login(String username, String password) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "login");

        JSONObject data = new JSONObject();
        data.put("usernameEmail", username); // username or email
        data.put("password", password); // hashed password

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }

}

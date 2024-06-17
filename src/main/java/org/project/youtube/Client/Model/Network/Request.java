package org.project.youtube.Client.Model.Network;

import org.json.JSONObject;

import java.io.IOException;
import java.util.UUID;

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

    public static void signup(String username, String email, String password) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "sign up");

        JSONObject data = new JSONObject();
        data.put("username", username);
        data.put("email", email);
        data.put("password", password); // hashed password

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }

    public static void logout() throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "logout");

        JSONObject data = new JSONObject();
        // TODO

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }

    public static void getRandomTags() throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "getRandomTags");

        JSONObject data = new JSONObject();


        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }

    public static void getRandomVideos() throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "getRandomVideos");

        JSONObject data = new JSONObject();


        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }

    public static void like(String contentType, char likeType, String userYID, UUID contentID) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "like");

        JSONObject data = new JSONObject();
        data.put("contentType", contentType); // video/short/comment
        data.put("likeType", likeType); // L: like / D: dislike
        data.put("userYID", userYID);
        data.put("contentID", contentID); // TODO UUID or String ??

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }






}

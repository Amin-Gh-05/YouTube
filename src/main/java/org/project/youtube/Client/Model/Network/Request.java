package org.project.youtube.Client.Model.Network;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Request {

    private static String jsonBuilder(String reqType, List<String> keys, List<String> values) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", reqType);

        JSONObject data = new JSONObject();
        for (int i = 0; i < keys.size(); i++) {
            data.put(keys.get(i), values.get(i));
        }
        jsonObject.put("reqData", data);

        return jsonObject.toString();
    }

    public static String signup(String username, String email, String password) throws IOException {
        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();

        keys.add("username");
        values.add(username);

        keys.add("email");
        values.add(email);

        keys.add("password");
        values.add(password);

        Client.sendRequest(jsonBuilder("signup", keys, values));
    }

    public static String findUsername(String username) throws IOException {
        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();

        keys.add("username");
        values.add(username);

        Client.sendRequest(jsonBuilder("findUsername", keys, values));

    }

    public static String findEmail(String email) throws IOException {
        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();

        keys.add("email");
        values.add(email);

        Client.sendRequest(jsonBuilder("findEmail", keys, values));
    }

    public static void login(String username, String password) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "login");

        JSONObject data = new JSONObject();
        data.put("usernameEmail", username); // username or email
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
        data.put("contentID", contentID);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }

    // getting Video/Videos | Short/Shorts | Channel/Channels | PL/PLs | Comments

    public static void getByUUID(UUID id, String type) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "get" + type); // Video/Short/PL/Channel

        JSONObject data = new JSONObject();
        data.put("ID", id);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }

    public static void getByUUIDList(List<UUID> IDs, String type) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "get" + type + "s"); // Video/Short/PL/Channel/Comment

        JSONObject data = new JSONObject();

        Gson gson = new Gson();
        Type listType = new TypeToken<List<UUID>>() {}.getType();

        String JsonIDListString = gson.toJson(IDs, listType);

        data.put("ID List", JsonIDListString);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }


}

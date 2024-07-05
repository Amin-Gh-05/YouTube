package org.project.youtube.Client.Model.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;
import org.project.youtube.Client.Main;
import org.project.youtube.Client.Model.*;
import org.project.youtube.Client.Model.Short;

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

    // ======================= Account =======================

    public static User signup(String username, String email, String password) throws IOException {
        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();

        keys.add("username");
        values.add(username);

        keys.add("email");
        values.add(email);

        keys.add("password");
        values.add(password);

        Client.sendRequest(jsonBuilder("signup", keys, values));
        String respStr = Client.getStringResponse();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        return gson.fromJson(respStr, User.class);
    }

    public static boolean findUsername(String username) throws IOException {
        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();

        keys.add("username");
        values.add(username);

        Client.sendRequest(jsonBuilder("findUsername", keys, values));
        return Client.getBooleanResponse();
    }

    public static boolean findEmail(String email) throws IOException {
        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();

        keys.add("email");
        values.add(email);

        Client.sendRequest(jsonBuilder("findEmail", keys, values));
        return Client.getBooleanResponse();
    }

    public static User login(int usernameInt, String username, String password) throws IOException {
        List<String> keys = new ArrayList<>();
        List<String> values = new ArrayList<>();

        keys.add("usernameInt");
        values.add(Integer.toString(usernameInt));

        keys.add("username");
        values.add(username);

        keys.add("password");
        values.add(password);

        Client.sendRequest(jsonBuilder("login", keys, values));
        String respStr = Client.getStringResponse();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        return gson.fromJson(respStr, User.class);
    }

    public static void logout() throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "logout");

        JSONObject data = new JSONObject();
        // TODO

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }

    // ======================= Read =======================

    public static Video getVideo(UUID id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "getVideo");

        JSONObject data = new JSONObject();
        data.put("ID", id);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
        String respStr = Client.getStringResponse();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        return gson.fromJson(respStr, Video.class);
    }
    public static Short getShort(UUID id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "getShort");

        JSONObject data = new JSONObject();
        data.put("ID", id);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
        String respStr = Client.getStringResponse();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        return gson.fromJson(respStr, Short.class);
    }
    public static Channel getChannel(UUID id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "getChannel");

        JSONObject data = new JSONObject();
        data.put("ID", id);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
        String respStr = Client.getStringResponse();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        return gson.fromJson(respStr, Channel.class);
    }
    public static Playlist getPL(UUID id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "getPL");

        JSONObject data = new JSONObject();
        data.put("ID", id);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
        String respStr = Client.getStringResponse();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        return gson.fromJson(respStr, Playlist.class);
    }

    public static List<Video> getChannelVideos(String handle) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "getChannelVideos");

        JSONObject data = new JSONObject();
        data.put("handle", handle);

        jsonObject.put("reqData", data);


        Client.sendRequest(jsonObject.toString());
        String respStr = Client.getStringResponse();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        Type listType2 = new TypeToken<List<Video>>() {}.getType();
        return gson.fromJson(respStr, listType2);
    }
    public static List<Video> getPlayListVideos(UUID id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "getPlayListVideos");

        JSONObject data = new JSONObject();
        data.put("ID", id);

        jsonObject.put("reqData", data);


        Client.sendRequest(jsonObject.toString());
        String respStr = Client.getStringResponse();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        Type listType2 = new TypeToken<List<Video>>() {}.getType();
        return gson.fromJson(respStr, listType2);
    }
    public static List<Short> getChannelShorts(String handle) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "getChannelShorts");

        JSONObject data = new JSONObject();
        data.put("handle", handle);

        jsonObject.put("reqData", data);


        Client.sendRequest(jsonObject.toString());
        String respStr = Client.getStringResponse();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        Type listType2 = new TypeToken<List<Short>>() {}.getType();
        return gson.fromJson(respStr, listType2);
    }
    public static List<Short> getPlayListShorts(UUID id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "getPlayListShorts");

        JSONObject data = new JSONObject();
        data.put("ID", id);

        jsonObject.put("reqData", data);


        Client.sendRequest(jsonObject.toString());
        String respStr = Client.getStringResponse();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        Type listType2 = new TypeToken<List<Short>>() {}.getType();
        return gson.fromJson(respStr, listType2);
    }


    public static List<Channel> getChannels(List<String> handles) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "getChannels");

        JSONObject data = new JSONObject();

        Gson gson = new Gson();
        Type listType = new TypeToken<List<String>>() {}.getType();

        String JsonIDListString = gson.toJson(handles, listType);

        data.put("Handle List", JsonIDListString);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
        String respStr = Client.getStringResponse();

        Type listType2 = new TypeToken<List<Channel>>() {}.getType();
        return gson.fromJson(respStr, listType2);
    }

    public static List<Playlist> getPLs(String handle) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "getPLs");

        JSONObject data = new JSONObject();
        data.put("handle", handle);

        jsonObject.put("reqData", data);


        Client.sendRequest(jsonObject.toString());
        String respStr = Client.getStringResponse();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        Type listType2 = new TypeToken<List<Playlist>>() {}.getType();
        return gson.fromJson(respStr, listType2);
    }

    // ======================= Update =======================

    public static boolean like(String contentType, char likeType, String userYID, UUID contentID) throws IOException {
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

    public static void updateUser(User user) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "updateUser");

        JSONObject data = new JSONObject();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String userJson = gson.toJson(user);
        data.put("user", userJson);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }

    public static void updateChannel(Channel channel) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "updateChannel");

        JSONObject data = new JSONObject();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String channelJson = gson.toJson(channel);
        data.put("channel", channelJson);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }

    public static void updateVideo(Video video) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "updateVideo");

        JSONObject data = new JSONObject();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String videoJson = gson.toJson(video);
        data.put("video", videoJson);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }
    public static void updateShort(Short shortt) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "updateShort");

        JSONObject data = new JSONObject();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String shorttJson = gson.toJson(shortt);
        data.put("short", shorttJson);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }
    public static void updatePL(Playlist playlist) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "updatePL");

        JSONObject data = new JSONObject();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String playlistJson = gson.toJson(playlist);
        data.put("pl", playlistJson);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }
    public static void updateVideoComment(Comment comment) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "updateVideoComment");

        JSONObject data = new JSONObject();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String commentJson = gson.toJson(comment);
        data.put("comment", commentJson);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }
    public static void updateShortComment(Comment comment) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "updateShortComment");

        JSONObject data = new JSONObject();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String commentJson = gson.toJson(comment);
        data.put("comment", commentJson);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }

    public static void updateVideoViews(Video video) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "updateVideoViews");

        JSONObject data = new JSONObject();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String Json = gson.toJson(video);
        data.put("video", Json);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }

    public static void updateShortViews(Short shortt) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "updateShortViews");

        JSONObject data = new JSONObject();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String Json = gson.toJson(shortt);
        data.put("short", Json);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }

    public static boolean subscribe(Channel channel, User user) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "subscribe");

        JSONObject data = new JSONObject();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String channelJson = gson.toJson(channel);
        data.put("channel", channelJson);
        String userJson = gson.toJson(user);
        data.put("user", userJson);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
        return Client.getBooleanResponse();
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
}

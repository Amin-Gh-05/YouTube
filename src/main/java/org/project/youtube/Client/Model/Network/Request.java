package org.project.youtube.Client.Model.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;
import org.project.youtube.Client.Controller.MainController;
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

    public static List<User> getUsers(List<YID> yidList) throws IOException {
        List<String> yidStrList = new ArrayList<>();
        for (YID yid : yidList) {
            yidStrList.add(yid.toString());
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "getUsers");

        JSONObject data = new JSONObject();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        Type listType = new TypeToken<List<String>>() {}.getType();

        String JsonYIDListString = gson.toJson(yidStrList, listType);
        data.put("YID List", JsonYIDListString);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
        String respStr = Client.getStringResponse();

        Type listType2 = new TypeToken<List<User>>() {}.getType();
        return gson.fromJson(respStr, listType2);
    }

    public static Video getVideo(UUID id) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "getVideo");

        JSONObject data = new JSONObject();
        data.put("ID", id);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
        FileTransfer.getFile();
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
        FileTransfer.getFile();
        String respStr = Client.getStringResponse();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        return gson.fromJson(respStr, Short.class);
    }
    public static Channel getChannel(String handle) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "getChannel");

        JSONObject data = new JSONObject();
        data.put("handle", handle);

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

    public static Playlist getWatchLaterPlaylist(String handle) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "getWatchLaterPlaylist");

        JSONObject data = new JSONObject();
        data.put("handle", handle);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
        String respStr = Client.getStringResponse();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        return gson.fromJson(respStr, Playlist.class);
    }

    public static Playlist getLikedVideosPlaylist(String handle) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "getLikedVideosPlaylist");

        JSONObject data = new JSONObject();
        data.put("handle", handle);

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

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        Type listType = new TypeToken<List<String>>() {}.getType();

        String JsonIDListString = gson.toJson(handles, listType);

        data.put("Handle List", JsonIDListString);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
        String respStr = Client.getStringResponse();

        Type listType2 = new TypeToken<List<Channel>>() {}.getType();
        return gson.fromJson(respStr, listType2);
    }

    public static List<Channel> getSubscribedChannels(User user) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "getSubscribedChannels");

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        JSONObject data = new JSONObject();
        data.put("user", gson.toJson(user));

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



    // ======================= Update =======================

    public static boolean likeVideo(String likeType, User user, Video video) throws IOException {
        JSONObject jsonObject = new JSONObject();
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        jsonObject.put("reqType", "likeVideo");

        JSONObject data = new JSONObject();
        data.put("likeType", likeType); // L: like / D: dislike
        data.put("user", gson.toJson(user));
        data.put("video", gson.toJson(video));

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
        return Client.getBooleanResponse();
    }
    public static boolean likeShort(String likeType, User user, Short shortt) throws IOException {
        JSONObject jsonObject = new JSONObject();
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        jsonObject.put("reqType", "likeShort");

        JSONObject data = new JSONObject();
        data.put("likeType", likeType); // L: like / D: dislike
        data.put("user", gson.toJson(user));
        data.put("short", gson.toJson(shortt));

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
        return Client.getBooleanResponse();
    }
    public static boolean likeVideoComment(String likeType, User user, Comment comment) throws IOException {
        JSONObject jsonObject = new JSONObject();
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        jsonObject.put("reqType", "likeVideoComment");

        JSONObject data = new JSONObject();
        data.put("likeType", likeType); // L: like / D: dislike
        data.put("user", gson.toJson(user));
        data.put("comment", gson.toJson(comment));

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
        return Client.getBooleanResponse();
    }
    public static boolean likeShortComment(String likeType, User user, Comment comment) throws IOException {
        JSONObject jsonObject = new JSONObject();
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        jsonObject.put("reqType", "likeShortComment");

        JSONObject data = new JSONObject();
        data.put("likeType", likeType); // L: like / D: dislike
        data.put("user", gson.toJson(user));
        data.put("comment", gson.toJson(comment));

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
        return Client.getBooleanResponse();
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
        MainController.user = user;
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

    // ======================= Create =======================

    public static void createChannel(Channel channel, User user) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "createChannel");

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
    }

    public static void createVideo(Video video, String path) throws IOException, InterruptedException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "createVideo");

        JSONObject data = new JSONObject();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String json = gson.toJson(video);
        data.put("video", json);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
        Thread.sleep(200);
        FileTransfer.sendFile(path, video.getId(), "video");
    }

    public static void createShort(Short shortt, String path) throws IOException, InterruptedException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "createShort");

        JSONObject data = new JSONObject();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String json = gson.toJson(shortt);
        data.put("short", json);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
        Thread.sleep(200);
        FileTransfer.sendFile(path, shortt.getId(), "short");
    }

    public static void createPlaylist(Playlist playlist) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "createPlaylist");

        JSONObject data = new JSONObject();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String json = gson.toJson(playlist);
        data.put("playlist", json);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }

    public static void createVideoComment(Comment comment) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "createVideoComment");

        JSONObject data = new JSONObject();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String json = gson.toJson(comment);
        data.put("comment", json);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }

    public static void createShortComment(Comment comment) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "createShortComment");

        JSONObject data = new JSONObject();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String json = gson.toJson(comment);
        data.put("comment", json);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }

    public static boolean addVideoToPlaylist(Playlist playlist, Video video) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "addVideoToPlaylist");

        JSONObject data = new JSONObject();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String json = gson.toJson(playlist);
        data.put("playlist", json);
        String json2 = gson.toJson(video);
        data.put("video", json2);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
        return Client.getBooleanResponse();
    }

    public static boolean addShortToPlaylist(Playlist playlist, Short shortt) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "addShortToPlaylist");

        JSONObject data = new JSONObject();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String json = gson.toJson(playlist);
        data.put("playlist", json);
        String json2 = gson.toJson(shortt);
        data.put("short", json2);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
        return Client.getBooleanResponse();
    }

    public static boolean addPlaylistToChannel(Playlist playlist, Channel channel) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "addPlaylistToChannel");

        JSONObject data = new JSONObject();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String json = gson.toJson(playlist);
        data.put("playlist", json);
        String json2 = gson.toJson(channel);
        data.put("channel", json2);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
        return Client.getBooleanResponse();
    }

    // ======================= Delete =======================

    public static void deleteUser(User user) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "deleteUser");

        JSONObject data = new JSONObject();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String json = gson.toJson(user);
        data.put("user", json);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }

     public static void deleteChannel(Channel channel) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "deleteChannel");

        JSONObject data = new JSONObject();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String json = gson.toJson(channel);
        data.put("channel", json);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }

     public static void deleteVideo(Video video) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "deleteVideo");

        JSONObject data = new JSONObject();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String json = gson.toJson(video);
        data.put("video", json);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }

     public static void deleteShort(Short shortt) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "deleteShort");

        JSONObject data = new JSONObject();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String json = gson.toJson(shortt);
        data.put("short", json);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }

    public static void deletePlaylist(Playlist playlist) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "deletePlaylist");

        JSONObject data = new JSONObject();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String json = gson.toJson(playlist);
        data.put("playlist", json);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }

    public static void deleteVideoComment(Comment comment) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "deleteVideoComment");

        JSONObject data = new JSONObject();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String json = gson.toJson(comment);
        data.put("comment", json);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }

    public static void deleteShortComment(Comment comment) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "deleteShortComment");

        JSONObject data = new JSONObject();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String json = gson.toJson(comment);
        data.put("comment", json);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }

    public static boolean unSubscribeChannel(User user, Channel channel) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "unSubscribeChannel");

        JSONObject data = new JSONObject();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String json = gson.toJson(user);
        data.put("user", json);
        String json2 = gson.toJson(channel);
        data.put("channel", json2);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
        return Client.getBooleanResponse();
    }

    public static boolean unLikeVideo(String likeType, User user, Video video) throws IOException {
        JSONObject jsonObject = new JSONObject();
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        jsonObject.put("reqType", "unLikeVideo");

        JSONObject data = new JSONObject();
        data.put("likeType", likeType); // L: like / D: dislike
        data.put("user", gson.toJson(user));
        data.put("video", gson.toJson(video));

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
        return Client.getBooleanResponse();
    }
    public static boolean unLikeShort(String likeType, User user, Short shortt) throws IOException {
        JSONObject jsonObject = new JSONObject();
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        jsonObject.put("reqType", "unLikeShort");

        JSONObject data = new JSONObject();
        data.put("likeType", likeType); // L: like / D: dislike
        data.put("user", gson.toJson(user));
        data.put("short", gson.toJson(shortt));

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
        return Client.getBooleanResponse();
    }
    public static boolean unLikeVideoComment(String likeType, User user, Comment comment) throws IOException {
        JSONObject jsonObject = new JSONObject();
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        jsonObject.put("reqType", "unLikeVideoComment");

        JSONObject data = new JSONObject();
        data.put("likeType", likeType); // L: like / D: dislike
        data.put("user", gson.toJson(user));
        data.put("comment", gson.toJson(comment));

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
        return Client.getBooleanResponse();
    }
    public static boolean unLikeShortComment(String likeType, User user, Comment comment) throws IOException {
        JSONObject jsonObject = new JSONObject();
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        jsonObject.put("reqType", "unLikeShortComment");

        JSONObject data = new JSONObject();
        data.put("likeType", likeType); // L: like / D: dislike
        data.put("user", gson.toJson(user));
        data.put("comment", gson.toJson(comment));

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
        return Client.getBooleanResponse();
    }

    public static void removeVideoFromPlaylist(Playlist playlist, Video video) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "removeVideoFromPlaylist");

        JSONObject data = new JSONObject();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String json = gson.toJson(playlist);
        data.put("playlist", json);
        String json2 = gson.toJson(video);
        data.put("video", json2);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }

    public static void removeShortFromPlaylist(Playlist playlist, Short shortt) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reqType", "removeShortFromPlaylist");

        JSONObject data = new JSONObject();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        String json = gson.toJson(playlist);
        data.put("playlist", json);
        String json2 = gson.toJson(shortt);
        data.put("short", json2);

        jsonObject.put("reqData", data);

        Client.sendRequest(jsonObject.toString());
    }


}

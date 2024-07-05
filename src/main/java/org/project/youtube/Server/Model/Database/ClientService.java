package org.project.youtube.Server.Model.Database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;
import org.project.youtube.Server.Model.*;
import org.project.youtube.Server.Model.Short;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientService {

    // ======================= Account =======================
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

    // ======================= Read =======================

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

    public static String getChannelVideos(JSONObject data) throws SQLException {
        String handle = data.getString("handle");
        List<Video> videos = DatabaseManager.readVideos(handle);

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        Type listType = new TypeToken<List<Video>>() {}.getType();

        return gson.toJson(videos, listType);
    }

    public static String getPlayListVideos(JSONObject data) throws SQLException {
        UUID id = UUID.fromString(data.getString("ID"));
        List<Video> videos = DatabaseManager.readVideos(id);

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        Type listType = new TypeToken<List<Video>>() {}.getType();

        return gson.toJson(videos, listType);
    }

    public static String getChannelShorts(JSONObject data) throws SQLException {
        String handle = data.getString("handle");
        List<Short> shorts = DatabaseManager.readShorts(handle);

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        Type listType = new TypeToken<List<Short>>() {}.getType();

        return gson.toJson(shorts, listType);
    }

    public static String getPlayListShorts(JSONObject data) throws SQLException {
        UUID id = UUID.fromString(data.getString("ID"));
        List<Short> shorts = DatabaseManager.readShorts(id);

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        Type listType = new TypeToken<List<Short>>() {}.getType();

        return gson.toJson(shorts, listType);
    }


    public static String getChannels(JSONObject data) throws SQLException {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        Type listType1 = new TypeToken<List<String>>() {}.getType();
        List<String> handleList = gson.fromJson(data.getString("Handle List"), listType1);

        List<Channel> channels = new ArrayList<>();
        for (String handle : handleList) {
            channels.add(DatabaseManager.readChannel(handle));
        }

        Type listType2 = new TypeToken<List<Channel>>() {}.getType();

        return gson.toJson(channels, listType2);
    }

    public static String getPls(JSONObject data) throws SQLException {
        String handle = data.getString("handle");
        List<Playlist> playlists = DatabaseManager.readPlaylists(handle);

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        Type listType = new TypeToken<List<Playlist>>() {}.getType();

        return gson.toJson(playlists, listType);
    }

    // ======================= Update =======================

    public static void updateUser(JSONObject data) throws SQLException {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        User user =  gson.fromJson(data.getString("user"), User.class);

        DatabaseManager.updateUser(user);
    }

    public static void updateChannel(JSONObject data) throws SQLException {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        Channel channel =  gson.fromJson(data.getString("channel"), Channel.class);

        DatabaseManager.updateChannel(channel);
    }

    public static void updateVideo(JSONObject data) throws SQLException {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        Video video =  gson.fromJson(data.getString("video"), Video.class);

        DatabaseManager.updateVideo(video);
    }

    public static void updateShort(JSONObject data) throws SQLException {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        Short shortt =  gson.fromJson(data.getString("short"), Short.class);

        DatabaseManager.updateShort(shortt);
    }

    public static void updatePL(JSONObject data) throws SQLException {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        Playlist playlist =  gson.fromJson(data.getString("pl"), Playlist.class);

        DatabaseManager.updatePlaylist(playlist);
    }

    public static void updateVideoComment(JSONObject data) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        Comment comment =  gson.fromJson(data.getString("comment"), Comment.class);

        DatabaseManager.updateVideoComment(comment);
    }

    public static void updateShortComment(JSONObject data) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        Comment comment =  gson.fromJson(data.getString("comment"), Comment.class);

        DatabaseManager.updateShortComment(comment);
    }


    public static void updateVideoViews(JSONObject data) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        Video video =  gson.fromJson(data.getString("video"), Video.class);
        int views = video.getViews();
        views++;
        DatabaseManager.updateViews(video, views);
    }

    public static void updateShortViews(JSONObject data) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        Short shortt =  gson.fromJson(data.getString("short"), Short.class);
        int views = shortt.getViews();
        views++;
        DatabaseManager.updateViews(shortt, views);
    }

    public static void subscribe(JSONObject data) throws SQLException {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        Channel channel =  gson.fromJson(data.getString("channel"), Channel.class);
        User user =  gson.fromJson(data.getString("user"), User.class);
        int subs = channel.getSubscribers();

        if (DatabaseManager.isSubscribed(user, channel)){
            return;
        }

        subs++;
        DatabaseManager.updateSubscribers(channel, subs);
    }
}

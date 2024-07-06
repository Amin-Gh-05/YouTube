package org.project.youtube.Server.Model.Database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.JSONObject;
import org.project.youtube.Client.Model.Network.FileTransfer;
import org.project.youtube.Server.Model.*;
import org.project.youtube.Server.Model.Short;

import java.io.IOException;
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

    public static boolean subscribe(JSONObject data) {
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();

            Channel channel =  gson.fromJson(data.getString("channel"), Channel.class);
            User user =  gson.fromJson(data.getString("user"), User.class);
            int subs = channel.getSubscribers();

            if (DatabaseManager.isSubscribed(user, channel)){
                return false;
            }

            subs++;
            DatabaseManager.updateSubscribers(channel, subs);
            DatabaseManager.subscribeChannel(user, channel);
            return true;
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static boolean likeVideo(JSONObject data) {
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();

            String likeType = data.getString("likeType");
            User user = gson.fromJson(data.getString("user"), User.class);
            Video video = gson.fromJson(data.getString("video"), Video.class);

            if (DatabaseManager.isLiked(user, video) || DatabaseManager.isDisliked(user, video)) {
                return false;
            }

            if (likeType.equals("L")) {
                DatabaseManager.likeVideo(video, user);
                return true;
            }
            else if (likeType.equals("D")) {
                DatabaseManager.dislikeVideo(video, user);
                return true;
            }

            return false;
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static boolean likeShort(JSONObject data) {
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();

            String likeType = data.getString("likeType");
            User user = gson.fromJson(data.getString("user"), User.class);
            Short shortt = gson.fromJson(data.getString("short"), Short.class);

            if (DatabaseManager.isLiked(user, shortt) || DatabaseManager.isDisliked(user, shortt)) {
                return false;
            }

            if (likeType.equals("L")) {
                DatabaseManager.likeShort(shortt, user);
                return true;
            }
            else if (likeType.equals("D")) {
                DatabaseManager.dislikeShort(shortt, user);
                return true;
            }

            return false;
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static boolean likeVideoComment(JSONObject data) {
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();

            String likeType = data.getString("likeType");
            User user = gson.fromJson(data.getString("user"), User.class);
            Comment comment = gson.fromJson(data.getString("comment"), Comment.class);

            if (DatabaseManager.isVideoCommentLiked(user, comment) || DatabaseManager.isVideoCommentDisliked(user, comment)) {
                return false;
            }

            if (likeType.equals("L")) {
                DatabaseManager.likeVideoComment(comment, user);
                return true;
            }
            else if (likeType.equals("D")) {
                DatabaseManager.dislikeVideoComment(comment, user);
                return true;
            }

            return false;
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public static boolean likeShortComment(JSONObject data) {
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();

            String likeType = data.getString("likeType");
            User user = gson.fromJson(data.getString("user"), User.class);
            Comment comment = gson.fromJson(data.getString("comment"), Comment.class);

            if (DatabaseManager.isShortCommentLiked(user, comment) || DatabaseManager.isShortCommentDisliked(user, comment)) {
                return false;
            }

            if (likeType.equals("L")) {
                DatabaseManager.likeShortComment(comment, user);
                return true;
            }
            else if (likeType.equals("D")) {
                DatabaseManager.dislikeShortComment(comment, user);
                return true;
            }

            return false;
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    // ======================= Create =======================
    // TODO User...
    public static void createChannel(JSONObject data) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        User user = gson.fromJson(data.getString("user"), User.class);
        Channel channel = gson.fromJson(data.getString("channel"), Channel.class);

        DatabaseManager.createChannel(channel);
    }

    public static void createVideo(JSONObject data) throws IOException {
        FileTransfer.getFile();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        Video video = gson.fromJson(data.getString("video"), Video.class);

        DatabaseManager.createVideo(video);
    }

    public static void createShort(JSONObject data) throws IOException {
        FileTransfer.getFile();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        Short shortt = gson.fromJson(data.getString("short"), Short.class);

        DatabaseManager.createShort(shortt);
    }

    public static void createPlaylist(JSONObject data) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        Playlist playlist = gson.fromJson(data.getString("playlist"), Playlist.class);

        DatabaseManager.createPlaylist(playlist);
    }

    public static void createVideoComment(JSONObject data) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        Comment comment = gson.fromJson(data.getString("comment"), Comment.class);

        DatabaseManager.createVideoComment(comment);
    }

    public static void createShortComment(JSONObject data) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        Comment comment = gson.fromJson(data.getString("comment"), Comment.class);

        DatabaseManager.createShortComment(comment);
    }

    public static void addVideoToPlaylist(JSONObject data) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        Playlist playlist = gson.fromJson(data.getString("playlist"), Playlist.class);
        Video video = gson.fromJson(data.getString("video"), Video.class);

        DatabaseManager.addVideoToPlaylist(playlist, video);
    }

    public static void addShortToPlaylist(JSONObject data) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        Playlist playlist = gson.fromJson(data.getString("playlist"), Playlist.class);
        Short shortt = gson.fromJson(data.getString("short"), Short.class);

        DatabaseManager.addShortToPlaylist(playlist, shortt);
    }

    // ======================= Delete =======================

}

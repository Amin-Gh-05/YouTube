package org.project.youtube.Server.Model.Database;

import org.project.youtube.Server.Model.YID;
import org.project.youtube.Server.Model.*;
import org.project.youtube.Server.Model.Short;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DatabaseManager {
    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/youtube";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    // ----------------------------- CREATE -----------------------------

    public static void createUser(User user) {
        try (Connection conn = connect()) {
            // add row to users table
            String userQuery = "INSERT INTO users (yid, username, email, password, is_premium, handle) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement userPrepStat = conn.prepareStatement(userQuery);
            userPrepStat.setString(1, user.getYid().toString());
            userPrepStat.setString(2, user.getUsername());
            userPrepStat.setString(3, user.getEmail());
            userPrepStat.setString(4, user.getPassword());
            userPrepStat.setBoolean(5, user.isPremium());
            userPrepStat.setString(6, user.getHandle());
            // execute and close
            userPrepStat.executeUpdate();
            userPrepStat.close();

            // add row to personal_info table
            String infoQuery = "INSERT INTO personal_info (user_id, joined_date) VALUES (?, ?)";
            PreparedStatement infoPrepStat = conn.prepareStatement(infoQuery);
            infoPrepStat.setString(1, user.getYid().toString());
            infoPrepStat.setDate(2, Date.valueOf(user.getJoinedDate()));
            // execute and close
            infoPrepStat.executeUpdate();
            infoPrepStat.close();

            log("user " + user.getUsername() + " created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createChannel(Channel channel) {
        try (Connection conn = connect()) {
            // add row to channels table
            String channelQuery = "INSERT INTO channels (handle, name, yid, description, created_date_time, views, " +
                    "logo, banner, subscribers) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement channelPrepStat = conn.prepareStatement(channelQuery);
            channelPrepStat.setString(1, channel.getHandle());
            channelPrepStat.setString(2, channel.getName());
            channelPrepStat.setString(3, channel.getOwnerYID().toString());
            channelPrepStat.setString(4, channel.getDescription());
            channelPrepStat.setTimestamp(5, Timestamp.valueOf(channel.getCreatedDateTime()));
            channelPrepStat.setInt(6, channel.getViews());
            channelPrepStat.setBytes(7, channel.getLogo());
            channelPrepStat.setBytes(8, channel.getBanner());
            channelPrepStat.setInt(9, channel.getSubscribers());
            // execute and close
            channelPrepStat.executeUpdate();
            channelPrepStat.close();

            // add row to links table
            String linksQuery = "INSERT INTO links (handle, website, email, facebook, instagram, x, telegram, tiktok, " +
                    "discord, linkedin, reddit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement linksPrepStat = conn.prepareStatement(linksQuery);
            linksPrepStat.setString(1, channel.getHandle());
            linksPrepStat.setString(2, channel.getWebsite());
            linksPrepStat.setString(3, channel.getEmail());
            linksPrepStat.setString(4, channel.getFacebook());
            linksPrepStat.setString(5, channel.getInstagram());
            linksPrepStat.setString(6, channel.getX());
            linksPrepStat.setString(7, channel.getTelegram());
            linksPrepStat.setString(8, channel.getTiktok());
            linksPrepStat.setString(9, channel.getDiscord());
            linksPrepStat.setString(10, channel.getLinkedin());
            linksPrepStat.setString(11, channel.getReddit());
            // execute and close
            linksPrepStat.executeUpdate();
            channelPrepStat.close();

            log("channel " + channel.getHandle() + " created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createVideo(Video video) {
        try (Connection conn = connect()) {
            StringBuilder tags = new StringBuilder();
            for (String tag : video.getTags()) {
                tags.append(tag).append(" ");
            }

            // add row to videos table
            String query = "INSERT INTO videos (video_id, title, description, duration, created_date_time, tags, " +
                    "is_age_restricted, thumbnail, handle, views) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement videoPrepStat = conn.prepareStatement(query);
            videoPrepStat.setObject(1, video.getId());
            videoPrepStat.setString(2, video.getTitle());
            videoPrepStat.setString(3, video.getDescription());
            videoPrepStat.setInt(4, video.getDuration());
            videoPrepStat.setTimestamp(5, Timestamp.valueOf(video.getCreatedDateTime()));
            videoPrepStat.setString(6, tags.toString());
            videoPrepStat.setBoolean(7, video.isAgeRestricted());
            videoPrepStat.setBytes(8, video.getThumbnail());
            videoPrepStat.setString(9, video.getVideoHandle());
            videoPrepStat.setInt(10, video.getViews());
            // execute and close
            videoPrepStat.executeUpdate();
            videoPrepStat.close();

            log("video " + video.getId() + " created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createShort(Short shortVideo) {
        try (Connection conn = connect()) {
            StringBuilder tags = new StringBuilder();
            for (String tag : shortVideo.getTags()) {
                tags.append(tag).append(" ");
            }

            // add row to shorts table
            String query = "INSERT INTO shorts (short_id, title, duration, created_date_time, tags, " +
                    "is_age_restricted, thumbnail, handle, views) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement shortPrepStat = conn.prepareStatement(query);
            shortPrepStat.setObject(1, shortVideo.getId());
            shortPrepStat.setString(2, shortVideo.getTitle());
            shortPrepStat.setInt(3, shortVideo.getDuration());
            shortPrepStat.setTimestamp(4, Timestamp.valueOf(shortVideo.getCreatedDateTime()));
            shortPrepStat.setString(5, tags.toString());
            shortPrepStat.setBoolean(6, shortVideo.isAgeRestricted());
            shortPrepStat.setBytes(7, shortVideo.getThumbnail());
            shortPrepStat.setString(8, shortVideo.getShortHandle());
            shortPrepStat.setInt(9, shortVideo.getViews());
            // execute and close
            shortPrepStat.executeUpdate();
            shortPrepStat.close();

            log("short " + shortVideo.getId() + " created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createPlaylist(Playlist playlist) {
        try (Connection conn = connect()) {
            // add row to playlists table
            String query = "INSERT INTO playlists (playlist_id, name, handle, description, image) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement playlistPrepStat = conn.prepareStatement(query);
            playlistPrepStat.setObject(1, playlist.getId());
            playlistPrepStat.setString(2, playlist.getName());
            playlistPrepStat.setString(3, playlist.getChannelHandle());
            playlistPrepStat.setString(4, playlist.getDescription());
            playlistPrepStat.setBytes(5, playlist.getImage());
            // execute and update
            playlistPrepStat.executeUpdate();
            playlistPrepStat.close();

            log("playlist " + playlist.getId() + " created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createVideoComment(Comment comment) {
        try (Connection conn = connect()) {
            // add row to video_comments table
            String query = "INSERT INTO video_comments (comment_id, video_id, yid, comment_text, reply_id, created_date_time) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement commentPrepStat = conn.prepareStatement(query);
            commentPrepStat.setObject(1, comment.getId());
            commentPrepStat.setObject(2, comment.getVideoID());
            commentPrepStat.setString(3, comment.getWriterYID().toString());
            commentPrepStat.setString(4, comment.getComment());
            commentPrepStat.setObject(5, comment.getReplyOnID());
            commentPrepStat.setTimestamp(6, Timestamp.valueOf(comment.getCreatedDateTime()));
            // execute and update
            commentPrepStat.executeUpdate();
            commentPrepStat.close();

            log("video comment " + comment.getId() + " created for video " + comment.getVideoID());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createShortComment(Comment comment) {
        try (Connection conn = connect()) {
            // add row to short_comments table
            String query = "INSERT INTO short_comments (comment_id, short_id, yid, comment_text, reply_id, created_date_time) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement commentPrepStat = conn.prepareStatement(query);
            commentPrepStat.setObject(1, comment.getId());
            commentPrepStat.setObject(2, comment.getVideoID());
            commentPrepStat.setString(3, comment.getWriterYID().toString());
            commentPrepStat.setString(4, comment.getComment());
            commentPrepStat.setObject(5, comment.getReplyOnID());
            commentPrepStat.setTimestamp(6, Timestamp.valueOf(comment.getCreatedDateTime()));
            // execute and update
            commentPrepStat.executeUpdate();
            commentPrepStat.close();

            log("short comment " + comment.getId() + " created for short " + comment.getVideoID());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void likeVideo(Video video, User user) {
        try (Connection conn = connect()) {
            // add row to video_likes table
            String query = "INSERT INTO video_likes (video_id, yid) VALUES (?, ?)";
            PreparedStatement likeVideoPrepStat = conn.prepareStatement(query);
            likeVideoPrepStat.setObject(1, video.getId());
            likeVideoPrepStat.setString(2, user.getYid().toString());
            // execute and update
            likeVideoPrepStat.executeUpdate();
            likeVideoPrepStat.close();

            log("video " + video.getId() + " was liked by user " + user.getYid());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void dislikeVideo(Video video, User user) {
        try (Connection conn = connect()) {
            // add row to video_dislikes table
            String query = "INSERT INTO video_dislikes VALUES (?, ?)";
            PreparedStatement dislikeVideoPrepStat = conn.prepareStatement(query);
            dislikeVideoPrepStat.setObject(1, video.getId());
            dislikeVideoPrepStat.setString(2, user.getYid().toString());
            // execute and close
            dislikeVideoPrepStat.executeUpdate();
            dislikeVideoPrepStat.close();

            log("video " + video.getId() + " was disliked by user " + user.getYid());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void likeShort(Short shortVideo, User user) {
        try (Connection conn = connect()) {
            // add row to short_likes table
            String query = "INSERT INTO short_likes (short_id, yid) VALUES (?, ?)";
            PreparedStatement likeShortPrepStat = conn.prepareStatement(query);
            likeShortPrepStat.setObject(1, shortVideo.getId());
            likeShortPrepStat.setString(2, user.getYid().toString());
            // execute and close
            likeShortPrepStat.executeUpdate();
            likeShortPrepStat.close();

            log("short " + shortVideo.getId() + " was liked by user " + user.getYid());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void dislikeShort(Short shortVideo, User user) {
        try (Connection conn = connect()) {
            // add row to short_dislikes table
            String query = "INSERT INTO short_likes (short_id, yid) VALUES (?, ?)";
            PreparedStatement dislikeShortPrepStat = conn.prepareStatement(query);
            dislikeShortPrepStat.setObject(1, shortVideo.getId());
            dislikeShortPrepStat.setString(2, user.getYid().toString());
            // execute and close
            dislikeShortPrepStat.executeUpdate();
            dislikeShortPrepStat.close();

            log("short " + shortVideo.getId() + " was disliked by user " + user.getYid());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void likeVideoComment(Comment comment, User user) {
        try (Connection conn = connect()) {
            // add row to video_comment_likes table
            String query = "INSERT INTO video_comment_likes (comment_id, yid) VALUES (?, ?)";
            PreparedStatement likeCommPrepStat = conn.prepareStatement(query);
            likeCommPrepStat.setObject(1, comment.getId());
            likeCommPrepStat.setString(2, user.getYid().toString());
            // execute and close
            likeCommPrepStat.executeUpdate();
            likeCommPrepStat.close();

            log("video comment " + comment.getId() + " was liked by user " + user.getYid());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void dislikeVideoComment(Comment comment, User user) {
        try (Connection conn = connect()) {
            // add row to video_comment_dislikes table
            String query = "INSERT INTO video_comment_dislikes (comment_id, yid) VALUES (?, ?)";
            PreparedStatement dislikeCommPrepStat = conn.prepareStatement(query);
            dislikeCommPrepStat.setObject(1, comment.getId());
            dislikeCommPrepStat.setString(2, user.getYid().toString());
            // execute and close
            dislikeCommPrepStat.executeUpdate();
            dislikeCommPrepStat.close();

            log("video comment " + comment.getId() + " was disliked by user " + user.getYid());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void likeShortComment(Comment comment, User user) {
        try (Connection conn = connect()) {
            // add row to short_comment_likes table
            String query = "INSERT INTO short_comment_likes (comment_id, yid) VALUES (?, ?)";
            PreparedStatement likeCommPrepStat = conn.prepareStatement(query);
            likeCommPrepStat.setObject(1, comment.getId());
            likeCommPrepStat.setString(2, user.getYid().toString());
            // execute and close
            likeCommPrepStat.executeUpdate();
            likeCommPrepStat.close();

            log("short comment " + comment.getId() + " was liked by user " + user.getYid());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void dislikeShortComment(Comment comment, User user) {
        try (Connection conn = connect()) {
            // add row to short_comment_dislikes table
            String query = "INSERT INTO public.short_comment_dislikes (comment_id, yid) VALUES (?, ?)";
            PreparedStatement dislikeCommPrepStat = conn.prepareStatement(query);
            dislikeCommPrepStat.setObject(1, comment.getId());
            dislikeCommPrepStat.setString(2, user.getYid().toString());
            // execute and close
            dislikeCommPrepStat.executeUpdate();
            dislikeCommPrepStat.close();

            log("short comment " + comment.getId() + " was disliked by user " + user.getYid());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addVideoToPlaylist(Playlist playlist, Video video) {
        try (Connection conn = connect()) {
            // add row to playlist_videos table
            String query = "INSERT INTO playlist_videos VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setObject(1, playlist.getId());
            stmt.setObject(2, video.getId());
            // execute and update
            stmt.executeUpdate();
            stmt.close();

            log("video " + video.getId() + " was added to playlist " + playlist.getId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addShortToPlaylist(Playlist playlist, Short shortVideo) {
        try (Connection conn = connect()) {
            // add row to playlist_shorts table
            String query = "INSERT INTO playlist_shorts VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setObject(1, playlist.getId());
            stmt.setObject(2, shortVideo.getId());
            // execute and update
            stmt.executeUpdate();
            stmt.close();

            log("short " + shortVideo.getId() + " was added to playlist " + playlist.getId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void subscribeChannel(User user, Channel channel) {
        try (Connection conn = connect()) {
            // add row to subscribed_channels table
            String query = "INSERT INTO subscribed_channels (yid, handle) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getYid().toString());
            stmt.setString(2, channel.getHandle());
            // execute and update
            stmt.executeUpdate();
            stmt.close();

            log("channel " + channel.getHandle() + " was subscribed by user " + user.getYid());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // ----------------------------- READ -----------------------------

    public static User readUserByUsername(String username, String password) throws SQLException {
        Connection conn = connect();
        // read user from users and personal_info tables
        String query = "SELECT * FROM users JOIN personal_info ON users.yid = personal_info.user_id WHERE username = ? AND password = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();

        conn.close();

        if (rs.next()) {
            log("get user " + rs.getString("yid") + " from database");

            String dateOfBirth = null;
            if (rs.getDate("date_of_birth") != null) {
                dateOfBirth = rs.getDate("date_of_birth").toString();
            }

            return new User(
                    YID.fromString(rs.getString("yid")),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("region"),
                    dateOfBirth,
                    rs.getDate("joined_date").toString(),
                    rs.getString("gender"),
                    rs.getBytes("profile_picture"),
                    rs.getBoolean("is_premium"),
                    rs.getString("handle")
            );
        }

        return null;
    }
    public static User readUserByEmail(String username, String password) throws SQLException {
        Connection conn = connect();
        // read user from users and personal_info tables
        String query = "SELECT * FROM users JOIN personal_info ON users.yid = personal_info.user_id WHERE email = ? AND password = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, password);
        ResultSet rs = stmt.executeQuery();

        conn.close();

        if (rs.next()) {
            log("get user " + rs.getString("yid") + " from database");

            String dateOfBirth = null;
            if (rs.getDate("date_of_birth") != null) {
                dateOfBirth = rs.getDate("date_of_birth").toString();
            }

            return new User(
                    YID.fromString(rs.getString("yid")),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("region"),
                    dateOfBirth,
                    rs.getDate("joined_date").toString(),
                    rs.getString("gender"),
                    rs.getBytes("profile_picture"),
                    rs.getBoolean("is_premium"),
                    rs.getString("handle")
            );
        }

        return null;
    }

    public static Channel readChannel(String handle) throws SQLException {
        Connection conn = connect();

        // read channel from channels and links tables
        String query = "SELECT * FROM channels JOIN links ON channels.handle = links.handle WHERE channels.handle = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, handle);
        ResultSet rs = stmt.executeQuery();

        conn.close();

        if (rs.next()) {
            log("get channel " + handle + " from database");

            return new Channel(
                    rs.getString("handle"),
                    rs.getString("name"),
                    YID.fromString(rs.getString("yid")),
                    rs.getString("description"),
                    rs.getTimestamp("created_date_time").toString(),
                    rs.getInt("views"),
                    rs.getInt("subscribers"),
                    rs.getBytes("logo"),
                    rs.getBytes("banner"),
                    rs.getString("website"),
                    rs.getString("email"),
                    rs.getString("facebook"),
                    rs.getString("instagram"),
                    rs.getString("x"),
                    rs.getString("telegram"),
                    rs.getString("tiktok"),
                    rs.getString("discord"),
                    rs.getString("linkedin"),
                    rs.getString("reddit"),
                    readVideos(handle),
                    readShorts(handle),
                    readPlaylists(handle)
            );
        }

        return null;
    }

    public static Video readVideo(UUID videoId) throws SQLException {
        Connection conn = connect();

        // read videos from video, video_likes, video_dislikes tables
        String query = "SELECT videos.video_id, title, description, duration, created_date_time, tags, is_age_restricted," +
                " thumbnail, handle, views, COUNT(video_likes.video_id) - COUNT(video_dislikes.video_id) AS likes FROM videos " +
                "LEFT JOIN video_likes ON videos.video_id = video_likes.video_id LEFT JOIN video_dislikes ON videos.video_id" +
                " = video_dislikes.video_id WHERE videos.video_id = ? GROUP BY videos.video_id";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setObject(1, videoId);
        ResultSet rs = stmt.executeQuery();

        conn.close();

        if (rs.next()) {
            log("get video " + videoId + " from database");

            return new Video(
                    videoId,
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getInt("duration"),
                    rs.getTimestamp("created_date_time").toString(),
                    rs.getInt("likes"),
                    readVideoComments(videoId),
                    rs.getBoolean("is_age_restricted"),
                    new ArrayList<>(List.of(rs.getString("tags").split(" "))),
                    rs.getBytes("thumbnail"),
                    rs.getString("handle"),
                    rs.getInt("views")
            );
        }

        return null;
    }

    public static List<Video> readVideos(String handle) throws SQLException {
        Connection conn = connect();
        List<Video> videos = new ArrayList<>();

        // read IDs from videos table
        String query = "SELECT video_id FROM videos WHERE handle = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, handle);
        ResultSet rs = stmt.executeQuery();

        conn.close();

        while (rs.next()) {
            UUID videoId = (UUID) rs.getObject("video_id");
            videos.add(readVideo(videoId));
        }
        log("get videos of channel " + handle + " from database");

        return videos;
    }

    public static List<Video> readVideos(UUID playlistId) throws SQLException {
        Connection conn = connect();
        List<Video> videos = new ArrayList<>();

        // read IDs from playlist_videos channel
        String query = "SELECT playlist_videos.video_id FROM playlist_videos WHERE playlist_videos.playlist_id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setObject(1, playlistId);
        ResultSet rs = stmt.executeQuery();

        conn.close();

        while (rs.next()) {
            UUID videoId = (UUID) rs.getObject("video_id");
            videos.add(readVideo(videoId));
        }
        log("get videos of playlist " + playlistId + " from database");

        return videos;
    }

    public static Short readShort(UUID shortId) throws SQLException {
        Connection conn = connect();

        // read shorts from shorts, short_likes, short_dislikes tables
        String query = "SELECT shorts.short_id, title, duration, created_date_time, tags, is_age_restricted, thumbnail, " +
                "handle, views, COUNT(short_likes.short_id) - COUNT(short_dislikes.short_id) AS likes FROM shorts LEFT JOIN " +
                "short_likes ON shorts.short_id = short_likes.short_id LEFT JOIN short_dislikes ON shorts.short_id = " +
                "short_dislikes.short_id WHERE shorts.short_id = ? GROUP BY shorts.short_id";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setObject(1, shortId);
        ResultSet rs = stmt.executeQuery();

        conn.close();

        if (rs.next()) {
            log("get short " + shortId + " from database");

            return new Short(
                    shortId,
                    rs.getString("title"),
                    rs.getInt("duration"),
                    rs.getTimestamp("created_date_time").toString(),
                    rs.getInt("likes"),
                    readShortComments(shortId),
                    rs.getBoolean("is_age_restricted"),
                    new ArrayList<>(List.of(rs.getString("tags").split(" "))),
                    rs.getBytes("thumbnail"),
                    rs.getString("handle"),
                    rs.getInt("views")
            );
        }

        return null;
    }

    public static List<Short> readShorts(String handle) throws SQLException {
        Connection conn = connect();
        List<Short> shorts = new ArrayList<>();

        // read IDs from shorts table
        String query = "SELECT short_id FROM shorts WHERE handle = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, handle);
        ResultSet rs = stmt.executeQuery();

        conn.close();

        while (rs.next()) {
            UUID shortId = (UUID) rs.getObject("short_id");
            shorts.add(readShort(shortId));
        }
        log("get shorts of channel " + handle + " from database");

        return shorts;
    }

    public static List<Short> readShorts(UUID playlistId) throws SQLException {
        Connection conn = connect();
        List<Short> shorts = new ArrayList<>();

        // read IDs from playlist_shorts table
        String query = "SELECT playlist_shorts.short_id FROM playlist_shorts WHERE playlist_shorts.playlist_id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setObject(1, playlistId);
        ResultSet rs = stmt.executeQuery();

        conn.close();

        while (rs.next()) {
            UUID shortId = (UUID) rs.getObject("short_id");
            shorts.add(readShort(shortId));
        }
        log("get shorts of playlist " + playlistId + " from database");

        return shorts;
    }

    public static Playlist readPlaylist(UUID playlistId) throws SQLException {
        Connection conn = connect();

        // read playlist from playlists table
        String query = "SELECT * FROM playlists WHERE playlist_id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setObject(1, playlistId);
        ResultSet rs = stmt.executeQuery();

        conn.close();

        if (rs.next()) {
            log("get playlist " + playlistId + " from database");

            return new Playlist(
                    playlistId,
                    rs.getString("name"),
                    rs.getString("handle"),
                    rs.getString("description"),
                    readVideos(playlistId),
                    readShorts(playlistId),
                    rs.getBytes("image"),
                    rs.getBoolean("is_public")
            );
        }

        return null;
    }

    public static List<Playlist> readPlaylists(String handle) throws SQLException {
        Connection conn = connect();
        List<Playlist> playlists = new ArrayList<>();

        // read IDs from playlists table
        String query = "SELECT playlist_id FROM playlists WHERE handle = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, handle);
        ResultSet rs = stmt.executeQuery();

        conn.close();

        while (rs.next()) {
            UUID playlistId = (UUID) rs.getObject("playlist_id");
            playlists.add(readPlaylist(playlistId));
        }
        log("get playlists of channel " + handle + " from database");

        return playlists;
    }

    public static Comment readVideoComment(UUID commentId) throws SQLException {
        Connection conn = connect();

        // read comment from video_comments table
        String query = "SELECT video_comments.comment_id, video_id, video_comments.yid, comment_text, reply_id, created_date_time, " +
                "COUNT(video_comment_likes.comment_id) - COUNT(video_comment_dislikes.comment_id) AS likes FROM video_comments " +
                "LEFT JOIN video_comment_likes ON video_comments.comment_id = video_comment_likes.comment_id LEFT JOIN video_comment_dislikes " +
                "ON video_comments.comment_id = video_comment_dislikes.comment_id WHERE video_comments.comment_id = ? " +
                "GROUP BY video_comments.comment_id";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setObject(1, commentId);
        ResultSet rs = stmt.executeQuery();

        conn.close();

        if (rs.next()) {
            log("get video comment " + commentId + " from database");

            return new Comment(
                    (UUID) rs.getObject("comment_id"),
                    (UUID) rs.getObject("video_id"),
                    YID.fromString(rs.getString("yid")),
                    rs.getString("comment_text"),
                    rs.getInt("likes"),
                    (UUID) rs.getObject("reply_id"),
                    rs.getTimestamp("created_date_time").toString()
            );
        }

        return null;
    }

    public static Comment readShortComment(UUID commentId) throws SQLException {
        Connection conn = connect();

        // read comment from short_comments table
        String query = "SELECT short_comments.comment_id, short_id, short_comments.yid, comment_text, reply_id, created_date_time," +
                " COUNT(short_comment_likes.comment_id) - COUNT(short_comment_dislikes.comment_id) AS likes FROM short_comments" +
                " LEFT JOIN short_comment_likes ON short_comments.comment_id = short_comment_likes.comment_id LEFT JOIN short_comment_dislikes" +
                " ON short_comments.comment_id = short_comment_dislikes.comment_id WHERE short_comments.comment_id = ? " +
                "GROUP BY short_comments.comment_id";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setObject(1, commentId);
        ResultSet rs = stmt.executeQuery();

        conn.close();

        if (rs.next()) {
            log("get short comment " + commentId + " from database");

            return new Comment(
                    (UUID) rs.getObject("comment_id"),
                    (UUID) rs.getObject("short_id"),
                    YID.fromString(rs.getString("yid")),
                    rs.getString("comment_text"),
                    rs.getInt("likes"),
                    (UUID) rs.getObject("reply_id"),
                    rs.getTimestamp("created_date_time").toString()
            );
        }

        return null;
    }

    public static List<Comment> readVideoComments(UUID videoId) throws SQLException {
        Connection conn = connect();
        List<Comment> comments = new ArrayList<>();

        // read IDs from video_comments table
        String query = "SELECT comment_id FROM video_comments WHERE video_id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setObject(1, videoId);
        ResultSet rs = stmt.executeQuery();

        conn.close();

        while (rs.next()) {
            UUID commentId = (UUID) rs.getObject("comment_id");
            comments.add(readVideoComment(commentId));
        }
        log("get comments of video " + videoId + " from database");

        return comments;
    }

    public static List<Comment> readShortComments(UUID shortId) throws SQLException {
        Connection conn = connect();
        List<Comment> comments = new ArrayList<>();

        // read IDs from short_comments table
        String query = "SELECT comment_id FROM short_comments WHERE short_id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setObject(1, shortId);
        ResultSet rs = stmt.executeQuery();

        conn.close();

        while (rs.next()) {
            UUID commentId = (UUID) rs.getObject("comment_id");
            comments.add(readShortComment(commentId));
        }
        log("get comments of short " + shortId + " from database");

        return comments;
    }

    public static boolean findYid(String yid) throws SQLException {
        Connection conn = connect();

        // checks if yid exists in users
        String query = "SELECT yid FROM users WHERE yid = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, yid);
        ResultSet rs = stmt.executeQuery();

        conn.close();
        log("check database for yid " + yid + " existence");

        return rs.next();
    }

    public static boolean findUsername(String username) throws SQLException {
        Connection conn = connect();

        // checks if username exists in users
        String query = "SELECT username FROM users WHERE username = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();

        conn.close();
        log("check database for username " + username + " existence");

        return rs.next();
    }

    public static boolean findEmail(String email) throws SQLException {
        Connection conn = connect();

        // checks if email exists in users
        String query = "SELECT email FROM users WHERE email = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();

        conn.close();
        log("check database for email " + email + " existence");

        return rs.next();
    }

    public static boolean isLiked(User user, Video video) throws SQLException {
        Connection conn = connect();

        // checks if video is liked by user
        String query = "SELECT * FROM video_likes WHERE yid = ? AND video_id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, user.getYid().toString());
        stmt.setObject(2, video.getId());

        ResultSet rs = stmt.executeQuery();

        conn.close();
        log("check if video " + video.getId() + " is liked by user " + user.getYid());

        return rs.next();
    }

    public static boolean isLiked(User user, Short shortVideo) throws SQLException {
        Connection conn = connect();

        // checks if short is liked by user
        String query = "SELECT * FROM video_dislikes WHERE yid = ? AND video_id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, user.getYid().toString());
        stmt.setObject(2, shortVideo.getId());

        ResultSet rs = stmt.executeQuery();

        conn.close();
        log("check if short " + shortVideo.getId() + " is liked by user " + user.getYid());

        return rs.next();
    }

    public static boolean isVideoCommentLiked(User user, Comment comment) throws SQLException {
        Connection conn = connect();

        // checks if comment is liked by user
        String query = "SELECT * FROM video_comments WHERE yid = ? AND comment_id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, user.getYid().toString());
        stmt.setObject(2, comment.getId());

        ResultSet rs = stmt.executeQuery();

        conn.close();
        log("check if comment " + comment.getId() + " is liked by user " + user.getYid());

        return rs.next();
    }

    public static boolean isShortCommentLiked(User user, Comment comment) throws SQLException {
        Connection conn = connect();

        // checks if comment is liked by user
        String query = "SELECT * FROM short_comments WHERE yid = ? and comment_id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, user.getYid().toString());
        stmt.setObject(2, comment.getId());

        ResultSet rs = stmt.executeQuery();

        conn.close();
        log("check if comment " + comment.getId() + " is liked by user " + user.getYid());

        return rs.next();
    }

    public static boolean isDisliked(User user, Video video) throws SQLException {
        Connection conn = connect();

        // checks if video is disliked by user
        String query = "SELECT * FROM video_dislikes WHERE yid = ? AND video_id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, user.getYid().toString());
        stmt.setObject(2, video.getId());

        ResultSet rs = stmt.executeQuery();

        conn.close();
        log("check if video " + video.getId() + " is disliked by user " + user.getYid());


        return rs.next();
    }

    public static boolean isDisliked(User user, Short shortVideo) throws SQLException {
        Connection conn = connect();

        // checks if short is disliked by user
        String query = "SELECT * FROM short_dislikes WHERE yid = ? AND short_id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, user.getYid().toString());
        stmt.setObject(2, shortVideo.getId());

        ResultSet rs = stmt.executeQuery();

        conn.close();
        log("check if short " + shortVideo.getId() + " is disliked by user " + user.getYid());


        return rs.next();
    }

    public static boolean isVideoCommentDisliked(User user, Comment comment) throws SQLException {
        Connection conn = connect();

        // checks if comment is disliked by user
        String query = "SELECT * FROM video_comment_dislikes WHERE yid = ? AND comment_id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, user.getYid().toString());
        stmt.setObject(2, comment.getId());

        ResultSet rs = stmt.executeQuery();

        conn.close();
        log("check if comment " + comment.getId() + " is disliked by user " + user.getYid());

        return rs.next();
    }

    public static boolean isShortCommentDisliked(User user, Comment comment) throws SQLException {
        Connection conn = connect();

        // checks if comment is disliked by user
        String query = "SELECT * FROM short_comment_dislikes WHERE yid = ? AND comment_id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, user.getYid().toString());
        stmt.setObject(2, comment.getId());

        ResultSet rs = stmt.executeQuery();

        conn.close();
        log("check if comment " + comment.getId() + " is disliked by user " + user.getYid());

        return rs.next();
    }

    public static boolean isSubscribed(User user, Channel channel) throws SQLException {
        Connection conn = connect();

        // checks if user has subscribed channel
        String query = "SELECT * FROM subscribed_channels WHERE yid = ? AND handle = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, user.getYid().toString());
        stmt.setString(2, channel.getHandle());

        ResultSet rs = stmt.executeQuery();

        conn.close();
        log("check if channel " + channel.getHandle() + " is subscribed by user " + user.getYid());

        return rs.next();
    }

    // ----------------------------- UPDATE -----------------------------

    public static void updateUser(User user) {
        try (Connection conn = connect()) {
            // update users table
            String userQuery = "UPDATE users SET username = ?, email = ?, password = ?, is_premium = ?, handle = ? " +
                    "WHERE yid = ?";
            PreparedStatement userStmt = conn.prepareStatement(userQuery);
            userStmt.setString(1, user.getUsername());
            userStmt.setString(2, user.getEmail());
            userStmt.setString(3, user.getPassword());
            userStmt.setBoolean(4, user.isPremium());
            userStmt.setString(5, user.getHandle());
            userStmt.setString(6, user.getYid().toString());

            userStmt.executeUpdate();
            userStmt.close();

            // update personal_info table
            String infoQuery = "UPDATE personal_info SET first_name = ?, last_name = ?, region = ?, date_of_birth = ?, " +
                    "gender = ?, profile_picture = ? WHERE user_id = ?";
            PreparedStatement infoStmt = conn.prepareStatement(infoQuery);
            infoStmt.setString(1, user.getFirstName());
            infoStmt.setString(2, user.getLastName());
            infoStmt.setString(3, user.getRegion());
            infoStmt.setDate(4, Date.valueOf(user.getDateOfBirth()));
            infoStmt.setString(5, user.getGender());
            infoStmt.setBytes(6, user.getProfilePic());
            infoStmt.setString(7, user.getYid().toString());

            infoStmt.executeUpdate();
            infoStmt.close();

            log("user " + user.getUsername() + " is updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateChannel(Channel channel) {
        try (Connection conn = connect()) {
            // update channels table
            String channelQuery = "UPDATE channels SET name = ?, description = ?, logo = ?, banner = ? WHERE handle = ?";
            PreparedStatement channelStmt = conn.prepareStatement(channelQuery);
            channelStmt.setString(1, channel.getName());
            channelStmt.setString(2, channel.getDescription());
            channelStmt.setBytes(3, channel.getLogo());
            channelStmt.setBytes(4, channel.getBanner());
            channelStmt.setString(5, channel.getHandle());

            channelStmt.executeUpdate();
            channelStmt.close();

            // update links table
            String linksQuery = "UPDATE links SET website = ?, email = ?, facebook = ?, instagram = ?, x = ?, telegram = ?, " +
                    "tiktok = ?, discord = ?, linkedin = ?, reddit = ? WHERE handle = ?";
            PreparedStatement linksStmt = conn.prepareStatement(linksQuery);
            linksStmt.setString(1, channel.getWebsite());
            linksStmt.setString(2, channel.getEmail());
            linksStmt.setString(3, channel.getFacebook());
            linksStmt.setString(4, channel.getInstagram());
            linksStmt.setString(5, channel.getX());
            linksStmt.setString(6, channel.getTelegram());
            linksStmt.setString(7, channel.getTiktok());
            linksStmt.setString(8, channel.getDiscord());
            linksStmt.setString(9, channel.getLinkedin());
            linksStmt.setString(10, channel.getReddit());
            linksStmt.setString(11, channel.getHandle());

            linksStmt.executeUpdate();
            linksStmt.close();

            log("channel " + channel.getHandle() + " is updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateVideo(Video video) {
        try (Connection conn = connect()) {
            StringBuilder tags = new StringBuilder();
            for (String tag : video.getTags()) {
                tags.append(tag).append(" ");
            }

            String query = "UPDATE videos SET title = ?, description = ?, tags = ?, thumbnail = ? WHERE video_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, video.getTitle());
            stmt.setString(2, video.getDescription());
            stmt.setString(3, tags.toString());
            stmt.setBytes(4, video.getThumbnail());
            stmt.setObject(5, video.getId());

            stmt.executeUpdate();
            stmt.close();

            log("video " + video.getId() + " is updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateShort(Short shortVideo) {
        try (Connection conn = connect()) {
            StringBuilder tags = new StringBuilder();
            for (String tag : shortVideo.getTags()) {
                tags.append(tag).append(" ");
            }

            String query = "UPDATE shorts SET title = ?, tags = ?, thumbnail = ? WHERE short_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, shortVideo.getTitle());
            stmt.setString(2, tags.toString());
            stmt.setBytes(3, shortVideo.getThumbnail());
            stmt.setObject(4, shortVideo.getId());

            stmt.executeUpdate();
            stmt.close();

            log("short " + shortVideo.getId() + " is updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updatePlaylist(Playlist playlist) {
        try (Connection conn = connect()) {
            String query = "UPDATE playlists SET name = ?, description = ?, image = ?, is_public = ? WHERE playlist_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, playlist.getName());
            stmt.setString(2, playlist.getDescription());
            stmt.setBytes(3, playlist.getImage());
            stmt.setBoolean(4, playlist.isPublic());
            stmt.setObject(5, playlist.getId());

            stmt.executeUpdate();
            stmt.close();

            log("playlist " + playlist.getId() + " is updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateVideoComment(Comment comment) {
        try (Connection conn = connect()) {
            String query = "UPDATE video_comments SET comment_text = ? WHERE comment_id = ? AND yid = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, comment.getComment());
            stmt.setObject(2, comment.getId());
            stmt.setString(3, comment.getWriterYID().toString());

            stmt.executeUpdate();
            stmt.close();

            log("video comment " + comment.getId() + " is updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateShortComment(Comment comment) {
        try (Connection conn = connect()) {
            String query = "UPDATE short_comments SET comment_text = ? WHERE comment_id = ? AND yid = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, comment.getComment());
            stmt.setObject(2, comment.getId());
            stmt.setString(3, comment.getWriterYID().toString());

            stmt.executeUpdate();
            stmt.close();

            log("short comment " + comment.getId() + " is updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateViews(Video video, int views) {
        try (Connection conn = connect()) {
            String query = "UPDATE videos SET views = ? WHERE video_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, views);
            stmt.setObject(2, video.getId());

            stmt.executeUpdate();
            stmt.close();

            log("video " + video.getId() + " is viewed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateViews(Short shortVideo, int views) {
        try (Connection conn = connect()) {
            String query = "UPDATE shorts SET views = ? WHERE short_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, views);
            stmt.setObject(2, shortVideo.getId());

            stmt.executeUpdate();
            stmt.close();

            log("short " + shortVideo + " is viewed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateSubscribers(Channel channel, int subscribers) {
        try (Connection conn = connect()) {
            String query = "UPDATE channels SET views = ? WHERE handle = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, subscribers);
            stmt.setObject(2, channel.getHandle());

            stmt.executeUpdate();
            stmt.close();

            log("channel " + channel.getHandle() + " is subscribed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // ----------------------------- DELETE -----------------------------

    public static void deleteUser(User user) {
        try (Connection conn = connect()) {
            // delete from users table and children
            String query = "DELETE FROM users WHERE yid = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getYid().toString());

            stmt.executeUpdate();
            stmt.close();

            log("user " + user.getYid() + " was deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteChannel(Channel channel) {
        try (Connection conn = connect()) {
            // delete from channels table and children
            String query = "DELETE FROM channels WHERE handle = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, channel.getHandle());

            stmt.executeUpdate();
            stmt.close();

            log("channel " + channel.getHandle() + " was deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteVideo(Video video) {
        try (Connection conn = connect()) {
            // delete from videos table and children
            String query = "DELETE FROM videos WHERE video_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setObject(1, video.getId());

            stmt.executeUpdate();
            stmt.close();

            log("video " + video.getId() + " was deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteShort(Short shortVideo) {
        try (Connection conn = connect()) {
            // delete from shorts table and children
            String query = "DELETE FROM shorts WHERE short_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setObject(1, shortVideo.getId());

            stmt.executeUpdate();
            stmt.close();

            log("short " + shortVideo.getId() + " was deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deletePlaylist(Playlist playlist) {
        try (Connection conn = connect()) {
            // delete from playlists table
            String query = "DELETE FROM playlists WHERE playlist_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setObject(1, playlist.getId());

            stmt.executeUpdate();
            stmt.close();

            log("playlist " + playlist.getId() + " was deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteVideoComment(Comment comment) {
        try (Connection conn = connect()) {
            // delete from video_comments table and children
            String query = "DELETE FROM video_comments WHERE comment_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setObject(1, comment.getId());

            stmt.executeUpdate();
            stmt.close();

            log("comment " + comment.getId() + " was deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteShortComment(Comment comment) {
        try (Connection conn = connect()) {
            // delete from short_comments table and children
            String query = "DELETE FROM short_comments WHERE comment_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setObject(1, comment.getId());

            stmt.executeUpdate();
            stmt.close();

            log("comment " + comment.getId() + " was deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void unlikeVideo(Video video, User user) {
        try (Connection conn = connect()) {
            // delete like from video_likes
            String query = "DELETE FROM video_likes WHERE video_id = ? AND yid = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setObject(1, video.getId());
            stmt.setString(2, user.getYid().toString());

            stmt.executeUpdate();
            stmt.close();

            log("user " + user.getYid() + " got back like from video " + video.getId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void undislikeVideo(Video video, User user) {
        try (Connection conn = connect()) {
            // delete from video_dislikes
            String query = "DELETE FROM video_dislikes WHERE video_id = ? AND yid = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setObject(1, video.getId());
            stmt.setString(2, user.getYid().toString());

            stmt.executeUpdate();
            stmt.close();

            log("user " + user.getYid() + " got back dislike from video " + video.getId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void unlikeShort(Short shortVideo, User user) {
        try (Connection conn = connect()) {
            // delete from short_likes
            String query = "DELETE FROM short_likes WHERE short_id = ? AND yid = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setObject(1, shortVideo.getId());
            stmt.setString(2, user.getYid().toString());

            stmt.executeUpdate();
            stmt.close();

            log("user " + user.getYid() + " got back like from short " + shortVideo.getId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void undislikeShort(Short shortVideo, User user) {
        try (Connection conn = connect()) {
            // delete from short_dislikes
            String query = "DELETE FROM short_dislikes WHERE short_id = ? AND yid = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setObject(1, shortVideo.getId());
            stmt.setString(2, user.getYid().toString());

            stmt.executeUpdate();
            stmt.close();

            log("user " + user.getYid() + " got back dislike from short " + shortVideo.getId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void unlikeVideoComment(Comment comment, User user) {
        try (Connection conn = connect()) {
            // delete from video_comment_likes
            String query = "DELETE FROM video_comment_likes WHERE comment_id = ? AND yid = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setObject(1, comment.getId());
            stmt.setString(2, user.getYid().toString());

            stmt.executeUpdate();
            stmt.close();

            log("user " + user.getYid() + " got back like from comment " + comment.getId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void undislikeVideoComment(Comment comment, User user) {
        try (Connection conn = connect()) {
            // delete from video_comment_dislikes
            String query = "DELETE FROM video_comment_dislikes WHERE comment_id = ? AND yid = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setObject(1, comment.getId());
            stmt.setString(2, user.getYid().toString());

            stmt.executeUpdate();
            stmt.close();

            log("user " + user.getYid() + " got back dislike from comment " + comment.getId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void unlikeShortComment(Comment comment, User user) {
        try (Connection conn = connect()) {
            // delete from short_comment_likes
            String query = "DELETE FROM short_comment_likes WHERE comment_id = ? AND yid = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setObject(1, comment.getId());
            stmt.setString(2, user.getYid().toString());

            stmt.executeUpdate();
            stmt.close();

            log("user " + user.getYid() + " got back like from comment " + comment.getId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void undislikeShortComment(Comment comment, User user) {
        try (Connection conn = connect()) {
            // delete from short_comment_dislikes
            String query = "DELETE FROM video_comment_dislikes WHERE comment_id = ? AND yid = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setObject(1, comment.getId());
            stmt.setString(2, user.getYid().toString());

            stmt.executeUpdate();
            stmt.close();

            log("user " + user.getYid() + " got back dislike from comment " + comment.getId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void unsubscribeChannel(User user, Channel channel) {
        try (Connection conn = connect()) {
            // delete from subscribed_channels
            String query = "DELETE FROM subscribed_channels WHERE yid = ? AND handle = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getYid().toString());
            stmt.setString(2, channel.getHandle());

            stmt.executeUpdate();
            stmt.close();

            log("channel " + channel.getHandle() + " was unsubscribed by user " + user.getYid());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeVideoFromPlaylist(Playlist playlist, Video video) {
        try (Connection conn = connect()) {
            // delete from playlist_videos
            String query = "DELETE FROM playlist_videos WHERE playlist_id = ? AND video_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setObject(1, playlist.getId());
            stmt.setObject(2, video.getId());

            stmt.executeUpdate();
            stmt.close();

            log("video " + video.getId() + " was removed from playlist " + playlist.getId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeShortFromPlaylist(Playlist playlist, Short shortVideo) {
        try (Connection conn = connect()) {
            // delete from playlist_shorts
            String query = "DELETE FROM playlist_shorts WHERE playlist_id = ? AND short_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setObject(1, playlist.getId());
            stmt.setObject(2, shortVideo.getId());

            stmt.executeUpdate();
            stmt.close();

            log("short " + shortVideo.getId() + " was removed from playlist " + playlist.getId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void log(String stmt) {
        try {
            // start file writer
            FileWriter fileWriter = new FileWriter("log.txt", true);

            // get date time and format it
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            fileWriter.write("| " + LocalDateTime.now().format(formatter) + ": " + stmt + "\n");
            fileWriter.close();

            System.out.println("| " + stmt);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

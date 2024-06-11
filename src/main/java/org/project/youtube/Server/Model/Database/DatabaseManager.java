package org.project.youtube.Server.Model.Database;

import org.project.youtube.Client.Model.YID;
import org.project.youtube.Server.Model.*;
import org.project.youtube.Server.Model.Short;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class DatabaseManager {
    private static final String JDBC_URL = DatabaseConfig.getUrl();
    private static final String USERNAME = DatabaseConfig.getUser();
    private static final String PASSWORD = DatabaseConfig.getPassword();

    private static Connection connect() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    public static void createUser(User user) {
        try (Connection conn = connect()) {
            // add row to users table
            String userQuery = "INSERT INTO users (yid, username, email, password) VALUES (?, ?, ?, ?)";
            PreparedStatement userPrepStat = conn.prepareStatement(userQuery);
            userPrepStat.setString(1, YID.randomYID().toString());
            userPrepStat.setString(2, user.getUsername());
            userPrepStat.setString(3, user.getEmail());
            userPrepStat.setString(4, user.getPassword());
            // execute and close
            userPrepStat.executeUpdate();
            userPrepStat.close();

            // add row to personal_info table
            String infoQuery = "INSERT INTO personal_info (user_id, first_name, last_name, region, date_of_birth, gender, " +
                    "joined_date_time, profile_picture) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement infoPrepStat = conn.prepareStatement(infoQuery);
            infoPrepStat.setString(1, user.getYid().toString());
            infoPrepStat.setString(2, user.getFirstName());
            infoPrepStat.setString(3, user.getLastName());
            infoPrepStat.setString(4, user.getRegion());
            infoPrepStat.setString(5, user.getDateOfBirth().toString());
            infoPrepStat.setString(6, user.getGender());
            infoPrepStat.setString(7, user.getJoinedDate().toString());
            infoPrepStat.setBytes(8, user.getProfilePic());
            // execute and close
            infoPrepStat.executeUpdate();
            infoPrepStat.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createChannel(Channel channel) {
        try (Connection conn = connect()) {
            UUID linksId = UUID.randomUUID();

            // add row to channels table
            String channelQuery = "INSERT INTO channels (handle, name, yid, description, created_date_time, views, links_id, " +
                    "logo, banner, subscribers) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement channelPrepStat = conn.prepareStatement(channelQuery);
            channelPrepStat.setString(1, channel.getHandle());
            channelPrepStat.setString(2, channel.getName());
            channelPrepStat.setString(3, channel.getOwnerYID().toString());
            channelPrepStat.setString(4, channel.getDescription());
            channelPrepStat.setString(5, channel.getCreatedDateTime().toString());
            channelPrepStat.setInt(6, channel.getViews());
            channelPrepStat.setObject(7, linksId);
            channelPrepStat.setBytes(8, channel.getLogo());
            channelPrepStat.setBytes(9, channel.getBanner());
            channelPrepStat.setInt(10, channel.getSubscribers());
            // execute and close
            channelPrepStat.executeUpdate();
            channelPrepStat.close();

            // add row to links table
            String linksQuery = "INSERT INTO links (links_id, website, email, facebook, instagram, x, telegram, tiktok, " +
                    "discord, linkedin, reddit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement linksPrepStat = conn.prepareStatement(linksQuery);
            linksPrepStat.setObject(1, linksId);
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
            String query = "INSERT INTO videos (video_id, title, description, duration, created_date_time, tags, is_liked, " +
                    "is_disliked, is_age_restricted, thumbnail, handle) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement videoPrepStat = conn.prepareStatement(query);
            videoPrepStat.setObject(1, video.getId());
            videoPrepStat.setString(2, video.getTitle());
            videoPrepStat.setString(3, video.getDescription());
            videoPrepStat.setString(4, video.getDuration());
            videoPrepStat.setString(5, video.getCreatedDateTime().toString());
            videoPrepStat.setString(6, tags.toString());
            videoPrepStat.setBoolean(7, video.isLiked());
            videoPrepStat.setBoolean(8, video.isDisliked());
            videoPrepStat.setBoolean(9, video.isAgeRestricted());
            videoPrepStat.setBytes(10, video.getThumbnail());
            videoPrepStat.setString(11, video.getVideoHandle());
            // execute and close
            videoPrepStat.executeUpdate();
            videoPrepStat.close();
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
            String query = "INSERT INTO shorts (short_id, title, duration, created_date_time, tags, is_liked, is_disliked, " +
                    "is_age_restricted, thumbnail, handle) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement shortPrepStat = conn.prepareStatement(query);
            shortPrepStat.setObject(1, shortVideo.getId());
            shortPrepStat.setString(2, shortVideo.getTitle());
            shortPrepStat.setInt(3, shortVideo.getDuration());
            shortPrepStat.setString(4, shortVideo.getCreatedDateTime().toString());
            shortPrepStat.setString(5, tags.toString());
            shortPrepStat.setBoolean(6, shortVideo.isLiked());
            shortPrepStat.setBoolean(7, shortVideo.isDisliked());
            shortPrepStat.setBoolean(8, shortVideo.isAgeRestricted());
            shortPrepStat.setBytes(9, shortVideo.getThumbnail());
            shortPrepStat.setString(10, shortVideo.getShortHandle());
            // execute and close
            shortPrepStat.executeUpdate();
            shortPrepStat.close();
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createVideoComment(Comment comment) {
        try (Connection conn = connect()) {
            // add row to video_comments table
            String query = "INSERT INTO video_comments (comment_id, video_id, yid, comment_text, reply_id, created_date_time, " +
                    "is_liked, is_disliked) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement commentPrepStat = conn.prepareStatement(query);
            commentPrepStat.setObject(1, comment.getId());
            commentPrepStat.setObject(2, comment.getVideoID());
            commentPrepStat.setString(3, comment.getWriterYID().toString());
            commentPrepStat.setString(4, comment.getComment());
            commentPrepStat.setObject(5, comment.getReplyOnID());
            commentPrepStat.setString(6, comment.getCreatedDateTime().toString());
            commentPrepStat.setBoolean(7, comment.isLiked());
            commentPrepStat.setBoolean(8, comment.isDisliked());
            // execute and update
            commentPrepStat.executeUpdate();
            commentPrepStat.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createShortComment(Comment comment) {
        try (Connection conn = connect()) {
            // add row to short_comments table
            String query = "INSERT INTO short_comments (comment_id, short_id, yid, comment_text, reply_id, created_date_time, " +
                    "is_liked, is_disliked) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement commentPrepStat = conn.prepareStatement(query);
            commentPrepStat.setObject(1, comment.getId());
            commentPrepStat.setObject(2, comment.getVideoID());
            commentPrepStat.setString(3, comment.getWriterYID().toString());
            commentPrepStat.setString(4, comment.getComment());
            commentPrepStat.setObject(5, comment.getReplyOnID());
            commentPrepStat.setString(6, comment.getCreatedDateTime().toString());
            commentPrepStat.setBoolean(7, comment.isLiked());
            commentPrepStat.setBoolean(8, comment.isDisliked());
            // execute and update
            commentPrepStat.executeUpdate();
            commentPrepStat.close();
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

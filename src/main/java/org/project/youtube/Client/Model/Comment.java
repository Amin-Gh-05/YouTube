package org.project.youtube.Client.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Comment {
    private UUID id;
    private UUID videoID; // video or short ID
    private YID writerYID;
    private String comment;
    private int likes;
    private String createdDateTime;

    public Comment(UUID id, UUID videoID, YID writerYID, String comment, int like, String createdDateTime) {
        this.id = id;
        this.videoID = videoID;
        this.writerYID = writerYID;
        this.comment = comment;
        this.likes = like;
        this.createdDateTime = createdDateTime;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getVideoID() {
        return videoID;
    }

    public void setVideoID(UUID videoID) {
        this.videoID = videoID;
    }

    public YID getWriterYID() {
        return writerYID;
    }

    public void setWriterYID(YID writerYID) {
        this.writerYID = writerYID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getLike() {
        return likes;
    }

    public void setLike(int like) {
        this.likes = like;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime == null ? null : LocalDateTime.parse(createdDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }
}

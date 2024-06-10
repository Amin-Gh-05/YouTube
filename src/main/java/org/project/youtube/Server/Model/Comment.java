package org.project.youtube.Server.Model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Comment {
    private UUID id;
    private UUID videoID; // video or short ID
    private YID writerYID;
    private String comment;
    private int like;
    private UUID replyOnID;
    private LocalDateTime createdDateTime;
    private boolean isLiked;
    private boolean isDisliked;

    public Comment(UUID id, UUID videoID, YID writerYID, String comment, int like, UUID replyOnID, LocalDateTime createdDateTime, boolean isLiked, boolean isDisliked) {
        this.id = id;
        this.videoID = videoID;
        this.writerYID = writerYID;
        this.comment = comment;
        this.like = like;
        this.replyOnID = replyOnID;
        this.createdDateTime = createdDateTime;
        this.isLiked = isLiked;
        this.isDisliked = isDisliked;
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
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public UUID getReplyOnID() {
        return replyOnID;
    }

    public void setReplyOnID(UUID replyOnID) {
        this.replyOnID = replyOnID;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public boolean isDisliked() {
        return isDisliked;
    }

    public void setDisliked(boolean disliked) {
        isDisliked = disliked;
    }
}

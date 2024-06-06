package org.project.youtube.Model;

import java.util.UUID;

public class Comment {
    UUID id;
    UUID videoID; // video or short ID
    YID writerYID;
    String comment;
    int like;
    UUID replyOnID;

    public Comment(UUID id, UUID videoID, YID writerYID, String comment, int like, UUID replyOnID) {
        this.id = id;
        this.videoID = videoID;
        this.writerYID = writerYID;
        this.comment = comment;
        this.like = like;
        this.replyOnID = replyOnID;
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
}

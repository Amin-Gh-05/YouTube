package org.project.youtube.Client.Model;

import java.util.List;
import java.util.UUID;

public class Short {
    UUID id;
    String title;
    String address;
    int duration;
    String createdDateTime;
    int likes;
    List<Comment> comments;

    public Short(UUID id, String title, String address, int duration, String createdDateTime, int likes, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.duration = duration;
        this.createdDateTime = createdDateTime;
        this.likes = likes;
        this.comments = comments;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}

package org.project.youtube.Client.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public class Short {
    private UUID id;
    private String title;
    private int duration;
    private String createdDateTime;
    private int likes;
    private List<Comment> comments;
    private boolean isAgeRestricted;
    private List<String> tags;
    private byte[] thumbnail;
    private String shortHandle;
    private int views;

    public Short(UUID id, String title, int duration, String createdDateTime, int likes, List<Comment> comments,
                 boolean isAgeRestricted, List<String> tags, byte[] thumbnail, String shortHandle, int views) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.createdDateTime = createdDateTime;
        this.likes = likes;
        this.comments = comments;
        this.isAgeRestricted = isAgeRestricted;
        this.tags = tags;
        this.thumbnail = thumbnail;
        this.shortHandle = shortHandle;
        this.views = views;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime == null ? null : LocalDateTime.parse(createdDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
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

    public boolean isAgeRestricted() {
        return isAgeRestricted;
    }

    public void setAgeRestricted(boolean ageRestricted) {
        isAgeRestricted = ageRestricted;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getShortHandle() {
        return shortHandle;
    }

    public void setShortHandle(String shortHandle) {
        this.shortHandle = shortHandle;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}

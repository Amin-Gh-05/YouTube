package org.project.youtube.Server.Model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Short {
    private UUID id;
    private String title;
    private int duration;
    private LocalDateTime createdDateTime;
    private int likes;
    private List<Comment> comments;
    private boolean isLiked;
    private boolean isDisliked;
    private boolean isAgeRestricted;
    private List<String> tags;

    public Short(UUID id, String title, int duration, LocalDateTime createdDateTime, int likes, List<Comment> comments, boolean isLiked, boolean isDisliked, boolean isAgeRestricted) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.createdDateTime = createdDateTime;
        this.likes = likes;
        this.comments = comments;
        this.isLiked = isLiked;
        this.isDisliked = isDisliked;
        this.isAgeRestricted = isAgeRestricted;
    }

    public Short(UUID id, String title, int duration, LocalDateTime createdDateTime, int likes, List<Comment> comments, boolean isLiked, boolean isDisliked, boolean isAgeRestricted, List<String> tags) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.createdDateTime = createdDateTime;
        this.likes = likes;
        this.comments = comments;
        this.isLiked = isLiked;
        this.isDisliked = isDisliked;
        this.isAgeRestricted = isAgeRestricted;
        this.tags = tags;
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
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
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
}

package org.project.youtube.Server.Model;

import javafx.scene.image.Image;

public class Channel {
    String handle;
    String name;
    YID ownerYID;
    String description;
    String createdDateTime;
    int views;
    int subscribers;
    Image logo;
    Image banner;

    // Links
    String website;
    String email;
    String facebook;
    String instagram;
    String X;
    String telegram;
    String tiktok;
    String discord;
    String linkedin;
    String reddit;

    public Channel(String handle, String name, YID ownerYID, String description, String createdDateTime, int views, int subscribers, Image logo, Image banner, String website, String email, String facebook, String instagram, String x, String telegram, String tiktok, String discord, String linkedin, String reddit) {
        this.handle = handle;
        this.name = name;
        this.ownerYID = ownerYID;
        this.description = description;
        this.createdDateTime = createdDateTime;
        this.views = views;
        this.subscribers = subscribers;
        this.logo = logo;
        this.banner = banner;
        this.website = website;
        this.email = email;
        this.facebook = facebook;
        this.instagram = instagram;
        X = x;
        this.telegram = telegram;
        this.tiktok = tiktok;
        this.discord = discord;
        this.linkedin = linkedin;
        this.reddit = reddit;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public YID getOwnerYID() {
        return ownerYID;
    }

    public void setOwnerYID(YID ownerYID) {
        this.ownerYID = ownerYID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(int subscribers) {
        this.subscribers = subscribers;
    }

    public Image getLogo() {
        return logo;
    }

    public void setLogo(Image logo) {
        this.logo = logo;
    }

    public Image getBanner() {
        return banner;
    }

    public void setBanner(Image banner) {
        this.banner = banner;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getX() {
        return X;
    }

    public void setX(String x) {
        X = x;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public String getTiktok() {
        return tiktok;
    }

    public void setTiktok(String tiktok) {
        this.tiktok = tiktok;
    }

    public String getDiscord() {
        return discord;
    }

    public void setDiscord(String discord) {
        this.discord = discord;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getReddit() {
        return reddit;
    }
    public void setReddit(String reddit) {
        this.reddit = reddit;
    }

}

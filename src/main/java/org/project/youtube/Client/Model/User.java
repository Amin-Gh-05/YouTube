package org.project.youtube.Client.Model;

import javafx.scene.image.Image;

public class User {
    YID yid;
    String username;
    String email;
    String password;

    // Personal Info
    String firstName;
    String lastName;
    String region;
    String dateOfBirth;
    String joinedDateTime;
    String gender;
    Image profilePic;

    public User(YID yid, String username, String email, String password, String firstName, String lastName, String region, String dateOfBirth, String joinedDateTime, String gender, Image profilePic) {
        this.yid = yid;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.region = region;
        this.dateOfBirth = dateOfBirth;
        this.joinedDateTime = joinedDateTime;
        this.gender = gender;
        this.profilePic = profilePic;
    }

    public YID getYid() {
        return yid;
    }

    public void setYid(YID yid) {
        this.yid = yid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getJoinedDateTime() {
        return joinedDateTime;
    }

    public void setJoinedDateTime(String joinedDateTime) {
        this.joinedDateTime = joinedDateTime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Image getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(Image profilePic) {
        this.profilePic = profilePic;
    }
}

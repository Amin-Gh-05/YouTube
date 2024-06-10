package org.project.youtube.Client.Model;

import org.project.youtube.Server.Model.YID;

import java.time.LocalDate;

public class User {
    private org.project.youtube.Server.Model.YID yid;
    private String username;
    private String email;
    private String password;

    // personal info
    private String firstName;
    private String lastName;
    private String region;
    private LocalDate dateOfBirth;
    private LocalDate joinedDate;
    private String gender;
    private byte[] profilePic;

    public User(org.project.youtube.Server.Model.YID yid, String username, String email, String password, String firstName, String lastName, String region, LocalDate dateOfBirth, LocalDate joinedDate, String gender, byte[] profilePic) {
        this.yid = yid;
        this.username = username;
        this.email = email;
        this.password = password;

        // personal info
        this.firstName = firstName;
        this.lastName = lastName;
        this.region = region;
        this.dateOfBirth = dateOfBirth;
        this.joinedDate = joinedDate;
        this.gender = gender;
        this.profilePic = profilePic;
    }

    public org.project.youtube.Server.Model.YID getYid() {
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDate joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public byte[] getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
    }
}

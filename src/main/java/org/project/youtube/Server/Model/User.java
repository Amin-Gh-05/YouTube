package org.project.youtube.Server.Model;

import java.time.LocalDate;

public class User {
    private YID yid;
    private String username;
    private String email;
    private String password;
    private boolean isPremium;
    private String handle;

    // personal info
    private String firstName;
    private String lastName;
    private String region;
    private String dateOfBirth;
    private String joinedDate;
    private String gender;
    private byte[] profilePic;

    public User(YID yid, String username, String email, String password, String firstName, String lastName, String region,
                LocalDate dateOfBirth, LocalDate joinedDate, String gender, byte[] profilePic, boolean isPremium, String handle) {
        this.yid = yid;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isPremium = isPremium;
        this.handle = handle;

        // personal info
        this.firstName = firstName;
        this.lastName = lastName;
        this.region = region;
        this.dateOfBirth = dateOfBirth.toString();
        this.joinedDate = joinedDate.toString();
        this.gender = gender;
        this.profilePic = profilePic;
    }
    public User(YID yid, String username, String email, String password) {
        this.yid = yid;
        this.username = username;
        this.email = email;
        this.password = password;
        this.isPremium = false;
        this.handle = "user-" + yid.toString();

        // personal info
        this.firstName = null;
        this.lastName = null;
        this.region = null;
        this.dateOfBirth = null;
        this.joinedDate = LocalDate.now().toString();
        this.gender = null;
        this.profilePic = null;
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

    public LocalDate getDateOfBirth() {
        return LocalDate.parse(dateOfBirth);
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth.toString();
    }

    public LocalDate getJoinedDate() {
        return LocalDate.parse(joinedDate);
    }

    public void setJoinedDate(LocalDate joinedDate) {
        this.joinedDate = joinedDate.toString();
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

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }
}

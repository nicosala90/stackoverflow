package com.codecool.stackoverflowtw.dao.model;

import java.sql.Timestamp;

public class User {
    private int userId;
    private String userName;
    private String userPassword;
    private String userEmail;
    private Timestamp registrationDateTime;
    private boolean isAdmin;
    private boolean isRejected;

    public User(int userId, String userName, String userPassword, String userEmail, Timestamp registrationDateTime, boolean isAdmin) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.registrationDateTime = registrationDateTime;
        this.isAdmin = isAdmin;
        this.isRejected = isRejected;
    }
    public User(int userId, String userName, String userPassword, String userEmail, Timestamp registrationDateTime, boolean isAdmin, boolean isRejected) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.registrationDateTime = registrationDateTime;
        this.isAdmin = isAdmin;
        this.isRejected = isRejected;
    }

    public User(String userName, String userPassword, String userEmail, Timestamp registrationDateTime) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.registrationDateTime = registrationDateTime;
        this.isAdmin = false;
        this.isRejected = false;
    }


    public int getUserId() {
        return userId;
    }
    public String getUserName() {
        return userName;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public Timestamp getRegistrationDateTime() {
        return registrationDateTime;
    }
    public boolean isAdmin() {
        return isAdmin;
    }
    public boolean isRejected() {
        return isRejected;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public void setEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
    public void setRejected(boolean rejected) {
        isRejected = rejected;
    }
}

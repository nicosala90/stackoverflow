package com.codecool.stackoverflowtw.dao.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class User {
    private int userId;
    private String userName;
    private String userPassword;
    private Timestamp registrationDateTime;
    private boolean isChecked;

    public User(String userName,String userPassword, Timestamp registrationDateTime) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.registrationDateTime = registrationDateTime;
        this.isChecked = false;
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

    public Timestamp getRegistrationDateTime() {
        return registrationDateTime;
    }

    public boolean isChecked() {
        return isChecked;
    }
}

package com.codecool.stackoverflowtw.dao.model;

import java.sql.Timestamp;

public class User {

    private int userId;
    private String userName;
    private Timestamp registrationDateTime;
    private String password;
    private boolean isChecked;

    public User(String userName,String userPassword, Timestamp registrationDateTime) {
        this.name = userName;
        this.password = userPassword;
        this.registrationDateTime = registrationDateTime;
    }

    public User(int userId, String userName, Timestamp registrationDateTime, String password, boolean isChecked) {
        this.userId = userId;
        this.userName = userName;
        this.registrationDateTime = registrationDateTime;
        this.password = password;
        this.isChecked = isChecked;
    }

     public int getUserId() {
        return id;
    }

    public String getUserName() {
        return name;
    }


    public String getPassword() {
        return password;
    }

    public Timestamp getRegistrationDateTime() {
        return registrationDateTime;
    }

    public boolean isChecked() {
        return isChecked;
    }
}

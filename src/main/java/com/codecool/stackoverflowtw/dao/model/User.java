package com.codecool.stackoverflowtw.dao.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class User {
    private int id;
    private String name;
    private String password;
    private Timestamp registrationDateTime;
    private boolean isChecked;

    public User(String userName,String userPassword, Timestamp registrationDateTime) {
        this.name = userName;
        this.password = userPassword;
        this.registrationDateTime = registrationDateTime;
        this.isChecked = false;
    }

     public int getUserId() {
        return id;
    }

    public String getUserName() {
        return name;
    }

    public String getUserPassword() {
        return password;
    }

    public Timestamp getRegistrationDateTime() {
        return registrationDateTime;
    }

    public boolean isChecked() {
        return isChecked;
    }
}

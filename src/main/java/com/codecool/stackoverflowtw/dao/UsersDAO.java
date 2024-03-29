package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.model.User;

import java.util.List;

public interface UsersDAO {

    void addUser(String userName, String userPassword, String userEmail);
    void addFirstAdminUser(String userName, String userPassword, String userEmail);
    List<User> getAllUser();
    User getUserById(int userId);
    User getUserForAdminCheck(int userId);
    boolean deleteUser(int userId);
}

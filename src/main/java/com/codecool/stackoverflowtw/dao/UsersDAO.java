package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.User;

import java.util.List;

public interface UsersDAO {

    void addNewUser(String userName,String userPassword);
    List<User> getAllUser();
    User getUserById(int userId);
    User getUserForAdminCheck(int userId);
    boolean deleteUser(int userId);
}

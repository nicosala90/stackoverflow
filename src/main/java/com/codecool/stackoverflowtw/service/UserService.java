package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.NewUserDTO;
import com.codecool.stackoverflowtw.controller.dto.UserDTO;
import com.codecool.stackoverflowtw.dao.UsersDAO;
import com.codecool.stackoverflowtw.dao.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UsersDAO usersDAO;

    @Autowired
    public UserService(UsersDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = usersDAO.getAllUser();
        return users.stream()
                .map(user -> new UserDTO(user.getUserId(), user.getUserName(), user.getRegistrationDateTime(), user.getPassword(), user.isChecked()))
                .toList();
    }

    public UserDTO getUserById(int userId) {
        User user = usersDAO.getUserById(userId);
        return new UserDTO(user.getUserId(), user.getUserName(), user.getRegistrationDateTime(), user.getPassword(), user.isChecked());
    }

    public void addNewUser(NewUserDTO user) {
        usersDAO.addUser(user.userName(), user.password());
    }
}

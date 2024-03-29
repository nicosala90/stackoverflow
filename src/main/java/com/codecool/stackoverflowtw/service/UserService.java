package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.dto.user.FirstAdminUserDTO;
import com.codecool.stackoverflowtw.dto.user.NewUserDTO;
import com.codecool.stackoverflowtw.dto.user.UserDTO;
import com.codecool.stackoverflowtw.dao.UsersDAO;
import com.codecool.stackoverflowtw.model.User;
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
                .map(user -> new UserDTO(user.getUserId(), user.getUserName(), user.getUserEmail(), user.getUserPassword(), user.getRegistrationDateTime(), user.isAdmin(),user.isRejected()))
                .toList();
    }

    public UserDTO getUserById(int userId) {
        User user = usersDAO.getUserById(userId);
        return new UserDTO(user.getUserId(), user.getUserName(), user.getUserEmail(),  user.getUserPassword(), user.getRegistrationDateTime(),user.isAdmin(), user.isRejected());
    }
    public boolean deleteUser(int userId){
        return usersDAO.deleteUser(userId);
    }

    public void addNewUser(NewUserDTO user) {
        usersDAO.addUser(user.userName(), user.userPassword(), user.userEmail());
    }

    public void addFirstAdminUser(FirstAdminUserDTO firstAdminUser) {
        usersDAO.addUser(firstAdminUser.userName(), firstAdminUser.userPassword(), firstAdminUser.userEmail());
    }
}

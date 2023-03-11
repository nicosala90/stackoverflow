package com.codecool.stackoverflowtw.controller.dto.controller;


import com.codecool.stackoverflowtw.controller.dto.user.NewUserDTO;
import com.codecool.stackoverflowtw.controller.dto.user.UserDTO;
import com.codecool.stackoverflowtw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public void addNewUser(@RequestBody NewUserDTO user) {
        userService.addNewUser(user);
    }

    @GetMapping("/all")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserDTO getUserById(@PathVariable int userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping("/{userId}")
    public boolean deleteUser(@PathVariable int userId) {
        return userService.deleteUser(userId);
    }
}

package com.codecool.stackoverflowtw.controller;


import com.codecool.stackoverflowtw.controller.dto.NewUserDTO;
import com.codecool.stackoverflowtw.controller.dto.UserDTO;
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

    @GetMapping("/all")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/")
    public void addNewUser(@RequestBody NewUserDTO user){
        userService.addNewUser(user);
    }
    @GetMapping("/{userId}")
    public UserDTO getUserById(@PathVariable int userId){
        return userService.getUserById(userId);
    }
}

package com.codecool.stackoverflowtw.controller.dto.user;

import java.sql.Timestamp;

public record UserDTO(int userId, String userName,String userEmail, Timestamp registrationDateTime, String password, boolean isAdmin, boolean isRejected) {}

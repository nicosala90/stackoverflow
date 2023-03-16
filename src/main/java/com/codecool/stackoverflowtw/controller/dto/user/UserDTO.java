package com.codecool.stackoverflowtw.controller.dto.user;

import java.sql.Timestamp;

public record UserDTO(int userId, String userName, String userPassword, String userEmail, Timestamp registrationDateTime, boolean isAdmin, boolean isRejected) { }

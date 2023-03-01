package com.codecool.stackoverflowtw.controller.dto;

import java.sql.Timestamp;

public record UserDTO(int userId, String userName, Timestamp registrationDateTime, String password, boolean isChecked) {
}

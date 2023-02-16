package com.codecool.stackoverflowtw.controller.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record QuestionDTO(int id, String title, String description, Timestamp created) {}

package com.codecool.stackoverflowtw.controller.dto.question;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record QuestionDTO(int questionId, int userId, String questionText, Timestamp postingTime) {}

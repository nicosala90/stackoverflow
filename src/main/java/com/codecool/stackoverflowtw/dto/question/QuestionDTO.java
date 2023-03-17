package com.codecool.stackoverflowtw.dto.question;

import java.sql.Timestamp;

public record QuestionDTO(int questionId, int userId, String questionText, Timestamp postingTime) {}

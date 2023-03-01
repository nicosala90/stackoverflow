package com.codecool.stackoverflowtw.controller.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record QuestionDTO(int question_id, int user_id, String question_text, Timestamp posting_time) {}

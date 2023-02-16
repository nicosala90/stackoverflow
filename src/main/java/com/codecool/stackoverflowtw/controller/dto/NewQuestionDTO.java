package com.codecool.stackoverflowtw.controller.dto;

import java.sql.Timestamp;

public record NewQuestionDTO(int user_id, String question_text, int points,
                             Timestamp posting_date){}

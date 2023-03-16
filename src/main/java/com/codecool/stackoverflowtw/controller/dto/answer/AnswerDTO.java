package com.codecool.stackoverflowtw.controller.dto.answer;

import java.sql.Timestamp;

public record AnswerDTO(int answerId, int questionId, int userId, String answerText, Timestamp postingTime,
                        boolean isChecked, boolean isRejected) {
}

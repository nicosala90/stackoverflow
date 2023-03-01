package com.codecool.stackoverflowtw.dao.model;

import java.sql.Timestamp;

public class Question {

    private int questionId;
    private int userId;
    private String questionText;
    private Timestamp postingTime;

    public Question(int questionId, int userId, String questionText, Timestamp postingTime) {
        this.questionId = questionId;
        this.userId = userId;
        this.questionText = questionText;
        this.postingTime = postingTime;
    }

    public Question(String questionText, Timestamp postingTime) {
        this.questionText = questionText;
        this.postingTime = postingTime;
    }

    public int getQuestionId() {
        return questionId;
    }

    public int getUserId() {
        return userId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public Timestamp getPostingTime() {
        return postingTime;
    }
}

package com.codecool.stackoverflowtw.dao.model;

import java.sql.Timestamp;

public class Answer {
    private int answerId;
    private int questionId;
    private int userId;
    private String answerText;
    private Timestamp postingTime;

    public Answer(int questionId, String answerText, Timestamp postingTime) {
        this.questionId = questionId;
        this.answerText = answerText;
        this.postingTime = postingTime;
    }

    public Answer(int answerId, int questionId, int userId, String answerText, Timestamp postingTime) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.userId = userId;
        this.answerText = answerText;
        this.postingTime = postingTime;
    }

    public int getAnswerId() {
        return answerId;
    }

    public int getUserId() {
        return userId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public int getQuestionId() {
        return questionId;
    }

    public Timestamp getPostingTime() {
        return postingTime;
    }


}

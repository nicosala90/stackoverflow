package com.codecool.stackoverflowtw.model;

import java.sql.Timestamp;

public class Answer {
    private int answerId;
    private int questionId;
    private int userId;
    private String answerText;
    private Timestamp postingTime;

    private boolean isChecked;
    private boolean isRejected;


    public Answer(int questionId, String answerText, Timestamp postingTime) {
        this.questionId = questionId;
        this.answerText = answerText;
        this.postingTime = postingTime;
        this.isChecked = false;
        this.isRejected = false;
    }

    public Answer(int answerId, int questionId, int userId, String answerText, Timestamp postingTime, boolean isChecked, boolean isRejected) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.userId = userId;
        this.answerText = answerText;
        this.postingTime = postingTime;
        this.isChecked = isChecked;
        this.isRejected = isRejected;
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

    public boolean isChecked() {return isChecked;}

    public boolean isRejected() {return isRejected;}

    public void setChecked(boolean checked) {isChecked = checked;}

    public void setRejected(boolean rejected) {isRejected = rejected;}
}

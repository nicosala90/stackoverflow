package com.codecool.stackoverflowtw.dao.model;

import java.sql.Timestamp;

public class Question {

    private int questionId;
    private int userId;
    private String questionText;
    private Timestamp postingTime;

    private boolean isChecked;
    private boolean isRejected;
    private int numberOfAnswers;


    public Question(int questionId, int userId, String questionText, Timestamp postingTime, boolean isChecked, boolean isRejected, int numberOfAnswers) {
        this.questionId = questionId;
        this.userId = userId;
        this.questionText = questionText;
        this.postingTime = postingTime;
        this.isChecked = isChecked;
        this.isRejected = isRejected;
        this.numberOfAnswers = numberOfAnswers;
    }

    public Question(int questionId, int userId, String questionText, Timestamp postingTime) {
        this.questionId = questionId;
        this.userId = userId;
        this.questionText = questionText;
        this.postingTime = postingTime;
    }


    public Question(int userId, String questionText, Timestamp postingTime) {
        this.userId = userId;
        this.questionText = questionText;
        this.postingTime = postingTime;
    }

    public int getQuestionId() {
        return questionId;
    }

    public int getUserId() {return userId;}

    public String getQuestionText() {
        return questionText;
    }

    public Timestamp getPostingTime() {
        return postingTime;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isRejected() {
        return isRejected;
    }

    public void setRejected(boolean rejected) {
        isRejected = rejected;
    }

    public int getNumberOfAnswers() {
        return numberOfAnswers;
    }

    public void setNumberOfAnswers(int numberOfAnswers) {
        this.numberOfAnswers = numberOfAnswers;
    }
}

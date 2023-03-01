package com.codecool.stackoverflowtw.dao.model;

import java.sql.Timestamp;

public class Question {

    private int question_id;
    private int user_id;
    private String question_text;
    private Timestamp posting_time;

    public Question(int question_id, int user_id, String question_text, Timestamp posting_time) {
        this.question_id = question_id;
        this.user_id = user_id;
        this.question_text = question_text;
        this.posting_time = posting_time;
    }

    public Question(int user_id, String question_text, Timestamp posting_time) {
        this.user_id = user_id;
        this.question_text = question_text;
        this.posting_time = posting_time;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public Timestamp getPosting_time() {
        return posting_time;
    }
}

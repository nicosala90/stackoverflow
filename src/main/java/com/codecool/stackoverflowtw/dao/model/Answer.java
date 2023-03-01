package com.codecool.stackoverflowtw.dao.model;

import java.sql.Timestamp;

public class Answer {
    private int answer_id;
    private int question_id;
    private int user_id;
    private String answer_text;
    private Timestamp posting_time;

    public Answer(String answer_text){
        this.answer_text = answer_text;
    }

    public Answer(int question_id, String answer_text, Timestamp posting_time) {
        this.question_id = question_id;
        this.answer_text = answer_text;
        this.posting_time = posting_time;
    }

    public Answer(String answer_text, Timestamp posting_time) {
        this.answer_text = answer_text;
        this.posting_time = posting_time;
    }

    public Answer(int answer_id, int question_id, int user_id, String answer_text, Timestamp posting_time){
        this.answer_id = answer_id;
        this.question_id = question_id;
        this.user_id = user_id;
        this.answer_text = answer_text;
        this.posting_time  = posting_time;
    }

    public int getAnswer_id() {
        return answer_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getAnswer_text() {
        return answer_text;
    }

    public int getQuestion_id() {
        return question_id;
    }
    public Timestamp getPosting_time() {
        return posting_time;
    }


}

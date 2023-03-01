package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.Answer;

import java.util.List;

public interface AnswersDAO {
    void postAnswer(String answerText);
    List<Answer> getAllAnswerByQuestion(int questionId);
    boolean deleteAnswer(int id);
}

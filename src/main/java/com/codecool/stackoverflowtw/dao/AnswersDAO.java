package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.Answer;

import java.util.List;

public interface AnswersDAO {
    void postAnswer(String answerText, int id);
    List<Answer> getAllAnswerByQuestion(int questionId);
    int getCountOfAnswerForAQuestion(int id);
    boolean deleteAnswer(int id);
}

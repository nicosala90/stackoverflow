package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.model.Answer;

import java.util.List;

public interface AnswersDAO {
    void postAnswer(String answerText, int answerId);
    List<Answer> getAllAnswerByQuestion(int questionId);
    int getCountOfAnswerForAQuestion(int questionId);
    boolean deleteAnswer(int answerId);
}

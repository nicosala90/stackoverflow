package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.Question;

import java.util.List;

public interface QuestionsDAO {
    void addQuestion(int userId, String questionText);

    List<Question> getAllQuestions();

    List<Question> getAllQuestionSortByAlphabet();

    List<Question> getAllQuestionSortByDate();

    List<Question> getAllQuestionSortByAnswerCount();

    Question getQuestionById(int id);

    boolean deleteQuestionById(int id);

}

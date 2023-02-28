package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionsDAO {
    void addQuestion(String text);
    List<Question> getAllQuestions();
    List<Question> getAllQuestionSortByAlphabet();
    List<Question> getAllQuestionSortByDate();
    List<Question> getAllQuestionSortByCount();
    Question getQuestionById(int id);
    boolean deleteQuestionById(int id);

}

package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionsDAO {
    void addQuestion(String text);
    List<Question> getAllQuestions();
    Question getQuestionById(int id);
    boolean deleteQuestionById(int id);

}

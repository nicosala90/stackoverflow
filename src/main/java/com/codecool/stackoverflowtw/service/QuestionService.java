package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.dao.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private QuestionsDAO questionsDAO;

    @Autowired
    public QuestionService(QuestionsDAO questionsDAO) {
        this.questionsDAO = questionsDAO;
    }

    public List<QuestionDTO> getAllQuestions() {
        List<Question> questions = questionsDAO.getAllQuestions();
        return questions.stream().map(question -> new QuestionDTO(question.getQuestionId(), question.getUserId(), question.getQuestionText(), question.getPostingTime())).toList();

    }

    public List<QuestionDTO> getAllQuestionSortByAlphabet() {
        List<Question> questions = questionsDAO.getAllQuestionSortByAlphabet();
        return questions.stream().map(question -> new QuestionDTO(question.getQuestionId(), question.getUserId(), question.getQuestionText(), question.getPostingTime())).toList();
    }

    public List<QuestionDTO> getAllQuestionSortByDate() {
        List<Question> questions = questionsDAO.getAllQuestionSortByDate();
        return questions.stream().map(question -> new QuestionDTO(question.getQuestionId(), question.getUserId(), question.getQuestionText(), question.getPostingTime())).toList();
    }

    public List<QuestionDTO> getAllQuestionSortByAnswerCount() {
        List<Question> questions = questionsDAO.getAllQuestionSortByAnswerCount();
        return questions.stream().map(question -> new QuestionDTO(question.getQuestionId(), question.getUserId(), question.getQuestionText(), question.getPostingTime())).toList();
    }

    public QuestionDTO getQuestionById(int id) {
        Question question = questionsDAO.getQuestionById(id);
        return new QuestionDTO(question.getQuestionId(), question.getUserId(), question.getQuestionText(), question.getPostingTime());
    }

    public boolean deleteQuestionById(int id) {
        return questionsDAO.deleteQuestionById(id);
    }

    public void addNewQuestion(NewQuestionDTO question) {

        questionsDAO.addQuestion(question.userId(), question.questionText());

    }
}

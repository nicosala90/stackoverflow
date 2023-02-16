package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.dao.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
        List<QuestionDTO> questionDTOList =
                questions.stream().map(question -> new QuestionDTO(question.getQuestion_id(),
                        "Title", question.getQuestion_text(), question.getPosting_time())).toList();
       return questionDTOList;
    }

    public QuestionDTO getQuestionById(int id) {
        // TODO
        //return new QuestionDTO(id, "example title", "example desc", LocalDateTime.now());
        return null;
    }

    public boolean deleteQuestionById(int id) {
        // TODO
        return false;
    }

    //public int addNewQuestion(NewQuestionDTO question) {
    public void addNewQuestion(NewQuestionDTO question) {
        Date date = new Date();
        Question questionToPost = new Question(0, question.user_id(), question.question_text(), 0
                , new Timestamp(date.getTime()));
        questionsDAO.addQuestion(questionToPost);
    }
}

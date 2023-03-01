package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.AnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.dao.AnswersDAO;
import com.codecool.stackoverflowtw.dao.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    private AnswersDAO answersDAO;

    @Autowired
    public AnswerService(AnswersDAO answersDAO) {
        this.answersDAO = answersDAO;
    }
    public List<AnswerDTO> getAllAnswers(int questionId){
        List<Answer> answers = answersDAO.getAllAnswerByQuestion(questionId);
        return answers.stream().map((answer -> new AnswerDTO(answer.getAnswer_id(), answer.getQuestion_id(), answer.getUser_id(),answer.getAnswer_text(), answer.getPosting_time()))).toList();
    }
    public void addNewAnswer(NewAnswerDTO answer){
        answersDAO.postAnswer(answer.text());
    }
    public boolean deleteAnswer(int id){
        return answersDAO.deleteAnswer(id);
    }
}

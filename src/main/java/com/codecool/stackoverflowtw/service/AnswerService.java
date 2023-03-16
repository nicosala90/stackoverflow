package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.answer.AnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.answer.NewAnswerDTO;
import com.codecool.stackoverflowtw.dao.AnswersDAO;
import com.codecool.stackoverflowtw.dao.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerService {

    private AnswersDAO answersDAO;

    @Autowired
    public AnswerService(AnswersDAO answersDAO) {
        this.answersDAO = answersDAO;
    }

    public List<AnswerDTO> getAllAnswers(int questionId) {
        List<Answer> answers = answersDAO.getAllAnswerByQuestion(questionId);
        return answers.stream().map((answer -> new AnswerDTO(answer.getAnswerId(), answer.getQuestionId(),
                answer.getUserId(), answer.getAnswerText(), answer.getPostingTime(), answer.isChecked(),
                answer.isRejected()))).toList();
    }
    public int getAllAnswerToOneQuestion(int questionId){
        List<Answer> answers = answersDAO.getAllAnswerByQuestion(questionId);
        return (int) answers.stream().filter(answer -> answer.getQuestionId() == questionId).count();
    }
    public void addNewAnswer(NewAnswerDTO answer) {
        answersDAO.postAnswer(answer.answerText(), answer.questionId());
    }

    public boolean deleteAnswer(int id) {
        return answersDAO.deleteAnswer(id);
    }
}

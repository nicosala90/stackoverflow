package com.codecool.stackoverflowtw.controller;

import com.codecool.stackoverflowtw.dto.answer.AnswerDTO;
import com.codecool.stackoverflowtw.dto.answer.NewAnswerDTO;
import com.codecool.stackoverflowtw.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/answers")
public class AnswerController {

    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping("/{questionId}/all")
    public List<AnswerDTO> getAllAnswersByQuestion(@PathVariable int questionId) {
        return answerService.getAllAnswers(questionId);
    }

    @GetMapping("/{questionId}/answerCount")
    public int getCountOfAnswerToAQuestion(@PathVariable int questionId) {
        return answerService.getAllAnswerToOneQuestion(questionId);
    }

    @DeleteMapping("/{answerId}")
    public boolean deleteAnswer(@PathVariable int answerId) {
        return answerService.deleteAnswer(answerId);
    }

    @PostMapping("/")
    public void addNewAnswer(@RequestBody NewAnswerDTO answer) {
        answerService.addNewAnswer(answer);
    }
}

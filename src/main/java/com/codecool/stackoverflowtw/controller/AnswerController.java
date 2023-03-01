package com.codecool.stackoverflowtw.controller;

import com.codecool.stackoverflowtw.controller.dto.AnswerDTO;
import com.codecool.stackoverflowtw.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("answers")
public class AnswerController {

    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping("/{id}/all")
    public List<AnswerDTO> getAllAnswersByQuestion(@PathVariable int id) {
        return answerService.getAllAnswers(id);
    }
    @DeleteMapping("/{id}")
    public boolean deleteAnswer(@PathVariable int id) {
        return answerService.deleteAnswer(id);
    }

}

package com.codecool.stackoverflowtw.controller;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/all")
    public List<QuestionDTO> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/sortByAlphabet")
    public List<QuestionDTO> getAllQuestionSortByAlphabet() {//maybe with Enum can do it dynamically changinf
        return questionService.getAllQuestionSortByAlphabet();
    }

    @GetMapping("/sortByDate")
    public List<QuestionDTO> getAllQuestionSortByDate() {
        return questionService.getAllQuestionSortByDate();
    }

    @GetMapping("/sortByCount")
    public List<QuestionDTO> getAllQuestionSortByAnswerCount() {
        return questionService.getAllQuestionSortByAnswerCount();
    }

    @GetMapping("/{id}")
    public QuestionDTO getQuestionById(@PathVariable int id) {
        return questionService.getQuestionById(id);
    }

    @PostMapping("/")
    public void addNewQuestion(@RequestBody NewQuestionDTO question) {
        questionService.addNewQuestion(question);
    }

    @DeleteMapping("/{id}")
    public boolean deleteQuestionById(@PathVariable int id) {
        return questionService.deleteQuestionById(id);
    }
}

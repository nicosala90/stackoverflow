package com.codecool.stackoverflowtw.controller.dto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainPageController {

    @GetMapping
    public String index() {
        return "mainPage";
    }

    @GetMapping("/mainPage-question-list")
    public String questions() {
        return "main_question_list";
    }

    @GetMapping("/mainPage-question{id}")
    public String questionDetailById(@PathVariable int id) {
        return "question_detail";
    }

    @GetMapping("/mainPage-new-question")
    public String newQuestion() {
        return "add_question";
    }

    @GetMapping("/mainPage-new-answer{questionId}")
    public String newAnswer(@PathVariable int questionId) {
        return "add_answer";
    }
}

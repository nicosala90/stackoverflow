package com.codecool.stackoverflowtw.controller.dto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/question-list")
    public String questions() {
        return "question_list";
    }

    @GetMapping("/question{id}")
    public String questionDetailById(@PathVariable int id) {
        return "question_detail";
    }

    @GetMapping("/new-question")
    public String newQuestion() {
        return "add_question";
    }

    @GetMapping("/new-answer{questionId}")
    public String newAnswer(@PathVariable int questionId) {
        return "add_answer";
    }

    @GetMapping("/new-user")
    public String newUser() {
        return "add_user";
    }
    @GetMapping("/user-list-for-admin")
    public String users() {
        return "user_list";
    }

    @GetMapping("/user{id}")
    public String userDetailById(@PathVariable int id) {
        return "user_detail";
    }

}

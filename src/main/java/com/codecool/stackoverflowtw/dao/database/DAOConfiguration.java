package com.codecool.stackoverflowtw.dao.database;

import com.codecool.stackoverflowtw.dao.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DAOConfiguration {

    @Bean
    public UsersDAO userDAO(Database database) {
        return new UsersDaoJdbc(database);
    }

    @Bean
    public QuestionsDAO questionsDAO(Database database) {
        return new QuestionsDaoJdbc(database);
    }

    @Bean
    public AnswersDAO answersDAO(Database database) {
        return new AnswersDaoJdbc(database);
    }
}

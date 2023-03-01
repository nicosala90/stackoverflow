package com.codecool.stackoverflowtw;

import com.codecool.stackoverflowtw.dao.AnswersDAO;
import com.codecool.stackoverflowtw.dao.AnswersDaoJdbc;
import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.dao.QuestionsDaoJdbc;
import com.codecool.stackoverflowtw.dao.database.*;
import com.codecool.stackoverflowtw.dao.model.Question;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class StackoverflowTwApplication {

    public static void main(String[] args) {
        SpringApplication.run(StackoverflowTwApplication.class, args);
    }

    @Bean
    public QuestionsDAO questionsDAO() {
        Database database = new DatabasePSQL(System.getenv("PSQL_URL"), System.getenv("PSQL_USERNAME"), System.getenv("PSQL_PASSWORD"));
        Map<String, String> tables = Map.of(
                "users", TableStatements.USER,
                "questions", TableStatements.QUESTION,
                "answers", TableStatements.ANSWER
        );
        TableInitializer tableInitializer = new TableInitializerPSQL(database, tables);
        tableInitializer.initialize();
        return new QuestionsDaoJdbc(tableInitializer, database);
    }
    @Bean
    public AnswersDAO answersDAO() {
        Database database = new DatabasePSQL(System.getenv("PSQL_URL"), System.getenv("PSQL_USERNAME"), System.getenv("PSQL_PASSWORD"));
        Map<String, String> tables = Map.of(
                "users", TableStatements.USER,
                "questions", TableStatements.QUESTION,
                "answers", TableStatements.ANSWER
        );
        TableInitializer tableInitializer = new TableInitializerPSQL(database, tables);
        tableInitializer.initialize();
        return new AnswersDaoJdbc(tableInitializer, database);
    }
}

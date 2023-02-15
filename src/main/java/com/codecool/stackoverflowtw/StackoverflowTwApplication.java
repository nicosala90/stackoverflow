package com.codecool.stackoverflowtw;

import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.dao.QuestionsDaoJdbc;
import com.codecool.stackoverflowtw.dao.database.Database;
import com.codecool.stackoverflowtw.dao.database.DatabasePSQL;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StackoverflowTwApplication {

    public static void main(String[] args) {
        SpringApplication.run(StackoverflowTwApplication.class, args);
    }

    @Bean
    public QuestionsDAO questionsDAO() {
        Database database = new DatabasePSQL(System.getenv("PSQL_URL"), System.getenv("PSQL_USERNAME"), System.getenv("PSQL_PASSWORD"));
        return new QuestionsDaoJdbc(database);
    }
}

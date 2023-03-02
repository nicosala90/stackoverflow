package com.codecool.stackoverflowtw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootApplication
public class StackoverflowTwApplication {

    public static void main(String[] args) {
        SpringApplication.run(StackoverflowTwApplication.class, args);
    }


    @Bean
    public QuestionsDAO questionsDAO() {
        Database database = new DatabasePSQL(System.getenv("PSQL_URL"), System.getenv("PSQL_USERNAME"), System.getenv("PSQL_PASSWORD"));
        List<String> tables = List.of(
                TableStatements.USER,
                TableStatements.QUESTION,
                TableStatements.ANSWER,
                TableStatements.DROPCONSTRAINTQUESTIONUSERID,
                TableStatements.DROPCONSTRAINTANSWERUSERID,
                TableStatements.DROPCONSTRAINTANSWERQUESTIONRID,
                TableStatements.ANSWERFOREIGHNKEYSUSERID,
                TableStatements.QUESTIONFOREIGHNKEYSUSERID,
                TableStatements.ANSWERFOREIGHNKEYSQUESTIONID
        );
        TableInitializer tableInitializer = new TableInitializerPSQL(database, tables);
        tableInitializer.initialize();
        return new QuestionsDaoJdbc(tableInitializer, database);
    }
    @Bean
    public AnswersDAO answersDAO() {
        Database database = new DatabasePSQL(System.getenv("PSQL_URL"), System.getenv("PSQL_USERNAME"), System.getenv("PSQL_PASSWORD"));
        List<String> tables = List.of(
                TableStatements.USER,
                TableStatements.QUESTION,
                TableStatements.ANSWER,
                TableStatements.DROPCONSTRAINTQUESTIONUSERID,
                TableStatements.DROPCONSTRAINTANSWERUSERID,
                TableStatements.DROPCONSTRAINTANSWERQUESTIONRID,
                TableStatements.ANSWERFOREIGHNKEYSUSERID,
                TableStatements.QUESTIONFOREIGHNKEYSUSERID,
                TableStatements.ANSWERFOREIGHNKEYSQUESTIONID
        );
        TableInitializer tableInitializer = new TableInitializerPSQL(database, tables);
        tableInitializer.initialize();
        return new AnswersDaoJdbc(tableInitializer, database);
    }  @Bean
    public UsersDAO userDAO() {
        Database database = new DatabasePSQL(System.getenv("PSQL_URL"), System.getenv("PSQL_USERNAME"), System.getenv("PSQL_PASSWORD"));
        List<String> tables = List.of(
                TableStatements.USER,
                TableStatements.QUESTION,
                TableStatements.ANSWER,
                TableStatements.DROPCONSTRAINTQUESTIONUSERID,
                TableStatements.DROPCONSTRAINTANSWERUSERID,
                TableStatements.DROPCONSTRAINTANSWERQUESTIONRID,
                TableStatements.ANSWERFOREIGHNKEYSUSERID,
                TableStatements.QUESTIONFOREIGHNKEYSUSERID,
                TableStatements.ANSWERFOREIGHNKEYSQUESTIONID
        );
        TableInitializer tableInitializer = new TableInitializerPSQL(database, tables);
        tableInitializer.initialize();
        return new UsersDaoJdbc(tableInitializer, database);
    }

}

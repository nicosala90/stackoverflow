package com.codecool.stackoverflowtw.dao.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class DataSourceConfiguration {

    @Bean
    public Database getDataSource() {
        Database database = new DatabasePSQL(
                System.getenv("PSQL_URL"),
                System.getenv("PSQL_USERNAME"),
                System.getenv("PSQL_PASSWORD"));

        Map<String, String> tables = Map.of(
                "users", TableStatements.USER,
                "questions", TableStatements.QUESTION,
                "answers", TableStatements.ANSWER
        );
        TableInitializer tableInitializer = new TableInitializerPSQL(database, tables);
        tableInitializer.initialize();
        return database;
    }

}

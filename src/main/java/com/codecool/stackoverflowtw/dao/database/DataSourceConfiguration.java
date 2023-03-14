package com.codecool.stackoverflowtw.dao.database;

import com.codecool.stackoverflowtw.dao.UsersDaoJdbc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataSourceConfiguration {

    @Bean
    public Database getDataSource() {
        Database database = new DatabasePSQL(
                System.getenv("PSQL_URL"),
                System.getenv("PSQL_USERNAME"),
                System.getenv("PSQL_PASSWORD"));

        List<String> tables = List.of(
/*  // Table drop
                TableStatements.DROPANSWERTABLE,
                TableStatements.DROPQUESTIONTABLE,
                TableStatements.DROPUSERTABLE,
 */
                TableStatements.USER,
                TableStatements.QUESTION,
                TableStatements.ANSWER,
                TableStatements.DROPCONSTRAINTQUESTIONUSERID,
                TableStatements.DROPCONSTRAINTANSWERUSERID,
                TableStatements.DROPCONSTRAINTANSWERQUESTIONID,
                TableStatements.ANSWERFOREIGHNKEYSUSERID,
                TableStatements.QUESTIONFOREIGHNKEYSUSERID,
                TableStatements.ANSWERFOREIGHNKEYSQUESTIONID
        );

        TableInitializer tableInitializer = new TableInitializerPSQL(database, tables);
        tableInitializer.initialize();
/*
        UsersDaoJdbc adminUser = new UsersDaoJdbc(database);
        adminUser.addFirstAdminUser("Admin", "admin", "firstAdmin@company.com");
*/
        return database;
    }
}
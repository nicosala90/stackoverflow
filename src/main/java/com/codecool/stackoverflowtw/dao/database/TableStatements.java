package com.codecool.stackoverflowtw.dao.database;

public interface TableStatements {

    String DROPQUESTIONTABLE = "DROP TABLE IF EXISTS QUESTIONS";
    String DROPANSWERTABLE = "DROP TABLE IF EXISTS ANSWERS";
    String DROPUSERTABLE = "DROP TABLE IF EXISTS USERS";
    String USER = """
            CREATE TABLE IF NOT EXISTS USERS (
                id SERIAL PRIMARY KEY NOT NULL,
                user_name CHARACTER VARYING(30),
                user_password CHARACTER VARYING(30),
                user_email CHARACTER VARYING(30),
                registration_date TIMESTAMPTZ,
                is_admin BOOLEAN,
                is_rejected BOOLEAN
            );
            """;
    String QUESTION = """
            CREATE TABLE IF NOT EXISTS QUESTIONS (
                question_id SERIAL PRIMARY KEY NOT NULL,
                user_id int,
                question_text TEXT,
                posting_time TIMESTAMPTZ,
                is_checked BOOLEAN,
                is_rejected BOOLEAN
            );
            """;

    String ANSWER = """
            CREATE TABLE IF NOT EXISTS ANSWERS (
                answer_id SERIAL PRIMARY KEY NOT NULL,
                user_id int,
                answer_text TEXT,
                question_id int,
                posting_time TIMESTAMPTZ,
                is_checked BOOLEAN,
                is_rejected BOOLEAN                
            );
            """;

    String QUESTIONFOREIGHNKEYSUSERID = """
            ALTER TABLE QUESTIONS
                    ADD CONSTRAINT fk_question_user FOREIGN KEY (user_id) REFERENCES users (id) 
                    ON DELETE CASCADE;                      
               """;

    String ANSWERFOREIGHNKEYSUSERID = """
            ALTER TABLE ANSWERS 
                    ADD CONSTRAINT fk_answer_user FOREIGN KEY (user_id) REFERENCES users (id) 
                    ON DELETE CASCADE;
               """;

    String ANSWERFOREIGHNKEYSQUESTIONID = """
            ALTER TABLE ANSWERS
                    ADD CONSTRAINT fk_answer_question FOREIGN KEY (question_id) REFERENCES questions (question_id) 
                    ON DELETE CASCADE;
               """;
    String DROPCONSTRAINTQUESTIONUSERID = """
            ALTER TABLE QUESTIONS DROP CONSTRAINT IF EXISTS fk_question_user;
            """;

    String DROPCONSTRAINTANSWERUSERID = """
            ALTER TABLE ANSWERS DROP CONSTRAINT IF EXISTS fk_answer_user;
            """;

    String DROPCONSTRAINTANSWERQUESTIONID = """
            ALTER TABLE ANSWERS DROP CONSTRAINT IF EXISTS fk_answer_question;
            """;

}

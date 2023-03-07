package com.codecool.stackoverflowtw.dao.database;

public interface TableStatements {
    String USER = """
            CREATE TABLE IF NOT EXISTS USERS (
                id SERIAL PRIMARY KEY NOT NULL,
                user_name CHARACTER VARYING(30),
                registration_date TIMESTAMPTZ,
                password CHARACTER VARYING(30),
                is_admin BOOLEAN
            );
            """;
    String QUESTION = """
            CREATE TABLE IF NOT EXISTS QUESTIONS (
                question_id SERIAL PRIMARY KEY NOT NULL,
                user_id int,
                question_text TEXT,
                posting_time TIMESTAMPTZ
            );
            """;

    String ANSWER = """
            CREATE TABLE IF NOT EXISTS ANSWERS (
                answer_id SERIAL PRIMARY KEY NOT NULL,
                user_id int,
                answer_text TEXT,
                question_id int,
                posting_time TIMESTAMPTZ
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

    String DROPCONSTRAINTANSWERQUESTIONRID = """
        ALTER TABLE ANSWERS DROP CONSTRAINT IF EXISTS fk_answer_question;
        """;
    String INSERTUSERS = """
            INSERT 
            """;

}

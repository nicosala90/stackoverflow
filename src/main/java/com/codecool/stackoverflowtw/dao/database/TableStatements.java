package com.codecool.stackoverflowtw.dao.database;

public interface TableStatements {

    String USER = """
            CREATE TABLE IF NOT EXISTS USERS (
                user_id SERIAL PRIMARY KEY NOT NULL,
                username CHARACTER VARYING(30),
                registration_date TIMESTAMPTZ,
                password CHARACTER VARYING(30),
                is_admin BOOLEAN
         
            );
            """;
    String QUESTION = """
            CREATE TABLE IF NOT EXISTS QUESTIONS (
                user_id Serial,
                question_id SERIAL PRIMARY KEY NOT NULL,
                question_text TEXT,
                points INTEGER,
                posting_time TIMESTAMPTZ,
                
                CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES USERS(user_id)
            )  
            """;

    String ANSWER = """
            CREATE TABLE IF NOT EXISTS ANSWERS (
                answer_id SERIAL PRIMARY KEY NOT NULL,
                user_id SERIAL,
                answer_text TEXT,
                points INTEGER,
                question_id SERIAL,
                posting_time TIMESTAMPTZ,
                
                CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES USERS(user_id),
                CONSTRAINT fk_question FOREIGN KEY(question_id) REFERENCES Questions(question_id)
                
            );
            """;

    String QUESTIONFOREIGHNKEYS = """
         ALTER TABLE QUESTIONS
                 ADD CONSTRAINT fk_orders_customers FOREIGN KEY (customer_id) REFERENCES customers (id);
 
                user_id SERIAL REFERENCES users(user_id),
            );
            """;
}

package com.codecool.stackoverflowtw.dao.database;

public interface TableStatements {
    String QUESTION = """
            CREATE TABLE IF NOT EXISTS QUESTIONS (
                question_id SERIAL PRIMARY KEY NOT NULL,

                question_text TEXT,
                points INTEGER,
                posting_time TIMESTAMPTZ
            );
            """;

    String ANSWER = """
            CREATE TABLE IF NOT EXISTS ANSWERS (
                answer_id SERIAL PRIMARY KEY NOT NULL,
                user_id SERIAL REFERENCES users(user_id),
                answer_text TEXT,
                points INTEGER,
                question_id SERIAL REFERENCES questions(question_id),
                posting_time TIMESTAMPTZ
            );
            """;

    String USER = """
            CREATE TABLE IF NOT EXISTS USERS (
                user_id SERIAL PRIMARY KEY NOT NULL,
                username CHARACTER VARYING(30),
                registration_date TIMESTAMPTZ,
                password CHARACTER VARYING(30),
                is_admin BOOLEAN
            );
            """;

    String QUESTIONFOREIGHNKEYS = """
         ALTER TABLE QUESTIONS
                 ADD CONSTRAINT fk_orders_customers FOREIGN KEY (customer_id) REFERENCES customers (id);
 
                user_id SERIAL REFERENCES users(user_id),
            );
            """;
}

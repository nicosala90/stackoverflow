package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.database.Database;

public class QuestionsDaoJdbc implements QuestionsDAO {
    private final Database database;

    public QuestionsDaoJdbc(Database database) {
        this.database = database;
    }

    @Override
    public void sayHi() {
        System.out.println("Hi DAO!");
    }
}

package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.database.Database;
import com.codecool.stackoverflowtw.dao.database.TableInitializer;

public class AnswersDaoJdbc implements AnswersDAO{

    private final TableInitializer tableInitializer;
    private final Database database;

    public AnswersDaoJdbc(TableInitializer tableInitializer, Database database){
        this.database = database;
        this.tableInitializer = tableInitializer;
    }


    @Override
    public void sayAnswer(){

    }
}

package com.codecool.stackoverflowtw.dao.database;

public class allTableClearMainUserCreate {

    String DROPQUESTIONTABLE = "DROP TABLE QUESTIONS";
    String DROPANSWERTABLE = "DROP TABLE ANSWERS";
    String DROPUSERTABLE = "DROP TABLE USERS";
    String ADDMAINUSERTOUSERTABLE = "INSERT INTO users(id, user_name,password, user_email, registration_date,  is_admin, is_rejected) values('1','Admin','admin@company.com','','true','false') ";

}

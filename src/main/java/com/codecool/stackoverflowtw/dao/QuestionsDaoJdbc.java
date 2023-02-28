package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.database.Database;
import com.codecool.stackoverflowtw.dao.database.TableInitializer;
import com.codecool.stackoverflowtw.dao.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
public class QuestionsDaoJdbc implements QuestionsDAO {
    private final TableInitializer tableInitializer;
    private final Database database;

    public QuestionsDaoJdbc(TableInitializer tableInitializer, Database database) {
        this.tableInitializer = tableInitializer;
        this.database = database;
    }

    @Override
    public void addQuestion() {
        Date date = new Date();
        String question_text = "Ez a másodi harmadik kérdésünk";
        //post(new Question(question_text));
       post(new Question(2, 5, question_text, 0, new Timestamp(date.getTime())));
    }

    public void post(Question question) {
        // Write the insert statements here!
        //String template = "INSERT INTO questions(question_text) values(?) ";
        String template = "INSERT INTO questions(question_id, user_id, question_text, points, posting_time) values( ?, ?,?, ?, ?) ";
        //String template = "INSERT INTO questions(question_id, user_id, question_text, points, posting_time) values(?, ?, ?, ?, ?) ";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            prepare(question, statement);
            statement.executeUpdate();
            System.out.println("Question posted. :)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void prepare(Question question, PreparedStatement statement) throws SQLException {
        statement.setInt(1, question.getQuestion_id());
        statement.setInt(2, question.getUser_id());
        statement.setString(3, question.getQuestion_text());
        statement.setInt(4, question.getPoints());
        statement.setTimestamp(5, question.getPosting_time());
    }
}

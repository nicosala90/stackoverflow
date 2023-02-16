package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.database.Database;
import com.codecool.stackoverflowtw.dao.database.TableInitializer;
import com.codecool.stackoverflowtw.dao.model.Question;

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
    public void addQuestion(Question question) {
//        Date date = new Date();
//        String question_text = "Ez az első kérdésünk";
//        //post(new Question(question_text));
//       post(new Question(2, question_text, 0, new Timestamp(date.getTime())));

        post(question);
    }

    public void post(Question question) {
        // Write the insert statements here!
        //String template = "INSERT INTO questions(question_text) values(?) ";
        String template = "INSERT INTO questions(user_id, question_text, points, posting_time) values(?, ?, ?, ?) ";
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
        statement.setInt(1, question.getUser_id());
        statement.setString(2, question.getQuestion_text());
        statement.setInt(3, question.getPoints());
        statement.setTimestamp(4, question.getPosting_time());
    }
}

package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.database.Database;
import com.codecool.stackoverflowtw.dao.database.TableInitializer;
import com.codecool.stackoverflowtw.dao.model.Answer;
import com.codecool.stackoverflowtw.dao.model.Question;

import java.sql.*;
import java.util.Date;

public class AnswersDaoJdbc implements AnswersDAO {

    private final TableInitializer tableInitializer;
    private final Database database;

    public AnswersDaoJdbc(TableInitializer tableInitializer, Database database) {
        this.database = database;
        this.tableInitializer = tableInitializer;
    }


    @Override
    public void sayAnswer() {
        Date date = new Date();
        String answer_text = "First answer";
        post(new Answer(2, 2, 2, answer_text, 3, new Timestamp(date.getTime())));
    }

    public void post(Answer answer) {
        String template = "INSERT INTO answers(answer_id, question_id, user_id, answer_text, points, posting_time ) values(?, ?, ?, ?, ?, ?) ";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            prepare(answer, statement);
            statement.executeUpdate();
            System.out.println("Answer posted. :)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void prepare(Answer answer, PreparedStatement statement) throws SQLException {
        statement.setInt(1, answer.getAnswer_id());
        statement.setInt(2, answer.getQuestion_id());
        statement.setInt(3, answer.getUser_id());
        statement.setString(4, answer.getAnswer_text());
        statement.setInt(5, answer.getPoints());
        statement.setTimestamp(6, answer.getPosting_time());

    }
}

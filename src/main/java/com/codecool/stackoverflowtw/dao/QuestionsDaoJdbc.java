package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.dao.database.Database;
import com.codecool.stackoverflowtw.dao.database.TableInitializer;
import com.codecool.stackoverflowtw.dao.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        String question_text = "Ez az első kérdésünk";
        //post(new Question(question_text));
       post(new Question(2, question_text, 0, new Timestamp(date.getTime())));
    }

    @Override
    public List<Question> getQuestions() {
        String getAllQuestions = "SELECT * FROM questions";
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getAllQuestions)) {
            List<Question> questions = new ArrayList<>();
            while (resultSet.next()) {
                Question question = toEntity(resultSet);
                questions.add(question);
            }
            return questions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private Question toEntity(ResultSet resultSet) throws SQLException {
        return new Question(
                resultSet.getInt("question_id"),
                resultSet.getInt("user_id"),
                resultSet.getString("question_text"),
                resultSet.getInt("points"),
                resultSet.getTimestamp("posting_time")
        );
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

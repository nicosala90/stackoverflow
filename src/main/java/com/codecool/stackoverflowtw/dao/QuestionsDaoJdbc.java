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
import java.util.Optional;

public class QuestionsDaoJdbc implements QuestionsDAO {
    private final TableInitializer tableInitializer;
    private final Database database;

    public QuestionsDaoJdbc(TableInitializer tableInitializer, Database database) {
        this.tableInitializer = tableInitializer;
        this.database = database;
    }

    @Override
    public List<Question> getAllQuestions() {
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

    @Override
    public List<Question> getAllQuestionBySort(String sortBy) {
        String getAllQuestionsBySorting = "SELECT * FROM questions ORDER BY " + sortBy + " ASC";
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement();

             ResultSet resultSet = statement.executeQuery(getAllQuestionsBySorting)) {
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

    @Override
    public Question getQuestionById(int id) {
        String getQuestions = "SELECT * FROM questions WHERE questions.question_id = ?";
        Question question = null;
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(getQuestions)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                question = toEntity(resultSet);
            }
            resultSet.close();
            return question;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteQuestionById(int id) {
        String deleteQuestion = "DELETE FROM questions WHERE question_id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteQuestion)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
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

    @Override
    public void addQuestion(String text) {
        Date date = new Date();

        post(new Question(text, new Timestamp(date.getTime())));

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

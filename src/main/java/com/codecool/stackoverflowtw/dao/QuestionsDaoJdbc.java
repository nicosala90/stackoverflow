package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.database.Database;
import com.codecool.stackoverflowtw.dao.database.TableInitializer;
import com.codecool.stackoverflowtw.dao.model.Question;

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
    public List<Question> getAllQuestions() {
        String getAllQuestions = "SELECT * FROM questions";
        try (Connection connection = database.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(getAllQuestions)) {
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
    public List<Question> getAllQuestionSortByAlphabet() {
        String getAllQuestionsSortingBy = "SELECT * FROM questions ORDER BY questions.question_text ASC";
        try (Connection connection = database.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(getAllQuestionsSortingBy)) {
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
    public List<Question> getAllQuestionSortByDate() {
        String getAllQuestionsSortingBy = "SELECT * FROM questions ORDER BY questions.posting_time ASC";
        try (Connection connection = database.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(getAllQuestionsSortingBy)) {
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
    public List<Question> getAllQuestionSortByAnswerCount() {
        //TODO
        //String getAllQuestionsSortingBy = "SELECT * FROM questions ORDER BY questions.question_count ASC";
        String getAllQuestionsSortingBy = "SELECT * FROM questions ORDER BY questions.question_id ASC";
        try (Connection connection = database.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(getAllQuestionsSortingBy)) {
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
        try (Connection connection = database.getConnection(); PreparedStatement statement = connection.prepareStatement(deleteQuestion)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            System.out.println("Question deleted. :) question_id : "+id+".");
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Question toEntity(ResultSet resultSet) throws SQLException {
        return new Question(resultSet.getInt("question_id"), resultSet.getInt("user_id"), resultSet.getString("question_text"), resultSet.getTimestamp("posting_time"));
    }

    @Override
    public void addQuestion(int userId, String questionText) {
        Date date = new Date();
        Question newQuestion = new Question(userId, questionText, new Timestamp(date.getTime()));
        post(newQuestion);

    }

    public void post(Question question) {
        String template = "INSERT INTO questions(question_id, user_id, question_text, posting_time) values(DEFAULT,?,?,?) ";
        try (Connection connection = database.getConnection(); PreparedStatement statement = connection.prepareStatement(template)) {
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
        statement.setTimestamp(3, question.getPosting_time());

    }
}
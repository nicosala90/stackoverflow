package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.database.Database;
import com.codecool.stackoverflowtw.dao.model.Answer;

import org.springframework.beans.factory.annotation.Autowired;

import com.codecool.stackoverflowtw.dao.model.Question;


import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnswersDaoJdbc implements AnswersDAO {
    private final Database database;

    @Autowired
    public AnswersDaoJdbc(Database database) {
        this.database = database;
    }

    @Override
    public List<Answer> getAllAnswerByQuestion(int questionId) {
        String getAllAnswer = "SELECT * FROM answers WHERE answers.question_id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(getAllAnswer)) {
            statement.setInt(1, questionId);
            ResultSet resultSet = statement.executeQuery();
            List<Answer> answers = new ArrayList<>();
            while (resultSet.next()) {
                Answer answer = toEntity(resultSet);
                answers.add(answer);
            }
            return answers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getCountOfAnswerForAQuestion(int id) {
        String getCountAnswers = "SELECT COUNT(answer_id) AS count_answers FROM answers WHERE question_id = ?";
        try (Connection connection = database.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(getCountAnswers)) {

            if (resultSet.next()) {
                return resultSet.getInt("count_answers");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteAnswer(int id) {
        String deleteAnswer = "DELETE FROM questions WHERE answer_id = ?";
        try (Connection connection = database.getConnection(); PreparedStatement statement = connection.prepareStatement(deleteAnswer)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void postAnswer(String answerText, int id) {
        Date date = new Date();
        post(new Answer(id, answerText, new Timestamp(date.getTime())));
    }

    public void post(Answer answer) {
        String template = "INSERT INTO answers(answer_id, question_id, user_id, answer_text,  posting_time ) values(DEFAULT, ?, ?, ?, ?) ";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            prepare(answer, statement);
            statement.executeUpdate();
            System.out.println("Answer posted. :)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Answer toEntity(ResultSet resultSet) throws SQLException {//létrehozz egy new Answer példányt amit egy adat bázis sorrbol kiolvass
        return new Answer(resultSet.getInt("answer_id"), resultSet.getInt("question_id"), resultSet.getInt("user_id"), resultSet.getString("answer_text"), resultSet.getTimestamp("posting_time"));
    }

    private void prepare(Answer answer, PreparedStatement statement) throws SQLException {//egy már létrejött Answer példányt bele raka az adatbázisba a "?" helyére
        statement.setInt(1, answer.getQuestionId());
        statement.setInt(2, 1);
        //TODO
        statement.setString(3, answer.getAnswerText());
        statement.setTimestamp(4, answer.getPostingTime());
    }
}

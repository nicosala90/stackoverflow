package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.database.Database;
import com.codecool.stackoverflowtw.dao.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;

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
        String getAllAnswer = "SELECT * FROM answers WHERE question_id = ?";
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
    public int getCountOfAnswerForAQuestion(int questionId) {
        String getCountAnswers = "SELECT COUNT(answer_id) AS count_answers FROM answers WHERE question_id = ?";
        int resultCount = 0;
        try (Connection connection = database.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(getCountAnswers)) {
            if (resultSet.next()) {
                resultCount = resultSet.getInt("count_answers");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("megszámolt : " + resultCount);
        return resultCount;
    }

    @Override
    public void postAnswer(String answerText, int answerId) {
        Date date = new Date();
        post(new Answer(answerId, answerText, new Timestamp(date.getTime())));
    }

    public void post(Answer answer) {
        String template = "INSERT INTO answers(answer_id, question_id, user_id, answer_text,  posting_time, is_checked, is_rejected ) values(DEFAULT, ?, ?, ?, ?, ?, ?) ";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(template)) {
            prepare(answer, statement);
            statement.executeUpdate();
            System.out.println("Answer posted. :)" + " Q nr: " + answer.getQuestionId() + " A nr: " + answer.getAnswerId() + ".");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Answer toEntity(ResultSet resultSet) throws SQLException {//létrehozz egy new Answer példányt amit egy adat bázis sorbol kiolvass
        return new Answer(
                resultSet.getInt("answer_id"),
                resultSet.getInt("question_id"),
                resultSet.getInt("user_id"),
                resultSet.getString("answer_text"),
                resultSet.getTimestamp("posting_time"),
                resultSet.getBoolean("is_checked"),
                resultSet.getBoolean("is_rejected"));
    }

    private void prepare(Answer answer, PreparedStatement statement) throws SQLException { //egy már létrejött Answer példányt belerak az adatbázisba a "?" helyére
        statement.setInt(1, answer.getQuestionId());
        statement.setInt(2, 1);
        //TODO  aktuális user ID-jét kellene id beírni!
        statement.setString(3, answer.getAnswerText());
        statement.setTimestamp(4, answer.getPostingTime());
        statement.setBoolean(5, answer.isChecked());
        statement.setBoolean(6, answer.isRejected());
    }

    @Override
    public boolean deleteAnswer(int answerId) {
        String deleteAnswer = "DELETE FROM answers WHERE answer_id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteAnswer)) {
            statement.setInt(1, answerId);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

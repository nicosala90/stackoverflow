package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.database.Database;
import com.codecool.stackoverflowtw.dao.database.TableInitializer;
import com.codecool.stackoverflowtw.dao.model.Answer;
import com.codecool.stackoverflowtw.dao.model.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnswersDaoJdbc implements AnswersDAO {

    private final TableInitializer tableInitializer;
    private final Database database;

    public AnswersDaoJdbc(TableInitializer tableInitializer, Database database) {
        this.database = database;
        this.tableInitializer = tableInitializer;
    }

    @Override
    public List<Answer> getAllAnswerByQuestion(int questionId) {
        String getAllAnswer = "SELECT * FROM answer WHERE answer.question_id = ?";
        try (Connection connection = database.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(getAllAnswer)) {
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

    private Answer toEntity(ResultSet resultSet) throws SQLException {
        return new Answer(resultSet.getInt("answer_id"), resultSet.getInt("question_id"), resultSet.getInt("user_id"), resultSet.getString("answer_text"), resultSet.getTimestamp("posting_time"));
    }

    @Override
    public void postAnswer(String answerText, int id) {
        Date date = new Date();
        post(new Answer(id,answerText, new Timestamp(date.getTime())));
    }

    public void post(Answer answer) {
        String template = "INSERT INTO answers(answer_id, question_id, user_id, answer_text,  posting_time ) values(?, ?, ?, ?, ?) ";
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
        statement.setInt(3, 1);
        statement.setString(4, answer.getAnswer_text());
        statement.setTimestamp(5, answer.getPosting_time());

    }
}

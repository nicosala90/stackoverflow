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

    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        String query = "select * from questions";
        try(Statement stmt = database.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columsNumber = rsmd.getColumnCount();
            System.out.println(columsNumber);
            while(rs.next()) {
                int user_id = rs.getInt(1);
                int quest_id = rs.getInt(2);
                String question_text = rs.getString(3);
                int point = rs.getInt(4);
                Timestamp timestamp = rs.getTimestamp(5);
                questions.add(new Question(user_id, quest_id, question_text, point, timestamp));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return questions;
    }
}

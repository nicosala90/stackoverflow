package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.database.Database;
import com.codecool.stackoverflowtw.dao.database.TableInitializer;
import com.codecool.stackoverflowtw.dao.model.Question;
import com.codecool.stackoverflowtw.dao.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsersDaoJdbc implements UsersDAO {

    private final TableInitializer tableInitializer;
    private final Database database;

    public UsersDaoJdbc(TableInitializer tableInitializer, Database database) {
        this.tableInitializer = tableInitializer;
        this.database = database;
    }

    @Override
    public List<User> getAllUser() {
        String getAllUsers = "SELECT * FROM users";
        try (Connection connection = database.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(getAllUsers)) {
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = toEntity(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserById(int userId) {
       String userById = "SELECT * FROM users WHERE users.user_id = ?";
        User user = null;
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(userById)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = toEntity(resultSet);
            }
            resultSet.close();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserForAdminCheck(int userId) {
        return null;
    }

    @Override
    public boolean deleteUser(int userId) {
        String deleteUser = "DELETE FROM users WHERE user_id = ?";
        try (Connection connection = database.getConnection(); PreparedStatement statement = connection.prepareStatement(deleteUser)) {
            statement.setInt(1, userId);
            int rowsDeleted = statement.executeUpdate();
            System.out.println("User deleted. :) user_id : "+ userId + ".");
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addUser(String userName, String userPassword) {
        Date date = new Date();
        User newUser = new User(userName, new Timestamp(date.getTime()), userPassword);
        post(newUser);
    }

    public void post(User user) {
        String template = "INSERT INTO users(user_id, user_name,registration_date, password, is_admin) values(?,?,?,?,?) ";
        try (Connection connection = database.getConnection(); PreparedStatement statement = connection.prepareStatement(template)) {
            prepare(user, statement);
            statement.executeUpdate();
            System.out.println("User created. :)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User toEntity(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getInt("user_id"), resultSet.getString("user_name"),
                resultSet.getTimestamp("registration_date"),
                resultSet.getString("password"),
                resultSet.getBoolean("is_admin"));
    }

    private void prepare(User user, PreparedStatement statement) throws SQLException {
        statement.setInt(1, user.getUserId());
        statement.setString(2, user.getUserName());
        statement.setTimestamp(3, user.getRegistrationDateTime());
        statement.setString(4, user.getPassword());
        statement.setBoolean(5, user.isChecked());

    }
}

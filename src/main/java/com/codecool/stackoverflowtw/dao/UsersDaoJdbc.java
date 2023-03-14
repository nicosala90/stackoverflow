package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.database.Database;
import com.codecool.stackoverflowtw.dao.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsersDaoJdbc implements UsersDAO {
    private final Database database;

    @Autowired
    public UsersDaoJdbc(Database database) {
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
        String userById = "SELECT * FROM users WHERE users.id = ?";
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
        User userForDelete = getAllUser().stream().filter(user -> user.getUserId() == userId).findAny().orElseThrow();
        if (userForDelete.isAdmin() && getAllUser().stream().filter(user -> user.isAdmin()).count() > 1 || !userForDelete.isAdmin()) {
            String deleteUser = "DELETE FROM users WHERE id = ?";
            try (Connection connection = database.getConnection(); PreparedStatement statement = connection.prepareStatement(deleteUser)) {
                statement.setInt(1, userId);
                int rowsDeleted = statement.executeUpdate();
                System.out.println("User deleted. :) user_id : " + userId + ".");
                return rowsDeleted > 0;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    @Override
    public void addUser(String userName, String userPassword, String email) {
        Date date = new Date();
        User newUser = new User(userName, userPassword, email, new Timestamp(date.getTime()));
        post(newUser);
    }

    @Override
    public void addFirstAdminUser(String userName, String userPassword, String userEmail) {
        Date date = new Date();
        User mainAdminUser = new User(1, userName, userPassword, userEmail, new Timestamp(date.getTime()), true, false);
        post(mainAdminUser);
    }

    public void post(User user) {
        String template = "INSERT INTO users(id, user_name,user_password, user_email, registration_date,  is_admin, is_rejected) values(DEFAULT,?,?,?,?,?,?) ";
        try (Connection connection = database.getConnection(); PreparedStatement statement = connection.prepareStatement(template)) {
            prepare(user, statement);
            statement.executeUpdate();
            System.out.println("User created. :)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User toEntity(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getInt("id"), resultSet.getString("user_name"), resultSet.getString("user_email"), resultSet.getString("user_password"), resultSet.getTimestamp("registration_date"), resultSet.getBoolean("is_admin"), resultSet.getBoolean("is_rejected"));
    }

    private void prepare(User user, PreparedStatement statement) throws SQLException {
        /* statement.setInt(1, user.getUserId());*/
        statement.setString(1, user.getUserName());
        statement.setString(2, user.getUserPassword());
        statement.setString(3, user.getUserEmail());
        statement.setTimestamp(4, user.getRegistrationDateTime());
        statement.setBoolean(5, user.isAdmin());
        statement.setBoolean(6, user.isRejected());

    }
}

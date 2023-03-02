package com.codecool.stackoverflowtw.dao.database;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
public class TableInitializerPSQL implements TableInitializer {
    private final Database database;
    private final List<String> listOfTables;

    public TableInitializerPSQL(Database database, List<String> listOfTables) {
        this.database = database;
        this.listOfTables = listOfTables;
    }

    private void create(String command) {
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(command);
            System.out.println("Table created");
        } catch (SQLException ex) {
            System.err.println("Could not create table");
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void initialize() {
        for (String command: listOfTables) {
            create(command);
        }
    }
    /*    @Override
    public void initialize() {
        tables.entrySet().stream()
                .filter(table -> !exists(table))
                .forEach(table -> create(table));
    }*/

  /*  private boolean exists(Map.Entry<String, String> table) {
        String tableName = table.getKey();
        try (Connection connection = database.getConnection();
             ResultSet resultSet = connection.getMetaData()
                     .getTables(null, null, null, new String[]{"TABLE"})) {
            while (resultSet.next()) {
                String name = resultSet.getString("TABLE_NAME");
                if (tableName.equalsIgnoreCase(name)) {
                    System.out.println("Table detected = " + name);
                    return true;
                }
            }
            return false;
        } catch (SQLException ex) {
            System.err.println("Could not determine whether the table exists: " + tableName);
            throw new RuntimeException(ex);
        }
    }*/
}

package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DbConnection {

    default Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/test_db",
                    "postgres",
                    "admin"
            );

            System.out.println("Connection succcesed!");

            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection failed. Please, try again later.");

            return null;
        }
    }

    void select();
    void insert(Employee employee);
    void update();
    void delete();
}

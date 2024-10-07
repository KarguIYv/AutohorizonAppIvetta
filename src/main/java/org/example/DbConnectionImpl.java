package org.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DbConnectionImpl implements DbConnection {

    @Override
    public void select() {
        try {
            String request = "SELECT * FROM public.Ivivi";
            Statement statement = connect().createStatement();
            ResultSet resultSet = statement.executeQuery(request);

            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var lastName = resultSet.getString("lastName");
                var  firstName = resultSet.getString(" firstName");
                var middleName = resultSet.getString("middleName");
                var login = resultSet.getString("login");
                var passwordHash= resultSet.getString("passwordHash");
                var salary = resultSet.getString("salary");
                var position  =resultSet.getString("position");


                System.out.println("ID - " + id + ", lastName - "+ lastName+ ", firstName- " +  firstName + ", middleName - "
                        + middleName+ ", login - "+login+ ", passwordHash - " + passwordHash + "salary - "+salary +position+ "position");
            }
        } catch (SQLException e) {
            System.out.println("Cannot load data from db. Please try again.");
        }
    }

    @Override
    public void insert(Employee employee) {
        try {
            var request = "INSERT INTO public.Ivivi(id, lastName, firstName, middleName, login, passwordHash, salary, position) VALUES(?, ?, ?, ?, ?, ?,?,?)";

            var connection = connect();

            var prepareStatement = connection.prepareStatement(
                    request, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.setString(1,employee.getId());
            prepareStatement.setString(2,employee.getLastName());
            prepareStatement.setString(3,employee.getFirstName());
            prepareStatement.setString(4,employee.getMiddleName());
            prepareStatement.setString(5,employee.getLogin());
            prepareStatement.setString(6,employee.getPasswordHash());
            prepareStatement.setDouble(7,employee.getSalary());
            prepareStatement.setString(8,employee.getPosition());

            prepareStatement.executeUpdate();

            System.out.println("Data inserted successfully!");
        }
        catch (SQLException e) {
            System.out.println("Data insertion failed. Please, try again!");
            System.out.println(e);
        }

            }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }


}
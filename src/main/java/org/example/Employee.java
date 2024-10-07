package org.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;
import java.util.*;

class Employee {
    String id;
    String lastName;
    String firstName;
    String middleName;
    String login;
    String passwordHash;
    double salary;
    String position;

    public Employee(String id, String lastName, String firstName, String middleName, String passwordHash, double salary, String position) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.login = generateLogin(lastName, firstName, middleName);
        this.passwordHash = passwordHash;
        this.salary = salary;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    private String generateLogin(String lastName, String firstName, String middleName) {
        return lastName.toLowerCase() + "." + firstName.charAt(0) + "." + middleName.charAt(0);
    }

    public String greet() {
        LocalTime now = LocalTime.now();
        String greeting;

        if (now.isAfter(LocalTime.of(4, 0)) && now.isBefore(LocalTime.of(12, 0))) {
            greeting = "Доброе утро";
        } else if (now.isAfter(LocalTime.of(12, 0)) && now.isBefore(LocalTime.of(17, 0))) {
            greeting = "Добрый день";
        } else if (now.isAfter(LocalTime.of(17, 0)) && now.isBefore(LocalTime.of(22, 0))) {
            greeting = "Добрый вечер";
        } else {
            greeting = "Доброй ночи";
        }

        return greeting + ", " + firstName + "!";
    }
}


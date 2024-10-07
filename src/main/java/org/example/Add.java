package org.example;
import java.util.Scanner;

import static org.example.EmployeeAuthSystem.login;
import static org.example.EmployeeAuthSystem.register;

public class Add {
    DbConnectionImpl connection = new DbConnectionImpl();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите действие: 1 - Вход, 2 - Регистрация, 0 - Выход");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                login(scanner);
            } else if (choice == 2) {
                register(scanner);
            } else if (choice == 0) {
                break;
            } else {
                System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        }

        scanner.close();
    }
}
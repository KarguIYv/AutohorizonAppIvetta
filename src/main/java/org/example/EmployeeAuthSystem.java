package org.example;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;
import java.util.*;

    public class EmployeeAuthSystem {
    private static final Map<String, Employee> employees = new HashMap<>();

    static {
        // Добавление superuser
        String superUserPasswordHash = hashPassword("/admin 1234");
        employees.put("admin", new Employee("A-000001", "admin", "admin", "admin", superUserPasswordHash, 0, "администратор"));

        // Генерация сотрудников
        generateEmployees();
    }
    public static void login(Scanner scanner) {
        System.out.print("Введите логин: ");
        String login = scanner.nextLine();

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        Employee employee = employees.get(login);

        if (employee != null && employee.passwordHash.equals(hashPassword(password))) {
            System.out.println(employee.greet());
            displayEmployeeInfo(employee);
        } else {
            System.out.println("Неверный логин или пароль. Попробуйте снова.");
        }
    }

    public static void register(Scanner scanner) {
        System.out.print("Введите фамилию: ");
        String lastName = scanner.nextLine();

        System.out.print("Введите имя: ");
        String firstName = scanner.nextLine();

        System.out.print("Введите отчество: ");
        String middleName = scanner.nextLine();

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        String position = getPositionFromInput();
        System.out.println("Введите логин: ");
        if (position != null) {
            String id = generateId(position);
            double salary = generateSalary(position);
            String passwordHash = hashPassword(password);

            employees.put(lastName.toLowerCase() + "." + firstName.charAt(0) + "." + middleName.charAt(0),
                    new Employee(id, lastName, firstName, middleName, passwordHash, salary, position));
            System.out.println("Регистрация прошла успешно!");
        } else {
            System.out.println("Некорректная должность.");
        }
    }

    private static String getPositionFromInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите должность: 1 - Менеджер, 2 - Администратор, 3 - Инженер");
        int positionChoice = scanner.nextInt();

        switch (positionChoice) {
            case 1: return "менеджер";
            case 2: return "администратор";
            case 3: return "инженер";
            default: return null;
        }
    }

    private static void displayEmployeeInfo(Employee employee) {
        System.out.printf("%s %s %s %s, %s%n", employee.id, employee.lastName, employee.firstName, employee.middleName, employee.position);
    }

    private static void generateEmployees() {
        // Пример генерации сотрудников
        for (int i = 1; i <= 10; i++) {
            String position;
            if (i % 3 == 0) {
                position = "инженер";
                employees.put("E-" + String.format("%06d", i), new Employee("E-" + String.format("%06d", i), "Иванов", "Иван", "Иванович", hashPassword("password"), generateSalary(position), position));
            } else if (i % 3 == 1) {
                position = "менеджер";
                employees.put("M-" + String.format("%06d", i), new Employee("M-" + String.format("%06d", i), "Петров", "Петр", "Петрович", hashPassword("password"), generateSalary(position), position));
            } else {
                position = "администратор";
                employees.put("A-" + String.format("%06d", i), new Employee("A-" + String.format("%06d", i), "Сидоров", "Сидор", "Сидорович", hashPassword("password"), generateSalary(position), position));
            }
        }
    }

    private static double generateSalary(String position) {
        Random random = new Random();

        switch (position) {
            case "менеджер":
                return 50000 + random.nextDouble() * 40000; // от 50000 до 90000
            case "администратор":
                return 60000 + random.nextDouble() * 60000; // от 60000 до 120000
            case "инженер":
                return 70000 + random.nextDouble() * 30000; // от 70000 до 100000
            default:
                return 0;
        }
    }

    private static String generateId(String position) {
        int count = employees.size() + 1; // для уникальности
        char posChar;

        switch (position) {
            case "менеджер": posChar = 'M'; break;
            case "администратор": posChar = 'A'; break;
            case "инженер": posChar = 'E'; break;
            default: throw new IllegalArgumentException("Некорректная должность");
        }

        return posChar + "-" + String.format("%06d", count);
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

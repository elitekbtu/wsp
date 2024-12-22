import users.User;
import users.employees.Admin;
import users.employees.Manager;
import users.employees.Teacher;
import users.students.MasterStudent;
import users.students.PhDStudent;
import users.students.UndergraduateStudent;

import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    private static final ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        initializeUsers();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("------------------");
            System.out.println("Welcome to WSP application!");
            System.out.println("------------------");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter the number of the action: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    register(scanner);
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static void initializeUsers() {
        // Initialize some users for testing purposes
        users.put("admin", new Admin("admin", "Administrator", "admin@example.com", "admin", "Administration"));
        users.put("manager", new Manager("manager", "Manager", "manager@example.com", "manager", "Management"));
        users.put("teacher", new Teacher("teacher", "Teacher", "teacher@example.com", "teacher", "Faculty"));
        users.put("undergraduate", new UndergraduateStudent("undergraduate", "Undergraduate Student", "undergraduate@example.com", "undergraduate"));
        users.put("master", new MasterStudent("master", "Master's Student", "master@example.com", "master"));
        users.put("phd", new PhDStudent("phd", "PhD Student", "phd@example.com", "phd"));
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter your login: ");
        String login = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User user = users.get(login);

        if (user != null && user.authenticate(login, password)) {
            System.out.println("Login successful!");
            handleLoggedInUser(user, scanner);
        } else {
            System.out.println("Invalid login or password.");
        }
    }

    private static void register(Scanner scanner) {
        System.out.println("------------------");
        System.out.println("Register new user");
        System.out.println("------------------");
        System.out.print("Enter your login: ");
        String login = scanner.nextLine();

        if (users.containsKey(login)) {
            System.out.println("This login is already taken.");
            return;
        }

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        if (!isValidEmail(email)) {
            System.out.println("Invalid email.");
            return;
        }

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (!isValidPassword(password)) {
            System.out.println("Password must be at least 6 characters long.");
            return;
        }

        System.out.println("Choose user type:");
        System.out.println("1. Administrator");
        System.out.println("2. Manager");
        System.out.println("3. Teacher");
        System.out.println("4. Undergraduate Student");
        System.out.println("5. Master's Student");
        System.out.println("6. PhD Student");
        System.out.print(" Enter the number of user type: ");
        int userType = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        User newUser  = null;
        switch (userType) {
            case 1:
                newUser  = new Admin(login, name, email, password, "Administration");
                break;
            case 2:
                newUser  = new Manager(login, name, email, password, "Management");
                break;
            case 3:
                newUser  = new Teacher(login, name, email, password, "Faculty");
                break;
            case 4:
                newUser  = new UndergraduateStudent(login, name, email, password);
                break;
            case 5:
                newUser  = new MasterStudent(login, name, email, password);
                break;
            case 6:
                newUser  = new PhDStudent(login, name, email, password);
                break;
            default:
                System.out.println("Invalid user type.");
                return;
        }

        users.put(login, newUser );
        System.out.println("Registration successful!");
    }

    private static void handleLoggedInUser (User user, Scanner scanner) {
        System.out.println("Welcome, " + user.getFullname() + "!");
        while (true) {
            System.out.println("Choose an action:");
            System.out.println("1. View Profile");
            System.out.println("2. Edit Profile");
            System.out.println("3. Logout");
            System.out.print("Enter the number of action: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println(user);
                    break;
                case 2:
                    System.out.println("Edit function is not yet implemented.");
                    break;
                case 3:
                    System.out.println("You have logged out.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static boolean isValidEmail(String email) {
        return email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    private static boolean isValidPassword(String password) {
        return password.length() >= 6;
    }
}
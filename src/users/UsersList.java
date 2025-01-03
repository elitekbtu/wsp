package users;

import users.employees.Admin;
import users.employees.Manager;
import users.employees.Teacher;
import users.employees.TechSupportSpecialist;
import users.students.Student;
import users.students.UndergraduateStudent;

import java.io.*;
import java.util.Scanner;

public class UsersList {
    private Scanner scanner;

    public UsersList() {
        this.scanner = new Scanner(System.in);
    }

    public void register() {
        System.out.println("Добро пожаловать в регистрацию");
        System.out.println("Выберите опцию: ");
        System.out.println("1. Student         2. Teacher        3. Admin\n4. TechSupport         5. Manager");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера

        switch (choice) {
            case 1:
                registerStudent();
                break;
            case 2:
                registerTeacher();
                break;
            case 3:
                registerAdmin();
                break;
            case 4:
                registerTechSupport();
                break;
            case 5:
                registerManager();
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }

    private void registerStudent() {
        System.out.println("Введите ID студента:");
        String id = scanner.nextLine();
        System.out.println("Введите полное имя студента:");
        String fullname = scanner.nextLine();
        System.out.println("Введите email студента:");
        String email = scanner.nextLine();
        System.out.println("Введите пароль студента:");
        String password = scanner.nextLine();

        Student student = new UndergraduateStudent(id, fullname, email, password);
        saveUserData(student);
        System.out.println("Студент зарегистрирован успешно!");
    }

    private void registerTeacher() {
        System.out.println("Введите ID учителя:");
        String id = scanner.nextLine();
        System.out.println("Введите полное имя учителя:");
        String fullname = scanner.nextLine();
        System.out.println("Введите email учителя:");
        String email = scanner.nextLine();
        System.out.println("Введите пароль учителя:");
        String password = scanner.nextLine();

        Teacher teacher = new Teacher(id, fullname, email, password, "Department");
        saveUserData(teacher);
        System.out.println("Учитель зарегистрирован успешно!");
    }

    private void registerAdmin() {
        System.out.println("Введите ID администратора:");
        String id = scanner.nextLine();
        System.out.println("Введите полное имя администратора:");
        String fullname = scanner.nextLine();
        System.out.println("Введите email администратора:");
        String email = scanner.nextLine();
        System.out.println("Введите пароль администратора:");
        String password = scanner.nextLine();

        Admin admin = new Admin(id, fullname, email, password, "Administration");
        saveUserData(admin);
        System.out.println("Администратор зарегистрирован успешно!");
    }

    private void registerTechSupport() {
        System.out.println("Введите ID сотрудника технической поддержки:");
        String id = scanner.nextLine();
        System.out.println("Введите полное имя сотрудника:");
        String fullname = scanner.nextLine();
        System.out.println("Введите email сотрудника:");
        String email = scanner.nextLine();
        System.out.println("Введите пароль сотрудника:");
        String password = scanner.nextLine();

        TechSupportSpecialist techSupport = new TechSupportSpecialist(id, fullname, email, password, "TechSupport");
        saveUserData(techSupport);
        System.out.println("Сотрудник технической поддержки зарегистрирован успешно!");
    }

    private void registerManager() {
        System.out.println("Введите ID менеджера:");
        String id = scanner.nextLine();
        System.out.println("Введите полное имя менеджера:");
        String fullname = scanner.nextLine();
        System.out.println("Введите email менеджера:");
        String email = scanner.nextLine();
        System.out.println("Введите пароль менеджера:");
        String password = scanner.nextLine();

        Manager manager = new Manager(id, fullname, email, password, "Management");
        saveUserData(manager);
        System.out.println("Менеджер зарегистрирован успешно!");
    }

    private void saveUserData(User user) {
        String userType = user.getClass().getSimpleName(); // Get the user type (e.g., "Student", "Teacher")
        String directoryPath = "users/UsersData/" + userType; // Create path based on user type
        File directory = new File(directoryPath);

        // Check and create the directory if it doesn't exist
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory " + directoryPath + " created successfully.");
            } else {
                System.out.println("Failed to create directory " + directoryPath);
                return; // If the directory cannot be created, exit the method
            }
        }

        String fileName = directoryPath + "/" + user.getId() + ".txt"; // Full path to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(user.getId() + ":" + user.getPassword()); // Save login and password
            writer.newLine();
            writer.write("Fullname: " + user.getFullname());
            writer.newLine();
            writer.write("Email: " + user.getEmail());
            System.out.println("User  data saved in file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
    public void loadUserData() {
        System.out.println("Введите ID: ");
        String id = scanner.nextLine();

        System.out.println("Выбери свой тип: ");
        System.out.println("1. Student         2. Teacher        3. Admin\n4. TechSupport         5. Manager");

        int choice = scanner.nextInt();
        scanner.nextLine();

        File file = null;

        switch (choice) {
            case 1:
                file = new File("users/UsersData/UndergraduateStudent/" + id + ".txt");
                break;
            case 2:
                file = new File("users/UsersData/Teacher/" + id + ".txt");
                break;
            case 3:
                file = new File("users/UsersData/Admin/" + id + ".txt");
                break;
            case 4:
                file = new File("users/UsersData/TechSupportSpecialist/" + id + ".txt");
                break;
            case 5:
                file = new File("users/UsersData/Manager/" + id + ".txt");
                break;
            default:
                System.out.println("Неверный выбор типа пользователя.");
                return;
        }

        if (file != null) {
            if (file.exists()) {
                readFile(file);
            } else {
                System.out.println("Файл пользователя не найден: " + file.getPath());
            }
        }
    }

    private void readFile(File file) {
        try (Scanner fileScanner = new Scanner(file)) {
            System.out.println("Данные пользователя:");
            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

}
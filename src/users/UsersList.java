package users;

import users.employees.Admin;
import users.employees.Manager;
import users.employees.Teacher;
import users.employees.TechSupportSpecialist;
import users.students.Student;
import users.students.UndergraduateStudent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        String directoryPath = "users/UsersData"; // Имя папки
        File directory = new File(directoryPath);

        // Проверка и создание папки, если ее нет
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Папка " + directoryPath + " успешно создана.");
            } else {
                System.out.println("Не удалось создать папку " + directoryPath);
                return; // Если папку нельзя создать, прерываем метод
            }
        }

        String fileName = directoryPath + "/" + user.getId() + ".txt"; // Полный путь к файлу
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(user.getId() + ":" + user.getPassword()); // Сохранение логина и пароля
            writer.newLine();
            writer.write("Fullname: " + user.getFullname());
            writer.newLine();
            writer.write("Email: " + user.getEmail());
            // Можно добавить дополнительные данные пользователя
            System.out.println("Данные пользователя сохранены в файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка записи: " + e.getMessage());
        }
    }
}
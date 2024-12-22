import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;
import news.NewsList;

public class Tester {
    private static final String FILE_NAME = "user_data.txt";
    private static HashMap<String, String> userDatabase = new HashMap<>(); // Логины и пароли в памяти
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        loadUserData();
        while (true) {
            System.out.println("\nВыберите действие: 1 - Регистрация, 2 - Вход, 0 - Выход");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerUser ();
                    break;
                case 2:
                    if (loginUser ()) {
                        mainMenu();
                    }
                    break;
                case 0:
                    System.out.println("Программа завершена.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void loadUserData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2); // Формат логин:пароль
                if (parts.length == 2) {
                    userDatabase.put(parts[0], parts[1]);
                }
            }
            System.out.println("Данные пользователей загружены.");
        } catch (FileNotFoundException e) {
            System.out.println("Файл данных не найден. Будет создан новый.");
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    private static void saveUserData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String login : userDatabase.keySet()) {
                String password = userDatabase.get(login);
                writer.write(login + ":" + password);
                writer.newLine();
            }
            System.out.println("Данные пользователей сохранены.");
        } catch (IOException e) {
            System.out.println("Ошибка записи файла: " + e.getMessage());
        }
    }

    private static void registerUser () {
        System.out.println("Введите логин:");
        String registerLogin = scanner.nextLine();

        if (userDatabase.containsKey(registerLogin)) {
            System.out.println("Логин уже существует. Попробуйте другой.");
        } else {
            System.out.println("Введите пароль:");
            String registerPassword = scanner.nextLine();
            userDatabase.put(registerLogin, registerPassword); // Сохранение логина и пароля
            saveUserData(); // Сохраняем данные в файл
            System.out.println("Регистрация успешна!");
        }
    }

    private static boolean loginUser () {
        System.out.println("Введите логин:");
        String login = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        if (userDatabase.containsKey(login) && userDatabase.get(login).equals(password)) {
            System.out.println("Вход выполнен успешно! Добро пожаловать, " + login + "!");
            return true;
        } else {
            System.out.println("Ошибка входа. Проверьте логин и пароль.");
            return false;
        }
    }

    private static void mainMenu() {
        while (true) {
            System.out.println("\nГлавное меню:");
            System.out.println("1 - Новостная лента");
            System.out.println("2 - Данные студента");
            System.out.println("3 - Транскрипт");
            System.out.println("4 - Курсы студента");
            System.out.println("5 - Техническая поддержка");
            System.out.println("6 - Выйти");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    newsFeed();
                    break;
                case 2:
                    studentData();
                    break;
                case 3:
                    transcript();
                    break;
                case 4:
                    studentCourses();
                    break;
                case 5:
                    technicalSupport();
                    break;
                case 6:
                    System.out.println("Выход из меню.");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void newsFeed() {
        System.out.println("Вы выбрали новостную ленту.");

        NewsList newsList = new NewsList();

        Random random = new Random();
        int count = 5;
        System.out.println("Последние новости:");

        for (int i = 0; i < count; i++) {
            int index = random.nextInt(newsList.news.length); // Генерация случайного индекса
            System.out.println((i + 1) + ". " + newsList.news[index]); // Вывод случайной новости
        }
    }


    private static void studentData() {
        // Пример реализации данных студента
        System.out.println("Вы выбрали данные студента.");
        // Здесь можно добавить логику для отображения данных студента
        System.out.println("Имя студента: Иван Иванов\nВозраст: 20 лет\nГруппа: ИТ-21");
    }

    private static void transcript() {
        // Пример реализации транскрипта
        System.out.println("Вы выбрали транскрипт.");
        // Здесь можно добавить логику для отображения транскрипта
        System.out.println("Транскрипт: \n1. Математика - 5\n2. Программирование - 4\n3. Физика - 3");
    }

    private static void studentCourses() {
        System.out.println("Вы выбрали курсы студента.");
    }

    private static void technicalSupport() {
        System.out.println("Вы выбрали техническую поддержку.");
        System.out.println("Для получения помощи, пожалуйста, свяжитесь с нашей службой поддержки по телефону: 123-456-7890.");
    }
}
package users;

import java.util.Scanner;

public class UsersList {
    public void register() {
        System.out.println("Добро пожаловать в регистрацию");
        System.out.println("Выберите опцию: ");
        System.out.println("1. Student         2. Teacher        3. Admin\n4. TechSupport         5. Manager");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Добро пожаловать студент:\nВыбери опцию: ");
                System.out.println("1.Бакалавр         2.Магистрант        3. PhD \n4. Обучающийся");
                int choice2 = scanner.nextInt();
                switch (choice2){

                }
        }

    }
}

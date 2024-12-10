package courses;

import users.employees.Teacher;
import users.students.MasterStudent;
import users.students.UndergraduateStudent;

import java.util.Scanner;

public class MyTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Language selection
        System.out.println("Choose language / Выберите язык:");
        System.out.println("1. English");
        System.out.println("2. Русский");
        int languageChoice = Integer.parseInt(scanner.nextLine());

        // Define language-specific messages
        String[] messages;
        if (languageChoice == 2) {
            messages = new String[]{
                    "Введите ID преподавателя:",
                    "Введите имя преподавателя:",
                    "Введите отдел преподавателя:",
                    "Введите ID студента:",
                    "Введите имя студента:",
                    "Введите email студента:",
                    "Введите пароль студента:",
                    "Введите код курса:",
                    "Введите название курса:",
                    "Введите кредиты курса:",
                    "Введите оценку за первую аттестацию:",
                    "Введите оценку за вторую аттестацию:",
                    "Введите оценку за финальный экзамен:",
                    "Информация о курсе:",
                    "Информация о студенте:"
            };
        } else {
            messages = new String[]{
                    "Enter Teacher ID:",
                    "Enter Teacher Name:",
                    "Enter Teacher Department:",
                    "Enter Student ID:",
                    "Enter Student Name:",
                    "Enter Student Email:",
                    "Enter Student Password:",
                    "Enter Course Code:",
                    "Enter Course Title:",
                    "Enter Course Credits:",
                    "Enter First Attestation Mark:",
                    "Enter Second Attestation Mark:",
                    "Enter Final Exam Mark:",
                    "Course Info:",
                    "Student Info:"
            };
        }

        // Input Teacher details
        System.out.println(messages[0]);
        String teacherId = scanner.nextLine();

        System.out.println(messages[1]);
        String teacherName = scanner.nextLine();

        System.out.println(messages[2]);
        String teacherDept = scanner.nextLine();

        Teacher teacher1 = new Teacher(teacherId, teacherName, teacherDept);

        // Input Student details
        System.out.println(messages[3]);
        String studentId = scanner.nextLine();

        System.out.println(messages[4]);
        String studentName = scanner.nextLine();

        System.out.println(messages[5]);
        String studentEmail = scanner.nextLine();

        System.out.println(messages[6]);
        String studentPassword = scanner.nextLine();

        UndergraduateStudent student1 = new UndergraduateStudent(studentId, studentName, studentEmail, studentPassword);

        // Input Course details
        System.out.println(messages[7]);
        String courseCode = scanner.nextLine();

        System.out.println(messages[8]);
        String courseTitle = scanner.nextLine();

        Course course = new Course(courseCode, courseTitle);

        course.addInstructor(teacher1);
        course.addStudent(student1);

        System.out.println(messages[9]);
        int credits = Integer.parseInt(scanner.nextLine());
        course.setCredits(credits);

        // Input Marks
        System.out.println(messages[10]);
        double firstAttMark = Double.parseDouble(scanner.nextLine());
        course.putMark(student1, firstAttMark, TypeOfMark.FIRST_ATTESTATION);

        System.out.println(messages[11]);
        double secondAttMark = Double.parseDouble(scanner.nextLine());
        course.putMark(student1, secondAttMark, TypeOfMark.SECOND_ATTESTATION);

        System.out.println(messages[12]);
        double finalExamMark = Double.parseDouble(scanner.nextLine());
        course.putMark(student1, finalExamMark, TypeOfMark.FINAL);

        // Output Course Info
        System.out.println("\n" + messages[13] + " " + courseTitle);
        course.getMarks().forEach((student, mark) -> System.out.println(student.getFullname() + ": " + mark));

        System.out.println("\n" + messages[14]);
        System.out.println(student1);

        scanner.close();
    }
}

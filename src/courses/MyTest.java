package courses;

import users.employees.Teacher;
import users.students.UndergraduateStudent;
import org.junit.jupiter.api.Test;

public class MyTest {
    @Test
    void T() {
        Teacher teacher1 = new Teacher("6B28112005", "ElnaraKadyrgali", "SITE");
        UndergraduateStudent student1 = new UndergraduateStudent("23B031469", "BeknurUalikhanuly", "ualikhanuly_beknur@kbtu.kz", "Beknur23!");
        Course course = new Course("aboba", "Research methods");

        course.addInstructor(teacher1);
        course.addStudent(student1);
        course.setCredits(4);

        course.putMark(student1, 17.5, TypeOfMark.FIRST_ATTESTATION);
        course.putMark(student1, 16.5, TypeOfMark.SECOND_ATTESTATION);
        course.putMark(student1, 0, TypeOfMark.FINAL);

        System.out.println("\nResearch Methods Info");
        course.getMarks().forEach((student, mark) -> System.out.println(student.getFullname() + ": " + mark));

        System.out.println(student1);

    }
}

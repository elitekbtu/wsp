package users.employees;

import courses.Course;
import users.User;
import users.students.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Admin extends Employee {
    public User createUser(User user) {
        return User.addUser(user);
    }

    public void deleteUser(Student user) {
        User.removeUser(user);
    }

    public Course createCourse(Course course) {
        return Course.addCourseToRegistry(course);
    }

    public void deleteCourse(Course course) {
        Course.removeCourseFromRegistry(course);
    }

}

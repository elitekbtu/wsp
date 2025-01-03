package users.employees;

import courses.Course;
import users.User;
import users.students.Student;



public class Admin extends Employee {
    public Admin(String admin, String administrator, String mail, String admin1, String administration) {
    }

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


package Users;

import Grades.*;
import NewsStuff.News;


import java.util.List;


public class Manager extends Employee {
    // Enum for manager types
    public enum ManagerType {
        OR, DEPARTMENT, ADMINISTRATION;
    }
    List<News> allNews;

    private ManagerType managerType;

    public Manager(String userName, String password, Boolean isAuthenticated, Language language, String fullName, String ID, String job, ManagerType managerType, int salary,List <News>allNews) {
        super(userName, password, isAuthenticated, language, fullName, ID, job, salary);
        this.managerType = managerType;
        this.allNews=allNews;
    }

    // Approve student registration
    public void approveStudentRegistration(Student student) {
        if (!student.isApproved()) {
            student.setApproved(true);
            System.out.println("Student " + student.getFullName() + " has been approved.");
        } else {
            System.out.println("Student " + student.getFullName() + " is already approved.");
        }
    }

    // Add courses for registration
    public void addCourse(Course course, List<Course> availableCourses) {
        if (!availableCourses.contains(course)) {
            availableCourses.add(course);
            System.out.println("Course " + course.getCourseName() + " added for registration.");
        } else {
            System.out.println("Course " + course.getCourseName() + " is already available.");
        }
    }

    // Assign courses to teachers
    public void assignCourseToTeacher(Course course, Teacher teacher) {
        if (!teacher.getCourses().contains(course)) {
            teacher.addCourse(course);
            course.setTeacher(teacher);
            System.out.println("Course " + course.getCourseName() + " has been assigned to " + teacher.getFullName() + ".");
        } else {
            System.out.println(teacher.getFullName() + " is already assigned to this course.");
        }
    }

    // Manage news
    public void manageNews(News news, boolean pin) {
        if (pin) {
            news.pinNews();
            System.out.println("News has been pinned.");
        } else {
            news.unpinNews();
            System.out.println("News has been unpinned.");
        }
    }

    public List<News> getNews(){
        return allNews;
    }

   

    // View info about students and teachers
    public void viewInfo(List<Student> students, List<Teacher> teachers) {
        System.out.println("Students:");
        for (Student student : students) {
            System.out.println("Full Name: " + student.getFullName() + ", Major: " + student.getMajor());
        }

        System.out.println("\nTeachers:");
        for (Teacher teacher : teachers) {
            System.out.println("Full Name: " + teacher.getFullName() + ", Courses: " + teacher.getCourses().size());
        }
    }

    @Override
public String toString() {
    String header = "=================== Manager Profile ===================";
    return header + "\n" +
           "Manager: " + getFullName() + " (ID: " + getID() + ")\n" +
           "Job Title: " + getJob() + "\n" +
           "Manager Type: " + managerType + "\n" +
           "Salary: $" + getSalary() + "\n" +
           "======================================================";
}


}


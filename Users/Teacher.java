package Users;
import Grades.*;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends Employee {
    private List<Course> coursesRunByTeacher;
    boolean isResearcher;
    float avrRating;

   public Teacher(String userName,String password, Boolean isAuthenticated, Language language,String fullName,String ID,String job,boolean isResearcher, float avrRating, int salary){
    super(userName,password,isAuthenticated,language,fullName,ID,job,salary);

    this.avrRating=avrRating;
    this.isResearcher=isResearcher;
    coursesRunByTeacher=new ArrayList<>();
   }

   public float putMarkForFinal(){
    return getRandomFloat();
   }

   public float getRandomFloat() {
    return (float) Math.random() * 40; // Generates a random float between 0 (inclusive) and 40 (exclusive)
}

   
public void assignMark(Student student, Mark mark, Course course) {
        if(student.getCourses().containsKey(course)){
            if(mark.accessTofinal()){
                //finalexam
                mark.setMarkFinalExam(putMarkForFinal());
                student.getMarks().put(course,mark);
                student.getTranscript().addCourseMark(course, mark);
                
            }
            else{
                mark.setMarkFinalExam(0);
                student.getMarks().put(course,mark);
                student.getTranscript().addCourseMark(course, mark);

            }
        }
        else{
            throw new IllegalArgumentException("Student is not enrolled in this course");
        }
    }


    public void viewStudentsInCourse(Course course) {
        List<Student> enrolledStudents = course.getEnrolledStudents();
        if (enrolledStudents.isEmpty()) {
            System.out.println("No students in this course");
        }

        else {
           // System.out.println("Students in this ");
            for (Student student : enrolledStudents) {
                System.out.println(student.toString());
                System.out.print("\n");
            }
        }
    }

    public List<Course> getCourses(){
        return coursesRunByTeacher;
    }

    public void addCourse(Course course){
        coursesRunByTeacher.add(course);
    }


    @Override
    public String toString() {
        String header = "=================== Teacher Profile ===================";
        return header + "\n" +
               "Teacher: " + getFullName() + " (ID: " + getID() + ")\n" +
               "Job Title: " + getJob() + "\n" +
               "Salary: $" + getSalary() + "\n" +
               "Average Rating: " + String.format("%.2f", avrRating) + "\n" +
               "Researcher: " + (isResearcher ? "Yes" : "No") + "\n" +
               "======================================================";
    }
    

    
}

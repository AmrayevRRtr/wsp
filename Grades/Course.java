package Grades;
import java.util.List;

import Users.*;

import java.io.Serializable;
import java.util.ArrayList;


public class Course implements Serializable {
   public String courseName;
   public int credits;
   private List<Student> enrolledStudents;
   private int retakes;
   Teacher instructor;

   public Course(String courseName, int credits,Teacher teacher){
    this.courseName=courseName;
    this.credits=credits;
    this.instructor=teacher;
    enrolledStudents=new ArrayList<>();

   }
   //getters
   public Teacher getInstructor(){
     return instructor;
   }

   public List<Student> getEnrolledStudents(){
    return enrolledStudents;
   }

   public int getRetakes(){
    return retakes;
   }

   //setters
   public void setTeacher(Teacher teacher){
      this.instructor=teacher;
   }

   
   public void enrollStudent(Student student){
    enrolledStudents.add(student);
   }

   public String getCourseName(){
    return courseName;
   }
   
   // equals
   public boolean equals(Object object){
    Course course = (Course) object;

    if(this.getCourseName().equals(course.getCourseName())){
        return true;
    }
    return false;
   }

   public String toString(){
    return  "CourseName: "+courseName+"\n"+"Number of credits: "+credits+"\n"+"Lecture/Practicer: "+instructor.getFullName();
   }


}

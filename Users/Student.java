package Users;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import Grades.*;
import LibraryStuff.Book;
import LibraryStuff.Librarian;

public class Student extends Employee {
    private String major;
    private int yearOfStudy;
    private Transcript transcript;
    private int totalCredits;
    private Map<Course,Mark> CourseAndMark;
    private Boolean isReseacher;
    private Boolean approved;

     public Student(String userName,String password, Boolean isAuthenticated, Language language,String fullName,String ID,String job,String major,int yearOfStudy,Transcript transcript,List<Course>coursec,int credits, Boolean isReseacher, Boolean isAppove, int salary){
        super(userName,password,isAuthenticated,language,fullName,ID,job,salary);
     
        this.major=major;
        this.yearOfStudy=yearOfStudy;
        this.transcript=new Transcript();
        this.totalCredits=credits;
        this.isReseacher=false;
        this.approved=isAppove;
        CourseAndMark=new HashMap<>();
    }


    public void registerForCourse(Course course) {
        totalCredits+=course.credits;

        if(totalCredits<=21){
            Mark defaultMark=new Mark(0);
            defaultMark=null;
            CourseAndMark.put(course,defaultMark);
        }

        else{
            totalCredits-=course.credits;
            System.out.println("The number of credits is more than 21, You can't take it!");
        }
    }

    
    //getters
    public Map<Course,Mark> getCourses(){
        return CourseAndMark;
    }

    public Map<Course,Mark> getMarks(){
        return CourseAndMark;
    }

    public Transcript getTranscript() {
        return transcript;
    }
    public String getMajor(){
        return major;
    }

     public boolean isApproved() {
        return approved;
    }

    //setters
    public void setApproved(boolean approved) {
        this.approved = approved;
    }


    public void borrowBookFromLibrarian(Librarian librarian, Book book) { 
        librarian.borrowBook(book);
     }
    public void returnBookToLibrarian(Librarian librarian,Book book) {
        librarian.returnBook(book);
    }

   /*  public String toString(){
        return  "Student's full Name: "+super.getFullName()+"\n"+"ID: "+super.getID()+"\n"+"Year of study: "+yearOfStudy+"\n"+"Is "+super.getFullName()+" reseacher: "+isReseacher+"\n"+"Major: "+major+"\n";
    }*/

    @Override
public String toString() {
    return String.format(
        "=================================\n" +
        "          Student Profile         \n" +
        "=================================\n" +
        "Full Name      : %s\n" +
        "ID             : %s\n" +
        "Year of Study  : %d\n" +
        "Researcher     : %s\n" +
        "Major          : %s\n" +
        "=================================",
        super.getFullName(),
        super.getID(),
        yearOfStudy,
        isReseacher ? "Yes" : "No",
        major
    );
}

}

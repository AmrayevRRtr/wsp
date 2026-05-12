package Grades;
import java.util.Map;
import java.io.Serializable;
import java.util.HashMap;


public class Transcript implements Serializable {
    private Map<Course,Mark> courseMarks;

    public Transcript(){
        courseMarks=new HashMap<>();
    }

    public void addCourseMark(Course course, Mark mark){
        courseMarks.put(course, mark);
    }

   

    public void PrintTranscript() {
        System.out.println(String.format("%-20s %-10s %-10s", "Course Name", "Score", "Letter Grade")); 
        System.out.println("-------------------------------------------------");
        
        for (Map.Entry<Course, Mark> entry : courseMarks.entrySet()) {
            String courseName = entry.getKey().getCourseName();
            float overallScore = entry.getValue().getOverallScore();
            float finalExam=entry.getValue().getFinalExam();
            String letterGrade = Mark.convertScoreToLetter(overallScore,finalExam);
            
            // Print course details in formatted columns
            System.out.println(String.format("%-20s %-10.2f %-10s", courseName, overallScore, letterGrade));
        }
    }
    



}

package Grades;

import java.io.Serializable;

public class Mark implements Serializable {
    private float firstAttestation;
    private float secondAttestation;
    private float finalExam;


    public Mark(float firstAttestation, float secondAttestation){
        if (firstAttestation < 0 || secondAttestation < 0 ) {
            throw new IllegalArgumentException("Marks cannot be negative.");
        }
        this.firstAttestation=firstAttestation;
        this.secondAttestation=secondAttestation;
    }

    public Mark(float finalExam) {
        if ( finalExam < 0) {
            throw new IllegalArgumentException("Marks cannot be negative.");
        }
        this.finalExam = finalExam;
    }

    
    
    public boolean accessTofinal(){
        return this.getFirstAttestation()+this.getSecondAttestation()>=30;
    }

    public boolean passFinalExam(){
        return this.getFinalExam()>=20;
    }

    public static String convertScoreToLetter(double overallscore, double finalExam) {
        if(finalExam<20 && finalExam>10){
            return "FX";
        }

        else if(finalExam<10){
            return "F";
        }
    
        else if(overallscore>=95){
            return "A+";
        }

        else if (overallscore >= 90) {
                return "A-";
         } else if (overallscore >= 85) {
                return "B+";
        } else if (overallscore >= 80) {
                return "B";
        } else if (overallscore >= 75) {
                return "B-";
        } else if (overallscore >= 70) {
                return "C+";
        } else if (overallscore >= 65) {
                return "C";
        } else if (overallscore >= 60) {
                return "C-";
        } else if(overallscore>=55){
            return "D";
        } else if(overallscore>=50){
            return "D-";
        }
         else {
                return "F";
            }
        }

    //getters

    public float getFirstAttestation(){
        return firstAttestation;
    }

    public float getSecondAttestation(){
        return secondAttestation;
    }

    public float getFinalExam(){
        return finalExam;
    }

    public float getOverallScore(){
        return finalExam+firstAttestation+secondAttestation;
    }

    //setters
    public void setMarkFinalExam(float score){
        this.finalExam=score;
    }


}

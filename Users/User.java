package Users;

import ResearcherStuff.ResearchPaper;

import java.io.Serializable;



public class User implements Serializable {

    private static final long serialVersionUID = 216866269752038359L; // Установите версию

    private String userName;
    private String password;
    private boolean isAuthenticated;
    public Language language;

    public enum Language{

    RUSSIAN,KAZAKH,ENGLISH;

    }

    public User(String userName,String password, Boolean isAuthenticated, Language language){
        this.userName=userName;
        this.password=password;
        this.isAuthenticated=isAuthenticated;
        this.language=language;
    }

    //getters

    public String getuserName(){
        return userName;
    }

    public String getpassword(){
        return password;
    }

    public Boolean getAuthenticated(){
        return isAuthenticated;
    }

    public void setAuthenticated(boolean b){
        this.isAuthenticated=b;
    }

    public String login(String userName, String password) {
        if(this.userName.equals(userName) && this.password.equals(password)){
            this.isAuthenticated=true;
            return "You entered in your account";
        }

        return "Please check password and user name!";
    }


    public void logout() {
        this.isAuthenticated = false;
       // return "You have logged out successfully.";
    }

    public void notifySubscription(ResearchPaper paper) {
    }

  
}
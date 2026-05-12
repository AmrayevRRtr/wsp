package Users;
public class Employee extends User {
   private String fullName;
   private String ID;
   private String job;
   private int salary;

   public Employee(String userName,String password, Boolean isAuthenticated, Language language,String fullName,String ID,String job, int salary){
    super(userName,password,isAuthenticated,language);

    this.fullName=fullName;
    this.ID=ID;
    this.job=job;
    this.salary=salary;
   }

   //getters

   public String getFullName(){
    return fullName;
   }

   public String getID(){
    return ID;
   }

   public String getJob(){
    return job;
   }

   public float getSalary(){
      return salary;
   }

public void setID(String newID){
      this.ID=newID;
}

   


}
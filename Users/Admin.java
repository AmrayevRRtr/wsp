package Users;
import java.util.ArrayList;
import java.util.List;

public class Admin extends Employee {
    private List<User> AllUsers;


    public Admin(String userName,String password, Boolean isAuthenticated, Language language,String fullName,String ID,String job, int salary){
        super(userName,password,isAuthenticated,language,fullName,ID,job,salary);
        this.AllUsers=new ArrayList<>();
    }

    public void addUser(User user){
    AllUsers.add(user);
}

    public void removeUser(User user){
    AllUsers.remove(user);
}

    public List<User> getAllUsers(){
        return AllUsers;
    }

    public void setAllUsers(List<User> users){
        this.AllUsers=users;
    }
}

package LibraryStuff;
import java.util.Map;



import Users.*;


public class Librarian extends Employee {
    private Library library;

    public Librarian(String userName,String password, Boolean isAuthenticated, Language language,String fullName,String ID,String job,Library library, int salary){
        super( userName, password,  isAuthenticated,  language, fullName, ID, job,salary);

        this.library=library;
    }

    public void returnBook(Book book){
        library.addBook(book);
    }

    public Library getLibrary() {
        return library;
    }

    public void borrowBook(Book book){
         if (library.isAvailable(book)) {
        // Reduce the quantity of the book by 1
        for (Map.Entry<Book, Integer> entry : library.getAllBooks().entrySet()) {
            if (entry.getKey().title.equals(book.title)) {
                entry.setValue(entry.getValue() - 1);
                System.out.println("The book '" + book.title + "' has been borrowed.");
                return;
            }
        }
    } else {
        System.out.println("The book '" + book.title + "' is not available.");
    }
    }

    @Override
    public String toString() {
        String header = "=================== Librarian Profile ===================";
        return header + "\n" +
               "Librarian: " + getFullName() + " (ID: " + getID() + ")\n" +
               "Job Title: " + getJob() + "\n" +
               "Salary: $" + getSalary() + "\n" +
               "========================================================";
    }
    





}

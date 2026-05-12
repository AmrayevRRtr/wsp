package Test;
import java.util.*;
import java.io.*;
import Grades.*;
import LibraryStuff.Book;
import LibraryStuff.Librarian;
import LibraryStuff.Library;
import NewsStuff.News;
import Users.*;
import Users.Manager.ManagerType;

import Users.User.Language;




public class Load {  public static void main(String[] args) {

       
       Student student1 = new Student("sh_alisher", "123", true, Language.ENGLISH, "Alice Brown", "S001", "Student", "Computer Science", 2, new Transcript(), new ArrayList<>(), 0, false, true, 0);
       Student student2 = new Student("Artem", "456", true, Language.ENGLISH, "Artem Smith", "S002", "Student", "Mathematics", 1, new Transcript(), new ArrayList<>(), 0, true, true, 0);
       Student student3 = new Student("Ivan", "789", true, Language.ENGLISH, "Ivan Johnson", "S003", "Student", "Physics", 3, new Transcript(), new ArrayList<>(), 0, false, false, 0);
       Student student4 = new Student("Timur", "10", true, Language.ENGLISH, "Timur Scott", "S004", "Student", "Biology", 4, new Transcript(), new ArrayList<>(), 0, true, true, 0);

       


       Teacher teacher1 = new Teacher("Arman", "10", true, Language.ENGLISH, "Dr. Smith", "T001", "Professor", true, 4.5f, 5000);
       Teacher teacher2 = new Teacher("Alex", "11", true, Language.ENGLISH, "Dr. Johnson", "T002", "Lecturer", false, 4.2f, 4000);
       Teacher teacher3 = new Teacher("Kanat", "12", true, Language.ENGLISH, "Dr. Williams", "T003", "Researcher", true, 4.7f, 5500);
      

    

      
        //Library
         Library library = new Library();

        // Step 2: Add books to the library
        Book book1 = new Book("The Alchemist", "Paulo Coelho", 197, 1988);
        Book book2 = new Book("1984", "George Orwell", 328, 1949);
        Book book3 = new Book("To Kill a Mockingbird", "Harper Lee", 281, 1960);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

          // Step 3: Create a Librarian object
        Librarian librarian = new Librarian(
            "librarianUser",      // Username
            "librarianPass",      // Password
            true,                 // isAuthenticated
            Language.ENGLISH,     // Language
            "John Smith",         // Full Name
            "L001",               // ID
            "Librarian",          // Job Title
            library,              // Library Object
            500000                  // Salary
        );
       //Researchers


       //courses
       Course course1 = new Course("Mathematics", 5, teacher1);
       Course course2 = new Course("Physics", 4, teacher2);
       Course course3 = new Course("Chemistry", 3, teacher3);

       teacher1.addCourse(course1);
       teacher2.addCourse(course2);
       teacher3.addCourse(course3);

       course1.enrollStudent(student1);
   course1.enrollStudent(student2);
   course1.enrollStudent(student3);

   course2.enrollStudent(student1);
   course2.enrollStudent(student2);
   course2.enrollStudent(student3);

   course3.enrollStudent(student1);
   course3.enrollStudent(student2);
   course3.enrollStudent(student3);

   student1.registerForCourse(course1);
   student1.registerForCourse(course2);
   student1.registerForCourse(course3);
   student2.registerForCourse(course1);
   student2.registerForCourse(course2);
   student2.registerForCourse(course3);
   student3.registerForCourse(course1);
   student3.registerForCourse(course2);
   student3.registerForCourse(course3);




      // Create News objects
        News news1 = new News(
            "Breaking News: Java 21 Released",
            "Java 21 introduces several new features, enhancing developer productivity.",
            "John Doe",
            new Date()
        );

        News news2 = new News(
            "Tech: AI Revolution",
            "Artificial Intelligence is set to revolutionize industries worldwide.",
            "Jane Smith",
            new Date()
        );

        News news3 = new News(
            "Sports: World Cup Update",
            "The World Cup final delivered an unforgettable match.",
            "Alex Johnson",
            new Date()
        );

        List<News> newsList = new ArrayList<>();
        newsList.add(news3);
        newsList.add(news2);
        newsList.add(news1);

        //manager
        Manager manager = new Manager("managerUser", "managerPass", true, Language.ENGLISH, 
        "John Doe", "M001", "Manager", ManagerType.DEPARTMENT, 7000,newsList);


         //Admin admin
       Admin admin = new Admin("adminUser", "adminPass", true, Language.ENGLISH, 
       "Jane Doe", "A001", "System Administrator", 9000);
       

       List<User> allUsers=new ArrayList<>();
       allUsers.add(student1);
       allUsers.add(student2);
       allUsers.add(student3);
       allUsers.add(student4);
       allUsers.add(teacher1);
       allUsers.add(teacher2);
       allUsers.add(teacher3);
       allUsers.add(admin);
       allUsers.add(manager);
       allUsers.add(librarian);
       admin.setAllUsers(allUsers);
    System.out.println("Admin user list size: " + admin.getAllUsers().size());

// researcher

//    UniversityJournal journal = new UniversityJournal("Journal of Advanced Studies", "Global Academic Press");
//
//    // Print initial state of the journal
//    System.out.println("Journal Name: " + journal.getName());
//    System.out.println("Publisher: " + journal.getPublisher());
//    System.out.println("Initial Papers: " + journal.getPapers().size());
//    ResearchPaper paper1 = new ResearchPaper(
//            "Advances in Quantum Computing",
//            "Journal of Advanced Studies",
//            LocalDate.of(2024, 12, 20),
//            15
//    );
//    paper1.setDoi("10.1234/jas.2024.001");
//
//
//    journal.addPaper(paper1);
//    System.out.println("Added paper: " + paper1.getTitle());
//
//
//    System.out.println("Papers in Journal: " + journal.getPapers().size());
//    for (ResearchPaper paper : journal.getPapers()) {
//        System.out.println(" - " + paper.getTitle());
//    }
//
//
//    journal.removePaper(paper1);
//    System.out.println("Removed paper: " + paper1.getTitle());
//    System.out.println("Papers in Journal after removal: " + journal.getPapers().size());
//
//

    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(UserDatabase.getUsersFile()))) {
        oos.writeObject(allUsers);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}


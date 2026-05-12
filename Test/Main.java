package Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Grades.Course;
import Grades.Mark;
import Grades.Transcript;
import LibraryStuff.Book;
import LibraryStuff.Librarian;
import LibraryStuff.Library;
import NewsStuff.News;
import ResearcherStuff.ResearchPaper;
import ResearcherStuff.UniversityJournal;
import Users.Admin;
import Users.Manager;
import Users.Student;
import Users.Teacher;
import Users.User;
import Users.User.Language;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the University Management System!");

        while (true) {
            System.out.print("Write down your user name: ");
            String userName = scanner.nextLine();

            System.out.print("Write down your password: ");
            String password = scanner.nextLine();
            System.out.print("\n");

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(UserDatabase.getUsersFile()))) {
                @SuppressWarnings("unchecked")
                List<User> allUsers = (List<User>) ois.readObject();

                for (User u : allUsers) {
                    if (u instanceof Admin) {
                        ((Admin) u).setAllUsers(allUsers);
                    }
                }

                User loggedUser = null;

                for (User user : allUsers) {
                    if (user.getuserName().equals(userName) && user.getpassword().equals(password)) {
                        loggedUser = user;
                        break;
                    }
                }

                if (loggedUser == null) {
                    System.out.println("Your password or user name is invalid!");
                } else if (loggedUser instanceof Student) {

                    Student studentUser = (Student) loggedUser;
                    studentUser.setAuthenticated(true);

                    while (studentUser.getAuthenticated()) {
                        System.out.println("\n--- Main Menu ---");
                        System.out.println("1. Get Transcript");
                        System.out.println("2. Library");
                        System.out.println("3. See News");
                        System.out.println("4. Exit \n");

                        System.out.print("Enter your choice (1-4): ");

                        int choice = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("\n");

                        if (choice == 1) {
                            studentUser.getTranscript().PrintTranscript();
                        } else if (choice == 2) {
                            handleStudentLibrary(scanner, studentUser, allUsers);
                        } else if (choice == 3) {
                            printNewsFromManager(allUsers);
                        } else if (choice == 4) {
                            System.out.println("You logged out from the system!\n");
                            studentUser.logout();
                        }
                    }
                } else if (loggedUser instanceof Teacher) {
                    Teacher teacherUser = (Teacher) loggedUser;
                    teacherUser.setAuthenticated(true);

                    while (teacherUser.getAuthenticated()) {
                        System.out.println("\n--- Main Menu ---");
                        System.out.println("1. Put marks");
                        System.out.println("2. Get information about students");
                        System.out.println("3. See News");
                        System.out.println("4. Exit \n");

                        System.out.print("Enter your choice (1-4): ");

                        int choice = scanner.nextInt();
                        scanner.nextLine();

                        if (choice == 1) {
                            for (Course course : teacherUser.getCourses()) {
                                System.out.println("\nAssign marks for course: " + course.getCourseName() + "\n");

                                for (Student student : course.getEnrolledStudents()) {
                                    System.out.println("Student: " + student.getFullName());

                                    if (student.getMarks().containsKey(course)) {
                                        Mark existingMark = student.getMarks().get(course);
                                        if (existingMark != null) {
                                            System.out.println("First Attestation: " + existingMark.getFirstAttestation());
                                            System.out.println("Second Attestation: " + existingMark.getSecondAttestation());
                                            System.out.println("Final Exam: " + existingMark.getFinalExam() + "\n");
                                        } else {
                                            System.out.print("Enter First Attestation mark: ");
                                            float firstAttestation = scanner.nextFloat();

                                            System.out.print("Enter Second Attestation mark: ");
                                            float secondAttestation = scanner.nextFloat();
                                            scanner.nextLine();

                                            teacherUser.assignMark(student, new Mark(firstAttestation, secondAttestation), course);
                                            System.out.println("Marks assigned successfully!\n");
                                        }
                                    } else {
                                        System.out.println("Student has no grade slot for this course; registering course on transcript map.");
                                        student.registerForCourse(course);
                                        System.out.print("Enter First Attestation mark: ");
                                        float firstAttestation = scanner.nextFloat();

                                        System.out.print("Enter Second Attestation mark: ");
                                        float secondAttestation = scanner.nextFloat();
                                        scanner.nextLine();

                                        teacherUser.assignMark(student, new Mark(firstAttestation, secondAttestation), course);
                                        System.out.println("Marks assigned successfully!\n");
                                    }
                                }
                            }
                        } else if (choice == 2) {
                            for (Course course : teacherUser.getCourses()) {
                                teacherUser.viewStudentsInCourse(course);
                            }
                        } else if (choice == 3) {
                            printNewsFromManager(allUsers);
                        } else if (choice == 4) {
                            System.out.println("You logged out from the system!\n");
                            teacherUser.logout();
                        }
                    }
                } else if (loggedUser instanceof Manager) {
                    Manager managerUser = (Manager) loggedUser;
                    managerUser.setAuthenticated(true);

                    while (managerUser.getAuthenticated()) {
                        System.out.println("\n--- Main Menu ---");
                        System.out.println("1. View information about Students and Teachers");
                        System.out.println("2. Library");
                        System.out.println("3. Manage News");
                        System.out.println("4. Exit \n");

                        System.out.print("Enter your choice (1-4): ");

                        int choice = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("\n");

                        if (choice == 1) {
                            List<Student> students = new ArrayList<>();
                            List<Teacher> teachers = new ArrayList<>();
                            for (User u : allUsers) {
                                if (u instanceof Student) {
                                    students.add((Student) u);
                                } else if (u instanceof Teacher) {
                                    teachers.add((Teacher) u);
                                }
                            }
                            managerUser.viewInfo(students, teachers);
                        } else if (choice == 2) {
                            printLibraryCatalog(allUsers);
                        } else if (choice == 3) {
                            handleManagerNews(scanner, managerUser);
                        } else if (choice == 4) {
                            System.out.println("You logged out from the system!\n");
                            managerUser.logout();
                        }
                    }
                } else if (loggedUser instanceof Admin) {
                    Admin admin = (Admin) loggedUser;
                    admin.setAuthenticated(true);

                    UniversityJournal journal = new UniversityJournal("Journal of Advanced Studies", "Global Academic Press");

                    while (admin.getAuthenticated()) {
                        System.out.println("\n--- Main Menu ---");
                        System.out.println("1. View information about Employees");
                        System.out.println("2. Add user");
                        System.out.println("3. Remove user");
                        System.out.println("4. View News");
                        System.out.println("5. Manage University Journal");
                        System.out.println("6. Exit \n");

                        System.out.print("Enter your choice (1-6): ");
                        int choice = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("\n");

                        if (choice == 1) {
                            System.out.println("          STUDENTS       \n");
                            for (User user : admin.getAllUsers()) {
                                if (user instanceof Student) {
                                    System.out.println(user);
                                }
                            }

                            System.out.println("\n                EMPLOYEES         \n");
                            System.out.println("TEACHERS:     \n");
                            for (User user : admin.getAllUsers()) {
                                if (user instanceof Teacher) {
                                    System.out.println(user);
                                }
                            }

                            System.out.println("\nLIBRARIANS:          \n");
                            for (User user : admin.getAllUsers()) {
                                if (user instanceof LibraryStuff.Librarian) {
                                    System.out.println(user);
                                }
                            }

                            System.out.println("\nMANAGERS:           \n");
                            for (User user : admin.getAllUsers()) {
                                if (user instanceof Manager) {
                                    System.out.println(user);
                                }
                            }
                        } else if (choice == 2) {
                            handleAdminAddUser(scanner, admin, allUsers);
                        } else if (choice == 3) {
                            handleAdminRemoveUser(scanner, admin, allUsers);
                        } else if (choice == 4) {
                            printNewsFromManager(allUsers);
                        } else if (choice == 5) {
                            handleJournalMenu(scanner, journal);
                        } else if (choice == 6) {
                            System.out.println("You logged out from the system!\n");
                            admin.setAuthenticated(false);
                        }
                    }
                }

                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(UserDatabase.getUsersFile()))) {
                    oos.writeObject(allUsers);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Could not read user database. Run Test.Load first to create DataBase/Users.bin");
                e.printStackTrace();
            }
        }
    }

    private static Librarian findLibrarian(List<User> allUsers) {
        for (User u : allUsers) {
            if (u instanceof Librarian) {
                return (Librarian) u;
            }
        }
        return null;
    }

    private static Book findBookInLibrary(Library library, String title) {
        if (title == null) {
            return null;
        }
        String t = title.trim();
        for (Map.Entry<Book, Integer> entry : library.getAllBooks().entrySet()) {
            if (entry.getKey().title.equalsIgnoreCase(t)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private static void printLibraryCatalog(List<User> allUsers) {
        Librarian librarian = findLibrarian(allUsers);
        if (librarian == null) {
            System.out.println("No librarian account found.");
            return;
        }
        Library library = librarian.getLibrary();
        System.out.println("\n--- Library catalog ---");
        if (library.getAllBooks().isEmpty()) {
            System.out.println("(empty)");
            return;
        }
        for (Map.Entry<Book, Integer> entry : library.getAllBooks().entrySet()) {
            Book b = entry.getKey();
            System.out.println("- " + b.title + " by " + b.authour + " | copies: " + entry.getValue());
        }
    }

    private static void handleStudentLibrary(Scanner scanner, Student student, List<User> allUsers) {
        Librarian librarian = findLibrarian(allUsers);
        if (librarian == null) {
            System.out.println("No librarian is available in the system.");
            return;
        }
        System.out.println("1. Borrow a book");
        System.out.println("2. Return a book");
        System.out.print("Choose (1-2): ");
        int sub = scanner.nextInt();
        scanner.nextLine();
        if (sub == 1) {
            printLibraryCatalog(allUsers);
            System.out.print("Enter book title to borrow: ");
            String title = scanner.nextLine();
            Book book = findBookInLibrary(librarian.getLibrary(), title);
            if (book == null) {
                System.out.println("Book not found in catalog.");
            } else {
                student.borrowBookFromLibrarian(librarian, book);
            }
        } else if (sub == 2) {
            System.out.print("Enter book title to return: ");
            String title = scanner.nextLine();
            Book book = findBookInLibrary(librarian.getLibrary(), title);
            if (book == null) {
                System.out.println("Unknown title (return uses catalog copy).");
            } else {
                student.returnBookToLibrarian(librarian, book);
                System.out.println("Return processed.");
            }
        }
    }

    private static void printNewsFromManager(List<User> allUsers) {
        for (User user : allUsers) {
            if (user instanceof Manager) {
                Manager manager = (Manager) user;
                System.out.println("\n---BREAKING NEWS---\n");
                for (News news : manager.getNews()) {
                    System.out.println(news + "\n");
                }
                break;
            }
        }
    }

    private static void handleManagerNews(Scanner scanner, Manager manager) {
        List<News> news = manager.getNews();
        if (news.isEmpty()) {
            System.out.println("No news items.");
            return;
        }
        for (int i = 0; i < news.size(); i++) {
            System.out.println((i + 1) + ". " + news.get(i).getTopic() + (news.get(i).isPinned() ? " [pinned]" : ""));
        }
        System.out.print("News number to pin/unpin (0 = cancel): ");
        int idx = scanner.nextInt();
        scanner.nextLine();
        if (idx < 1 || idx > news.size()) {
            return;
        }
        News item = news.get(idx - 1);
        System.out.print("Pin? (y/n): ");
        String ans = scanner.nextLine();
        manager.manageNews(item, ans.trim().equalsIgnoreCase("y"));
    }

    private static void handleAdminAddUser(Scanner scanner, Admin admin, List<User> allUsers) {
        System.out.println("Add: 1 = Student, 2 = Teacher");
        System.out.print("Type: ");
        int type = scanner.nextInt();
        scanner.nextLine();
        if (type == 1) {
            System.out.print("Username: ");
            String un = scanner.nextLine();
            System.out.print("Password: ");
            String pw = scanner.nextLine();
            System.out.print("Full name: ");
            String fn = scanner.nextLine();
            System.out.print("ID: ");
            String id = scanner.nextLine();
            System.out.print("Major: ");
            String major = scanner.nextLine();
            System.out.print("Year of study: ");
            int year = scanner.nextInt();
            scanner.nextLine();
            Student s = new Student(un, pw, false, Language.ENGLISH, fn, id, "Student", major, year,
                    new Transcript(), new ArrayList<>(), 0, false, true, 0);
            allUsers.add(s);
            System.out.println("Student added.");
        } else if (type == 2) {
            System.out.print("Username: ");
            String un = scanner.nextLine();
            System.out.print("Password: ");
            String pw = scanner.nextLine();
            System.out.print("Full name: ");
            String fn = scanner.nextLine();
            System.out.print("ID: ");
            String id = scanner.nextLine();
            System.out.print("Salary: ");
            int salary = scanner.nextInt();
            scanner.nextLine();
            Teacher t = new Teacher(un, pw, false, Language.ENGLISH, fn, id, "Teacher", false, 0f, salary);
            allUsers.add(t);
            System.out.println("Teacher added.");
        } else {
            System.out.println("Unknown type.");
        }
    }

    private static void handleAdminRemoveUser(Scanner scanner, Admin admin, List<User> allUsers) {
        System.out.println("Users (excluding removal of yourself from this list is still possible if shown):");
        for (int i = 0; i < allUsers.size(); i++) {
            User u = allUsers.get(i);
            System.out.println((i + 1) + ". " + u.getuserName() + " (" + u.getClass().getSimpleName() + ")");
        }
        System.out.print("Number to remove (0 = cancel): ");
        int idx = scanner.nextInt();
        scanner.nextLine();
        if (idx < 1 || idx > allUsers.size()) {
            return;
        }
        User toRemove = allUsers.get(idx - 1);
        allUsers.remove(toRemove);
        admin.removeUser(toRemove);
        System.out.println("Removed user: " + toRemove.getuserName());
    }

    private static void handleJournalMenu(Scanner scanner, UniversityJournal journal) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- University Journal ---");
            System.out.println("1. List papers");
            System.out.println("2. Add paper");
            System.out.println("3. Remove paper by title");
            System.out.println("0. Back");
            System.out.print("Choice: ");
            int c = scanner.nextInt();
            scanner.nextLine();
            if (c == 1) {
                System.out.println("Journal: " + journal.getName() + " | Publisher: " + journal.getPublisher());
                List<ResearchPaper> papers = journal.getPapers();
                if (papers.isEmpty()) {
                    System.out.println("(no papers)");
                } else {
                    for (ResearchPaper p : papers) {
                        System.out.println(" - " + p.getTitle() + (p.getDoi() != null ? " DOI: " + p.getDoi() : ""));
                    }
                }
            } else if (c == 2) {
                System.out.print("Title: ");
                String title = scanner.nextLine();
                System.out.print("Pages: ");
                int pages = scanner.nextInt();
                scanner.nextLine();
                System.out.print("DOI (optional, empty to skip): ");
                String doi = scanner.nextLine();
                ResearchPaper paper = new ResearchPaper(title, journal.getName(), LocalDate.now(), pages);
                if (!doi.isEmpty()) {
                    paper.setDoi(doi);
                }
                journal.addPaper(paper);
                System.out.println("Paper added.");
            } else if (c == 3) {
                System.out.print("Title to remove: ");
                String title = scanner.nextLine();
                ResearchPaper toRemove = null;
                for (ResearchPaper p : journal.getPapers()) {
                    if (p.getTitle().equalsIgnoreCase(title.trim())) {
                        toRemove = p;
                        break;
                    }
                }
                if (toRemove == null) {
                    System.out.println("Not found.");
                } else {
                    journal.removePaper(toRemove);
                    System.out.println("Removed.");
                }
            } else if (c == 0) {
                back = true;
            }
        }
    }
}

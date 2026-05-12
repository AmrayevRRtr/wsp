# University Management System

Console application for a small university: **roles** (student, teacher, manager, librarian, admin), **grades & transcripts**, **library** (catalog, borrow / return), **news**, and **admin** tools (users, university journal). Data is stored in a **serialized** binary file under `DataBase/`.

## Tech stack

- **Java** (plain JDK, no external dependencies)
- **Object serialization** (`ObjectInputStream` / `ObjectOutputStream`) for persistence
- **OOP**: layered packages (`Users`, `Grades`, `LibraryStuff`, `NewsStuff`, `ResearcherStuff`, `Test`)

## Requirements

- **JDK 17+** (or any JDK that compiles the sources; project uses standard library only)

## How to run

From the **repository root** (the folder that contains `DataBase/`, `Test/`, `Users/`, …):

### 1. Create / refresh the user database (seed data)

```bash
javac -encoding UTF-8 Test/*.java Users/*.java Grades/*.java LibraryStuff/*.java NewsStuff/*.java ResearcherStuff/*.java
java Test.Load
```

This creates `DataBase/Users.bin` if the directory does not exist.

### 2. Start the application

```bash
java Test.Main
```

Log in with a username and password from the seed data (see below). After each session the app writes updates back to `DataBase/Users.bin`.

## Seed accounts (`Test/Load.java`)

| Role       | Username       | Password      |
|-----------|----------------|---------------|
| Student   | `sh_alisher`   | `123`         |
| Student   | `Artem`        | `456`         |
| Teacher   | `Arman`        | `10`          |
| Manager   | `managerUser`  | `managerPass` |
| Librarian | `librarianUser`| `librarianPass` |
| Admin     | `adminUser`    | `adminPass`   |

*(Other students/teachers are also defined in `Load.java`.)*

## Project layout

| Path | Purpose |
|------|--------|
| `Test/Main.java` | Entry point: login loop and role menus |
| `Test/Load.java` | One-off seed + initial `Users.bin` |
| `Test/UserDatabase.java` | Path to `DataBase/Users.bin` (relative to working directory) |
| `Users/` | `User`, `Student`, `Teacher`, `Admin`, `Manager`, `Employee`, … |
| `Grades/` | `Course`, `Mark`, `Transcript` |
| `LibraryStuff/` | `Library`, `Book`, `Librarian` |
| `NewsStuff/` | `News`, `Message` |
| `ResearcherStuff/` | Research / journal models |
| `ProjectB/src/App.java` | Optional entry that delegates to `Test.Main` |

## Notes

- Run commands from the **project root** so `DataBase/Users.bin` resolves correctly.
- `Users.bin` is runtime data; you may add `DataBase/*.bin` to `.gitignore` if you do not want local DB files in Git.



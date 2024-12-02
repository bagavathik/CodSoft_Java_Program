package CodSoft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// Class to represent a course
class Course {
    String courseCode; // Unique identifier for the course
    String title; // Title of the course
    String description; // Brief description of the course
    int capacity; // Maximum number of students allowed
    String schedule; // Course schedule (e.g., days and time)
    ArrayList<String> registeredStudents; // List of student IDs registered for the course

    // Constructor to initialize course details
    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    // Method to check if the course has available slots
    public boolean hasSlot() {
        return registeredStudents.size() < capacity;
    }

    // Method to display course details
    public void displayCourse() {
        System.out.printf("Code: %s | Title: %s | Description: %s | Capacity: %d/%d | Schedule: %s%n",
                courseCode, title, description, registeredStudents.size(), capacity, schedule);
    }
}

// Class to represent a student
class Student {
    String studentID; 
    String name; 
    ArrayList<String> registeredCourses; 

    // Constructor to initialize student details
    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }
}

// Main class for the Student Course Registration System
public class CourseRegistration {
    static HashMap<String, Course> courseDatabase = new HashMap<>(); 
    static HashMap<String, Student> studentDatabase = new HashMap<>(); 
    static Scanner scanner = new Scanner(System.in); 

    public static void main(String[] args) {
        initializeData(); // Initialize course data
        int choice;

        // Display menu until the user chooses to exit
        do {
            System.out.println("\n==== Student Course Registration System ====");
            System.out.println("1. List Courses");
            System.out.println("2. Register Student");
            System.out.println("3. Register for a Course");
            System.out.println("4. Drop a Course");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            // Perform actions based on user's choice
            switch (choice) {
                case 1 -> listCourses();
                case 2 -> registerStudent();
                case 3 -> registerForCourse();
                case 4 -> dropCourse();
                case 5 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    // Initialize sample course data
    static void initializeData() {
        courseDatabase.put("CS101", new Course("CS101", "Introduction to Programming", "Basics of Java", 3, "MWF 10-11AM"));
        courseDatabase.put("CS102", new Course("CS102", "Data Structures", "Learn about data structures", 2, "TTh 2-3:30PM"));
        courseDatabase.put("CS103", new Course("CS103", "Algorithms", "Introduction to algorithms", 1, "MW 1-2PM"));
    }

    // Display the list of available courses
    static void listCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courseDatabase.values()) {
            course.displayCourse();
        }
    }

    // Register a new student
    static void registerStudent() {
        System.out.print("\nEnter Student ID: ");
        String studentID = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        // Check if student already exists
        if (studentDatabase.containsKey(studentID)) {
            System.out.println("Student already exists!");
        } else {
            studentDatabase.put(studentID, new Student(studentID, name));
            System.out.println("Student registered successfully!");
        }
    }

    // Register a student for a course
    static void registerForCourse() {
        System.out.print("\nEnter Student ID: ");
        String studentID = scanner.nextLine();
        Student student = studentDatabase.get(studentID);

        if (student == null) {
            System.out.println("Student not found! Please register first.");
            return;
        }

        listCourses(); // Display available courses
        System.out.print("Enter Course Code to register: ");
        String courseCode = scanner.nextLine();
        Course course = courseDatabase.get(courseCode);

        if (course == null) {
            System.out.println("Invalid course code.");
        } else if (!course.hasSlot()) {
            System.out.println("Course is full.");
        } else if (student.registeredCourses.contains(courseCode)) {
            System.out.println("You are already registered for this course.");
        } else {
            course.registeredStudents.add(studentID);
            student.registeredCourses.add(courseCode);
            System.out.println("Registration successful!");
        }
    }

    // Drop a course for a student
    static void dropCourse() {
        System.out.print("\nEnter Student ID: ");
        String studentID = scanner.nextLine();
        Student student = studentDatabase.get(studentID);

        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        // Display student's registered courses
        System.out.println("Your Registered Courses:");
        for (String courseCode : student.registeredCourses) {
            System.out.println(courseCode + ": " + courseDatabase.get(courseCode).title);
        }

        System.out.print("Enter Course Code to drop: ");
        String courseCode = scanner.nextLine();

        // Check and process course drop
        if (student.registeredCourses.contains(courseCode)) {
            student.registeredCourses.remove(courseCode);
            courseDatabase.get(courseCode).registeredStudents.remove(studentID);
            System.out.println("Course dropped successfully!");
        } else {
            System.out.println("You are not registered for this course.");
        }
    }
}


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

class Student {
    int id;
    String name;
    double marks;

    Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks;
    }
}

public class StudentManagementSystem {
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n=== Student Record Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Search Student by ID");
            System.out.println("6. Calculate Average Marks");
            System.out.println("7. Sort Students by Marks");
            System.out.println("8. Display Topper(s)");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");
            choice = input.nextInt();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewStudents();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> searchStudent();
                case 6 -> calculateAverage();
                case 7 -> sortByMarks();
                case 8 -> showToppers();
                case 9 -> System.out.println("Exiting program.");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 9);
    }

    static void addStudent() {
        System.out.print("Enter ID: ");
        int id = input.nextInt();
        input.nextLine();
        System.out.print("Enter Name: ");
        String name = input.nextLine();
        System.out.print("Enter Marks: ");
        double marks = input.nextDouble();

        students.add(new Student(id, name, marks));
        System.out.println("Student added successfully!");
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("\n--- Student List ---");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    static void updateStudent() {
        System.out.print("Enter ID to update: ");
        int id = input.nextInt();
        boolean found = false;

        for (Student s : students) {
            if (s.id == id) {
                input.nextLine();
                System.out.print("Enter new name: ");
                s.name = input.nextLine();
                System.out.print("Enter new marks: ");
                s.marks = input.nextDouble();
                System.out.println("Student updated successfully!");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    static void deleteStudent() {
        System.out.print("Enter ID to delete: ");
        int id = input.nextInt();
        boolean removed = students.removeIf(s -> s.id == id);

        if (removed) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    static void searchStudent() {
        System.out.print("Enter ID to search: ");
        int id = input.nextInt();
        boolean found = false;

        for (Student s : students) {
            if (s.id == id) {
                System.out.println("Student found: " + s);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    static void calculateAverage() {
        if (students.isEmpty()) {
            System.out.println("No students to calculate average.");
            return;
        }

        double total = 0;
        for (Student s : students) {
            total += s.marks;
        }

        double avg = total / students.size();
        System.out.printf("Average Marks = %.2f\n", avg);
    }

    static void sortByMarks() {
        if (students.isEmpty()) {
            System.out.println("No students to sort.");
            return;
        }

        students.sort(Comparator.comparingDouble(s -> -s.marks));
        System.out.println("--- Students sorted by marks (High to Low) ---");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    static void showToppers() {
        if (students.isEmpty()) {
            System.out.println("No students to evaluate.");
            return;
        }

        double maxMarks = students.stream().mapToDouble(s -> s.marks).max().getAsDouble();
        System.out.println("Top Scoring Student(s):");
        for (Student s : students) {
            if (s.marks == maxMarks) {
                System.out.println(s);
            }
        }
    }
}
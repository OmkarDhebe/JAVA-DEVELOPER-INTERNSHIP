import java.sql.*;
import java.util.Scanner;

public class EmployeeDBApp {
    // DB Connection Details
    static final String URL = "jdbc:mysql://localhost:3306/employee_db";
    static final String USER = "root";   // Change to your DB username
    static final String PASS = "Omkar@15"; // Change to your DB password

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            Scanner sc = new Scanner(System.in);
            int choice;
            do {
                System.out.println("\n--- Employee Database App ---");
                System.out.println("1. Add Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1 -> addEmployee(conn, sc);
                    case 2 -> viewEmployees(conn);
                    case 3 -> updateEmployee(conn, sc);
                    case 4 -> deleteEmployee(conn, sc);
                    case 5 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice!");
                }
            } while (choice != 5);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void addEmployee(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter position: ");
        String position = sc.nextLine();
        System.out.print("Enter salary: ");
        double salary = sc.nextDouble();

        String sql = "INSERT INTO employees (name, position, salary) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, position);
            ps.setDouble(3, salary);
            ps.executeUpdate();
            System.out.println("Employee added successfully.");
        }
    }

    static void viewEmployees(Connection conn) throws SQLException {
        String sql = "SELECT * FROM employees";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\nID | Name | Position | Salary");
            System.out.println("------------------------------");
            while (rs.next()) {
                System.out.printf("%d | %s | %s | %.2f%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("position"),
                        rs.getDouble("salary"));
            }
        }
    }

    static void updateEmployee(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Employee ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new name: ");
        String name = sc.nextLine();
        System.out.print("Enter new position: ");
        String position = sc.nextLine();
        System.out.print("Enter new salary: ");
        double salary = sc.nextDouble();

        String sql = "UPDATE employees SET name=?, position=?, salary=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, position);
            ps.setDouble(3, salary);
            ps.setInt(4, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Employee updated." : "Employee not found.");
        }
    }

    static void deleteEmployee(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter Employee ID to delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM employees WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Employee deleted." : "Employee not found.");
        }
    }
}

package Day3;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Register User");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Show All Books");
            System.out.println("6. Show All Users");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Book ID: ");
                    int bid = input.nextInt();
                    input.nextLine(); // consume newline
                    System.out.print("Title: ");
                    String title = input.nextLine();
                    System.out.print("Author: ");
                    String author = input.nextLine();
                    library.addBook(new Book(bid, title, author));
                    break;

                case 2:
                    System.out.print("Name: ");
                    String name = input.nextLine();
                    System.out.print("Phone Number as User Id");
                    int uid = input.nextInt();
                    input.nextLine();
                    
                    library.registerUser(new User(uid, name));
                    break;

                case 3:
                    System.out.print("Enter Book ID to issue: ");
                    int bookToIssue = input.nextInt();
                    System.out.print("Enter User ID: ");
                    int issueToUser = input.nextInt();
                    library.issueBook(bookToIssue, issueToUser);
                    break;

                case 4:
                    System.out.print("Enter Book ID to return: ");
                    int bookToReturn = input.nextInt();
                    System.out.print("Enter User ID: ");
                    int returnFromUser = input.nextInt();
                    library.returnBook(bookToReturn, returnFromUser);
                    break;

                case 5:
                    library.showAllBooks();
                    break;

                case 6:
                    library.showAllUsers();
                    break;

                case 0:
                    System.out.println("Exiting Library System.");
                    input.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

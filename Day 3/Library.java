package Day3;
import java.util.*;

public class Library {
    private Map<Integer, Book> books = new HashMap<>();
    private Map<Integer, User> users = new HashMap<>();

    public void addBook(Book book) {
        books.put(book.getId(), book);
        System.out.println("Book added: " + book);
    }

    public void registerUser(User user) {
        users.put(user.getUserId(), user);
        System.out.println("User registered: " + user);
    }

    public void issueBook(int bookId, int userId) {
        Book book = books.get(bookId);
        User user = users.get(userId);

        if (book == null || user == null) {
            System.out.println("Invalid book or user ID.");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("Book is already issued.");
            return;
        }

        book.setAvailable(false);
        user.borrowBook(book);
        System.out.println("Book issued: \"" + book.getTitle() + "\" to " + user.getName());
    }

    public void returnBook(int bookId, int userId) {
        Book book = books.get(bookId);
        User user = users.get(userId);

        if (book == null || user == null) {
            System.out.println("Invalid book or user ID.");
            return;
        }

        if (!user.getBorrowedBooks().contains(book)) {
            System.out.println("This user didn’t borrow the book.");
            return;
        }

        book.setAvailable(true);
        user.returnBook(book);
        System.out.println("Book returned: \"" + book.getTitle() + "\" by " + user.getName());
    }

    public void showAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in library.");
            return;
        }
        books.values().forEach(System.out::println);
    }

    public void showAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }
        users.values().forEach(System.out::println);
    }
}

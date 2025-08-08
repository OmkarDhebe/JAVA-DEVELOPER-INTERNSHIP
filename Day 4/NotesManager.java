import java.io.*;
import java.util.Scanner;

public class NotesManager {

 
    private static final String FILE_NAME = "C:\\Users\\Omkar\\Desktop\\JAVA\\JAVA DEVELOPER INTERNSHIP\\Day 4\\notes.txt";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice;

        System.out.println("Welcome to Notes Manager!");

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addNote(input);
                    break;
                case 2:
                    viewNotes();
                    break;
                case 3:
                    System.out.println("Exiting Notes Manager. Bye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 3);

        input.close();
    }

    // Method to add note
    private static void addNote(Scanner input) {
        System.out.print("Enter your note: ");
        String note = input.nextLine();

        try (FileWriter fw = new FileWriter(FILE_NAME, true)) { // true = append mode
            fw.write(note + "\n");
            System.out.println("Note saved successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to view notes
    private static void viewNotes() {
        System.out.println("\n--- Your Notes ---");
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            int count = 1;
            while ((line = br.readLine()) != null) {
                System.out.println(count++ + ". " + line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No notes found yet.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

import java.util.*;

class Question {
    String question;
    String[] options;
    int correctAnswer;

    // Constructor
    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    // Method to display the question
    public void displayQuestion() {
        System.out.println(question);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    // Method to check answer
    public boolean checkAnswer(int userAnswer) {
        return userAnswer == correctAnswer;
    }
}

public class QuizApp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int score = 0;

        // Create list of questions
        List<Question> quiz = new ArrayList<>();
        quiz.add(new Question("Which Java keyword is used to prevent inheritance?",
                new String[]{"Abstact", "final", "static", "super"}, 2));
        quiz.add(new Question("Which company developed Java?",
                new String[]{"Microsoft", "Google", "Sun Microsystems", "Apple"}, 3));
        quiz.add(new Question("Which one will execute first in a Java program?",
                new String[]{"main() method", "static block", "constructor", "object method"}, 2));
        quiz.add(new Question("Which keyword is used for exception handling in Java?",
                new String[]{"try", "catch", "throw", "All of these"}, 4));
        quiz.add(new Question("Which one is not an OOP concept?",
                new String[]{"Encapsulation", "Polymorphism", "Compilation", "Inheritance"}, 3));

        System.out.println("=== Welcome to the Online Quiz App ===");

        // Loop through questions
        for (int i = 0; i < quiz.size(); i++) {
            System.out.println("\nQuestion " + (i + 1) + ":");
            quiz.get(i).displayQuestion();
            System.out.print("Your Answer (1-4): ");
            int userAnswer = input.nextInt();

            if (quiz.get(i).checkAnswer(userAnswer)) {
                System.out.println(" Correct!");
                score++;
            } else {
                System.out.println(" Wrong! Correct Answer: " + quiz.get(i).options[quiz.get(i).correctAnswer - 1]);
            }
        }

        // Final Score
        System.out.println("\n=== Quiz Finished! ===");
        System.out.println("Your Score: " + score + "/" + quiz.size());

        // Simple result feedback
        if (score == quiz.size()) {
            System.out.println("Excellent! Perfect score!");
        } else if (score >= quiz.size() / 2) {
            System.out.println(" Good Job! You passed.");
        } else {
            System.out.println(" Keep Practicing! You can do better.");
        }
    }
}

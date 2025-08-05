



import java.util.Scanner;

public class Calculator {


    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Cannot divide by zero.");
            return Double.NaN;
        }
        return a / b;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double result = 0;
        boolean firstTime = true;
        boolean continueCalc = true;

        System.out.println("=====# Wellcome to Java Console Calculator #=====");

        while (continueCalc) {
            double num1;

            if (firstTime) {
                System.out.print("\nEnter first number: ");
                num1 = input.nextDouble();
                firstTime = false;
            } else {
                num1 = result;
                System.out.println("\nUsing previous result: " + num1);
            }

            System.out.print("Enter operator (+, -, *, /): ");
            char operator = input.next().charAt(0);    //  charAt gets first element from the string

            System.out.print("Enter second number: ");
            double num2 = input.nextDouble();

            switch (operator) {
                case '+':
                    result = add(num1, num2);
                    break;
                case '-':
                    result = subtract(num1, num2);
                    break;
                case '*':
                    result = multiply(num1, num2);
                    break;
                case '/':
                    result = divide(num1, num2);
                    break;
                default:
                    System.out.println("Invalid operator!");
                    continue;
            }

            System.out.println("Result: " + result);

            // User decision
            System.out.println("\nWhat would you like to do next?");
            System.out.println("1. Continue with this result");
            System.out.println("2. Start a new calculation");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1/2/3): ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    // Continue with current result
                    break;
                case 2:
                    // Start fresh
                    firstTime = true;
                    result = 0;
                    break;
                case 3:
                    // Exit
                    continueCalc = false;
                    System.out.println("Calculator closed.");
                    break;
                default:
                    System.out.println("Invalid choice. Exiting.");
                    continueCalc = false;
            }
        }

        
    }
}























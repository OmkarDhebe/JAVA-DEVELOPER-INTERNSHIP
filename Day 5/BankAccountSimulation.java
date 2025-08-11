import java.util.ArrayList;
import java.util.Scanner;

// Base Class: Account
class Account {
    protected String accountHolder;
    protected String accountNumber;
    protected double balance;
    protected ArrayList<String> transactionHistory;

    public Account(String accountHolder, String accountNumber, double initialBalance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with balance: Rs. " + initialBalance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: Rs. " + amount + " | Balance: Rs. " + balance);
            System.out.println("Deposit Successful: Rs. " + amount);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: Rs. " + amount + " | Balance: Rs. " + balance);
            System.out.println("Withdrawal Successful: Rs. " + amount);
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }

    public void showBalance() {
        System.out.println("Current Balance: Rs. " + balance);
    }

    public void showTransactionHistory() {
        System.out.println("\n--- Transaction History for " + accountHolder + " ---");
        for (String record : transactionHistory) {
            System.out.println(record);
        }
    }
}

// Derived Class: SavingsAccount (Inheritance & Method Overriding)
class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String accountHolder, String accountNumber, double initialBalance, double interestRate) {
        super(accountHolder, accountNumber, initialBalance);
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount) {
        // Overridden: Prevent withdrawing if balance < 500 after withdrawal
        if (balance - amount >= 500) {
            super.withdraw(amount);
        } else {
            System.out.println("Withdrawal denied! Maintain minimum balance of Rs. 500.");
        }
    }

    public void addInterest() {
        double interest = balance * interestRate / 100;
        balance += interest;
        transactionHistory.add("Interest Added: Rs. " + interest + " | Balance: Rs. " + balance);
        System.out.println("Interest of Rs. " + interest + " added successfully!");
    }
}

// Main Class
public class BankAccountSimulation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();

        System.out.print("Enter Initial Balance: Rs. ");
        double balance = sc.nextDouble();

        SavingsAccount account = new SavingsAccount(name, accNo, balance, 5.0);

        int choice;
        do {
            System.out.println("\n===== BANK MENU =====");
            System.out.println("1) Deposit");
            System.out.println("2) Withdraw");
            System.out.println("3) Show Balance");
            System.out.println("4) Show Transaction History");
            System.out.println("5) Add Interest");
            System.out.println("0) Exit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: Rs. ");
                    account.deposit(sc.nextDouble());
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: Rs. ");
                    account.withdraw(sc.nextDouble());
                    break;
                case 3:
                    account.showBalance();
                    break;
                case 4:
                    account.showTransactionHistory();
                    break;
                case 5:
                    account.addInterest();
                    break;
                case 0:
                    System.out.println("Thank you for banking with us!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 0);

        sc.close();
    }
}

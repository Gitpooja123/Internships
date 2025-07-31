//Task--->3
//ATM INTERFACE
import java.util.Scanner;

class BankAccount {
    double balance;
    int pin;

    BankAccount(double initialBalance,int accountPin) {
        balance = initialBalance;
        pin=accountPin;
    }
    boolean validatePin(int enteredPin) {
        return pin == enteredPin;
    }

    void deposit(double amount) {
        if (amount > 0) {
            balance = balance + amount;
            System.out.println("Amount deposited successfully.");
        } else {
            System.out.println("Enter a valid amount.");
        }
    }

    void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance = balance - amount;
            System.out.println("Please collect your cash.");
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    void checkBalance() {
        System.out.println("Your current balance is: " + balance);
    }
}

public class ATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = new BankAccount(1000, 5812);
        System.out.println("      Welcome to ATM      ");
        System.out.println("Please insert your card...");
        System.out.println("Loading...");
        System.out.println();
        System.out.print("Enter your 4-digit ATM PIN: ");
        int userPin = scanner.nextInt();

        if (account.validatePin(userPin)) {
            System.out.println("PIN verified. Access granted!");
            while (true) {
                System.out.println("\nATM MENU:");
                System.out.println("1. Deposit\n2. Withdraw\n3. Check Balance\n4. Exit");
                System.out.print("Enter choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter amount to deposit: ");
                        double deposit = scanner.nextDouble();
                        account.deposit(deposit);
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double withdraw = scanner.nextDouble();
                        account.withdraw(withdraw);
                        break;
                    case 3:
                        account.checkBalance();
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM.");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } else {
            System.out.println("Incorrect PIN. Access denied.");
        }

    }
}

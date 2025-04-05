import java.rmi.AlreadyBoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        /*Give a driver based implementation where action is decided based on the input received from the user.
        It should prompt the user to enter a choice (an integer value) based
        on which the program should allow the user to:
        1) Add new accounts details to the bank.
        2) Deposit money into an account.
        3) Withdraw money from an account.
        4) Search for the account with the given account number.
        5) Print the details all accounts in the bank.
        6) Exit */

        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank(10);   // Assuming a maximum of 10 accounts
        int choice;

        while(true){
            System.out.println("\nBank Management System");
            System.out.println("1. Add new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. Search for the account");
            System.out.println("5. Print the details of all accounts");
            System.out.println("6. Exit");

            System.out.println("Enter your choice : ");
            try{
                choice = sc.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("Invalid input, please enter a number between 1-6.");
                sc.next();    // Clear the invalid input
                continue;
            }

            switch(choice){
                case 1:
                    try{
                        System.out.print("Enter a 5-digit account number: ");
                        int accountNumber = sc.nextInt();
                        if(String.valueOf(accountNumber).length() != 5){
                            throw new Exception("Account number must be 5 digits.");
                        }

                        System.out.print("Enter the initial Balance = ");
                        double balance = sc.nextDouble();
                        if(balance < 0){
                            throw new Exception("Balance can't be negative.");
                        }
                        bank.addAccount(accountNumber, balance);   // May throw AccountAlreadyExistsException
                    }
                    catch(AccountAlreadyExistsException ac){
                        System.out.println("Error: " +ac.getMessage());
                    }
                    catch(Exception e){
                        System.out.println("Error: " +e.getMessage());
                        sc.nextLine();   // Clear the buffer
                    }
                    break;

                case 2:
                    try {
                        System.out.print("Enter account number: ");
                        int accountNumber = sc.nextInt();
                        BankAccount account = bank.searchAccount(accountNumber);
                        if (account != null) {
                            System.out.print("Enter amount to deposit : ");
                            double amount = sc.nextDouble();
                            account.deposit(amount);
                        } else {
                            System.out.println("Account not found");
                        }
                    }
                    catch (IllegalAccessException e){
                        System.out.println("Error: " +e.getMessage());
                    }
                    break;

                case 3:
                    try {
                        System.out.print("Enter account number: ");
                        int accountNumber = sc.nextInt();
                        BankAccount account = bank.searchAccount(accountNumber);
                        if (account != null) {
                            System.out.print("Enter amount to withdraw: ");
                            double amount = sc.nextDouble();
                            account.widthdraw(amount);
                        } else {
                            System.out.println("Account not found.");
                        }
                    }
                    catch (IllegalAccessException e){
                        System.out.println("Error: " +e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Enter account number: ");
                    int accountNumber = sc.nextInt();
                    BankAccount accounts = bank.searchAccount(accountNumber);
                    if (accounts != null) {
                        System.out.println(accounts.toString());
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 5:
                    System.out.println("Bank Accounts: ");
                    System.out.println(bank.toString());
                    break;

                case 6:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

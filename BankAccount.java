package CodSoft;

import java.util.Scanner;

//Class representing the user's bank account
class BankAccount {
 private double balance;

 // Constructor to initialize the account balance
 public BankAccount(double initialBalance) {
     this.balance = initialBalance;
 }

 // Method to deposit money
 public void deposit(double amount) {
     if (amount > 0) {
         balance += amount;
         System.out.println("Successfully deposited: $" + amount);
     } else {
         System.out.println("Invalid deposit amount!");
     }
 }

 // Method to withdraw money
 public void withdraw(double amount) {
     if (amount > 0 && amount <= balance) {
         balance -= amount;
         System.out.println("Successfully withdrew: $" + amount);
     } else if (amount > balance) {
         System.out.println("Insufficient balance!");
     } else {
         System.out.println("Invalid withdrawal amount!");
     }
 }

 // Method to check the account balance
 public double checkBalance() {
     return balance;
 }
}

//Class representing the ATM machine
class ATM {
 private BankAccount account;

 // Constructor to initialize the ATM with a BankAccount
 public ATM(BankAccount account) {
     this.account = account;
 }

 // Method to display the ATM interface and handle user actions
 public void start() {
     Scanner scanner = new Scanner(System.in);
     int option;

     do {
         System.out.println("\n===== ATM Menu =====");
         System.out.println("1. Check Balance");
         System.out.println("2. Deposit Money");
         System.out.println("3. Withdraw Money");
         System.out.println("4. Exit");
         System.out.print("Choose an option: ");
         option = scanner.nextInt();

         switch (option) {
             case 1:
                 System.out.println("Your current balance is: $" + account.checkBalance());
                 break;
             case 2:
                 System.out.print("Enter the amount to deposit: ");
                 double depositAmount = scanner.nextDouble();
                 account.deposit(depositAmount);
                 break;
             case 3:
                 System.out.print("Enter the amount to withdraw: ");
                 double withdrawAmount = scanner.nextDouble();
                 account.withdraw(withdrawAmount);
                 break;
             case 4:
                 System.out.println("Thank you for using the ATM. Goodbye!");
                 break;
             default:
                 System.out.println("Invalid option! Please try again.");
         }
     } while (option != 4);

     scanner.close();
 }
}



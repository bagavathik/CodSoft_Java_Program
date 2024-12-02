package CodSoft;

//Main class to run the ATM program
	public class AtmProgram {
	 public static void main(String[] args) {
	     // Create a BankAccount with an initial balance
	     BankAccount userAccount = new BankAccount(1000.0);

	     // Create an ATM linked to the user's account
	     ATM atm = new ATM(userAccount);

	     // Start the ATM interface
	     atm.start();
	 }
	}
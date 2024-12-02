package CodSoft;
import java.util.Random;
import java.util.Scanner;

	public class NumberGuessingGame {
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        Random random = new Random();

	        int numberOfRounds = 0;
	        int totalAttempts = 0;
	        int wins = 0;
	        String userChoice;

	        System.out.println("**************");
	        System.out.println("** WELCOME TO THE NUMBER GAME **");
	        System.out.println("**************");

	        do {
	            numberOfRounds++;
	            int numberToGuess = random.nextInt(100) + 1; // Random number between 1 and 100
	            int attempts = 0;
	            boolean isGuessed = false;

	            System.out.println("\nRound " + numberOfRounds);
	            System.out.println("I'm thinking of a number between 1 and 100. Can you guess it?");
	            System.out.println("You have 6 chances to get it right. Let's go!");

	            // User guessing loop
	            while (!isGuessed && attempts < 6) {
	                System.out.print("\nWhat's your guess? ");
	                int userGuess = scanner.nextInt();
	                attempts++;

	                if (userGuess < numberToGuess) {
	                    System.out.println("Oops, your guess is too low! Try a higher number.");
	                } else if (userGuess > numberToGuess) {
	                    System.out.println("Uh-oh, that's too high! Try a lower number.");
	                } else {
	                    System.out.println("\n🎉 Congratulations! 🎉");
	                    System.out.println("You've found the number in " + attempts + " attempts. Well done!");
	                    isGuessed = true;
	                    wins++;
	                }
	            }

	            // If the user didn't guess the number within 6 attempts
	            if (!isGuessed) {
	                System.out.println("\nBetter luck next time! The correct number was " + numberToGuess);
	            }

	            totalAttempts += attempts;

	            // Ask if the user wants to play again
	            System.out.print("\nDo you want to play another round? (yes/no): ");
	            userChoice = scanner.next();

	        } while (userChoice.equalsIgnoreCase("yes")); // Repeat if the user enters 'yes'

	        // Game over summary
	        System.out.println("\n**************");
	        System.out.println("** GAME OVER! THANK YOU FOR PLAYING! **");
	        System.out.println("**************");
	        System.out.println("Total rounds played: " + numberOfRounds);
	        System.out.println("Total number of attempts made: " + totalAttempts);
	        System.out.println("Total wins: " + wins);
	        System.out.println("\nHope to see you again soon! 👋");

	        scanner.close();
	    }
	}
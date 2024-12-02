package CodSoft;

import java.util.Scanner;

public class StudentGradeCalculator {
	
	    public static void main(String[] args) {
	    	
	        Scanner scanner = new Scanner(System.in);

	        // Input: Take marks for subjects
	        System.out.print("Enter the number of subjects: ");
	        int numOfSubjects = scanner.nextInt();
	        
	        int[] marks = new int[numOfSubjects];
	        int totalMarks = 0;

	        for (int i = 0; i < numOfSubjects; i++) {
	            System.out.print("Enter the marks obtained in subject " + (i + 1) + " (out of 100): ");
	            marks[i] = scanner.nextInt();
	            totalMarks += marks[i];
	        }

	        // Calculate the total marks
	        System.out.println("\nTotal Marks obtained: " + totalMarks);

	        // Calculate the average percentage
	        double averagePercentage = (double) totalMarks / numOfSubjects;
	        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);

	        // Determine grade
	        char grade;
	        if (averagePercentage >= 90) {
	            grade = 'A';
	        } else if (averagePercentage >= 75) {
	            grade = 'B';
	        } else if (averagePercentage >= 50) {
	            grade = 'C';
	        } else if (averagePercentage >= 35) {
	            grade = 'D';
	        } else {
	            grade = 'F';
	        }

	        // Display grade
	        System.out.println("Grade: " + grade);

	        // Close the scanner
	        scanner.close();
	    }
	}


package CodSoft;	

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

	public class QuizApplication {
		
	    static class Question {
	        String question;
	        String[] options;
	        String correctAnswer;

	        Question(String question, String[] options, String correctAnswer) {
	            this.question = question;
	            this.options = options;
	            this.correctAnswer = correctAnswer;
	        }
	    }
          // Quiz Questions and Options
	    static Question[] quizData = {
	        new Question("Which keyword is used to define a class in Java?",
	                new String[] {"class", "define", "public", "object"}, "class"),
	        new Question("Which method is the entry point of a Java program?",
	                new String[] {"start()", "run()", "main()", "init()"}, "main()"),
	        new Question("What is the extension of Java bytecode files?",
	                new String[] {".java", ".class", ".exe", ".jar"}, ".class"),
	        new Question("Which symbol is used for comments in Java?",
	                new String[] {"#", "//", "/* */", "// and /* */"}, "// and /* */"),
	        new Question("What is the result of 10 / 3 in Java?",
	                new String[] {"3.333", "3", "Error", "None of these"}, "3")
	    };
         // Set Timer to limit the time to answer
	    static class TimerHandler {
	        boolean timeUp = false;

	        public void startTimer(int seconds) {
	            Timer timer = new Timer();
	            timer.schedule(new TimerTask() {
	                public void run() {
	                    timeUp = true;
	                }
	            }, seconds * 1000);
	        }
	    }

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        int score = 0;
	        String[] userAnswers = new String[quizData.length];
	        TimerHandler timerHandler = new TimerHandler();

	        System.out.println("<<Welcome to the Java Quiz! Test your knowledge>>");

	        try {
	            Thread.sleep(2000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
            // Question Display to the user   
	        for (int i = 0; i < quizData.length; i++) {
	            Question currentQuestion = quizData[i];
	            System.out.println("\nQuestion " + (i + 1) + ": " + currentQuestion.question);

	            for (int j = 0; j < currentQuestion.options.length; j++) {
	                System.out.println((j + 1) + ". " + currentQuestion.options[j]);
	            }

	            timerHandler.timeUp = false;
	            timerHandler.startTimer(20);
	              // users to select an option and submit their answer within the given time
	            System.out.println("\nYou have 20 seconds to answer. Please enter the number of your choice.");
	            String userAnswer = null;
	            long startTime = System.currentTimeMillis();

	            while (!timerHandler.timeUp && (System.currentTimeMillis() - startTime) < 20000) {
	                if (scanner.hasNextLine()) {
	                    userAnswer = scanner.nextLine();
	                    break;
	                }
	            }

	            if (userAnswer == null || timerHandler.timeUp) {
	                System.out.println("Time's up! No answer provided.");
	                userAnswers[i] = null;
	            } else {
	                try {
	                    int optionIndex = Integer.parseInt(userAnswer) - 1;
	                    if (optionIndex >= 0 && optionIndex < currentQuestion.options.length) {
	                        userAnswers[i] = currentQuestion.options[optionIndex];
	                    } else {
	                        System.out.println("Invalid choice.");
	                        userAnswers[i] = null;
	                    }
	                } catch (NumberFormatException e) {
	                    System.out.println("Invalid input. Please enter a number.");
	                    userAnswers[i] = null;
	                }
	            }

	            if (userAnswers[i] != null && userAnswers[i].equals(currentQuestion.correctAnswer)) {
	                score++;
	            }
	        }
            // Score Calculation
	        System.out.println("\nQuiz Completed!");
	        System.out.println("Your final score: " + score + "/" + quizData.length);

	        for (int i = 0; i < quizData.length; i++) {
	            String correctAnswer = quizData[i].correctAnswer;
	            String userAnswer = userAnswers[i];
	            String result = (userAnswer != null && userAnswer.equals(correctAnswer)) ?
	                    "Correct" : "Incorrect";
	            if (userAnswer == null) {
	                result = "No Answer (Time's up)";
	            }
	            System.out.println("Question " + (i + 1) + ": " + result +
	                    " (Your answer: " + (userAnswer != null ? userAnswer : "No Answer") + ")");
	        }

	        scanner.close();
	    }
	}

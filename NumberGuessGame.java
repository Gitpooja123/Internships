//Task--->1
//#Number Guessing Game

import java.util.Scanner;
import java.util.Random;

public class NumberGuessGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();

        int score = 0;
        boolean keepPlaying = true;

        System.out.println(" Hello POOJA RATHORE here !!\nWelcome to the Number Guessing Game! Ready to test your luck and logic...");

        while (keepPlaying) {
            int target = random.nextInt(100) + 1;
           System.out.println("( DEBUG: Computer chose → " + target + ")");
            int chances = 5;
            boolean guessed = false;

            System.out.println("\n________I've picked a number between 1 and 100.__________");
            System.out.println("You have " + chances + " chances to guess it!");

            for (int attempt = 1; attempt <= chances; attempt++) {
                System.out.print("Enter guess #" + attempt + ": ");
                int guess = input.nextInt();

                if (guess == target) {
                    System.out.println("Congratulations! You guessed it right!");
                    guessed = true;
                    score++;
                    break;
                } else if (guess < target) {
                    System.out.println("You guess low!");
                } else {
                    System.out.println("You guess high!");
                }
            }

            if (!guessed) {
                System.out.println("Oops! Out of attempts. The number was: " + target);
            }

            System.out.println("Wanna play again? (yes/no): ");
            input.nextLine(); // for Clear buffer
            String response = input.nextLine().trim().toLowerCase();

            keepPlaying = response.equals("yes") || response.equals("y");
        }

        System.out.println("\nGame Over! Your final score: " + score);
        System.out.println("Thanks for playing. Come back soon!");

        input.close();

    }
}

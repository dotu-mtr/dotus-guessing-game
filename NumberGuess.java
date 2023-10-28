/*
 * AP Comp Sci A, p1, 10/27/23
 * This program will execute a "Guess the Number" game.
 * @author dotu_mtr
 * @version Oct 27 2023
*/

import java.util.*;

public class NumberGuess {

   // These are variables that will be accessed throughout the program.
   public static int min, max, attempts, winner;
   public static String name = "Dee";
   public static Scanner keyboard = new Scanner(System.in);
   
   /* This method will give a random int value to the 'winner' variable,
    * based off of the minimum and maximum variable that was inputted before.
    */
   
   public static void getWinner() {
      winner = (int) (Math.random() * (max - min) + 1 + min);
   }
   
   /* This method takes a scanner input, and checks if it is an int.
    * If it is an int, it returns that value.
    * If it isn't an int, it prompts the user to try again.
    * 
    * @param Scanner in
    * @return int
    */
   
   public static int getInt(Scanner in) {
      String temp;
      //System.out.println("Please enter a valid integer.");
      boolean isInt = true;
      while (true) {
         if (in.hasNextInt()) {
            temp = in.nextLine();
            for (int i = 0; i < temp.length(); i++) {
               // prohibits entering multiple tokens at a time
               if (Character.isWhitespace(temp.charAt(i))) {isInt = false;}
            }
            if (isInt) {
               Integer temp2 = Integer.valueOf(temp);
               return temp2;
            } else {
               System.out.println("This is not a valid integer. Please try again.");
            }
         } else {
            System.out.println("This is not a valid integer. Please try again.");
         }
         in.nextLine();
      }
   }
   
   /* This method gives values to the variables that are necessary to play the game.
    * @param Scanner in
    */
   
   public static void initVars(Scanner in) {
      int temp;
      
      System.out.println("Please type your name and then press enter:");
      name = in.nextLine();
      
      System.out.println("Welcome " + name + "!");
      boolean g = false;
      while (!g) {
         System.out.println("Please enter how many attempts you would like to have.");
         System.out.println("This number must be greater than zero.");
         temp = getInt(in);
         if (temp > 0) {
            attempts = temp;
            g = true;
         } else {
            System.out.println("This number is not greater than zero.");
            System.out.println("Please try again.");
         }
      }
      
      
      System.out.println("Please enter the smallest number you would like to guess from.");
      min = getInt(in);
      
      g = false;
      while (!g) {
         System.out.println("Please enter the largest number you would like to guess from.");
         System.out.println("This number must be greater than the smallest number.");
         temp = getInt(in);
         if (temp > min) {
            max = temp;
            g = true;
         } else {
            System.out.println("This number is not greater than the smallest number.");
            System.out.println("Please try again.");
         }
      }
      System.out.println("Thank you for your answers! We will now commence the game.");
   }
   
   /* This method lets the user guess a number within the min-max range.
    * @param Scanner in
    */
   
   public static void guessing(Scanner in) {
      getWinner();
      int guess, triesLeft = attempts;
      boolean notDone = true;
      System.out.println("Guess an integer between " + min + " and " + max + ".");
      while (notDone) {
         guess = getInt(in);
         if (guess >= min && guess <= max) {
            triesLeft--;
            if (guess < winner) {
               System.out.println("Too low!");
            } else if (guess > winner) {
               System.out.println("Too high!");
            } else if (guess == winner) {
               System.out.println("Congartion! You guessed the number with " 
               + triesLeft + " guess(es) left!");
               notDone = false;
            } 
            if (triesLeft == 0) {
               System.out.println("Game over! The number was " + winner);
               notDone = false;
            } else if (notDone) {
               System.out.println(triesLeft + " left!");
            }
         } else {
            System.out.println("Your integer is out of range. Please try again.");
         }
      }
   }
   
   /* This method lets the user choose if they want to play the game again.
    * @param Scanner in
    */
   
   public static void ending(Scanner in) {
      boolean a = true, b = true;
      char cart;
      System.out.println("Would you like to play the game again? (Y/N)");
      while (a) {
         cart = in.next().toLowerCase().charAt(0);
         if (cart == 'y') {
            System.out.println("Awesome! Would you like to change the settings of the game? (Y/N)");
            while (b) {
               cart = in.next().toLowerCase().charAt(0);
               if (cart == 'y') {
                  game(in);
                  a = false;
                  b = false;
               } else if (cart == 'n') {
                  guessing(in);
                  ending(in);
                  a = false;
                  b = false;
               } else {
                  System.out.println("Invalid answer, please try again.");
               }
            }
         } else if (cart == 'n') {
            System.out.println("Game exited.");
            a = false;
         } else {
            System.out.println("Invalid answer, try again.");
         }
      }
   }
  
   /* This method lets the user play the number guessing game.
    * @param Scanner in
    */
   
   public static void game(Scanner in) {
      initVars(in);
      guessing(in);
      ending(in);
   }
   
   public static void main(String[] args) {
      game(keyboard); // Yay game time
      // ending(keyboard); 
   }

}
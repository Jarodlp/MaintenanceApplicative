package trivia;

import java.util.Random;
import java.util.Scanner;

// DON'T TOUCH THIS CLASS. DON'T REFACTOR THIS CLASS.
// ONLY RUN IT TO MANUALLY PLAY THE GAME YOURSELF TO UNDERSTAND THE PROBLEM
public class PlayGame {

   private static final Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) {

      System.out.println("*** Welcome to Trivia Game ***\n");
      System.out.println("Enter number of players: 1-6");
      int playerCount = Integer.parseInt(scanner.nextLine());
      if (playerCount < 2 || playerCount > 6) throw new IllegalArgumentException("No player 2..6");
      System.out.println("Reading names for " + playerCount + " players:");

      Game aGame = new Game();

      for (int i = 1; i <= playerCount; i++) {
         System.out.print("Player "+i+" name: ");
         String playerName = scanner.nextLine();
         aGame.add(playerName);
      }

      System.out.println("\n\n--Starting game--");


      boolean notAWinner = true;
      do {
         int roll = readRoll();
         aGame.roll(roll);

         if (!aGame.players.get(aGame.currentPlayer).inPenaltyBox) {

            System.out.print(">> Was the answer correct? [y/n] ");
            boolean correct = readYesNo();
            if (correct) {
               notAWinner = aGame.correctAnswer();
            } else {
               notAWinner = aGame.wrongAnswer();
            }
         }

      } while (notAWinner);
      System.out.println(">> Game won!");
   }

   private static boolean readYesNo() {
      String yn = scanner.nextLine().trim().toUpperCase();
      if (!yn.matches("[YN]")) {
         System.err.println("y or n please");
         return readYesNo();
      }
      return yn.equalsIgnoreCase("Y");
   }

   private static int readRoll() {
      System.out.print(">> Throw a dice and input roll, or [ENTER] to generate a random roll: ");
      String rollStr = scanner.nextLine().trim();
      if (rollStr.isEmpty()) {
         int roll = new Random().nextInt(6) + 1;
         System.out.println(">> Random roll: " + roll);
         return roll;
      }
      if (!rollStr.matches("\\d+")) {
         System.err.println("Not a number: '" + rollStr + "'");
         return readRoll();
      }
      int roll = Integer.parseInt(rollStr);
      if (roll < 1 || roll > 6) {
         System.err.println("Invalid roll");
         return readRoll();
      }
      return roll;
   }
}

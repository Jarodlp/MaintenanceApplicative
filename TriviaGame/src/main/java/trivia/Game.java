package trivia;

import java.util.ArrayList;
import java.util.LinkedList;

// REFACTOR ME
public class Game implements IGame {
    ArrayList<Player> players = new ArrayList<Player>();

    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public Game() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast(createRockQuestion(i));
        }
    }

    public String createRockQuestion(int index) {
        return "Rock Question " + index;
    }

    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    public boolean add(String playerName) {
        players.add(new Player(playerName));

        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        Player currentPlayer = players.get(this.currentPlayer);
        System.out.println(currentPlayer + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (currentPlayer.inPenaltyBox) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                System.out.println(currentPlayer + " is getting out of the penalty box");
                currentPlayer.place += roll;
                if (currentPlayer.place > 12) currentPlayer.place -= 12;

                System.out.println(currentPlayer
                        + "'s new location is "
                        + currentPlayer.place);
                System.out.println("The category is " + currentCategory());
                askQuestion();
            } else {
                System.out.println(currentPlayer + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            currentPlayer.place += roll;
            if (currentPlayer.place > 12) currentPlayer.place -= 12;

            System.out.println(currentPlayer
                    + "'s new location is "
                    + currentPlayer.place);
            System.out.println("The category is " + currentCategory());
            askQuestion();
        }

    }

    private void askQuestion() {
        switch (currentCategory()) {
            case "Pop":
                System.out.println(popQuestions.removeFirst());
                break;
            case "Science":
                System.out.println(scienceQuestions.removeFirst());
                break;
            case "Sports":
                System.out.println(sportsQuestions.removeFirst());
                break;
            case "Rock":
                System.out.println(rockQuestions.removeFirst());
                break;
        }
    }


    private String currentCategory() {
        Player currentPlayer = players.get(this.currentPlayer);
        if (currentPlayer.place == 1 || currentPlayer.place == 5 || currentPlayer.place == 9) {
            return "Pop";
        }
        if (currentPlayer.place == 2 || currentPlayer.place == 6 || currentPlayer.place == 10) {
            return "Science";
        }
        if (currentPlayer.place == 3 || currentPlayer.place == 7 || currentPlayer.place == 11) {
            return "Sports";
        }
        return "Rock";
    }

    public boolean handleCorrectAnswer() {
        Player currentPlayer = players.get(this.currentPlayer);
        if (currentPlayer.inPenaltyBox) {
            if (isGettingOutOfPenaltyBox) {
                System.out.println("Answer was correct!!!!");
                currentPlayer.purse++;
                System.out.println(currentPlayer
                        + " now has "
                        + currentPlayer.purse
                        + " Gold Coins.");

                boolean winner = didPlayerWin();
                this.currentPlayer++;
                if (this.currentPlayer == players.size()) this.currentPlayer = 0;

                return winner;
            } else {
                this.currentPlayer++;
                if (this.currentPlayer == players.size()) this.currentPlayer = 0;
                return true;
            }


        } else {

            System.out.println("Answer was correct!!!!");
            currentPlayer.purse++;
            System.out.println(currentPlayer
                    + " now has "
                    + currentPlayer.purse
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            this.currentPlayer++;
            if (this.currentPlayer == players.size()) this.currentPlayer = 0;

            return winner;
        }
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
        players.get(currentPlayer).inPenaltyBox = true;

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return true;
    }


    private boolean didPlayerWin() {
        return !(players.get(currentPlayer).purse == 6);
    }
}

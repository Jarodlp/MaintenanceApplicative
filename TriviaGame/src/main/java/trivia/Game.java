package trivia;

import java.util.ArrayList;

// REFACTOR ME
public class Game implements IGame {
    ArrayList<Player> players = new ArrayList<Player>();
    Questions questions = new Questions();
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

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
            if (roll % 2 == 0) {
                System.out.println(currentPlayer + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
                return;
            }
            System.out.println(currentPlayer + " is getting out of the penalty box");
            isGettingOutOfPenaltyBox = true;
        }

        currentPlayer.place += roll;
        if (currentPlayer.place > 12) currentPlayer.place -= 12;

        System.out.println(currentPlayer
                + "'s new location is "
                + currentPlayer.place);
        System.out.println("The category is " + currentCategory());
        questions.getNextQuestion(currentCategory());
    }


    private String currentCategory() {
        Player currentPlayer = players.get(this.currentPlayer);
        if (currentPlayer.place % 4 == 1) {
            return "Pop";
        }
        if (currentPlayer.place % 4 == 2) {
            return "Science";
        }
        if (currentPlayer.place % 4 == 3) {
            return "Sports";
        }
        return "Rock";
    }

    public boolean handleCorrectAnswer() {
        Player currentPlayer = players.get(this.currentPlayer);
        if (isGettingOutOfPenaltyBox || !currentPlayer.inPenaltyBox) {
            System.out.println("Answer was correct!!!!");
            currentPlayer.purse++;
            System.out.println(currentPlayer
                    + " now has "
                    + currentPlayer.purse
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            joueurSuivant();

            return winner;
        } else {
            joueurSuivant();
            return true;
        }
    }

    public boolean wrongAnswer() {
        System.out.println("Question was incorrectly answered");
        System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
        players.get(currentPlayer).inPenaltyBox = true;

        joueurSuivant();
        return true;
    }

    public void joueurSuivant() {
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
    }


    private boolean didPlayerWin() {
        return !(players.get(currentPlayer).purse == 6);
    }
}
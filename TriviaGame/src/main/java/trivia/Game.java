package trivia;

import java.util.ArrayList;

// REFACTOR ME
public class Game implements IGame {
    public static final int nbCasesPlateau = 12;
    ArrayList<Player> players = new ArrayList<>();
    Questions questions = new Questions();
    int currentPlayer = 0;

    public boolean isPlayable() {
        return (howManyPlayers() >= 2) && (howManyPlayers() <= 6);
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
                return;
            }
            System.out.println(currentPlayer + " is getting out of the penalty box");
            currentPlayer.inPenaltyBox = false;
        }

        currentPlayer.place += roll;
        if (currentPlayer.place > nbCasesPlateau) currentPlayer.place -= nbCasesPlateau;

        System.out.println(currentPlayer
                + "'s new location is "
                + currentPlayer.place);
        System.out.println("The category is " + currentCategory());
        questions.getNextQuestion(currentCategory());
    }


    private String currentCategory() {
        return Questions.CATEGORIES.get(players.get(currentPlayer).place % Questions.CATEGORIES.size());
    }

    public boolean correctAnswer() {
        Player currentPlayer = players.get(this.currentPlayer);
        if (!currentPlayer.inPenaltyBox) {
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
        Player currentPlayer = players.get(this.currentPlayer);
        System.out.println("Question was incorrectly answered");
        System.out.println(currentPlayer + " was sent to the penalty box");
        currentPlayer.inPenaltyBox = true;

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
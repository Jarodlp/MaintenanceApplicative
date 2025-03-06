package trivia;

public class Player {
    public final String name;
    public int place;
    public int purse;
    public boolean inPenaltyBox;

    public Player(String name) {
        this.name = name;
        this.place = 1;
        this.purse = 0;
        this.inPenaltyBox = false;
    }

    @Override
    public String toString() {
        return name;
    }
}
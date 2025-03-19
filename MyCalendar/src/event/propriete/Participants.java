package event.propriete;

public record Participants(String participants) {

    @Override
    public String toString() {
        return participants;
    }
}

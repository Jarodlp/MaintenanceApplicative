package event.propriete;

public record Title (String title) {

    @Override
    public String toString() {
        return title;
    }
}

package event.propriete;

public record DureeMinutes(int dureeMinutes) {

    @Override
    public String toString() {
        return dureeMinutes + "";
    }
}

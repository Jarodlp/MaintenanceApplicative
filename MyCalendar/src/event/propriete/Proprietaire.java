package event.propriete;

public record Proprietaire(String proprietaire) {

    @Override
    public String toString() {
        return proprietaire;
    }
}

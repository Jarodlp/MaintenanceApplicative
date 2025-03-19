package event.propriete;

public record FrequenceJours(int frequenceJours) {

    @Override
    public String toString() {
        return frequenceJours + "";
    }
}
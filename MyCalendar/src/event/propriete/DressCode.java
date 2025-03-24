package event.propriete;

public record DressCode(String dresscode) {
    @Override
    public String toString() {
        return dresscode;
    }
}
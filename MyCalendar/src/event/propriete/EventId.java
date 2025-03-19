package event.propriete;
public class EventId {

    public int eventId;

    public EventId() {
        // On crée un identifiant aléatoire à 6 chiffres
        this.eventId = (int) Math.round(Math.random()*1000000);
    }
}

package event;

import event.propriete.Lieu;
import event.propriete.Participants;

import java.time.LocalDateTime;
public class Reunion extends Event {

    public Lieu lieu;
    public Participants participants;

    public Reunion(String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes, String lieu, String participants) {
        super(title, proprietaire, dateDebut, dureeMinutes);
        this.lieu = new Lieu(lieu);
        this.participants = new Participants(participants);
    }

    @Override
    public String description() {
        return "Réunion : " + title + " à " + lieu + " avec " + participants;
    }
}

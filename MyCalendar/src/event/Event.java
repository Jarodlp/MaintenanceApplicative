package event;

import event.propriete.*;

import java.time.LocalDateTime;

public abstract class Event {
    public EventId eventId;
    public Title title;
    public Proprietaire proprietaire;
    public LocalDateTime dateDebut;
    public DureeMinutes dureeMinutes;

    public Event(String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes) {
        this.eventId = new EventId();
        this.title = new Title(title);
        this.proprietaire = new Proprietaire(proprietaire);
        this.dateDebut = dateDebut;
        this.dureeMinutes = new DureeMinutes(dureeMinutes);
    }

    public abstract String description();
}
package event;

import event.propriete.DressCode;
import event.propriete.Lieu;

import java.time.LocalDateTime;

public class Fete extends Event{
    public Lieu lieu;
    public DressCode dresscode;
    public Fete(String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes, String lieu, String dresscode) {
        super(title, proprietaire, dateDebut, dureeMinutes);
        this.lieu = new Lieu(lieu);
        this.dresscode = new DressCode(dresscode);
    }

    @Override
    public String description() {
        return "Fête : " + title + " à " + lieu + " le " + dateDebut + " avec dresscode : " + dresscode;
    }
}

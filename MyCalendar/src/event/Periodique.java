package event;

import event.propriete.FrequenceJours;

import java.time.LocalDateTime;

public class Periodique extends Event {
    public FrequenceJours frequenceJours;
    public Periodique(String title, String proprietaire, LocalDateTime dateDebut, int dureeMinutes, int frequenceJours) {
        super(title, proprietaire, dateDebut, dureeMinutes);
        this.frequenceJours = new FrequenceJours(frequenceJours);
    }

    @Override
    public boolean eventDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        LocalDateTime temp = this.dateDebut;
        while (temp.isBefore(fin)) {
            if (!temp.isBefore(debut)) {
                return true;
            }
            temp = temp.plusDays(this.frequenceJours.frequenceJours());
        }
        return false;
    }

    @Override
    public String description() {
        return "Événement périodique : " + title + " tous les " + frequenceJours + " jours";
    }
}

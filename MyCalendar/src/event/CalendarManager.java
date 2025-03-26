package event;

import java.time.LocalDateTime;
import java.util.List;

public class CalendarManager {
    public Evenements events;

    public CalendarManager() {
        this.events = new Evenements();
    }

    public boolean ajouterEvent(Event e) {
        // On détecte automatiquement les conflits quand on ajoute un nouvel événement
        if (events.stream().noneMatch(event -> conflit(event, e))) {
            events.add(e);
            return true;
        }
        return false;
    }

    public List<Event> eventsDansPeriode(LocalDateTime debut, LocalDateTime fin) {
        List<Event> result = new Evenements();
        for (Event e : events) {
            if (e.eventDansPeriode(debut, fin)) {
                result.add(e);
            }
        }
        return result;
    }

    public boolean conflit(Event e1, Event e2) {
        LocalDateTime fin1 = e1.dateDebut.plusMinutes(e1.dureeMinutes.dureeMinutes());
        LocalDateTime fin2 = e2.dateDebut.plusMinutes(e2.dureeMinutes.dureeMinutes());

        if (e1.getClass() == Periodique.class && e2.getClass() == Periodique.class) {
            return false; // Simplification abusive
        }

        return e1.dateDebut.isBefore(fin2) && fin1.isAfter(e2.dateDebut);
    }

    public void afficherEvenements() {
        for (Event e : events) {
            System.out.println(e.description());
        }
    }
}
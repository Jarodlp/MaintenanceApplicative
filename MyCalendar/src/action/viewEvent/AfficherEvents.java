package action.viewEvent;

import action.Action;
import action.VisualisateurEvenements;
import event.Event;

import java.util.List;

public class AfficherEvents implements Action {

    public VisualisateurEvenements visualisateurEvenements;

    public AfficherEvents(VisualisateurEvenements ve) {
        this.visualisateurEvenements = ve;
    }
    @Override
    public boolean execute() {
        printEvents();
        return true;
    }

    public void printEvents(){
        this.visualisateurEvenements.calendar.afficherEvenements();
    }

    protected static void afficherListe(List<Event> evenements) {
        if (evenements.isEmpty()) {
            System.out.println("Aucun événement trouvé pour cette période.");
        } else {
            System.out.println("Événements trouvés : ");
            for (Event e : evenements) {
                System.out.println("- " + e.description());
            }
        }
    }
}

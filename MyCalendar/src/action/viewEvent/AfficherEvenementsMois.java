package action.viewEvent;

import action.VisualisateurEvenements;

import java.time.LocalDateTime;

public class AfficherEvenementsMois extends AfficherEvents{
    public AfficherEvenementsMois(VisualisateurEvenements ve) {
        super(ve);
    }

    @Override
    public void printEvents(){
        System.out.print("Entrez l'année (AAAA) : ");
        int anneeMois = Integer.parseInt(visualisateurEvenements.scanner.nextLine());
        System.out.print("Entrez le mois (1-12) : ");
        int mois = Integer.parseInt(visualisateurEvenements.scanner.nextLine());

        LocalDateTime debutMois = LocalDateTime.of(anneeMois, mois, 1, 0, 0);
        LocalDateTime finMois = debutMois.plusMonths(1).minusSeconds(1);

        afficherListe(visualisateurEvenements.calendar.eventsDansPeriode(debutMois, finMois));
    }
}

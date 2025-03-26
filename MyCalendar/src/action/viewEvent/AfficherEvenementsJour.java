package action.viewEvent;

import action.VisualisateurEvenements;

import java.time.LocalDateTime;

public class AfficherEvenementsJour extends AfficherEvents{
    public AfficherEvenementsJour(VisualisateurEvenements ve) {
        super(ve);
    }

    @Override
    public void printEvents(){
        System.out.print("Entrez l'année (AAAA) : ");
        int anneeJour = Integer.parseInt(visualisateurEvenements.scanner.nextLine());
        System.out.print("Entrez le mois (1-12) : ");
        int moisJour = Integer.parseInt(visualisateurEvenements.scanner.nextLine());
        System.out.print("Entrez le jour (1-31) : ");
        int jour = Integer.parseInt(visualisateurEvenements.scanner.nextLine());

        LocalDateTime debutJour = LocalDateTime.of(anneeJour, moisJour, jour, 0, 0);
        LocalDateTime finJour = debutJour.plusDays(1).minusSeconds(1);

        afficherListe(visualisateurEvenements.calendar.eventsDansPeriode(debutJour, finJour));
    }
}

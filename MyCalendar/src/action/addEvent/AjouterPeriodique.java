package action.addEvent;

import action.GestionnaireEvenements;
import event.Periodique;

import java.time.LocalDateTime;

public class AjouterPeriodique extends AjouterEvent {
    public AjouterPeriodique(GestionnaireEvenements ge) {
        super(ge);
    }

    @Override
    public boolean creerEvent(String titre, LocalDateTime dateDebut, int duree) {
        System.out.print("Frequence (en jours) : ");
        int frequence = Integer.parseInt(gestionnaireEvenements.scanner.nextLine());

        return gestionnaireEvenements.calendar.ajouterEvent(
                new Periodique(titre, gestionnaireEvenements.utilisateurCourant.nom, dateDebut, duree, frequence));
    }
}

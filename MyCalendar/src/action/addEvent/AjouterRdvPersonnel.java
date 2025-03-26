package action.addEvent;

import action.GestionnaireEvenements;
import event.RdvPersonnel;

import java.time.LocalDateTime;

public class AjouterRdvPersonnel extends AjouterEvent {
    public AjouterRdvPersonnel(GestionnaireEvenements ge) {
        super(ge);
    }

    @Override
    public boolean creerEvent(String titre, LocalDateTime dateDebut, int duree) {
        return gestionnaireEvenements.calendar.ajouterEvent(
                new RdvPersonnel(titre, gestionnaireEvenements.utilisateurCourant.nom, dateDebut, duree));
    }
}

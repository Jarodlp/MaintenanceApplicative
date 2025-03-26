package action.addEvent;

import action.GestionnaireEvenements;
import event.Fete;

import java.time.LocalDateTime;

public class AjouterFete extends AjouterEvent {
    public AjouterFete(GestionnaireEvenements ge) {
        super(ge);
    }

    @Override
    public boolean creerEvent(String titre, LocalDateTime dateDebut, int duree) {
        System.out.println("Lieu :");
        String lieu = gestionnaireEvenements.scanner.nextLine();

        System.out.println("Dresscode :");
        String dresscode = gestionnaireEvenements.scanner.nextLine();

        return gestionnaireEvenements.calendar.ajouterEvent(
                new Fete(titre, gestionnaireEvenements.utilisateurCourant.nom, dateDebut, duree, lieu, dresscode));
    }
}

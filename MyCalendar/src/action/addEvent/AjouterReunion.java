package action.addEvent;

import action.GestionnaireEvenements;
import event.Reunion;

import java.time.LocalDateTime;

public class AjouterReunion extends AjouterEvent {
    public AjouterReunion(GestionnaireEvenements ge) {
        super(ge);
    }

    @Override
    public boolean creerEvent(String titre, LocalDateTime dateDebut, int duree) {
        System.out.println("Lieu :");
        String lieu = gestionnaireEvenements.scanner.nextLine();

        StringBuilder participants = new StringBuilder(gestionnaireEvenements.utilisateurCourant.nom);

        System.out.println("Ajouter un participant ? (oui / non)");
        while (gestionnaireEvenements.scanner.nextLine().equals("oui")) {
            System.out.print("Participants : " + participants + ", ");
            participants.append(", ").append(gestionnaireEvenements.scanner.nextLine());
            System.out.println("Ajouter un participant ? (oui / non)");
        }

        return gestionnaireEvenements.calendar.ajouterEvent(
                new Reunion(titre, gestionnaireEvenements.utilisateurCourant.nom, dateDebut, duree, lieu, lieu));
    }
}

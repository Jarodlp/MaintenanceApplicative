package action.addEvent;

import action.Action;
import action.GestionnaireEvenements;

import java.time.LocalDateTime;

public abstract class AjouterEvent implements Action {

    public GestionnaireEvenements gestionnaireEvenements;

    public AjouterEvent(GestionnaireEvenements ge) {
        this.gestionnaireEvenements = ge;
    }

    @Override
    public boolean execute() {
        System.out.print("Titre de l'événement : ");
        String titre = gestionnaireEvenements.scanner.nextLine();
        System.out.print("Année (AAAA) : ");
        int annee = Integer.parseInt(gestionnaireEvenements.scanner.nextLine());
        System.out.print("Mois (1-12) : ");
        int moisRdv = Integer.parseInt(gestionnaireEvenements.scanner.nextLine());
        System.out.print("Jour (1-31) : ");
        int jourRdv = Integer.parseInt(gestionnaireEvenements.scanner.nextLine());
        System.out.print("Heure début (0-23) : ");
        int heure = Integer.parseInt(gestionnaireEvenements.scanner.nextLine());
        System.out.print("Minute début (0-59) : ");
        int minute = Integer.parseInt(gestionnaireEvenements.scanner.nextLine());
        System.out.print("Durée (en minutes) : ");
        int duree = Integer.parseInt(gestionnaireEvenements.scanner.nextLine());

        LocalDateTime dateDebut = LocalDateTime.of(annee, moisRdv, jourRdv, heure, minute);

        boolean bool = creerEvent(titre, dateDebut, duree);
        if (bool) {
            System.out.println("Événement ajouté avec succès !");
        } else {
            System.out.println("Événement non ajouté : conflit avec un autre événement");
        }
        return bool;
    }

    public abstract boolean creerEvent(String titre, LocalDateTime dateDebut, int duree);
}

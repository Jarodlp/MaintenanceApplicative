package action;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import action.addEvent.AjouterFete;
import action.addEvent.AjouterPeriodique;
import action.addEvent.AjouterRdvPersonnel;
import action.addEvent.AjouterReunion;
import action.auth.Utilisateur;
import event.CalendarManager;

public class GestionnaireEvenements implements Action{

    public final Scanner scanner = new Scanner(System.in);
    public Utilisateur utilisateurCourant;

    public CalendarManager calendar = new CalendarManager();

    public GestionnaireEvenements(Utilisateur utilisateur) {
        this.utilisateurCourant = utilisateur;
    }

    @Override
    public boolean execute() {

        System.out.println("\nBonjour, " + this.utilisateurCourant);
        System.out.println("=== Menu Gestionnaire d'Événements ===");
        System.out.println("1 - Voir les événements");
        System.out.println("2 - Ajouter un rendez-vous perso");
        System.out.println("3 - Ajouter une réunion");
        System.out.println("4 - Ajouter un évènement périodique");
        System.out.println("5 - Ajouter une fête");
        System.out.println("6 - Se déconnecter");
        System.out.print("Votre choix : ");

        int choix = Integer.parseInt(scanner.nextLine());

        List<Action> actions = new ArrayList<>(){{
            add(new VisualisateurEvenements(calendar, scanner));
            add(new AjouterRdvPersonnel(GestionnaireEvenements.this));
            add(new AjouterReunion(GestionnaireEvenements.this));
            add(new AjouterPeriodique(GestionnaireEvenements.this));
            add(new AjouterFete(GestionnaireEvenements.this));
            add(new Deconnexion());
        }};

        actions.get(choix - 1).execute();

        return execute();
    }
}

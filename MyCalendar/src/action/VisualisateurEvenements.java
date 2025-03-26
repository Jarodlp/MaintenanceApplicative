package action;

import action.viewEvent.*;
import event.CalendarManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VisualisateurEvenements implements Action{

    public CalendarManager calendar;

    public Scanner scanner;

    public VisualisateurEvenements(CalendarManager calendar, Scanner scanner) {
        this.calendar = calendar;
        this.scanner = scanner;
    }
    @Override
    public boolean execute() {

        System.out.println("\n=== Menu de visualisation d'Événements ===");
        System.out.println("1 - Afficher TOUS les événements");
        System.out.println("2 - Afficher les événements d'un MOIS précis");
        System.out.println("3 - Afficher les événements d'une SEMAINE précise");
        System.out.println("4 - Afficher les événements d'un JOUR précis");
        System.out.println("5 - Retour");
        System.out.print("Votre choix : ");

        int choix = Integer.parseInt(scanner.nextLine());

        List<Action> actions = new ArrayList<>(){{
            add(new AfficherEvents(VisualisateurEvenements.this));
            add(new AfficherEvenementsMois(VisualisateurEvenements.this));
            add(new AfficherEvenementsSemaine(VisualisateurEvenements.this));
            add(new AfficherEvenementsJour(VisualisateurEvenements.this));
            add(new Retour());
        }};

        boolean bool = actions.get(choix - 1).execute();

        if (bool){
            return execute();
        } else {
            return false;
        }
    }
}

import action.Auth;
import action.ShowLogo;
import event.Event;
import event.Periodique;
import event.RdvPersonnel;
import event.Reunion;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.*;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        CalendarManager calendar = new CalendarManager();

        new ShowLogo().execute();

        Auth auth = new Auth();

        boolean bool = false;
        while (!bool) {
            bool = auth.execute();
        }

        while (!auth.utilisateurCourant.nom.equals("")) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nBonjour, " + auth.utilisateurCourant);
            System.out.println("=== Menu Gestionnaire d'Événements ===");
            System.out.println("1 - Voir les événements");
            System.out.println("2 - Ajouter un rendez-vous perso");
            System.out.println("3 - Ajouter une réunion");
            System.out.println("4 - Ajouter un évènement périodique");
            System.out.println("5 - Se déconnecter");
            System.out.print("Votre choix : ");

            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    System.out.println("\n=== Menu de visualisation d'Événements ===");
                    System.out.println("1 - Afficher TOUS les événements");
                    System.out.println("2 - Afficher les événements d'un MOIS précis");
                    System.out.println("3 - Afficher les événements d'une SEMAINE précise");
                    System.out.println("4 - Afficher les événements d'un JOUR précis");
                    System.out.println("5 - Retour");
                    System.out.print("Votre choix : ");

                    choix = scanner.nextLine();

                    switch (choix) {
                        case "1":
                            calendar.afficherEvenements();
                            break;

                        case "2":
                            System.out.print("Entrez l'année (AAAA) : ");
                            int anneeMois = Integer.parseInt(scanner.nextLine());
                            System.out.print("Entrez le mois (1-12) : ");
                            int mois = Integer.parseInt(scanner.nextLine());

                            LocalDateTime debutMois = LocalDateTime.of(anneeMois, mois, 1, 0, 0);
                            LocalDateTime finMois = debutMois.plusMonths(1).minusSeconds(1);

                            afficherListe(calendar.eventsDansPeriode(debutMois, finMois));
                            break;

                        case "3":
                            System.out.print("Entrez l'année (AAAA) : ");
                            int anneeSemaine = Integer.parseInt(scanner.nextLine());
                            System.out.print("Entrez le numéro de semaine (1-52) : ");
                            int semaine = Integer.parseInt(scanner.nextLine());

                            LocalDateTime debutSemaine = LocalDateTime.now()
                                    .withYear(anneeSemaine)
                                    .with(WeekFields.of(Locale.FRANCE).weekOfYear(), semaine)
                                    .with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1)
                                    .withHour(0).withMinute(0);
                            LocalDateTime finSemaine = debutSemaine.plusDays(7).minusSeconds(1);

                            afficherListe(calendar.eventsDansPeriode(debutSemaine, finSemaine));
                            break;

                        case "4":
                            System.out.print("Entrez l'année (AAAA) : ");
                            int anneeJour = Integer.parseInt(scanner.nextLine());
                            System.out.print("Entrez le mois (1-12) : ");
                            int moisJour = Integer.parseInt(scanner.nextLine());
                            System.out.print("Entrez le jour (1-31) : ");
                            int jour = Integer.parseInt(scanner.nextLine());

                            LocalDateTime debutJour = LocalDateTime.of(anneeJour, moisJour, jour, 0, 0);
                            LocalDateTime finJour = debutJour.plusDays(1).minusSeconds(1);

                            afficherListe(calendar.eventsDansPeriode(debutJour, finJour));
                            break;
                    }
                    break;

                case "2":
                    // Ajout simplifié d'un RDV personnel
                    System.out.print("Titre de l'événement : ");
                    String titre = scanner.nextLine();
                    System.out.print("Année (AAAA) : ");
                    int annee = Integer.parseInt(scanner.nextLine());
                    System.out.print("Mois (1-12) : ");
                    int moisRdv = Integer.parseInt(scanner.nextLine());
                    System.out.print("Jour (1-31) : ");
                    int jourRdv = Integer.parseInt(scanner.nextLine());
                    System.out.print("Heure début (0-23) : ");
                    int heure = Integer.parseInt(scanner.nextLine());
                    System.out.print("Minute début (0-59) : ");
                    int minute = Integer.parseInt(scanner.nextLine());
                    System.out.print("Durée (en minutes) : ");
                    int duree = Integer.parseInt(scanner.nextLine());

                    calendar.ajouterEvent(new RdvPersonnel(titre, auth.utilisateurCourant.nom,
                            LocalDateTime.of(annee, moisRdv, jourRdv, heure, minute), duree));

                    System.out.println("Événement ajouté.");
                    break;

                case "3":
                    // Ajout simplifié d'une réunion
                    System.out.print("Titre de l'événement : ");
                    String titre2 = scanner.nextLine();
                    System.out.print("Année (AAAA) : ");
                    int annee2 = Integer.parseInt(scanner.nextLine());
                    System.out.print("Mois (1-12) : ");
                    int moisRdv2 = Integer.parseInt(scanner.nextLine());
                    System.out.print("Jour (1-31) : ");
                    int jourRdv2 = Integer.parseInt(scanner.nextLine());
                    System.out.print("Heure début (0-23) : ");
                    int heure2 = Integer.parseInt(scanner.nextLine());
                    System.out.print("Minute début (0-59) : ");
                    int minute2 = Integer.parseInt(scanner.nextLine());
                    System.out.print("Durée (en minutes) : ");
                    int duree2 = Integer.parseInt(scanner.nextLine());
                    System.out.println("Lieu :");
                    String lieu = scanner.nextLine();

                    StringBuilder participants = new StringBuilder(auth.utilisateurCourant.nom);

                    System.out.println("Ajouter un participant ? (oui / non)");
                    while (scanner.nextLine().equals("oui")) {
                        System.out.print("Participants : " + participants);
                        participants.append(", ").append(scanner.nextLine());
                        System.out.println("Ajouter un participant ? (oui / non)");
                    }

                    calendar.ajouterEvent(new Reunion(titre2, auth.utilisateurCourant.nom,
                            LocalDateTime.of(annee2, moisRdv2, jourRdv2, heure2, minute2), duree2,
                            lieu, participants.toString()));

                    System.out.println("Événement ajouté.");
                    break;

                case "4":
                    // Ajout simplifié d'une réunion
                    System.out.print("Titre de l'événement : ");
                    String titre3 = scanner.nextLine();
                    System.out.print("Année (AAAA) : ");
                    int annee3 = Integer.parseInt(scanner.nextLine());
                    System.out.print("Mois (1-12) : ");
                    int moisRdv3 = Integer.parseInt(scanner.nextLine());
                    System.out.print("Jour (1-31) : ");
                    int jourRdv3 = Integer.parseInt(scanner.nextLine());
                    System.out.print("Heure début (0-23) : ");
                    int heure3 = Integer.parseInt(scanner.nextLine());
                    System.out.print("Minute début (0-59) : ");
                    int minute3 = Integer.parseInt(scanner.nextLine());
                    System.out.print("Frequence (en jours) : ");
                    int frequence = Integer.parseInt(scanner.nextLine());

                    calendar.ajouterEvent(new Periodique(titre3, auth.utilisateurCourant.nom,
                            LocalDateTime.of(annee3, moisRdv3, jourRdv3, heure3, minute3), 0, frequence));

                    System.out.println("Événement ajouté.");
                    break;

                default:
                    System.out.println("Déconnexion ! Voulez-vous continuer ? (O/N)");
                    if (scanner.nextLine().equalsIgnoreCase("O")) {
                        main(args);
                    } else {
                        exit(0);
                    }
            }
        }

    }

    private static void afficherListe(List<Event> evenements) {
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

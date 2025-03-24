package action;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Auth implements Action{

    public final Scanner scanner = new Scanner(System.in);
    public ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
    public Utilisateur utilisateurCourant = new Utilisateur("","");

    public Auth() {
        utilisateurs.add(new Utilisateur("Roger", "Chat"));
        utilisateurs.add(new Utilisateur("Pierre", "KiRouhl"));
    }

    @Override
    public boolean execute() {

        System.out.println("1 - Se connecter");
        System.out.println("2 - Créer un compte");
        System.out.println("Choix : ");

        int choix = Integer.parseInt(scanner.nextLine());

        // Peu importe le choix, on initialise l'utilisateur courant

        System.out.print("Nom d'utilisateur: ");
        utilisateurCourant.nom = scanner.nextLine();

        System.out.print("Mot de passe: ");
        utilisateurCourant.motDePasse = scanner.nextLine();

        // On ne peut retourner true qu'en se connectant à un compte existant
        List<Action> actions = new ArrayList<>(){{
            add(new Connexion(Auth.this));
            add(new Inscription(Auth.this));
        }};
        boolean bool = actions.get(choix - 1).execute();

        // Si vrai, on redirige vers d'autres actions sans passer par le Main si possible et sinon on rappelle Auth
        //TODO

        return bool;
    }
}

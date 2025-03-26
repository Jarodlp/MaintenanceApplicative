package action.auth;

import action.Action;

public class Inscription implements Action {
    private final Auth auth;
    public Inscription(Auth auth) {
        this.auth = auth;
    }


    @Override
    public boolean execute() {
        System.out.print("Répéter mot de passe: ");
        String mdp = auth.scanner.nextLine();
        if (mdp.equals(auth.utilisateurCourant.motDePasse)) {
            auth.utilisateurs.add(auth.utilisateurCourant);
            System.out.println("Compte créé avec succès !");
        } else {
            System.out.println("Les mots de passes ne correspondent pas...");
        }
        // Par soucis de sécurité, on redemande à l'utilisateur de se connecter au compte qu'il vient de créer
        return false;
    }
}

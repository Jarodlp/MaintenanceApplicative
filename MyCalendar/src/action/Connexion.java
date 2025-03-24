package action;

public class Connexion implements Action {
    private final Auth auth;
    public Connexion(Auth auth) {
        this.auth = auth;
    }

    @Override
    public boolean execute() {

        if (auth.utilisateurs.contains(auth.utilisateurCourant)) {
            System.out.println("Connexion réussie !");
            return true;
        }
        System.out.println("Nom d'utilisateur ou mot de passe incorrect...");
        return false;
    }
}

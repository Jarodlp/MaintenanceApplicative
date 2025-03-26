package action.auth;

import java.util.Objects;

public class Utilisateur {
    public String nom;
    public String motDePasse;
    public Utilisateur(String nom, String motDePasse) {
        this.nom = nom;
        this.motDePasse = motDePasse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utilisateur u)) return false;
        return Objects.equals(nom, u.nom) && Objects.equals(motDePasse, u.motDePasse);
    }

    @Override
    public String toString() {
        return nom;
    }
}

package action;

import action.auth.Auth;

import java.util.Scanner;

import static java.lang.System.exit;

public class Deconnexion implements Action {

    public Scanner scanner = new Scanner(System.in);

    @Override
    public boolean execute() {
        System.out.println("Déconnexion ! Voulez-vous continuer ? (O/N)");
        if (scanner.nextLine().equalsIgnoreCase("O")) {
            Auth auth = new Auth();
            new ShowLogo().execute();
            return auth.execute();
        } else {
            exit(0);
            return false;
        }
    }
}

import action.auth.Auth;
import action.ShowLogo;

public class Main {
    public static void main(String[] args) {

        new ShowLogo().execute();

        new Auth().execute();
    }
}

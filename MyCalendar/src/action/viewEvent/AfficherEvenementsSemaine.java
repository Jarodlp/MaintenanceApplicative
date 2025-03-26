package action.viewEvent;

import action.VisualisateurEvenements;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class AfficherEvenementsSemaine extends AfficherEvents{
    public AfficherEvenementsSemaine(VisualisateurEvenements ve) {
        super(ve);
    }

    @Override
    public void printEvents(){
        System.out.print("Entrez l'année (AAAA) : ");
        int anneeSemaine = Integer.parseInt(visualisateurEvenements.scanner.nextLine());
        System.out.print("Entrez le numéro de semaine (1-52) : ");
        int semaine = Integer.parseInt(visualisateurEvenements.scanner.nextLine());

        LocalDateTime debutSemaine = LocalDateTime.now()
                .withYear(anneeSemaine)
                .with(WeekFields.of(Locale.FRANCE).weekOfYear(), semaine)
                .with(WeekFields.of(Locale.FRANCE).dayOfWeek(), 1)
                .withHour(0).withMinute(0);
        LocalDateTime finSemaine = debutSemaine.plusDays(7).minusSeconds(1);

        afficherListe(visualisateurEvenements.calendar.eventsDansPeriode(debutSemaine, finSemaine));
    }
}

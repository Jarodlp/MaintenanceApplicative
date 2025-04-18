import event.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
public class TestCalendarManager {

    @Test
    public void testAddEvent() {
        CalendarManager calendarManager = new CalendarManager();
        Event event = new RdvPersonnel("rdv professionnel", "Moi", LocalDateTime.now(), 60);
        calendarManager.ajouterEvent(event);
        assertEquals(1, calendarManager.events.size());
        assertEquals(event, calendarManager.events.get(0));
    }

    @Test
    public void testEventsDansPeriode() {
        CalendarManager calendarManager = new CalendarManager();
        Event event1 = new RdvPersonnel("rdv professionnel", "Moi", LocalDateTime.now(), 60);
        Event event2 = new RdvPersonnel("rdv médical", "Moi", LocalDateTime.now().plusDays(1), 60);
        Event event3 = new RdvPersonnel("rdv ultra secret", "Anonymous", LocalDateTime.now().plusDays(2), 60);
        calendarManager.ajouterEvent(event1);
        calendarManager.ajouterEvent(event2);
        calendarManager.ajouterEvent(event3);
        assertEquals(1, calendarManager.eventsDansPeriode(LocalDateTime.now().minusMinutes(10), LocalDateTime.now()).size());
        assertEquals(2, calendarManager.eventsDansPeriode(LocalDateTime.now().minusMinutes(10), LocalDateTime.now().plusDays(1)).size());
        assertEquals(3, calendarManager.eventsDansPeriode(LocalDateTime.now().minusMinutes(10), LocalDateTime.now().plusDays(2)).size());
    }

    @Test
    public void testConflit() {
        CalendarManager calendarManager = new CalendarManager();
        Event event1 = new Periodique("rdv professionnel", "Moi", LocalDateTime.now().plusDays(1), 60,10);
        Event event2 = new RdvPersonnel("rdv médical", "Moi", LocalDateTime.now().plusDays(1), 20);
        Event event3 = new Periodique("rdv ultra secret", "Anonymous", LocalDateTime.now(), 5,2);
        Event event4 = new Reunion("les réunions 7", "Cyprien", LocalDateTime.now().plusDays(1).plusMinutes(30), 30, "Webedia", "Cyprien, 100 figurants, cadreurs, etc ...");
        // Faux, car deux périodiques
        assertFalse(calendarManager.conflit(event1, event3));
        // Vrai, car les deux événements se chevauchent
        assertTrue(calendarManager.conflit(event1, event2));
        // Faux, les deux événements se succèdent
        assertFalse(calendarManager.conflit(event2, event4));
        // Vrai, car les deux événements se chevauchent
        assertTrue(calendarManager.conflit(event1, event4));

    }

    @Test
    public void testDescription() {
        // On crée une date fixe pour éviter les problèmes de comparaison
        LocalDateTime now = LocalDateTime.now();
        Event event1 = new RdvPersonnel("rdv professionnel", "Moi", now, 60);
        assertEquals("RDV : rdv professionnel à " + now, event1.description());
        Event event2 = new Reunion("les réunions 7", "Cyprien", LocalDateTime.now().plusDays(1).plusMinutes(30), 30, "Webedia", "Cyprien, 100 figurants, cadreurs, etc ...");
        assertEquals("Réunion : les réunions 7 à Webedia avec Cyprien, 100 figurants, cadreurs, etc ...", event2.description());
        Event event3 = new Periodique("rdv ultra secret", "Anonymous", LocalDateTime.now(), 5,2);
        assertEquals("Événement périodique : rdv ultra secret tous les 2 jours", event3.description());
    }

    @Test
    public void testSupprimerEventParSonEventId() {
        CalendarManager calendarManager = new CalendarManager();
        Event event1 = new RdvPersonnel("rdv professionnel", "Moi", LocalDateTime.now(), 60);
        Event event2 = new RdvPersonnel("rdv médical", "Moi", LocalDateTime.now().plusDays(1), 60);
        Event event3 = new RdvPersonnel("rdv ultra secret", "Anonymous", LocalDateTime.now().plusDays(2), 60);
        calendarManager.ajouterEvent(event1);
        calendarManager.ajouterEvent(event2);
        calendarManager.ajouterEvent(event3);
        assertEquals(3, calendarManager.events.size());
        calendarManager.events.supprimerEventParSonEventId(event2.eventId.eventId);
        assertEquals(2, calendarManager.events.size());
        assertEquals(event1, calendarManager.events.get(0));
        assertEquals(event3, calendarManager.events.get(1));
    }

    @Test
    public void testNouveauEventFete() {
        // On crée une date fixe pour éviter les problèmes de comparaison
        LocalDateTime now = LocalDateTime.now();
        CalendarManager calendarManager = new CalendarManager();
        Event event = new Fete("Bal de fin d'année", "Mairie de Nancy", now, 240, "Nancy", "Tenue de soirée");
        assertEquals("Fête : Bal de fin d'année à Nancy le " + now + " avec dresscode : Tenue de soirée", event.description());
        calendarManager.ajouterEvent(event);
        assertEquals(1, calendarManager.events.size());
        assertEquals(event, calendarManager.events.get(0));
    }
}

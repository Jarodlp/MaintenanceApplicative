import event.Event;

import java.util.ArrayList;

public class Evenements extends ArrayList<Event>{

    public Evenements() {
        super();
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Event e : this) {
            result.append(e.description()).append("\n");
        }
        return result.toString();
    }

    public void supprimerEventParSonEventId(int eventId) {
        for (Event e : this) {
            if (e.eventId.eventId == eventId) {
                this.remove(e);
                return;
            }
        }
    }
}

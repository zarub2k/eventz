package xyz.tham.events;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@ApplicationScoped
public class EventManager {
    private static final List<Event> events = new ArrayList(12);
    public List<Event> all() {
        events.add(new Event(1, "Madras JUG Monthly event", Calendar.getInstance().getTime()));
        return events;
    }

    public void create(Event event) {
        events.add(event);
    }
}

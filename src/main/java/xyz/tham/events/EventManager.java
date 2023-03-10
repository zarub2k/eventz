package xyz.tham.events;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class EventManager {
    private static final List<Event> events = new ArrayList(12);
    public List<Event> all() {
//        events.add(new Event(1, "Madras JUG Monthly event", Calendar.getInstance().getTime()));
        return events;
    }

    public void create(Event event) {
        events.add(event);
    }

    public Event get(int id) {
        for (Event event : events) {
            if (event.getId() == id) {
                return event;
            }
        }

        return null;
    }

    public void delete(int id) {
        Iterator<Event> iterator = events.iterator();
        Event event;
        while (iterator.hasNext()) {
            event = iterator.next();
            if (event.getId() == id) {
                iterator.remove();
                System.out.println("Event is removed!");
                break;
            }
        }
    }

    public List<Event> recommendations() {

        return events.stream()
                .filter(event -> isEven(event.getId()))
                .limit(2)
                .collect(Collectors.toList());
    }

    private boolean isEven(int value) {
        return (value % 2 == 0);
    }
}

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventCalendar {
    List<Event> events;

    EventCalendar()
    {
        events = new ArrayList<Event>();
    }

    // Initialized a new calendar with a list of Events
    // if copy is true then what list is copied over
    // else a reference is made
    EventCalendar(List<Event> events, boolean copy)
    {
        if (copy)
            this.events = new ArrayList<Event>(events);
        else 
            this.events = events;
    }
    
    EventCalendar(Event event, boolean copy)
    {
        new EventCalendar();
        if (copy)
            this.events.add(new Event(event));
        else
            this.events.add(event);
    }

    public void addEvent(Event e, boolean copy)
    {
        if (copy)
            this.events.add(new Event(e));
        else
            this.events.add(e);
    }
    
    // TODO
    public void eachBetween(LocalDateTime rangeStart, LocalDateTime rangeEnd) {}
    public void each() {}

    public static void main(String[] args)
    {

    }
}

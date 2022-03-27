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

    public void addRef(Event e)
    {
        this.events.add(e);
    }
    
    public void addCopy(Event e)
    {
        this.events.add(new Event(e));
    }
    
    public List<Event> getEventList()
    {
        return events;
    }
    
    public List<Event> getList()
    {
        return events;
    } 
}

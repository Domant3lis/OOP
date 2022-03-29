import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EventCollection extends NodeCollection {
    
    public EventCollection(List<Event> items) { super(items); }
    
    public void addCopy(Event e) { this.items.add(e.clone()); }
    
    // TODO:
    // public List<Event> getFilteredEventsDescription(String filter)
    // public List<Event> getFilteredEventsTitle(String filter)
    // public List<Event> getFilteredEvents(String filter)
    
    // getEvents{After/Before}{Start/End} methods
    public List<Event> getEventsAfterStart(LocalDateTime from)
    {
        Predicate<Event> filter = e -> e.getStartDateTime().isAfter(from);
        return getListFiltered(filter);
    }
    
    public List<Event> getEventsBeforeStart(LocalDateTime from)
    {
        Predicate<Event> filter = e -> e.getStartDateTime().isBefore(from);
        return getListFiltered(filter);
    }
    
    public List<Event> getEventsAfterEnd(LocalDateTime from)
    {
        Predicate<Event> filter = e -> e.getEndDateTime().isAfter(from);
        return getListFiltered(filter);
    }
    
    public List<Event> getEventsBeforeEnd(LocalDateTime from)
    {
        Predicate<Event> filter = e -> e.getEndDateTime().isBefore(from);
        return getListFiltered(filter);
    }
}

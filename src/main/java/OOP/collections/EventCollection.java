package OOP.collections;
import OOP.items.Event;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EventCollection extends ItemCollection {
    
    private LocalDateTime earliestEventDateTime;
    private LocalDateTime latestEventDateTime;
    
    // public EventCollection() { super(); }
    public EventCollection(List<Event> events)
    {
        super(events);
        updateEarliestLatestDateTime();
    }
    
    public void addCopy(Event e)
    {
        this.items.add(e.clone());
        updateEarliestLatestDateTime();
    }
    
    private void updateEarliestLatestDateTime()
    {
        LocalDateTime ear = ((Event) getList().get(0)).getStartDateTime();
        LocalDateTime late = ((Event) getList().get(0)).getStartDateTime();
        
        for (Object o : getList().subList(1, getList().size() -1))
        {
            Event e = (Event) o; 
            if ( e.getStartDateTime().isBefore(ear) )
                ear = e.getStartDateTime();
            else if ( e.getStartDateTime().isAfter(late) )
                late = e.getEndDateTime();
        }
        
        earliestEventDateTime = ear;
        latestEventDateTime = late;
    }
    
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

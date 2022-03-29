import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NodeCollection<T>
{
	protected List <T> items;
	
	// Constructors
	public NodeCollection() { items = new ArrayList<T>(); }
	public NodeCollection(List<T> items) { this.items = items; }
    
    // New `NodeCollection` is created with `item` taken as a referance
    public NodeCollection(T item)
    {
        this();
        this.items.add(item);
    }
    
    // Non-static methods
    public void addRef(T i)
    {
        this.items.add(i);
    }
    
    // Getters and setters
    public List<T> getList()
    {
        return items;
    }
    
    public List<T> getListFiltered(Predicate<T> filter)
    {
        Stream<T> s = getList().stream();
        List<T> ret = s.filter(filter).collect(Collectors.toList());
        return ret;
    }
    
    // public List<Event> getEventsTill(LocalTimeDate till)
    // public List<Event> getEventsTimeRange(LocalTimeDate from, LocalTimeDate till)

}

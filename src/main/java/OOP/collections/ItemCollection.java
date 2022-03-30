package OOP.collections;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ItemCollection<T>
{
    // TODO: Add somesort of aditional field
	protected List <T> items;
	
	// Constructors
	public ItemCollection() { items = new ArrayList<T>(); }
	public ItemCollection(List<T> items) { this.items = items; }
    // New `ItemCollection` is created with `item` taken as a referance
	public ItemCollection(T item) { super(); items.add(item); }
    
    // Non-static methods
    final public void addRef(T i)
    {
        this.items.add(i);
    }
    
    public List<T> getListFiltered(Predicate<T> filter)
    {
        Stream<T> s = getList().stream();
        List<T> ret = s.filter(filter).collect(Collectors.toList());
        return ret;
    }
    
    // Getters and setters
    public List<T> getList()
    {
        return items;
    }
}

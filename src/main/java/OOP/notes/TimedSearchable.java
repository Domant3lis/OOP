package OOP.notes;

import java.time.LocalDateTime;

public interface TimedSearchable extends Searchable {
    public boolean isCreatedBetweenTimedRange(LocalDateTime start, LocalDateTime end, boolean startInclusive, boolean endInclusive);
    public boolean isCreatedBefore(LocalDateTime time); 
    public boolean isCreatedAfter(LocalDateTime time);
    public boolean isCreatedAt(LocalDateTime time);
}

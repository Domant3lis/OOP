import java.time.Duration;
import java.time.LocalDateTime;
import java.lang.String;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class Event
{
    private static int eventCount = 0;
    
    final private LocalDateTime creationDateTime;
    
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    // TODO:
    // private TimeSpan timeSpan;
    private Duration timeSpan;
    
    private String title;
    private String description;

    Event(String title, String desc, LocalDateTime start, LocalDateTime end)
    {
        eventCount += 1;
        this.title = title;
        this.description = desc;
        this.startDateTime = start;
        this.endDateTime = end;
        this.creationDateTime = LocalDateTime.now();
        updateTimeSpan();
    }
    
    Event()
    {
        this(new String("Event #" + (eventCount + 1)), "", LocalDateTime.now().plusSeconds(1), LocalDateTime.now().plusHours(1));
    }
    
    public Event clone()
    {
        return new Event(this.title, this.description, this.startDateTime, this.endDateTime);
    }
    
    private void updateTimeSpan()
    {
        timeSpan = Duration.between(startDateTime, endDateTime);
    }
    
    static public boolean startDateTimeEquals(Event e0, Event e1)
    {
        if (e0.startDateTime.equals(e1.startDateTime))
            return true;
        else
            return false;
    }
    
    static public boolean endDateTimeEquals(Event e0, Event e1)
    {
        if (e0.endDateTime.equals(e1.endDateTime))
            return true;
        else
            return false;
    }
    
    public String toString()
    {
        return new String(
              "Title: " + title
            + "\nDescription: " + description 
            + "\nStart Time: " + startDateTime
            + "\nEnd time: " + endDateTime 
            + "\nCreation Time: " + creationDateTime + "\n");
    }
    
    // Append string to the description
    public void appendToDescription(String appendix) {
		this.description = description.concat(appendix);
	}
    
    public void postpone(long amount, TemporalUnit unit)
    {
        addTimeToStart(amount, unit);
        addTimeToEnd(amount, unit);
    }
    
    public void prepone(long amount, TemporalUnit unit)
    {
        subTimeFromStart(amount, unit);
        subTimeFromEnd(amount, unit);
    }
    
    // Time manipulation related methods
    public void addTimeToStart(long amountToAdd, TemporalUnit unit)
    {
        this.startDateTime = this.startDateTime.plus(amountToAdd, unit);
    }
    public void addTimeToEnd(long amountToAdd, TemporalUnit unit)
    {
        this.endDateTime = this.endDateTime.plus(amountToAdd, unit);
    }
    public void subTimeFromStart(long amountToSub, TemporalUnit unit)
    {
        this.startDateTime = this.startDateTime.minus(amountToSub, unit);
    }
    public void subTimeFromEnd(long amountToSub, TemporalUnit unit)
    {
        this.endDateTime = this.endDateTime.minus(amountToSub, unit);
    }
    
    // Bellow getters and setters lay 
    public LocalDateTime getStartDateTime() { return startDateTime; }
    public LocalDateTime getEndDateTime() { return endDateTime; }
    public LocalDateTime getCreationTime() { return creationDateTime; }
    public Duration getTimeSpan() { return timeSpan; }
    public String getDescription() { return this.description; }
    public String getTitle() { return this.title; }

    public void setDescription(String description) { this.description = description; }
    public void setTitle(String title) { this.title = title; }
    
    public void setStartDateTime(LocalDateTime dateTime)
    {
        this.startDateTime = dateTime;
        updateTimeSpan();
    }
        
    public void setStartDateTime(int year, int month, int day, int hours, int minutes) 
    { 
        this.startDateTime = LocalDateTime.of(year, month, day, hours, minutes);
        updateTimeSpan();
    }
        
    public void setEndDateTime(LocalDateTime dateTime)
    {
        this.endDateTime = dateTime;
        updateTimeSpan();
    }
        
    public void setEndDateTime(int year, int month, int day, int hours, int minutes)
    {
        this.endDateTime = LocalDateTime.of(year, month, day, hours, minutes);
        updateTimeSpan();
    }
}


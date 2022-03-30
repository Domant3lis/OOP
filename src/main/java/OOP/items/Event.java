package OOP.items;

import java.time.Duration;
import java.time.LocalDateTime;
import java.lang.String;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class Event extends Item
{
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    // TODO: Switch to TimeSpan for time duration
    // private TimeSpan timeSpan;
    private Duration timeSpan;

    public Event(String title, String desc, LocalDateTime start, LocalDateTime end)
    {
        super(title, desc);
        this.startDateTime = start;
        this.endDateTime = end;
        updateTimeSpan();
    }
    
    public Event()
    {
        super(new String("Event #" + (itemCount + 1)), "");
        this.startDateTime = LocalDateTime.now();
        this.endDateTime = LocalDateTime.now();
    }
    
    public Event clone()
    {
        return new Event(this.title, this.description, this.startDateTime, this.endDateTime);
    }
    
    private void updateTimeSpan()
    {
        timeSpan = Duration.between(startDateTime, endDateTime);
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
    public Duration getTimeSpan() { return timeSpan; }
    
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


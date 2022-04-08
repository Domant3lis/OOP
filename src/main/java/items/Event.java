package items;

import java.lang.String;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.regex.Pattern;

public class Event extends Note
{
    private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;

    public Event(String title, String desc, LocalDateTime start, LocalDateTime end)
    {
        super(title, desc);
        this.startDateTime = start;
        setEndDateTime(end);
    }
    
    @Override
    public String toString()
    {
        String ret = super.toString();
        String concat = new String(
            "\nStarts at: " + getStartDateTime() + 
            "\nEnds at: " + getEndDateTime()
        );
        return ret.concat(concat);
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

    public boolean timeConflicts(LocalDateTime time)
    {
        // startDateTime < time < endDateTime
        if (startDateTime.isAfter(time) && endDateTime.isBefore(time))
            return true;
        
        return false;
    }

    // Time related methods
    public void addTimeToStart(long amountToAdd, TemporalUnit unit)
    {
        this.startDateTime = this.startDateTime.plus(amountToAdd, unit);
    }
    
    public void subTimeFromStart(long amountToSub, TemporalUnit unit)
    {
        this.startDateTime = this.startDateTime.minus(amountToSub, unit);
    }

    public void addTimeToEnd(long amountToAdd, TemporalUnit unit)
        { this.endDateTime = this.endDateTime.plus(amountToAdd, unit); }
    public void subTimeFromEnd(long amountToSub, TemporalUnit unit)
        { this.endDateTime = this.endDateTime.minus(amountToSub, unit); }

    @Override
    public boolean contains(String match)
    {
        if (super.contains(match))
            return true;
        
        if(this.startDateTime.toString().contains(match))
            return true;

        if(this.endDateTime.toString().contains(match))
            return true;
            
        return false;
    }

    @Override
    public boolean contains(Pattern regex)
    {
        if (super.contains(regex))
            return true;
        
        if (regex.matcher(this.startDateTime.toString()).find())
            return true;
        
        if (regex.matcher(this.endDateTime.toString()).find())
            return true;

        return false;
    }

    public Duration getDuration() { return Duration.between(startDateTime, getEndDateTime()); }
    
    // Bellow getters and setters lay 
    public LocalDateTime getStartDateTime() { return startDateTime; }
    public LocalDateTime getEndDateTime() { return endDateTime; }
    
    public void setStartDateTime(LocalDateTime dateTime) { this.startDateTime = dateTime; }
    public void setStartDateTime(int year, int month, int day, int hours, int minutes) 
        { this.startDateTime = LocalDateTime.of(year, month, day, hours, minutes); }
    public void setEndDateTime(LocalDateTime dateTime) { this.endDateTime = dateTime; }  
    public void setEndDateTime(int year, int month, int day, int hours, int minutes)
        { this.endDateTime = LocalDateTime.of(year, month, day, hours, minutes); }

}


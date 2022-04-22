package notes;

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
        throws StartAfterEndException
    {
        super(title, desc);

        if (start.isAfter(end))
            throw new StartAfterEndException(start, end);

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

    @Override
    public Event clone() throws CloneNotSupportedException
    {
        Event clone = (Event) super.clone();

        return clone;
    }
    
    public void postpone(long amount, TemporalUnit unit)
    {
        addTimeToEnd(amount, unit);
        try {addTimeToStart(amount, unit);} catch (Exception e){}
    }
    
    public void prepone(long amount, TemporalUnit unit)
    {
        subTimeFromStart(amount, unit);
        try {subTimeFromEnd(amount, unit);} catch (Exception e){}
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
        throws StartAfterEndException
    {
        LocalDateTime newTime = this.startDateTime.plus(amountToAdd, unit);
        if (newTime.isAfter(this.endDateTime))
            throw new StartAfterEndException(newTime, this.endDateTime);
        
        this.startDateTime = newTime;
    }
    
    public void subTimeFromStart(long amountToSub, TemporalUnit unit)
    {
        this.startDateTime = this.startDateTime.minus(amountToSub, unit);
    }

    public void addTimeToEnd(long amountToAdd, TemporalUnit unit)
        { this.endDateTime = this.endDateTime.plus(amountToAdd, unit); }
    
    public void subTimeFromEnd(long amountToSub, TemporalUnit unit)
        throws StartAfterEndException
    {
        LocalDateTime newTime = this.endDateTime.minus(amountToSub, unit);
        if (newTime.isBefore(this.endDateTime))
            throw new StartAfterEndException(this.startDateTime, newTime);
        
        this.endDateTime = newTime;
    }

    @Override
    public boolean contentContains(String match)
    {
        if (titleContains(match))
            return true;
        
        if (descriptionContains(match))
            return true;
        
        if(this.startDateTime.toString().contains(match))
            return true;

        if(this.endDateTime.toString().contains(match))
            return true;
            
        return false;
    }

    @Override
    public boolean contentContains(Pattern regex)
    {        
        if (titleContains(regex))
            return true;

        if (descriptionContains(regex))
            return true;
        
        if (regex.matcher(this.startDateTime.toString()).find())
            return true;
        
        if (regex.matcher(this.endDateTime.toString()).find())
            return true;

        return false;
    }

    public void appendToContent(String appendix)
    {
        setDescription(getDescription().concat(appendix));
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


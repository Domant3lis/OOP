package notes;

import java.lang.String;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.regex.Pattern;

public class Event extends Note {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public Event(String title, LocalDateTime start, LocalDateTime end)
            throws IllegalArgumentException {
        super(title);

        if (start == null || end == null)
            throw new IllegalArgumentException("Neither start, nor end datetime should be null");

        if (start.isAfter(end)) {
            throw new IncorrectTimeRangeException(start, end);
        }

        setStartDateTime(start);
        setEndDateTime(end);
    }

    @Override
    public String toString() {
        return super.toString().concat(
            "\nStarts at: " + getStartDateTime() +
            "\nEnds at: " + getEndDateTime());
    }

    @Override
    public Event clone() throws CloneNotSupportedException {
        Event clone = (Event) super.clone();

        return clone;
    }

    // Move both start and end dateTimes by the same amount
    // To prepone amount must be negative
    public void postpone(long amount, TemporalUnit unit) {
        try {
            addTimeToEnd(amount, unit);
            addTimeToStart(amount, unit);
        } catch (Exception e) {
            // since addTimeToStart may throw an exception
            // if startDateTime happens to be before endDateTime
            // this does not need to handled since both times
            // are moved by the same amount
        }
    }

    // Shows if specified time falls between
    // start and end time of this Event
    public boolean timeConflicts(LocalDateTime time) {
        // startDateTime < time < endDateTime
        if (startDateTime.isAfter(time) && endDateTime.isBefore(time))
            return true;

        return false;
    }

    // Time related methods
    // to subtract time amountToAdd should be negative
    public void addTimeToStart(long amountToAdd, TemporalUnit unit)
            throws IncorrectTimeRangeException {

        LocalDateTime newTime = this.startDateTime.plus(amountToAdd, unit);
        if (newTime.isAfter(this.endDateTime))
            throw new IncorrectTimeRangeException(newTime, this.endDateTime);

        this.startDateTime = newTime;
    }

    // Time related methods
    // to subtract time amountToAdd should be negative
    public void addTimeToEnd(long amountToAdd, TemporalUnit unit) 
        throws IncorrectTimeRangeException {
        
        LocalDateTime newTime = this.endDateTime.plus(amountToAdd, unit);
        if (newTime.isBefore(this.startDateTime))
            throw new IncorrectTimeRangeException(this.startDateTime, newTime);

        this.endDateTime = newTime;
    }

    // @Override
    // public boolean contentContains(String match) {
    //     if (titleContains(match))
    //         return true;

    //     if (this.startDateTime.toString().contains(match))
    //         return true;

    //     if (this.endDateTime.toString().contains(match))
    //         return true;

    //     return false;
    // }

    // @Override
    // public boolean contentContains(Pattern regex) {
    //     if (titleContains(regex))
    //         return true;

    //     if (regex.matcher(this.startDateTime.toString()).find())
    //         return true;

    //     if (regex.matcher(this.endDateTime.toString()).find())
    //         return true;

    //     return false;
    // }

    public Duration getDuration() {
        return Duration.between(startDateTime, getEndDateTime());
    }

    // Bellow getters and setters lay
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setStartDateTime(LocalDateTime dateTime) {
        this.startDateTime = dateTime;
    }

    public void setStartDateTime(int year, int month, int day, int hours, int minutes) {
        this.startDateTime = LocalDateTime.of(year, month, day, hours, minutes);
    }

    public void setEndDateTime(LocalDateTime dateTime) {
        this.endDateTime = dateTime;
    }

    public void setEndDateTime(int year, int month, int day, int hours, int minutes) {
        this.endDateTime = LocalDateTime.of(year, month, day, hours, minutes);
    }

}

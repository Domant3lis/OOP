package notes;

import java.lang.String;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.regex.Pattern;

public class Event extends Note {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public Event(String title, String desc, LocalDateTime start, LocalDateTime end)
            throws IncorrectArgumentException {
        super(title, desc);

        if (start == null || end == null)
            throw new IncorrectArgumentException("Neither start, nor end datetime should be null");

        if (start.isAfter(end)) {
            throw new IncorrectTimeRangeException(start, end);
        }

        setStartDateTime(start);
        setEndDateTime(end);
    }

    @Override
    public String toString() {
        String ret = super.toString();
        String concat = new String(
                "\nStarts at: " + getStartDateTime() +
                        "\nEnds at: " + getEndDateTime());
        return ret.concat(concat);
    }

    @Override
    public Event clone() throws CloneNotSupportedException {
        Event clone = (Event) super.clone();

        return clone;
    }

    public void postpone(long amount, TemporalUnit unit) {
        try {
            addTimeToEnd(amount, unit);
            addTimeToStart(amount, unit);
        } catch (Exception e) {
        }
    }

    public void prepone(long amount, TemporalUnit unit) {
        try {
            subTimeFromStart(amount, unit);
            subTimeFromEnd(amount, unit);
        } catch (Exception e) {
        }
    }

    public boolean timeConflicts(LocalDateTime time) {
        // startDateTime < time < endDateTime
        if (startDateTime.isAfter(time) && endDateTime.isBefore(time))
            return true;

        return false;
    }

    // Time related methods
    public void addTimeToStart(long amountToAdd, TemporalUnit unit)
            throws IncorrectTimeRangeException, IncorrectArgumentException {

        if (amountToAdd < 0)
            throw new IncorrectArgumentException(
                    "amountToAdd should be positive, use subTimeFromStart to subtract time");

        LocalDateTime newTime = this.startDateTime.plus(amountToAdd, unit);
        if (newTime.isAfter(this.endDateTime))
            throw new IncorrectTimeRangeException(newTime, this.endDateTime);

        this.startDateTime = newTime;
    }

    public void subTimeFromStart(long amountToSub, TemporalUnit unit)
            throws IncorrectArgumentException {
        if (amountToSub < 0)
            throw new IncorrectArgumentException("amountToSub should be positive");

        this.startDateTime = this.startDateTime.minus(amountToSub, unit);
    }

    public void addTimeToEnd(long amountToAdd, TemporalUnit unit) {
        this.endDateTime = this.endDateTime.plus(amountToAdd, unit);
    }

    public void subTimeFromEnd(long amountToSub, TemporalUnit unit)
            throws IncorrectTimeRangeException {

        LocalDateTime newTime = this.endDateTime.minus(amountToSub, unit);
        if (newTime.isBefore(this.endDateTime))
            throw new IncorrectTimeRangeException(this.startDateTime, newTime);

        this.endDateTime = newTime;
    }

    @Override
    public boolean contentContains(String match) {
        if (titleContains(match))
            return true;

        if (descriptionContains(match))
            return true;

        if (this.startDateTime.toString().contains(match))
            return true;

        if (this.endDateTime.toString().contains(match))
            return true;

        return false;
    }

    @Override
    public boolean contentContains(Pattern regex) {
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

    public void appendToContent(String appendix) {
        setDescription(getDescription().concat(appendix));
    }

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

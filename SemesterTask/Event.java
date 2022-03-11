import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

public class Event
{
    private static int eventCount = 0;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String title;
    private String description;

    Event(String title, String description, LocalDateTime start, LocalDateTime end) {
        eventCount += 1;
        this.title = title;
        this.description = description;
        this.startDate = start;
        this.endDate = end;
    }

    Event(Event e)
    {
        new Event(e.title, e.description, e.startDate, e.endDate);
    }
    
    Event()
    {
        new Event(new String("Event #" + (eventCount + 1)), "", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
    }

    // public Period getPeriod()
    // {
    //     return new Period(this.startDate, this.endDate);
    // }


    public int getEventCount()
    {
        return eventCount;
    }

    /**
     * @return String return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * @return Date return the startDate
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }
}

class LocalDateTimeSpan
{
    Period per;
    Duration dur;

    LocalDateTimeSpan(Period per, Duration dur)
    {
        this.per = per;
        this.dur = dur;
    }

    // PeriodDuration(Period per, Duration dur) {
    //     this.per = per;
    //     this.dur = dur;
    // }

} 
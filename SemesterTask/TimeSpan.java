import java.lang.String;

// Only needed java.time.* imports 
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.Duration;

public class TimeSpan
{
    Period per;
    Duration dur;

    TimeSpan(LocalDateTime start, LocalDateTime end)
    {
        this.per = Period.between(start.toLocalDate(), end.toLocalDate());
        this.dur = Duration.between(start.toLocalTime(), end.toLocalTime());
    }
    
    enum DateTimePart { YEAR, MONTH, DAY, HOUR, MINUTE, SECOND, MILLIS, NANOS}
    
    public String toString()
    {
        String date = String.format("%02d-%02d-%02d", getYears(), getMonths(), getDays());
        String time = String.format("%02d:%02d:%02d.%d", getHours(), getMinutes(), getSeconds(), getNanos());
        return new String(date + "T" + time);
    }
    
    public DateTimePart getBiggestPart() { return getBiggestPart(this); }
    
    public static DateTimePart getBiggestPart(TimeSpan ts)
    {
        if (ts.getYears() > 0)
            return DateTimePart.YEAR;
        if (ts.getMonths() > 0)
            return DateTimePart.MONTH;
        if (ts.getDays() > 0)
            return DateTimePart.DAY;
        if (ts.getHours() > 0)
            return DateTimePart.HOUR;
        if (ts.getMinutes() > 0)
            return DateTimePart.MINUTE;
        if (ts.getMillis() > 0)
            return DateTimePart.MILLIS;
        else
            return DateTimePart.NANOS;
    }
    
    public int getYears() { return per.getYears(); }
    public int getMonths() { return per.getMonths(); }
    public int getDays() { return per.getDays(); }
    public int getHours() { return dur.toHoursPart(); }
    public int getMinutes() { return dur.toMinutesPart(); }
    public int getSeconds() { return dur.toSecondsPart(); }
    public int getMillis() { return dur.toMillisPart(); }
    public int getNanos() { return dur.toNanosPart(); }
}

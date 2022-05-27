package OOP.notes;

import java.time.LocalDateTime;

public class IncorrectTimeRangeException extends IllegalArgumentException {

    LocalDateTime start;
    LocalDateTime end;

    IncorrectTimeRangeException(LocalDateTime start, LocalDateTime end) {

        super("the end date should be after the start date\n"
                + "Start date: " + start
                + "\nEnd date: " + end);

        this.start = start;
        this.end = end;
    }
}

package notes;

import java.time.LocalDateTime;

public class StartAfterEndException extends IncorrectArgumentException {
    StartAfterEndException(LocalDateTime start, LocalDateTime end)
    {
        super("the end date should be after the start date\n"
        + "Start date: " + start
        + "\nEnd date: " + end);
    }
}

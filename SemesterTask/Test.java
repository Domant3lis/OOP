import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        Event e0 = new Event();
        Event e1 = new Event(
            "Meeting", 
            "LOl", 
            LocalDateTime.of(2022, 04, 20, 6, 9), 
            LocalDateTime.of(2022, 04, 20, 6, 10)
        );
        Event e2 = new Event(
            "Eat", 
            "Olo", 
            LocalDateTime.of(2021, 04, 20, 6, 9), 
            LocalDateTime.of(2021, 04, 20, 6, 10)
        );

        assert(e0.getEventCount() == 3);
        assert(e1.getEventCount() == 3);
        assert(e2.getEventCount() == 3);

        EventCalendar ec0 = new EventCalendar();
        ec0.addEvent(e0, false);
        ec0.addEvent(e0, true);
        assert (ec0.events.get(0) == e0); 
        assert (ec0.events.get(1) != e0); 
        assert (ec0.events.get(0).equals(e0)); 

        // EventCalendar ec2 = new EventCalendar();

    }
}

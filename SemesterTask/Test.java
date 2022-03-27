// Personalinė užrašų sistema: adresai, užrašai, darbai, kalendorius
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.temporal.ChronoUnit;

public class Test {
    public static void main(String[] args) throws InterruptedException{


        Event e0 = new Event();
        Event e1 = new Event(
            "Meeting", 
            "Very important", 
            LocalDateTime.now().plusMinutes(1), 
            LocalDateTime.of(2022, 03, 20, 4, 30)
        );
        
        Event.startTimeEquals(e0, e1);
        
        Event e2 = new Event(
            "Project Foo", 
            "Less important", 
            LocalDateTime.now().plusSeconds(3), 
            LocalDateTime.of(2022, 04, 20, 16, 00)
        );
         
        e2.setEndDateTime(e2.getEndDateTime().plusYears(1));
        
        Event e3 = e2.clone();

        List<Event> el0 = new ArrayList<Event>();
        
        el0.add(e0);
        el0.add(e1);
        el0.add(e2);
        el0.add(e3);
        
        System.out.println(Event.startDateTimeEquals(e0, e1));
        System.out.println(Event.startDateTimeEquals(e1, e2));
        System.out.println(Event.endDateTimeEquals(e1, e2) + "\n");

        el0.stream().forEach( Event::println );
        System.out.println("------");
        
        e0.addTimeToStart(1, ChronoUnit.YEARS);
        e1.addTimeToStart(1, ChronoUnit.DAYS);
        e2.addTimeToStart(1, ChronoUnit.SECONDS);
        e3.addTimeToEnd(2000, ChronoUnit.YEARS);
        
        e0.subTimeFromStart(1, ChronoUnit.SECONDS);
        e1.subTimeFromStart(1, ChronoUnit.MONTHS);
        e2.subTimeFromStart(1, ChronoUnit.MONTHS);
        e3.subTimeFromEnd(10, ChronoUnit.MONTHS);
        
        el0.stream().forEach( Event::println );
        System.out.println("------");
        
        for (Event e : el0)
        {
            e.postpone(2, ChronoUnit.YEARS);
        }
        
        e1.appendToDescription(" | Appended text");
        el0.stream().forEach( Event::println );
        
        String match = new String("important");
        System.out.println(" --- Matches for \"" + match + "\":");
        el0.stream().forEach( e -> {
            if (e.descriptionContains(match)) 
                System.out.println(e);
        });
        
        // el.stream().forEach( Event::addTimeToStart());
        // el.stream().forEach( Event::addTimeToEnd);
        // el.stream().forEach( Event::subtractTimeToStart);
        // el.stream().forEach( Event::subtractTimeToEnd);
        
        // el0.stream().forEach( e -> System.out.println("\n" + e.getTimeSpan()));
                
   
   //      EventCalendar ec0 = new EventCalendar();
   //      ec0.addRef(e0);
   //      ec0.addCopy(e0);
   //      assert (ec0.events.get(0) == e0);
   //      assert (ec0.events.get(1) != e0);
   //      assert (ec0.events.get(0).equals(e0));
        
   //      ec0.addRef(e1);
   //      ec0.addCopy(e2);
        
   //      ec0.getList().stream().forEach(e -> e.println());
			
   //      ec0.getList().stream()
			// .forEach( e -> System.out.println(e.getTimeSpan() + "\n"));

    }
}


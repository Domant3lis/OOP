// Personalinė užrašų sistema: adresai, užrašai, darbai, kalendorius
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.temporal.ChronoUnit;

// Pasirinkto projekto kontekste sudaryti klasių hierarchiją
// bent iš trijų paveldėjimo ryšiais susijusių klasių. Išvestinės klasės privalo:
// - [x] Pasinaudoti bazinės klasės konstruktoriumi super() bei super-metodu.
// - [x] Turėti papildomų metodų ir laukų
// - [ ] Užkloti Object metodą toString() ir dar bent vieną metodą
// - [ ] Kitos klasės privalo pasinaudoti sukurtų klasių polimorfiniu elgesiu
// (kviesti užklotus metodus bazinio tipo nuorodai)
// - [ ] Bazinė klasė privalo turėti metodų, kuriuos draudžiama užkloti
// - [ ] Visos projekto klasės privalo priklausyti bent 2 skirtingiems paketams

// IDEA:
// Create a NodeCollection class
// And extend it with EventCollection (i.e. calendar) and NoteCollection classes

public class Test {
    public static void main(String[] args) throws InterruptedException{

        Event e0 = new Event();
        Event e1 = new Event(
            "Meeting", 
            "Very important", 
            LocalDateTime.now().plusMinutes(1), 
            LocalDateTime.of(2022, 03, 20, 4, 30)
        );
        
        Event.startDateTimeEquals(e0, e1);
        
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

        el0.stream().forEach( System.out::println );
        System.out.println("------");
        
        e0.addTimeToStart(1, ChronoUnit.YEARS);
        e1.addTimeToStart(1, ChronoUnit.DAYS);
        e2.addTimeToStart(1, ChronoUnit.SECONDS);
        e3.addTimeToEnd(2000, ChronoUnit.YEARS);
        
        e0.subTimeFromStart(1, ChronoUnit.SECONDS);
        e1.subTimeFromStart(1, ChronoUnit.MONTHS);
        e2.subTimeFromStart(1, ChronoUnit.MONTHS);
        e3.subTimeFromEnd(10, ChronoUnit.MONTHS);
        
        el0.stream().forEach( System.out::println );
        System.out.println("------");
        
        for (Event e : el0)
        {
            e.postpone(2, ChronoUnit.YEARS);
        }
        
        e1.getDescription().concat(" | Appended text");
        el0.stream().forEach( System.out::println );
        
        String match = new String("important");
        System.out.println(" --- Matches for \"" + match + "\":");
        el0.stream().forEach( e -> {
            if (e.getDescription().contains(match)) 
                System.out.println(e);
        });
        
        el0.stream().forEach( e -> e.addTimeToStart(1, ChronoUnit.SECONDS) );
        // el.stream().forEach( Event::addTimeToEnd);
        // el.stream().forEach( Event::subtractTimeToStart);
        // el.stream().forEach( Event::subtractTimeToEnd);
        
        el0.stream().forEach( e -> System.out.println("\n" + e.getTimeSpan()));
                
   
        EventCollection ec0 = new EventCollection(new ArrayList<Event>(el0));
        ec0.addRef(e0);
        ec0.addCopy(e0);
        assert (ec0.getList().get(0) == e0);
        assert (ec0.getList().get(1) != e0);
        assert (ec0.getList().get(0).equals(e0));
        
        ec0.addRef(e1);
        ec0.addCopy(e2);
        
        ec0.getList().stream().forEach(System.out::println);
        
        System.out.println(ec0.getEventsStartFrom(LocalDateTime.of(2024, 12, 20, 4, 30)));
			
        // ec0.getList().stream()
        //     .forEach( e -> System.out.println(((Event)e).getTimeSpan() + "\n"));

    }
}


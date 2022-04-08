// Personalinė užrašų sistema: adresai, užrašai, darbai, kalendorius
package OOP;
import items.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) {

        Event e0 = new Event("Project Foo", 
            "Very important", 
            LocalDateTime.now().plusSeconds(3), 
            LocalDateTime.of(2022, 04, 15, 16, 00)
        );
        
        Event e1 = new Event("Project Bar", 
            "Somewhat important", 
            LocalDateTime.now().plusSeconds(3), 
            LocalDateTime.of(2022, 04, 16, 16, 00)
        );
        e1.postpone(1, ChronoUnit.SECONDS);
        
        Event e2 = new Event(
            "Project FooBar", 
            "Less important", 
            LocalDateTime.now().plusSeconds(3), 
            LocalDateTime.of(2022, 04, 17, 16, 00)
        );

        List<Note> nl0 = new ArrayList<Note>();
        
        nl0.add(e0);
        nl0.add(e1);
        nl0.add(e2);
        
        e1.appendToDescription(" | Appended text");
        
        String match = new String("important");
        System.out.println(" --- Matches for \"" + match + "\":");
        nl0.stream().forEach( n -> {
            if (n.contains(match))
                System.out.println("\n" + n);
        });

        Note c0 = new Contact("Mom", "", "Vardenė Pavardenė", "+370 603 03030", "mom@examaple.com", LocalDateTime.of(1965, 8, 7, 12, 13, 14) );
        nl0.add(c0);
        
        Note c1 = new Contact("BFF", "", "Vardenis Pavardenis", "+370 602 02020", "bff@examaple.com", LocalDateTime.of(1999, 8, 7, 12, 13, 14));
        nl0.add(c1);
        
        nl0.add(c1);
        ((Contact) c1).setCustom("Likes", "Apples, Oranges");
        ((Contact) c1).setCustom("Dislikes", "Bananas");
        // ((Contact) c1).writeEmail();

        Pattern reg = Pattern.compile("2022-04-17");
        
        System.out.print("\nREGEX:\n");
        nl0.forEach(n -> { if(n.contains(reg)) { System.out.println("\n" + n); } });

        System.out.println("\nALL ITEMS:\n");
        // il0.stream().forEach( System.out::println );
        nl0.stream().forEach( n -> { System.out.println(n + "\n"); } );
    }
}


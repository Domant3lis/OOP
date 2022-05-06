// Personalinė užrašų sistema: adresai, užrašai, darbai, kalendorius
package OOP;

import notes.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.lang.IllegalArgumentException;

public class App {
    public static void main(String[] args) {
        try {
            Event e0, e1, e2;

            e0 = new Event("Project Foo",
                    "Very important",
                    LocalDateTime.now().plusSeconds(3),
                    LocalDateTime.of(2022, 05, 15, 16, 00));

            e1 = new Event("Project Bar",
                    "Somewhat important",
                    LocalDateTime.now().plusSeconds(3),
                    LocalDateTime.of(2022, 05, 16, 16, 00));

            e1.postpone(1, ChronoUnit.SECONDS);

            e2 = new Event(
                    "Project FooBar",
                    "Less important",
                    LocalDateTime.now().plusSeconds(3),
                    LocalDateTime.of(2022, 05, 17, 16, 00));

            List<Note> nl0 = new ArrayList<Note>();

            nl0.add(e0);
            nl0.add(e1);
            nl0.add(e2);

            e1.appendToContent(" | Appended text");

            String match = new String("important");
            System.out.println(" --- Matches for \"" + match + "\":");
            nl0.stream().forEach(n -> {
                if (n.contentContains(match))
                    System.out.println("\n" + n);
            });

            Note c0 = new Contact("Mom",
                    "",
                    "Vardenė Pavardenė",
                    "+370 603 03030",
                    "mom@examaple.com",
                    LocalDateTime.of(1965, 8, 7, 12, 13, 14));

            nl0.add(c0);

            Note c1 = new Contact("BFF", "", "Vardenis Pavardenis", "+370 602 02020", "bff@examaple.com",
                    LocalDateTime.of(1999, 8, 7, 12, 13, 14));
            nl0.add(c1);

            nl0.add(c1);
            ((Contact) c1).setCustom("Likes", "Apples, Oranges");
            ((Contact) c1).setCustom("Dislikes", "Bananas");
            // ((Contact) c1).writeEmail();

            Pattern reg = Pattern.compile("2022-04-17");

            System.out.print("\nREGEX:\n");
            nl0.forEach(n -> {
                if (n.contentContains(reg)) {
                    System.out.println("\n" + n);
                }
            });

            System.out.println("\nALL ITEMS:\n");
            // il0.stream().forEach( System.out::println );

            Todo t0 = new Todo("Take medicine", "Brandanol 5mg", LocalDateTime.now());

            t0.appendToContent("After breakfast");
            t0.appendToContent("After lunch");
            t0.appendToContent("After diner");

            Todo t1 = t0.clone();
            Todo t2 = t0.clone();
            Todo t3 = t0.clone();

            t1.postpone(1, ChronoUnit.DAYS);
            t2.postpone(2, ChronoUnit.DAYS);
            t3.postpone(3, ChronoUnit.DAYS);

            t3.setDescription("Brandanol 2.5 mg");
            t3.appendToTitle(" | Last DAY! :)");
            t3.appendToContent("Before sleep");
            t3.removeAction("After breakfast");
            t3.removeAction(1);

            nl0.add(t0);
            nl0.add(t1);
            nl0.add(t2);
            nl0.add(t3);
            nl0.stream().forEach(n -> { System.out.println(n + "\n"); });

        } catch (IncorrectTimeRangeException te) {
            System.out.println(te);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        } catch (CloneNotSupportedException ce) {
            System.out.println(ce);
        }
    }
}

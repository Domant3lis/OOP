// Personalinė užrašų sistema: adresai, užrašai, darbai, kalendorius
package OOP;

import notes.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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

            Event medicine0 = new Event("Take medicine", "Brandanol 5mg", LocalDateTime.now(), LocalDateTime.now());

            Event medicine1 = medicine0.clone();
            Event medicine2 = medicine0.clone();
            Event medicine3 = medicine0.clone();

            medicine1.postpone(1, ChronoUnit.DAYS);
            medicine2.postpone(2, ChronoUnit.DAYS);
            medicine3.postpone(3, ChronoUnit.DAYS);
            medicine3.setDescription("Brandanol 10mg");
            nl0.add(medicine0);
            nl0.add(medicine1);
            nl0.add(medicine2);
            nl0.add(medicine3);
            nl0.stream().forEach(n -> {
                System.out.println(n + "\n");
            });

            // Event ex0 = new Event("title", "desc", LocalDateTime.now(),
            // LocalDateTime.now().minusSeconds(1));

            Event ex1 = new Event("title", "desc", null, null);

            // This never gets executed since previous statement throws an exception
            // System.out.println(ex0);
            // System.out.println(ex1);

        } catch (IncorrectTimeRangeException te) {
            System.out.println(te);
        } catch (IncorrectArgumentException e) {
            System.out.println(e);
        } catch (CloneNotSupportedException ce) {
            System.out.println(ce);
        }
    }
}

// Personalinė užrašų sistema: adresai, užrašai, darbai, kalendorius
package OOP;

import notes.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.regex.Pattern;
import java.lang.IllegalArgumentException;

public class App {
    public static void main(String[] args) {

        List<Note> notes = new ArrayList<Note>();

        System.out.println("Hello, World!");

        TUI tui = new TUI(notes, List.of("exit", "q"));
        // tui.log = true;

        tui.onStartDo((argList, noteList) -> {
            System.out.println("Type `help` for help!");
        });

        tui.onCommandDo(List.of("help"), (argList, noteList) -> {
            System.out.println("This is help");
        });

        tui.run();
    }
}

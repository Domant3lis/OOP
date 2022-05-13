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

        final String errMsg = "Invalid arguments, type `help` for usage";

        List<Note> notes = new ArrayList<Note>();

        System.out.println("Hello, World!");

        TUI tui = new TUI("Quits the application", notes, List.of("exit", "q"));
        // tui.log = true;

        tui.onStartDo((argList, noteList) -> {
            System.out.println("Type `help` for help!");
        });

        tui.onCommandDo("Displays this page", List.of("help"), (argList, noteList) -> {
            tui.displayHelp();
        });

        tui.onCommandDo("Loads a save file", List.of("load"), (argList, noteList) -> {
            // TODO
        });

        tui.onCommandDo("Saves current information into a file", List.of("save"), (argList, noteList) -> {
            // TODO
        });

        tui.onCommandDo("Creates a new entry", List.of("new"), (argList, noteList) -> {
            try
            {
                switch (argList.get(0)) {
                    case "Note":
                        notes.add(new Note(argList.get(1), argList.get(2)));
                        break;
                    case "Event":
                        System.out.println("TODO");
                        break;
                    // dafault:
                    //     System.out.println(errMsg);
                    //     break;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Wrong number of arguments");
            }

        });

        tui.onCommandDo("Displays all entries", List.of("display"), (argList, noteList) -> {
            noteList.forEach(e -> {System.out.println(e + "\n"); });
        });

        tui.run();
    }
}

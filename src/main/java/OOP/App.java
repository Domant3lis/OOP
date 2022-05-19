// Personalinė užrašų sistema: adresai, užrašai, darbai, kalendorius
package OOP;

import notes.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.regex.Pattern;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.IllegalArgumentException;

public class App {
    public static void main(String[] args) throws Exception{

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

        tui.onCommandDo("Save a file", List.of("save"), (argList, noteList) -> {
            Thread t = new Thread(
                new Runnable() {
                    public void run() {
                        try {
                            FileOutputStream out = new FileOutputStream(argList.get(0));
                            ObjectOutputStream s = new ObjectOutputStream(out);

                            s.writeObject((ArrayList<Note>) noteList);
                            s.flush();
                            s.close();
                            System.out.println("File '" + argList.get(0) + "' has been saved succesfully");
                        } catch (IOException ioEx) { System.out.println(ioEx); }
                    }
                 }
            );
            t.run();
        });

        tui.onCommandDo("Load a file", List.of("load"), (argList, noteList) -> {
            Thread t = new Thread(
                new Runnable() {
                    public void run() {
                        try {
                            FileInputStream in = new FileInputStream(argList.get(0));
                            ObjectInputStream s = new ObjectInputStream(in);

                            tui.setNotesRef((ArrayList<Note>) s.readObject());
                            s.close();
                            System.out.println("File '" + argList.get(0) + "' has been loaded succesfully");
                        } catch (Exception e) { System.out.println(e); }
                    }
                 }
            );
            t.run();
        });

        tui.run();
    }
}

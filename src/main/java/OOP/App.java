// Personalinė užrašų sistema: adresai, užrašai, darbai, kalendorius
package OOP;

import notes.*;

import java.util.LinkedList;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class App {
    public static void main(String[] args) throws Exception{

        final String errMsg = "Invalid arguments, type `help` for usage";

        TUI tui = new TUI("Quits the application", List.of("exit", "q"));
        // tui.log = true;

        tui.onStartDo((argList) -> {
            System.out.println("Type `help` for help!");
        });

        tui.onCommandDo("Displays this page", List.of("help"), (argList) -> {
            tui.displayHelp();
        });

        tui.onCommandDo("Creates a new entry", List.of("new"), (argList) -> {
            try
            {
                switch (argList.get(0)) {
                    case "Note":
                        tui.addNotesRef(new Note(argList.get(1), argList.get(2)));
                        break;
                    case "Contact":
                        tui.addNotesRef(new Contact(argList.get(1), argList.get(2), argList.get(3), argList.get(4), argList.get(5), argList.get(6)));
                        break;
                    // case "Event":
                    //     System.out.println("TODO");
                    //     break;
                    // dafault:
                        // System.out.println(errMsg);
                        // break;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Wrong number of arguments");
            }

        });

        tui.onCommandDo("Displays all entries", List.of("display"), (argList) -> {
            tui.getNotesRef().forEach(e -> {System.out.println(e + "\n"); });
        });

        tui.onCommandDo("Save a file", List.of("save"), (argList) -> {
            Thread t = new Thread(
                new Runnable() {
                    public void run() {
                        try {
                            FileOutputStream out = new FileOutputStream(argList.get(0));
                            ObjectOutputStream s = new ObjectOutputStream(out);

                            s.writeObject((LinkedList<Note>) tui.getNotesRef());
                            s.flush();
                            s.close();
                            System.out.println("File '" + argList.get(0) + "' has been saved succesfully");
                        } catch (IOException ioEx) { System.out.println(ioEx); }
                    }
                 }
            );
            t.start();
        });

        tui.onCommandDo("Load a file", List.of("load"), (argList) -> {
            Thread t = new Thread(
                new Runnable() {
                    public void run() {
                        try {
                            FileInputStream in = new FileInputStream(argList.get(0));
                            ObjectInputStream s = new ObjectInputStream(in);

                            tui.setNotesRef((List<Note>) s.readObject());
                            s.close();
                            System.out.println("File '" + argList.get(0) + "' has been loaded succesfully");
                        } catch (Exception e) { System.out.println(e); }
                    }
                 }
            );
            t.start();
        });

        tui.start();
    }
}

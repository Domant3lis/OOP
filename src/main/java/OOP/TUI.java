package OOP;

import notes.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.*;

public class TUI extends Thread {
    public static final String ANSI_CLEAR = "\033[H\033[2J";
    public static String lineBeginning = "> ";
    public static String errorMsg = "Failed to parse this line";

    public boolean log = false;

    private List<String> exitCommand;

    private List<Note> notesRef;

    private BiConsumer<List<String>, List<Note>> initAction;

    private HashMap<String, BiConsumer<List<String>, List<Note>>> actions = new HashMap<>();

    public TUI(List<Note> notesRef, List<String> exitCommand) {
        this.exitCommand = new ArrayList<>(exitCommand);
        this.notesRef = notesRef;
        this.initAction = (l0, l1) -> {
            System.out.println("HELLLLLLLLLO");
        };
    }

    public static void clearScreen() {
        System.out.print(ANSI_CLEAR);
        System.out.flush();
    }

    public void onCommandDo(List<String> commands, BiConsumer<List<String>, List<Note>> action) {
        for (String command : commands)
            actions.put(command, action);
    }

    public void onStartDo(BiConsumer<List<String>, List<Note>> action) {
        this.initAction = action;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String inputLine;

        clearScreen();
        initAction.accept(List.of(), List.of());

        while (true) {
            System.out.print(lineBeginning);
            inputLine = scanner.nextLine();

            List<String> input = List.of(inputLine.split(" "));
            if (log) {
                System.out.println(input.get(0) + "'");
                scanner.nextLine();
            }

            if (exitCommand.stream().anyMatch(ec -> ec.equals(input.get(0))))
                break;

            // Match commands to actions
            if (this.actions.containsKey(input.get(0))) {
                this.actions.get(input.get(0)).accept(
                        input.subList(1, input.size()),
                        notesRef);
            } else {
                System.out.println(errorMsg);
            }

        }

        scanner.close();
    }

    // private List<String> parse(String )
    // {

    // }
}
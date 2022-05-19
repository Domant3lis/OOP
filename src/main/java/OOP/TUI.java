package OOP;

import notes.*;

import java.io.IOException;
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
    public static String commandHelpSep = " - ";

    public boolean log = false;

    private List<String> exitCommand;

    private List<String> helpInstructions = new ArrayList<String>();

    private List<Note> notesRef;

    private BiConsumer<List<String>, List<Note>> initAction;

    private HashMap<String, BiConsumer<List<String>, List<Note>>> actions = new HashMap<>();

    public TUI(String exitHelp, List<Note> notesRef, List<String> exitCommand) {
        this.exitCommand = new ArrayList<>(exitCommand);
        this.notesRef = notesRef;
        this.initAction = (l0, l1) -> {};
        this.helpInstructions.add(exitCommand.toString() + commandHelpSep + exitHelp);
    }

    public static void clearScreen() {
        System.out.print(ANSI_CLEAR);
        System.out.flush();
    }

    public void onCommandDo(
        String help,
        List<String> commands,
        BiConsumer<List<String>, List<Note>> action)
    {
        this.helpInstructions.add(new String(commands.toString() + commandHelpSep + help));

        for (String command : commands)
            actions.put(command, action);
    }

    public void displayHelp()
    {
        this.helpInstructions.forEach(System.out::println);
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

            // Searches for exit command
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

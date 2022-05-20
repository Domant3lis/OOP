package OOP;

import notes.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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

    private List<Note> notesRef = new LinkedList<>();

    private Consumer<List<String>> initAction;

    private HashMap<String, Consumer<List<String>>> actions = new HashMap<>();

    public TUI(String exitHelp, List<String> exitCommand) {
        this.exitCommand = new ArrayList<>(exitCommand);
        this.initAction = (l0) -> {};
        this.helpInstructions.add(exitCommand.toString() + commandHelpSep + exitHelp);
    }

    public static void clearScreen() {
        System.out.print(ANSI_CLEAR);
        System.out.flush();
    }

    private boolean commandConflict(List<String> commands)
    {
        for (String com : commands)
        {
            if (this.actions.keySet().contains(com))
                return true;
        }
        return false;
    }

    public void onCommandDo(
        String help,
        List<String> commands,
        Consumer<List<String>> action)
        throws Exception
    {
        if (commandConflict(commands))
            throw new Exception("This command name is already taken");

        this.helpInstructions.add(new String(commands.toString() + commandHelpSep + help));

        for (String command : commands)
            actions.put(command, action);
    }

    public void displayHelp()
    {
        this.helpInstructions.forEach(System.out::println);
    }

    public void onStartDo(Consumer<List<String>> action) {
        this.initAction = action;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String inputLine;

        clearScreen();
        initAction.accept(List.of());

        while (true) {
            System.out.print(lineBeginning);
            inputLine = scanner.nextLine();

            List<String> input = List.of(inputLine.split("; "));

            // Searches for exit command
            if (exitCommand.stream().anyMatch(ec -> ec.equals(input.get(0))))
                break;

            // Match commands to actions
            if (this.actions.containsKey(input.get(0))) {
                this.actions.get(input.get(0))
                    .accept(input.subList(1, input.size()));
            } else {
                System.out.println(errorMsg);
            }
        }

        scanner.close();
    }

    public List<Note> getNotesRef() { return this.notesRef; }
    public void setNotesRef(List<Note> notes) { this.notesRef = notes; }
    public void addNotesRef(Note note) { this.notesRef.add(note); }
}

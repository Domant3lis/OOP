package OOP.notes;

import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Todo extends Note{
    private LocalDateTime deadline; 
    List<String> actions;

    public Todo(String title, String desc, LocalDateTime deadline)
    {
        super(title, desc);
        this.deadline = deadline;
        this.actions = new ArrayList<String>();
    }

    public Todo clone() throws CloneNotSupportedException
    {
        Todo newTodo = (Todo) super.clone();
        newTodo.setActions(new ArrayList<String>(newTodo.getActions()));

        return newTodo;
    }

    public String toString()
    {
        String ret = super.toString().concat("\nDeadline: " + deadline + "\nActions:");
        for (String action : this.actions)
            ret = ret.concat("\n- " + action);

        return ret;
    }

    public void appendToContent(String appendix) { this.actions.add(appendix); }

    public boolean contentContains(String match)
    {
        return this.actions.stream().anyMatch(a -> a.contains(match));
    }

    public boolean contentContains(Pattern regex)
    {
        for (String action : this.actions)
        {
            Matcher matcher = regex.matcher(action);
            if (matcher.find())
                return true;

        }

        return false;
    }

    public void postpone(long amount, TemporalUnit unit)
    {
        this.deadline = this.deadline.plus(amount, unit);
    }

    public void removeAction(String match) { this.actions.remove(match); }
    public void removeAction(int index) { this.actions.remove(index); }

    // Getters and setters
    public List<String> getActions() { return this.actions; }
    public void setActions(List <String> actions) {this.actions = actions; }
    public LocalDateTime getDeadline() { return this.deadline; }
    public void setDeadline(LocalDateTime deadline) { this.deadline = deadline; }
}

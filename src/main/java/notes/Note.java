package notes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.String;

public class Note implements Cloneable {
    final private LocalDateTime creationDateTime = LocalDateTime.now();

    private String title;
    private List<Note> subnotes = new ArrayList<Note>();

    public Note(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return new String(
        "Title: " + this.title +
        "\nCreated at: " + this.creationDateTime);
    }

    public Note clone() throws CloneNotSupportedException {
        Note newNote = (Note) super.clone();
        this.subnotes = new ArrayList<Note>(getSubnotes());

        return newNote;
    }

    // public boolean titleContains(String match) {
    //     return this.title.contains(match);
    // }

    // public boolean titleContains(Pattern regex) {
    //     Matcher title = regex.matcher(this.title);
    //     if (title.find())
    //         return true;

    //     return false;
    // }

    // public boolean contains(String match) {
    //     return this.creationDateTime.toString().contains(match) || titleContains(match);
    // }

    // // public boolean contains(Pattern regex) {
    // //     return titleContains(regex);
    // // }

    // public boolean contentContains(String match)
    // {
    //     return contains(match) ||
    //         getSubnotes().stream().anyMatch(note -> note.itselfContains(match));
    // }

    // public boolean contentContains(Pattern regex)
    // {
    //     boolean ret = false;
    //     ret ||= 
    //     return ret; // itselfContains(regex)
    // }

    public boolean isCreatedBetweenTimedRange(LocalDateTime start, LocalDateTime end, boolean startInclusive,
            boolean endInclusive) {
        boolean isAfterStart, isBeforeEnd;

        isAfterStart = isCreatedAfter(start);
        if (startInclusive)
            isAfterStart |= isCreatedAt(start);

        isBeforeEnd = isCreatedBefore(end);
        if (endInclusive)
            isBeforeEnd = isCreatedAt(end);

        return isAfterStart && isBeforeEnd;

    }

    public boolean isCreatedBefore(LocalDateTime time) {
        return this.creationDateTime.isBefore(time);
    }

    public boolean isCreatedAfter(LocalDateTime time) {
        return this.creationDateTime.isAfter(time);
    }

    public boolean isCreatedAt(LocalDateTime time) {
        return this.creationDateTime.isEqual(time);
    }

    // Appends a string to the title
    final public void appendToTitle(String appendix) {
        this.title = this.title.concat(appendix);
    }

    final public void addSubNote(Note appendix) 
        { this.subnotes.add(appendix); }
    
    final public List<Note> getSubNotes()
    {
        return this.subnotes;
    }

    // Getters and setters
    public LocalDateTime getCreationDateTime() { return creationDateTime; }
    public String getTitle() { return this.title; }
    public void setTitle(String title) { this.title = title; }
    public List<Note> getSubnotes() { return this.subnotes; }
}

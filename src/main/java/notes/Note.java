package notes;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.String;

public class Note implements Serializable, Cloneable {
    final private LocalDateTime creationDateTime;

    private String title, description;

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
        this.creationDateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return new String(
                "Title: " + this.title +
                        "\nDescription: " + this.description +
                        "\nCreated at: " + this.creationDateTime);
    }

    public Note clone() throws CloneNotSupportedException {
        Note newNote = (Note) super.clone();
        return newNote;
    }

    public boolean titleContains(String match) {
        return (this.title.contains(match));
    }

    public boolean titleContains(Pattern regex) {
        Matcher title = regex.matcher(this.title);
        if (title.find())
            return true;

        return false;
    }

    public boolean descriptionContains(String match) {
        return (this.description.contains(match));
    }

    public boolean descriptionContains(Pattern regex) {
        Matcher title = regex.matcher(this.description);
        if (title.find())
            return true;

        return false;
    }

    // abstract public boolean contentContains(String match);

    // abstract public boolean contentContains(Pattern regex);

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

    // Appends a string to the contents of the note
    // public void appendToContent(String appendix);

    // Appends a string to the title
    final public void appendToTitle(String appendix) {
        this.title = this.title.concat(appendix);
    }

    // Appends a string to the title
    final public void appendToDescription(String appendix) {
        this.description = this.description.concat(appendix);
    }

    // Getters and setters
    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

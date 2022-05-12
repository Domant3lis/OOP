package notes;

import java.util.Optional;
import java.util.regex.Pattern;

public interface Searchable
{
    public boolean itselfContains(String match);
    public boolean itselfContains(Pattern regex);
    public boolean contains(String match);
    public boolean contains(Pattern regex);
    public Optional<Note> subNoteContains(String match);
    public Optional<Note> subNoteContains(Pattern regex);
}
package notes;

import java.util.regex.Pattern;

public interface Searchable
{
    public boolean titleContains(String match);
    public boolean titleContains(Pattern regex);
    public boolean descriptionContains(String match);
    public boolean descriptionContains(Pattern regex);
    public boolean contentContains(String match);
    public boolean contentContains(Pattern regex);
}
package items;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Note
{
    final private LocalDateTime creationDateTime;
	
	private String title;
    private String description;
    
	public Note(String title, String desc)
	{
		this.title = title;
		this.description = desc;
        this.creationDateTime = LocalDateTime.now();
	}
	
    @Override
	public String toString()
	{
        return new String(
            "Title: " + this.title +
            "\nDescription: " + this.description + 
            "\nCreated at: " + this.creationDateTime
        );
	}

    public boolean contains(String match)
    {
        if (description == null)
            return false;

        if (this.description.contains(match) || this.title.contains(match))
            return true;

        return false;
    }
    
    public boolean contains(Pattern regex)
    {
        if (description != null)
        {
            Matcher desc = regex.matcher(this.description);
            if (desc.find())
                return true;
        }
        
        Matcher title = regex.matcher(this.title);
        if (title.find())
            return true;
        
        return false;
    }
	
    // Appends a string to the description
    final public void appendToDescription(String appendix) {
        if (description != null)
		    this.description = this.description.concat(appendix);
        else
            this.description = appendix;
	}
	
    // Appends a string to the title
    final public void appendToTitle(String appendix) {
		this.title = this.title.concat(appendix);
	}
	
	// Getters and setters
    public LocalDateTime getCreationDateTime() { return creationDateTime; }
    public String getDescription() { return this.description; }
    public String getTitle() { return this.title; }
    
    public void setDescription(String description) { this.description = description; }
    public void setTitle(String title) { this.title = title; }
}

package OOP.items;

import java.time.LocalDateTime;

public class Item
{
    protected static int itemCount = 0;
    final protected LocalDateTime creationDateTime;
	
	protected String title;
    protected String description;
    
	public Item(String title, String desc)
	{
		itemCount += 1;
		this.title = title;
		this.description = desc;
        this.creationDateTime = LocalDateTime.now();
	}
	
	// Getters and setters
	final public int getInstanceCount() { return itemCount; }
    public LocalDateTime getCreationTime() { return creationDateTime; }
    public String getDescription() { return this.description; }
    public String getTitle() { return this.title; }
    
    public void setDescription(String description) { this.description = description; }
    public void setTitle(String title) { this.title = title; }
	
	protected void finalize() throws java.lang.Throwable
	{
		itemCount -= 1;
		super.finalize();
	}
}

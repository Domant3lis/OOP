package OOP.items;

import java.awt.Color;

public class Note extends Item
{
	private static Color defaultColor = Color.WHITE;
	private Color noteColor;
	
	Note()
	{
		super(new String("Note #" + (itemCount + 1)), "");
		this.noteColor = defaultColor;
	}
}

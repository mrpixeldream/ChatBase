package de.mrpixeldream.beta.textsender.client.controls;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import de.mrpixeldream.beta.textsender.client.ChatFrame;

public class ChatBoxMouseListener extends MouseAdapter
{
	private ChatFrame parent;
	
	public ChatBoxMouseListener(ChatFrame parent)
	{
		this.parent = parent;
	}
	
	@Override
	public void mouseEntered(MouseEvent e)
	{
		if (this.parent.messageField.getText().equalsIgnoreCase("") && !this.parent.messageField.hasFocus())
		{
			this.parent.messageField.requestFocus();
		}
	}
}
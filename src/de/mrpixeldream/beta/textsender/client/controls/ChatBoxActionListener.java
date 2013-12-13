package de.mrpixeldream.beta.textsender.client.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.mrpixeldream.beta.textsender.client.ChatFrame;
import de.mrpixeldream.beta.textsender.client.ClientMain;

public class ChatBoxActionListener implements ActionListener
{
	ChatFrame parent;
	
	public ChatBoxActionListener(ChatFrame parent)
	{
		this.parent = parent;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (!this.parent.messageField.getText().equalsIgnoreCase(""))
		{
			ClientMain.client.sender.println("BROADCAST " + this.parent.messageField.getText());
			ClientMain.client.sender.flush();
			this.parent.messageField.setText("");
		}
	}
}

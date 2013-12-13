package de.mrpixeldream.beta.textsender.client.controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.mrpixeldream.beta.textsender.client.ChatFrame;
import de.mrpixeldream.beta.textsender.client.ClientMain;
import de.mrpixeldream.beta.textsender.client.LoginFrame;

public class LoginActionListener implements ActionListener
{
	private LoginFrame parent;
	
	public LoginActionListener(LoginFrame parent)
	{
		this.parent = parent;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.parent.btnEinloggen || e.getSource() == this.parent.txtName)
		{
			String srvMessage = "LOGIN " + this.parent.txtName.getText().trim();
			ClientMain.client.sender.println(srvMessage);
			ClientMain.client.sender.flush();
				
			if (!ClientMain.client.receiver.nextLine().startsWith("Successfully"))
			{
				System.err.println("Login failure");
			}
			else
			{
				this.parent.setVisible(false);
				ChatFrame.main();
			}
		}
	}

}

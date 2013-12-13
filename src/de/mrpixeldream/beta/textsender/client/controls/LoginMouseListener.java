package de.mrpixeldream.beta.textsender.client.controls;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import de.mrpixeldream.beta.textsender.client.LoginFrame;

public class LoginMouseListener extends MouseAdapter
{
	private LoginFrame parent;
	
	public LoginMouseListener(LoginFrame parent)
	{
		this.parent = parent;
	}
	
	@Override
	public void mouseEntered(MouseEvent e)
	{
		if (this.parent.txtName.getText().equalsIgnoreCase("Name..."))
		{
			this.parent.txtName.setText("");
		}
	}
	
	@Override
	public void mouseExited(MouseEvent e)
	{
		if (this.parent.txtName.getText().equalsIgnoreCase(""))
		{
			this.parent.txtName.setText("Name...");
		}
	}
}
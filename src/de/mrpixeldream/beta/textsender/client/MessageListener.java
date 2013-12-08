package de.mrpixeldream.beta.textsender.client;


public class MessageListener extends Thread
{
	@Override
	public void run()
	{
		while (!ClientMain.client.connection.isClosed() || !ClientMain.client.receiver.hasNextLine())
		{
			String msg = ClientMain.client.receiver.nextLine();
			ChatFrame.chatFrame.addMessage(msg);
		}
	}
}
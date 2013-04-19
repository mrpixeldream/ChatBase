package de.mrpixeldream.beta.textsender.server.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import de.mrpixeldream.beta.textsender.server.TextSenderServer;

public class ClientHandler extends Thread
{
	Socket client;
	Scanner input;
	PrintWriter output;
	
	public ClientHandler(Socket client)
	{
		this.client = client;
		try
		{
			this.input = new Scanner(client.getInputStream());
			this.output = new PrintWriter(client.getOutputStream());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void run()
	{
		String msg = "";
		
		do
		{
			msg = "";
			
			try
			{
				msg = this.input.nextLine();
				msg.toUpperCase();
			}
			catch (Exception e)
			{
				TextSenderServer.log("Failed to read line from client!");
				msg = "IDLE";
			}
			
			if (msg.startsWith("LOGIN"))
			{
				this.output.println(TextSenderServer.doLogin(client, msg.split(" ")[1]));
				this.output.flush();
			}
			if (msg.startsWith("SHOW"))
			{
				for (Object now : TextSenderServer.listClients())
				{
					this.output.println(now);
				}
				this.output.flush();
			}
			if (msg.startsWith("SEND"))
			{
				String id = msg.split(" ")[1];
				String send = msg.split(" ")[2];
				try
				{
					Integer.parseInt(id);
					TextSenderServer.sendMessage(id, send);
				}
				catch (NumberFormatException e)
				{
					TextSenderServer.sendMessage(TextSenderServer.idByName(id), send);
				}
			}
		} while (!msg.equalsIgnoreCase("LOGOUT"));
		
		TextSenderServer.doLogout(client.getInetAddress());
	}
}
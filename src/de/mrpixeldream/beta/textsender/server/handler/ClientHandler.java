package de.mrpixeldream.beta.textsender.server.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import de.mrpixeldream.beta.textsender.server.TextSenderServer;

public class ClientHandler extends Thread
{
	Socket client;
	String id;
	Scanner input;
	PrintWriter output;
	
	public ClientHandler(Socket client)
	{
		this.client = client;
		
		
		
		try
		{
			this.input = new Scanner(client.getInputStream());
			this.output = new PrintWriter(client.getOutputStream());
			
			this.output.println("ACK");
			this.output.flush();
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
				TextSenderServer.log("Failed to read line from client! Logging out...");
				msg = "LOGOUT";
			}
			
			if (msg.toUpperCase().startsWith("LOGIN"))
			{
				this.output.println(TextSenderServer.doLogin(client, msg.split(" ")[1]));
				this.output.flush();
				
				this.id = TextSenderServer.ips.get(client.getInetAddress());
			}
			if (msg.toUpperCase().startsWith("SHOW"))
			{
				for (Object now : TextSenderServer.listClients())
				{
					this.output.println(now);
				}
				this.output.flush();
			}
			if (msg.toUpperCase().startsWith("SEND"))
			{
				String id = msg.split(" ")[1];
				String send = "";
				
				for (int i = 2; i < msg.split(" ").length; i++)
				{
					send += msg.split(" ")[i] + " ";
				}
				
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
			if (msg.toUpperCase().startsWith("BROADCAST"))
			{
				String send = TextSenderServer.clientNameByID(id) + ": ";
				
				for (int i = 1; i < msg.split(" ").length; i++)
				{
					send += msg.split(" ")[i] + " ";
				}
				
				TextSenderServer.broadcastMessage(send);
			}
		} while (!msg.equalsIgnoreCase("LOGOUT"));
		
		TextSenderServer.doLogout(client.getInetAddress());
	}
}
package de.mrpixeldream.beta.textsender.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientMain
{
	public static ClientMain client;
	
	// Declaring public objects which should be accessible from the frame classes
	public Socket connection;
	public PrintWriter sender;
	public Scanner receiver;
	
	// Declaring private, internal objects which are only for decorating the streams
	private BufferedOutputStream outStream;
	private BufferedInputStream inStream;
	
	public ClientMain(String[] args)
	{
		try
		{
			// Creating a connection to the main server
			this.connection = new Socket("84.201.35.70", 22558);
			
			// Initializing the raw streams to and from
			this.outStream = new BufferedOutputStream(this.connection.getOutputStream());
			this.inStream = new BufferedInputStream(this.connection.getInputStream());
			
			// Decorate the streams to get a better internal handling
			this.sender = new PrintWriter(this.outStream);
			this.receiver = new Scanner(this.inStream);
			
			// Search for the ACK flag from server to see that it got our connection
			if (this.receiver.nextLine().equalsIgnoreCase("ACK"))
			{
				LoginFrame.main(args);
			}
			else
			{
				System.err.println("Connection wasn't initialized correct.");
			}
		}
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void exitProgram()
	{
		System.out.println("dispose");
		ClientMain.client.sender.println("LOGOUT");
		ClientMain.client.sender.flush();
		ClientMain.client.sender.close();
		ClientMain.client.receiver.close();
		try
		{
			ClientMain.client.connection.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	public static void main(String[] args)
	{
		// Create the client
		client = new ClientMain(args);
	}
}
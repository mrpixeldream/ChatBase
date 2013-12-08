package de.mrpixeldream.beta.textsender.server;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Random;

import de.mrpixeldream.beta.textsender.server.handler.ClientHandler;

public class TextSenderServer
{
	static int port;
	
	static FileWriter logger;
	
	static HashMap<String, Socket> clients;
	static HashMap<String, String> names;
	static public HashMap<InetAddress, String> ips;
	static HashMap<String, String> ids;
	
	static Random idGenerator;
	
	static ServerSocket server;
	
	public static void main(String[] args)
	{
		File logfile = new File("C:\\Users\\Niklas\\Desktop\\chatbase.log"); // TODO: Pfad anpassen!
		
		System.out.println("Welcome to ChatBase chat server! The small chat solution.");
		System.out.println("Setting up variables...");
		
		clients = new HashMap<String, Socket>();
		names = new HashMap<String, String>();
		ips = new HashMap<InetAddress, String>();
		ids = new HashMap<String, String>();
		
		System.out.println("Done. Creating logger...");
		
		try
		{
			logger = new FileWriter(logfile, true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Logger created, events are logged now.");
		
		if (args.length == 1)
		{
			port = Integer.parseInt(args[0]);
			try
			{
				log("Set port to " + port);
				System.out.println("Server port: " + port);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			port = 22558;
			try
			{
				log("Set port to " + port);
				System.out.println("Server port: " + port);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		
		log("Creating socket...");
		System.out.println("Creating socket...");
		
		try
		{
			server = new ServerSocket(port);
			System.out.println("Server now listening for new connections...");
			
			while (true)
			{
				Socket client;
				client = server.accept();
				System.out.println("Connected from " + client.getInetAddress());
				new ClientHandler(client).start();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
			log("Got error while creating socket:");
			log(e.getMessage());
		}
		
		try
		{
			System.out.println("Closing logger...");
			logger.flush();
			logger.close();
			System.out.println("Closed. Server shutting down!");
		}
		catch (Exception ex)
		{
			System.err.println("Logger not closed properly.");
		}
	}
	
	private static String datePrefix()
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd | HH:mm:ss");
		Date currentTime = new Date(System.currentTimeMillis());
		return "[" + format.format(currentTime) + "]: ";
	}
	
	public static void log(String msg)
	{
		try
		{
			logger.write(datePrefix() + msg + "\n\n");
			logger.flush();
		}
		catch (Exception ex)
		{
			System.err.println("Can't write to log.");
		}
		System.err.println(msg);
	}
	
	public static String doLogin(Socket client, String name)
	{
		String id;
		if (!clients.containsValue(client.getInetAddress()) && !names.containsValue(name) && !ips.containsKey(client.getInetAddress()))
		{
			do
			{
				id = makeId();
			} while (clients.containsKey(id));
			
			clients.put(id, client);
			names.put(id, name);
			ips.put(client.getInetAddress(), id);
			ids.put(name, id);
			System.out.println("Logged in from " + client.getInetAddress() + " with ID " + id);
			return "Successfully logged in! Got ID: " + id;
		}
		else if (names.containsValue(name))
		{
			return "This user is already online!";
		}
		else if (ips.containsKey(client.getInetAddress()))
		{
			return "This IP is already connected!";
		}
		else
		{
			return "Login failed. Unkown error!";
		}
	}
	
	public static void doLogout(InetAddress addr)
	{
		String clientID = ips.get(addr);
		sendMessage(clientID, "Logging you out...");
		Socket sock = clients.get(clientID);
		clients.remove(clientID);
		try
		{
			sock.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		names.remove(clientID);
		ips.remove(addr);
	}
	
	private static String makeId()
	{
		idGenerator = new Random(System.currentTimeMillis() | 5);
		String id = String.valueOf(idGenerator.nextInt(9998) + 1);
		while (id.length() < 4)
		{
			String tmp = "0";
			id = tmp + id;
		}
		
		return id;
	}
	
	public static Object[] listClients()
	{
		return names.keySet().toArray();
	}
	
	public static String clientNameByID(String id)
	{
		return names.get(id);
	}
	
	public static String idByName(String name)
	{
		if (ids.containsKey(name))
		{
			return ids.get(name);
		}
		else
		{
			return "No client with this ID found!";
		}
	}
	
	public static void sendMessage(String id, String message)
	{
		try
		{
			PrintWriter writer = new PrintWriter(clients.get(id).getOutputStream());
			writer.println(message);
			writer.flush();
			writer = null;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void broadcastMessage(String message)
	{	
		try
		{
			for (String client : clients.keySet())
			{
				PrintWriter writer = new PrintWriter(clients.get(client).getOutputStream());
				writer.println(message);
				writer.flush();
				writer = null;
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

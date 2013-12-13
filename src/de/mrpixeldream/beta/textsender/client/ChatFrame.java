package de.mrpixeldream.beta.textsender.client;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import de.mrpixeldream.beta.textsender.client.controls.ChatBoxActionListener;
import de.mrpixeldream.beta.textsender.client.controls.ChatBoxMouseListener;

public class ChatFrame extends JFrame
{
	public static ChatFrame chatFrame;
	
	private JPanel contentPane;
	public JTextField messageField;
	private JEditorPane chatPanel;
	
	private ChatBoxActionListener chatBoxActionListener = new ChatBoxActionListener(this);
	private ChatBoxMouseListener chatBoxMouseListener = new ChatBoxMouseListener(this);

	/**
	 * Launch the application.
	 */
	public static void main()
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					chatFrame = new ChatFrame();
					chatFrame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChatFrame()
	{
		setTitle("ChatBase - the small chat solution");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		messageField = new JTextField();
		messageField.setText("");
		messageField.setBounds(10, 230, 315, 20);
		messageField.addActionListener(chatBoxActionListener);
		messageField.addMouseListener(chatBoxMouseListener);
		contentPane.add(messageField);
		messageField.setColumns(10);
		
		JButton sendButton = new JButton("Senden");
		sendButton.addActionListener(chatBoxActionListener);
		sendButton.setBounds(335, 229, 89, 23);
		contentPane.add(sendButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 208);
		contentPane.add(scrollPane);
		
		chatPanel = new JEditorPane();
		chatPanel.setEditable(false);
		chatPanel.setBackground(Color.WHITE);
		scrollPane.setViewportView(chatPanel);
		
		new MessageListener().start();
		
		messageField.requestFocus();
	}
	
	public void addMessage(String message)
	{
		Document chatContent = chatPanel.getDocument();
		try
		{
			chatContent.insertString(chatContent.getLength(), message + "\n", null);
		}
		catch (BadLocationException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void dispose()
	{
		ClientMain.exitProgram();
	}
}

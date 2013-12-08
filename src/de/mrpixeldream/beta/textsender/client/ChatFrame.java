package de.mrpixeldream.beta.textsender.client;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class ChatFrame extends JFrame implements ActionListener
{
	public static ChatFrame chatFrame;
	
	private JPanel contentPane;
	private JTextField messageField;
	private JEditorPane chatPanel;

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
		messageField.setText("Nachricht");
		messageField.setBounds(10, 230, 315, 20);
		contentPane.add(messageField);
		messageField.setColumns(10);
		
		JButton sendButton = new JButton("Senden");
		sendButton.addActionListener(this);
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
	}
	
	public void actionPerformed(ActionEvent arg0)
	{
		ClientMain.client.sender.println("BROADCAST " + this.messageField.getText());
		ClientMain.client.sender.flush();
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

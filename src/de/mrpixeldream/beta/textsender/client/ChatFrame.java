package de.mrpixeldream.beta.textsender.client;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ChatFrame extends JFrame
{

	private JPanel	contentPane;
	private JEditorPane dtrpnnameIchBin;
	private JButton btnVerlassen;
	private JTextField txtNachricht;
	private JButton btnAbsenden;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					ChatFrame frame = new ChatFrame();
					frame.setVisible(true);
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 381);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);
		
		this.dtrpnnameIchBin = new JEditorPane();
		this.dtrpnnameIchBin.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.dtrpnnameIchBin.setText("[Name]: Ich bin cool");
		this.dtrpnnameIchBin.setBounds(10, 11, 525, 284);
		this.contentPane.add(this.dtrpnnameIchBin);
		
		this.btnVerlassen = new JButton("Verlassen...");
		this.btnVerlassen.setBounds(430, 306, 105, 23);
		this.contentPane.add(this.btnVerlassen);
		
		this.txtNachricht = new JTextField();
		this.txtNachricht.setText("Nachricht...");
		this.txtNachricht.setBounds(10, 309, 122, 20);
		this.contentPane.add(this.txtNachricht);
		this.txtNachricht.setColumns(10);
		
		this.btnAbsenden = new JButton("Absenden!");
		this.btnAbsenden.setBounds(142, 306, 89, 23);
		this.contentPane.add(this.btnAbsenden);
	}
}

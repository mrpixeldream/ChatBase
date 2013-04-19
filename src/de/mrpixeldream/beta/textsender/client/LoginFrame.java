package de.mrpixeldream.beta.textsender.client;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends JFrame
{

	private JPanel	contentPane;
	private JLabel lblBitteEinenBenutzernamen;
	private JTextField txtName;
	private JButton btnEinloggen;

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
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 434, 135);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);
		
		this.lblBitteEinenBenutzernamen = new JLabel("Bitte einen Benutzernamen w\u00E4hlen:");
		this.lblBitteEinenBenutzernamen.setFont(new Font("Tahoma", Font.BOLD, 15));
		this.lblBitteEinenBenutzernamen.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblBitteEinenBenutzernamen.setBounds(10, 11, 398, 26);
		this.contentPane.add(this.lblBitteEinenBenutzernamen);
		
		this.txtName = new JTextField();
		this.txtName.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtName.setText("Name...");
		this.txtName.setBounds(20, 48, 155, 35);
		this.contentPane.add(this.txtName);
		this.txtName.setColumns(10);
		
		this.btnEinloggen = new JButton("Einloggen!");
		this.btnEinloggen.setBounds(257, 48, 108, 35);
		this.contentPane.add(this.btnEinloggen);
	}
}

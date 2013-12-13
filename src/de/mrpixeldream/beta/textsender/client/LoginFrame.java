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

import de.mrpixeldream.beta.textsender.client.controls.LoginActionListener;
import de.mrpixeldream.beta.textsender.client.controls.LoginMouseListener;

public class LoginFrame extends JFrame
{
	public JPanel	contentPane;
	public JLabel lblBitteEinenBenutzernamen;
	public JTextField txtName;
	public JButton btnEinloggen;
	
	private LoginActionListener loginActionListener;
	private LoginMouseListener loginMouseListener;

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
		this.loginActionListener = new LoginActionListener(this);
		this.loginMouseListener = new LoginMouseListener(this);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		this.txtName.addMouseListener(loginMouseListener);
		this.contentPane.add(this.txtName);
		this.txtName.addActionListener(this.loginActionListener);
		this.txtName.setColumns(10);
		
		this.btnEinloggen = new JButton("Anmelden");
		this.btnEinloggen.setBounds(257, 48, 108, 35);
		this.btnEinloggen.addActionListener(this.loginActionListener);
		this.contentPane.add(this.btnEinloggen);
	}
	
	@Override
	public void dispose()
	{
		ClientMain.exitProgram();
	}
}

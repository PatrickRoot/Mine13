package org.sixlab.myqqclient;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.sixlab.myqqutil.PortVerify;

public class Client extends JFrame
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private JPanel				maPanel;
	private JLabel				userJLabel;
	private JLabel				hostJLabel;
	private JLabel				portJLabel;
	
	private JTextField			userJTextField;
	private JTextField			hostJTextField;
	private JTextField			portJTextField;
	
	private JButton				loginJButton;
	private JButton				resetJButton;
	
	private ClientConn			conn;
	
	public Client()
	{
		super("��¼");
		this.setResizable(false);
		this.setLocationByPlatform(true);
		maPanel = new JPanel();
		maPanel.setLayout(new GridLayout(4, 2));
		
		maPanel.setBorder(BorderFactory.createTitledBorder("��¼��Ϣ"));
		
		userJLabel = new JLabel("�û���");
		userJTextField = new JTextField(10);
		hostJLabel = new JLabel("��ַ");
		hostJTextField = new JTextField(10);
		portJLabel = new JLabel("�˿ں�");
		portJTextField = new JTextField(10);
		
		loginJButton = new JButton("��¼");
		resetJButton = new JButton("����");
		
		userJTextField.setText("user" + new Random().nextInt(1000));
		hostJTextField.setText("localhost");
		portJTextField.setText("5000");
		
		maPanel.add(userJLabel);
		maPanel.add(userJTextField);
		maPanel.add(hostJLabel);
		maPanel.add(hostJTextField);
		maPanel.add(portJLabel);
		maPanel.add(portJTextField);
		maPanel.add(loginJButton);
		maPanel.add(resetJButton);
		
		this.add(maPanel);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		resetJButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				userJTextField.setText("user" + new Random().nextInt(1000));
				hostJTextField.setText("localhost");
				portJTextField.setText("5000");
			}
		});
		
		loginJButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				runConn();
			}
		});
	}
	
	private void runConn()
	{
		String userName = userJTextField.getText();
		String hostName = hostJTextField.getText();
		String portStri = portJTextField.getText();
		int portName = PortVerify.verifyString(portStri);
		if (portName != -1)
		{
			conn = new ClientConn(this, userName, hostName, portName);
			conn.start();
		} else
		{
			JOptionPane.showMessageDialog(this, "��������ȷ�˿ں�", "����",
					JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public static void main(String[] args)
	{
		Client client = new Client();
		
		client.setVisible(true);
	}
}

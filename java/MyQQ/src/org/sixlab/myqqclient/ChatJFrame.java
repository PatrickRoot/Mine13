package org.sixlab.myqqclient;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.sixlab.myqqutil.HandleXML;

public class ChatJFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private JPanel				messageJPanel;
	private JPanel				listJPanel;
	private JPanel				sendJPanel;
	private JPanel				buttonJPanel;
	
	private JTextArea			logJTextArea;
	private JTextArea			listJTextArea;
	
	private JScrollPane			logJScrollPane;
	private JScrollPane			listJScrollPane;
	
	private JTextArea			inpuTextArea;
	private JScrollPane			inputJScrollPane;
	private JButton				sendJButton;
	private JButton				clearJButton;
	
	private Socket				chatSocket;
	private String				userName;
	
	public JTextArea getLogJTextArea()
	{
		return logJTextArea;
	}
	
	public JTextArea getListJTextArea()
	{
		return listJTextArea;
	}
	
	public ChatJFrame(String userName, String host, int port, Socket socket)
	{
		super(userName + "@" + host + ":" + port);
		
		this.setResizable(false);
		this.setLocationByPlatform(true);
		
		this.userName = userName;
		this.chatSocket = socket;
		
		messageJPanel = new JPanel();
		messageJPanel.setBorder(BorderFactory.createTitledBorder("聊天信息"));
		messageJPanel.setLayout(new BorderLayout());
		
		listJPanel = new JPanel();
		listJPanel.setBorder(BorderFactory.createTitledBorder("用户列表"));
		
		logJTextArea = new JTextArea();
		logJTextArea.setLineWrap(true);
		logJTextArea.setEditable(false);
		listJTextArea = new JTextArea();
		listJTextArea.setLineWrap(true);
		listJTextArea.setEditable(false);
		
		logJTextArea.setColumns(40);
		logJTextArea.setRows(20);
		
		listJTextArea.setColumns(20);
		listJTextArea.setRows(30);
		
		logJScrollPane = new JScrollPane();
		listJScrollPane = new JScrollPane();
		
		logJScrollPane.setViewportView(logJTextArea);
		listJScrollPane.setViewportView(listJTextArea);
		
		inputJScrollPane = new JScrollPane();
		inpuTextArea = new JTextArea();
		inpuTextArea.setLineWrap(true);
		inputJScrollPane.setViewportView(inpuTextArea);
		inpuTextArea.setColumns(35);
		inpuTextArea.setRows(3);
		
		buttonJPanel = new JPanel(new BorderLayout());
		sendJButton = new JButton("发送");
		clearJButton = new JButton("清屏");
		buttonJPanel.add(clearJButton, BorderLayout.NORTH);
		buttonJPanel.add(sendJButton, BorderLayout.SOUTH);
		
		sendJPanel = new JPanel();
		
		sendJPanel.add(inputJScrollPane);
		sendJPanel.add(buttonJPanel);
		
		messageJPanel.add(logJScrollPane, BorderLayout.CENTER);
		messageJPanel.add(sendJPanel, BorderLayout.SOUTH);
		
		listJPanel.add(listJScrollPane);
		
		this.add(messageJPanel, BorderLayout.CENTER);
		this.add(listJPanel, BorderLayout.EAST);
		
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		clearJButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				logJTextArea.setText("");
			}
		});
		
		sendJButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				sendChatMessage();
			}
		});
		
		inpuTextArea.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					sendChatMessage();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					inpuTextArea.setText(null);
				}
			}
		});
		
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				sendLogoutXML();
			}
		});
	}
	
	private void sendChatMessage()
	{
		try
		{
			OutputStream os = chatSocket.getOutputStream();
			String sendXML = HandleXML.genChatXML(userName,
					inpuTextArea.getText());
			os.write(sendXML.getBytes());
		} catch (IOException e1)
		{
			JOptionPane.showMessageDialog(this, "聊天消息发送失败！", "与主机通讯异常",
					JOptionPane.WARNING_MESSAGE);
			// e1.printStackTrace();
		}
	}
	
	private void sendLogoutXML()
	{
		String logoutXML = HandleXML.genMessageXML(userName, HandleXML.LOG_OUT);
		try
		{
			chatSocket.getOutputStream().write(logoutXML.getBytes());
		} catch (IOException e)
		{
			JOptionPane.showMessageDialog(ChatJFrame.this, "退出消息发送失败！",
					"与主机通讯异常", JOptionPane.WARNING_MESSAGE);
			// e.printStackTrace();
		}
	}
}

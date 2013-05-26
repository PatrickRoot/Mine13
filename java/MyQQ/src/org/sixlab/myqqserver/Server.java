package org.sixlab.myqqserver;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.sixlab.myqqutil.HandleXML;
import org.sixlab.myqqutil.PortVerify;

public class Server extends JFrame
{
	/**
	 * 
	 */
	private static final long						serialVersionUID	= 1L;
	
	private JPanel									upJPanel;
	private JPanel									downJPanel;
	private JLabel									infoJLabel;
	private JLabel									stateJLabel;
	private JLabel									portJLabel;
	
	private JTextField								portJTextField;
	private JButton									startJButton;
	
	private JTextArea								listJTextArea;
	private JScrollPane								listJScrollPane;
	
	private HashMap<String, ServerMessageThread>	map					= new HashMap<String, ServerMessageThread>();
	
	public HashMap<String, ServerMessageThread> getMap()
	{
		return map;
	}
	
	public Server(String title)
	{
		super(title);
		
		this.setResizable(false);
		this.setLocationByPlatform(true);
		
		upJPanel = new JPanel();
		downJPanel = new JPanel();
		
		infoJLabel = new JLabel("服务器状态");
		stateJLabel = new JLabel("未运行");
		stateJLabel.setForeground(Color.RED);
		portJLabel = new JLabel("端口");
		
		portJTextField = new JTextField("5000");
		startJButton = new JButton("开启服务器");
		listJTextArea = new JTextArea();
		listJTextArea.setEditable(false);
		listJTextArea.setRows(20);
		listJTextArea.setColumns(25);
		listJTextArea.setLineWrap(true);
		listJScrollPane = new JScrollPane();
		
		listJScrollPane.setViewportView(listJTextArea);
		
		upJPanel.setBorder(BorderFactory.createTitledBorder("服务器信息"));
		downJPanel.setBorder(BorderFactory.createTitledBorder("用户列表"));
		upJPanel.add(infoJLabel);
		upJPanel.add(stateJLabel);
		upJPanel.add(portJLabel);
		upJPanel.add(portJTextField);
		upJPanel.add(startJButton);
		
		downJPanel.add(listJScrollPane);
		
		// this.setLayout(BorderLayout);
		this.add(upJPanel, BorderLayout.NORTH);
		this.add(downJPanel, BorderLayout.SOUTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		portJTextField.setSelectionEnd(4);
		portJTextField.setFocusable(true);
		
		startJButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				startServer();
			}
		});
		
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				sendCloseXML();
			}
		});
	}
	
	public JLabel getStateJLabel()
	{
		return stateJLabel;
	}
	
	public JTextField getPortJTextField()
	{
		return portJTextField;
	}
	
	public JButton getStartJButton()
	{
		return startJButton;
	}
	
	public JTextArea getListJTextArea()
	{
		return listJTextArea;
	}
	
	private void startServer()
	{
		String getInput = portJTextField.getText();
		
		int port = PortVerify.verifyString(getInput);
		
		if (port != -1)
		{
			new ServerConn(this, port).start();
		} else
		{
			JOptionPane.showMessageDialog(this, "请输入正确端口号", "警告",
					JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void sendCloseXML()
	{
		String closeXML = HandleXML.genLogXML(HandleXML.SERVER_CONN_LOST);
		try
		{
			if (map != null)
			{
				Set<String> userSet = map.keySet();
				for (Iterator<String> iterator = userSet.iterator(); iterator
						.hasNext();)
				{
					String string = iterator.next();
					map.get(string).getOs().write(closeXML.getBytes());
				}
			}
		} catch (IOException e)
		{
			JOptionPane.showMessageDialog(this, "退出消息发送失败！", "与主机通讯异常",
					JOptionPane.WARNING_MESSAGE);
			// e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		Server aServer = new Server("服务器");
		
		aServer.setVisible(true);
	}
}

package org.sixlab.myqqclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import org.sixlab.myqqutil.HandleXML;

public class ClientConn extends Thread
{
	private Client		client;
	private Socket		socket;
	private ChatJFrame	chatJFrame;
	
	public ClientConn(Client client, String userName, String hostName,
			int portName)
	{
		this.client = client;
		try
		{
			socket = new Socket(hostName, portName);
			
			OutputStream os = socket.getOutputStream();
			
			String logXML = HandleXML.genMessageXML(userName, HandleXML.LOG_IN);
			
			os.write(logXML.getBytes());
			
			InputStream is = socket.getInputStream();
			
			byte[] buffer = new byte[5000];
			
			int length = is.read(buffer);
			
			String backMessageXML = new String(buffer, 0, length);
			
			if (HandleXML.identifyInput(backMessageXML) == HandleXML.SERVER_CONN_FAIL)
			{
				JOptionPane.showMessageDialog(client, "用户名已存在", "警告",
						JOptionPane.WARNING_MESSAGE);
			} else
			{
				chatJFrame = new ChatJFrame(userName, hostName, portName,
						socket);
				chatJFrame.setVisible(true);
				client.setVisible(false);
			}
			
		} catch (UnknownHostException e)
		{
			JOptionPane.showMessageDialog(client, "未知主机名,请正确填写主机!", "未知主机",
					JOptionPane.WARNING_MESSAGE);
		} catch (IOException e)
		{
			JOptionPane.showMessageDialog(client, "可能服务器未开启！", "登录失败",
					JOptionPane.WARNING_MESSAGE);
		}
	}
	
	@Override
	public void run()
	{
		
		try
		{
			while (true)
			{
				InputStream is = socket.getInputStream();
				byte[] buffer = new byte[5000];
				
				int length = is.read(buffer);
				
				String inputMessage = new String(buffer, 0, length);
				
				int messageKind = HandleXML.identifyInput(inputMessage);
				
				if (messageKind == HandleXML.CHAT_MESSAGE)
				{
					String addChat = HandleXML.getMessage(inputMessage,
							HandleXML.CHAT_MESSAGE);
					chatJFrame.getLogJTextArea().append(addChat + "\n");
				} else if (messageKind == HandleXML.USER_LIST)
				{
					String userList = HandleXML.getMessage(inputMessage,
							HandleXML.USER_LIST);
					chatJFrame.getListJTextArea().setText(userList);
				} else
				{
					client.setVisible(true);
					chatJFrame.setVisible(false);
					JOptionPane.showMessageDialog(client, "服务器关闭，退出聊天！",
							"服务器已关闭", JOptionPane.WARNING_MESSAGE);
					break;
				}
			}
			
		} catch (IOException e)
		{
			JOptionPane.showMessageDialog(client, "聊天信息通讯失败！", "与主机通讯异常",
					JOptionPane.WARNING_MESSAGE);
			// e.printStackTrace();
		}
	}
}

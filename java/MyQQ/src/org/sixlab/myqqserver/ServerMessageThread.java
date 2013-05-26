package org.sixlab.myqqserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JOptionPane;

import org.sixlab.myqqutil.HandleXML;

public class ServerMessageThread extends Thread
{
	private Server			server;
	
	private InputStream		is;
	
	private OutputStream	os;
	
	public ServerMessageThread(Server server, Socket socket)
	{
		this.server = server;
		try
		{
			this.is = socket.getInputStream();
			this.os = socket.getOutputStream();
			
			String logsucc = HandleXML.genLogXML(HandleXML.SERVER_CONN_SUCC);
			os.write(logsucc.getBytes());
		} catch (IOException e)
		{
			JOptionPane.showMessageDialog(server, "登陆信息发送失败！", "与客户端通讯异常",
					JOptionPane.WARNING_MESSAGE);
//			e.printStackTrace();
		}
	}
	
	@Override
	public void run()
	{
		try
		{
			while (true)
			{
				byte[] buffer = new byte[5000];
				int length = is.read(buffer);
				String clientMessage = new String(buffer, 0, length);
				if (HandleXML.identifyInput(clientMessage) == HandleXML.LOG_OUT)
				{
					String userName = HandleXML.getUserName(clientMessage);
					this.server.getMap().remove(userName);
					Set<String> userSet = this.server.getMap().keySet();
					String userList = getUseresXML("", userSet);
					this.server.getListJTextArea().setText(userList);
					
					String userListXML = HandleXML.genMessageXML(userList,
							HandleXML.USER_LIST);
					sendMessage(userSet, userListXML);
				} else if (HandleXML.identifyInput(clientMessage) == HandleXML.CHAT_MESSAGE)
				{
					Set<String> userSet = this.server.getMap().keySet();
					sendMessage(userSet, clientMessage);
				}
			}
			
		} catch (IOException e)
		{
			JOptionPane.showMessageDialog(server, "可能客户已退出！", "与客户端通讯异常",
					JOptionPane.WARNING_MESSAGE);
//			e.printStackTrace();
		}
	}
	
	/**
	 * @param userList
	 * @param userSet
	 * @return
	 */
	private String getUseresXML(String userList, Set<String> userSet)
	{
		for (Iterator<String> iterator = userSet.iterator(); iterator.hasNext();)
		{
			String user = iterator.next();
			userList += (user + "\n");
		}
		return userList;
	}
	
	/**
	 * @param userSet
	 *            用户名列表
	 * @throws IOException
	 */
	private void sendMessage(Set<String> userSet, String message)
			throws IOException
	{
		for (Iterator<String> iterator = userSet.iterator(); iterator.hasNext();)
		{
			String string = iterator.next();
			this.server.getMap().get(string).getOs().write(message.getBytes());
		}
	}
	
	public OutputStream getOs()
	{
		return os;
	}
}

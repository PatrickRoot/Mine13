package org.sixlab.myqqserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JOptionPane;

import org.sixlab.myqqutil.HandleXML;

public class ServerConn extends Thread
{
	private Server			server;
	private ServerSocket	serverSocket;
	private InputStream		is;
	private OutputStream	os;
	
	public OutputStream getOs()
	{
		return os;
	}
	
	public ServerConn(Server server, int port)
	{
		this.server = server;
		
		try
		{
			serverSocket = new ServerSocket(port);
			server.getStartJButton().setEnabled(false);
			server.getPortJTextField().setEditable(false);
			server.getStateJLabel().setText("运行中");
		} catch (IOException e)
		{
			// e.printStackTrace();
			JOptionPane.showMessageDialog(server, "启动服务器失败，请换端口号重试。", "启动失败",
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
				
				Socket socket = serverSocket.accept();
				
				byte[] buffer = new byte[5000];
				
				is = socket.getInputStream();
				os = socket.getOutputStream();
				
				int length = is.read(buffer);
				String clientMessage = new String(buffer, 0, length);
				
				if (HandleXML.identifyInput(clientMessage) == HandleXML.LOG_IN)
				{
					String userName = HandleXML.getUserName(clientMessage);
					if (this.server.getMap().containsKey(userName))
					{
						String logfail = HandleXML
								.genLogXML(HandleXML.SERVER_CONN_FAIL);
						os.write(logfail.getBytes());
						continue;
					} else
					{
						ServerMessageThread aThread = new ServerMessageThread(
								server, socket);
						aThread.start();
						
						this.server.getMap().put(userName, aThread);
						
						Set<String> userSet = this.server.getMap().keySet();
						String userList = "";
						for (Iterator<String> iterator = userSet.iterator(); iterator
								.hasNext();)
						{
							String user = iterator.next();
							userList += (user + "\n");
						}
						this.server.getListJTextArea().setText(userList);
						String userListXML = HandleXML.genMessageXML(userList,
								HandleXML.USER_LIST);
						for (Iterator<String> iterator = userSet.iterator(); iterator
								.hasNext();)
						{
							String string = iterator.next();
							this.server.getMap().get(string).getOs()
									.write(userListXML.getBytes());
						}
					}
				}
			}
		} catch (IOException e)
		{
			JOptionPane.showMessageDialog(server, "可能通讯中断", "与客户端通讯异常",
					JOptionPane.WARNING_MESSAGE);
			// e.printStackTrace();
		}
	}
	
}

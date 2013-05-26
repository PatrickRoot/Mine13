package org.sixlab.myqqutil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import javax.swing.JOptionPane;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class HandleXML
{
	public static final int	LOG_IN				= 1;
	public static final int	LOG_OUT				= 2;
	public static final int	CHAT_MESSAGE		= 3;
	
	public static final int	SERVER_CONN_SUCC	= 4;
	public static final int	SERVER_CONN_FAIL	= 5;
	public static final int	USER_LIST			= 6;
	public static final int	SERVER_CONN_LOST	= 8;
	
	private static String tranDoc(Document doc)
	{
		Format format=Format.getRawFormat();
		format.setEncoding("gbk");
		XMLOutputter out = new XMLOutputter(format);
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		try
		{
			out.output(doc, bo);
			return bo.toString();
			
		} catch (IOException e)
		{
			JOptionPane.showMessageDialog(null, "��XMLת��Ϊ�ַ����IO�쳣", "XMLת���쳣",
					JOptionPane.WARNING_MESSAGE);
			// e.printStackTrace();
		}
		return null;
	}
	
	public static int identifyInput(String input)
	{
		Reader inputReader = new StringReader(input);
		SAXBuilder builder = new SAXBuilder();
		Document document = null;
		Element element = null;
		
		try
		{
			document = builder.build(inputReader);
			element = document.getRootElement();
			if (element.getName().equals("userlist"))
			{
				return USER_LIST;
			} else if (element.getName().equals("chat"))
			{
				return CHAT_MESSAGE;
			} else if (element.getName().equals("log"))
			{
				if (element.getAttributeValue("kind").equals("Server"))
				{
					if (element.getAttributeValue("state").equals("4"))
					{
						return SERVER_CONN_SUCC;
					} else if (element.getAttribute("state").equals("5"))
					{
						return SERVER_CONN_FAIL;
					} else
					{
						return SERVER_CONN_LOST;
					}
				} else if (element.getAttributeValue("kind").equals("Client"))
				{
					if (element.getAttributeValue("state").equals("in"))
					{
						return LOG_IN;
					} else
					{
						return LOG_OUT;
					}
				}
			}
			
		} catch (JDOMException e)
		{
			JOptionPane.showMessageDialog(null, "���XML�����쳣,����XML�ļ������",
					"���XML�쳣", JOptionPane.WARNING_MESSAGE);
			// e.printStackTrace();
		} catch (IOException e)
		{
			JOptionPane.showMessageDialog(null, "���XML����IO�쳣", "���XML�쳣",
					JOptionPane.WARNING_MESSAGE);
			// e.printStackTrace();
		}
		return 0;
	}
	
	public static String getUserName(String clientMessage)
	{
		Reader inputReader = new StringReader(clientMessage);
		SAXBuilder builder = new SAXBuilder();
		
		try
		{
			Document document = builder.build(inputReader);
			Element element = document.getRootElement();
			if (element.getName().equals("chat")
					| element.getName().equals("log"))
			{
				return element.getChild("user").getValue();
			}
			
		} catch (JDOMException e)
		{
			JOptionPane.showMessageDialog(null, "����XML�ļ������", "��ȡ�û����쳣",
					JOptionPane.WARNING_MESSAGE);
			// e.printStackTrace();
		} catch (IOException e)
		{
			JOptionPane.showMessageDialog(null, "ͨ��XML��ȡ�û������IO�쳣", "��ȡ�û����쳣",
					JOptionPane.WARNING_MESSAGE);
			// e.printStackTrace();
		}
		return null;
	}
	
	public static String genLogXML(int connState)
	{
		Document document = new Document();

		Element root = new Element("log");
		root.setAttribute("state", String.valueOf(connState)).setAttribute(
				"kind", "Server");
		document.addContent(root);
		return tranDoc(document);
	}
	
	public static String genMessageXML(String text, int messageType)
	{
		Document document = new Document();
		Element root = null;
		if (messageType == LOG_IN)
		{
			root = new Element("log");
			root.setAttribute("kind", "Client").setAttribute("state", "in")
					.addContent(new Element("user").setText(text));
		} else if (messageType == USER_LIST)
		{
			root = new Element("userlist");
			root.setText(text);
			
		} else if (messageType == LOG_OUT)
		{
			root = new Element("log");
			root.setAttribute("kind", "Client").setAttribute("state", "out")
					.addContent(new Element("user").setText(text));
		}
		
		document.addContent(root);
		return tranDoc(document);
	}
	
	public static String getMessage(String inputMessage, int messageType)
	{
		Reader inputReader = new StringReader(inputMessage);
		SAXBuilder builder = new SAXBuilder();
		
		Document document = null;
		Element root = null;
		String returnString = null;
		
		try
		{
			document = builder.build(inputReader);
			root = document.getRootElement();
			
		} catch (JDOMException e)
		{
			JOptionPane.showMessageDialog(null, "��ȡ��Ϣ�쳣������XML�ļ������", "��ȡ��Ϣ�쳣",
					JOptionPane.WARNING_MESSAGE);
			// e.printStackTrace();
		} catch (IOException e)
		{
			JOptionPane.showMessageDialog(null, "ͨ��XML��ȡ��Ϣ����IO�쳣", "��ȡ�쳣",
					JOptionPane.WARNING_MESSAGE);
			// e.printStackTrace();
		}
		
		if (messageType == CHAT_MESSAGE)
		{
			
			returnString = root.getChildText("user") + " : "
					+ root.getChildText("message");
			
		} else if (messageType == USER_LIST)
		{
			returnString = root.getText();
		}
		return returnString;
	}
	
	public static String genChatXML(String userName, String text)
	{
		Document document = new Document();
		
		Element root = new Element("chat");
		document.addContent(root);
		root.addContent(new Element("user").setText(userName)).addContent(
				new Element("message").setText(text));
		
		return tranDoc(document);
	}
}

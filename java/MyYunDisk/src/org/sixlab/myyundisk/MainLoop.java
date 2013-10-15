package org.sixlab.myyundisk;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URI;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * 
 * @author sixlab@nianqinianyi nianqinianyi@163.com
 * @version 2013-6-28,下午3:30:49
 */
public class MainLoop extends Frame
{
	private static final long		serialVersionUID	= -3991473444098558558L;
	private Vector<Vector<String>>	content;
	private Vector<String>			title;
	
	private String					gloabID;
	private String					gloabName;
	private String					gloabMark;
	private String					gloabLink;
	
	private Panel					jupJPanel;
	private Panel					jcenJPanel;
	private Panel					jdownpJPanel;
	
	private TextField				jTextField1;
	private TextField				jTextField2;
	private TextField				jTextField3;
	private TextField				jTextField4;
	
	private JButton					resetButton;
	private JButton					updateButton;
	private JButton					insertButton;
	private JButton					searchButton;
	
	private JScrollPane				jScrollPane;
	private JTable					jTable;
	
	private JLabel					resultLabel;
	private JButton					helpButton;
	private JButton					browserButton;
	
	public MainLoop()
	{
		this.setTitle("我的网盘文件查询");
		this.setBounds(450, 150, 450, 600);
		jupJPanel = new Panel(new GridLayout(2, 4));
		jcenJPanel = new Panel();
		jdownpJPanel = new Panel(new BorderLayout());
		
		jTextField2 = new TextField();
		jTextField3 = new TextField();
		jTextField4 = new TextField();
		jTextField1 = new TextField();
		jTextField2.setColumns(10);
		jTextField3.setColumns(10);
		jTextField4.setColumns(10);
		jTextField1.setColumns(10);
		updateButton = new JButton("修改");
		insertButton = new JButton("插入");
		resetButton = new JButton("重置");
		searchButton = new JButton("搜索");
		
		jupJPanel.add(jTextField1);
		jupJPanel.add(jTextField2);
		jupJPanel.add(jTextField3);
		jupJPanel.add(jTextField4);
		jupJPanel.add(updateButton);
		jupJPanel.add(insertButton);
		jupJPanel.add(resetButton);
		jupJPanel.add(searchButton);
		
		// 中部
		title = new Vector<String>();
		title.add("序号");
		title.add("名字");
		title.add("备注");
		title.add("链接");
		content = new Vector<Vector<String>>();
		jScrollPane = new JScrollPane();
		DBHandle.queryFile("", "", "", "", content);
		gloabID = "";
		gloabName = "";
		gloabMark = "";
		gloabLink = "";
		
		jTable = new JTable(content, title);
		
		jScrollPane.setViewportView(jTable);
		jcenJPanel.add(jScrollPane);
		
		// 下部
		helpButton = new JButton("帮助");
		resultLabel = new JLabel();
		browserButton = new JButton("访问");
		jdownpJPanel.add(helpButton, BorderLayout.WEST);
		jdownpJPanel.add(resultLabel, BorderLayout.CENTER);
		jdownpJPanel.add(browserButton, BorderLayout.EAST);
		resultLabel.setText("共有 " + content.size() + " 个文件记录。");
		// 整合
		this.add(jupJPanel, BorderLayout.NORTH);
		this.add(jcenJPanel, BorderLayout.CENTER);
		this.add(jdownpJPanel, BorderLayout.SOUTH);
		this.pack();
		jTextField2.requestFocus();
		
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		
		helpButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				StringBuffer helpText = new StringBuffer();
				helpText.append("1、修改时必需要有ID。\n");
				helpText.append("2、插入时必需有名字。\n");
				helpText.append("3、重置恢复最初状态。\n");
				helpText.append("4、全为空时搜索所有。\n");
				helpText.append("5、在记录上右键点击，记录内容会填充到文本框。\n");
				JOptionPane.showMessageDialog(null, helpText, "帮助",
						JOptionPane.PLAIN_MESSAGE);
				jTextField2.requestFocus();
			}
		});
		
		jTable.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (e.isMetaDown())
				{
					tableRightClicked(e);
				}
			}
		});
		
		searchButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				searchTrigger();
			}
		});
		
		insertButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				insertTrigger();
			}
		});
		
		updateButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				updateTrigger();
			}
		});
		
		resetButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				jTextField1.setText("");
				jTextField2.setText("");
				jTextField4.setText("");
				jTextField3.setText("");
				jTextField2.requestFocus();
				DBHandle.queryFile("", "", "", "", content);
				resultLabel.setText("共有 " + content.size() + " 个文件记录。");
				jTable.invalidate();
				jTable.repaint();
				pack();
			}
		});
		
		browserButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				browserTrigger();
			}
		});
	}
	
	private void browserTrigger()
	{
		int row = jTable.getSelectedRow();
		String cellVal4 = (String) jTable.getValueAt(row, 3);
		try
		{
			Desktop.getDesktop().browse(new URI(cellVal4));
		} catch (Exception e1)
		{
			e1.printStackTrace();
		}
		jTextField2.requestFocus();
	}
	
	private void searchTrigger()
	{
		String queryID = jTextField1.getText();
		String queryName = jTextField2.getText();
		String queryMark = jTextField3.getText();
		String queryLink = jTextField4.getText();
		
		gloabID = queryID;
		gloabName = queryName;
		gloabMark = queryMark;
		gloabLink = queryLink;
		
		if (queryID == null)
		{
			queryID = "";
		}
		if (queryName == null)
		{
			queryName = "";
		}
		if (queryMark == null)
		{
			queryMark = "";
		}
		if (queryLink == null)
		{
			queryLink = "";
		}
		
		queryName = queryName.replace("'", "''");
		queryMark = queryMark.replace("'", "''");
		
		DBHandle.queryFile(queryID, queryName, queryMark, queryLink, content);
		String queryString = queryID + ":" + queryName + " : " + queryMark
				+ " : " + queryLink;
		resultLabel.setText("查询“" + queryString + "”共有" + content.size()
				+ "条结果。");
		jTable.invalidate();
		jTable.repaint();
		jTextField2.requestFocus();
		this.pack();
	}
	
	private void insertTrigger()
	{
		String queryName = jTextField2.getText();
		String queryMark = jTextField3.getText();
		String queryLink = jTextField4.getText();
		
		if (queryName == null)
		{
			queryName = "";
		}
		if (queryMark == null)
		{
			queryMark = "";
		}
		if (queryLink == null | queryLink.equals(""))
		{
			queryLink = "http://www.baidu.com/s?word=" + queryMark;
		} else
		{
			if (!queryLink.startsWith("http://"))
			{
				queryLink = "http://" + queryLink;
			}
		}
		
		if (queryName.equals(""))
		{
			resultLabel.setText("严重警告：没有名字无法插入信息，请一定要输入名字！");
			jTextField2.requestFocus();
			this.pack();
			return;
		}
		
		queryName = queryName.replace("'", "''");
		queryMark = queryMark.replace("'", "''");
		
		boolean isSucc = DBHandle.addFile(queryName, queryMark, queryLink);
		if (isSucc)
		{
			resultLabel.setText("增加成功！");
			Vector<Vector<String>> content1 = new Vector<Vector<String>>();
			DBHandle.queryFile("", queryName, queryMark, queryLink, content1);
			for (Vector<String> vector : content1)
			{
				Vector<String> newRecord = new Vector<String>();
				for (String string : vector)
				{
					newRecord.add(string);
				}
				content.add(newRecord);
			}
			jTextField2.requestFocus();
			jTable.invalidate();
			jTable.repaint();
		} else
		{
			resultLabel.setText("增加失败！");
			jTextField2.requestFocus();
		}
		this.pack();
	}
	
	private void updateTrigger()
	{
		String queryID = jTextField1.getText();
		String queryName = jTextField2.getText();
		String queryMark = jTextField3.getText();
		String queryLink = jTextField4.getText();
		
		if (queryID == null | queryID.equals(""))
		{
			resultLabel.setText("严重警告：没有ID无法修改信息，请一定要输入ID！！！");
			jTextField1.requestFocus();
			this.pack();
			return;
		} else
		{
			for (int i = 0; i < queryID.length(); i++)
			{
				char c = queryID.charAt(i);
				if (!Character.isDigit(c))
				{
					resultLabel.setText("严重警告：ID输入错误，请一定要输入数字形式ID!!！");
					jTextField1.requestFocus();
					this.pack();
					return;
				}
			}
		}
		if (queryName == null)
		{
			queryName = "";
		}
		if (queryMark == null)
		{
			queryMark = "";
		}
		if (queryLink == null | "".equals(queryLink))
		{
			queryLink = "http://www.baidu.com/s?word=" + queryMark;
		} else
		{
			if (!queryLink.startsWith("http://"))
			{
				queryLink = "http://" + queryLink;
			}
		}
		
		queryName = queryName.replace("'", "''");
		queryMark = queryMark.replace("'", "''");
		
		boolean isSucc = DBHandle.updateFile(queryID, queryName, queryMark,
				queryLink);
		if (isSucc)
		{
			resultLabel.setText("修改成功！");
			DBHandle.queryFile(gloabID, gloabName, gloabMark, gloabLink,
					content);
			jTable.invalidate();
			jTable.repaint();
			jTextField2.requestFocus();
		} else
		{
			resultLabel.setText("修改失败！");
			jTextField1.requestFocus();
		}
		this.pack();
	}
	
	private void tableRightClicked(MouseEvent e)
	{
		if (e.isMetaDown())
		{
			JTable clickTable = (JTable) e.getSource();
			int row = clickTable.rowAtPoint(e.getPoint());
			
			String cellVal1 = (String) clickTable.getValueAt(row, 0);
			String cellVal2 = (String) clickTable.getValueAt(row, 1);
			String cellVal3 = (String) clickTable.getValueAt(row, 2);
			String cellVal4 = (String) clickTable.getValueAt(row, 3);
			
			jTextField1.setText(cellVal1);
			jTextField2.setText(cellVal2);
			jTextField3.setText(cellVal3);
			jTextField4.setText(cellVal4);
			jTextField2.requestFocus();
		}
	}
	
	public static void main(String[] args)
	{
		MainLoop mLoop = new MainLoop();
		mLoop.setVisible(true);
	}
}

package com.sixlab;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * 
 * @author sixlab@nianqinianyi nianqinianyi@163.com
 * @version 2013-2-15,下午22:28:34
 * 
 */
public class MainLoop extends JFrame
{
	
	private static final long		serialVersionUID	= -3991473444098558558L;
	private Vector<Vector<String>>	content;
	private Vector<String>			title;
	
	private JPanel					jupJPanel;
	private JPanel					jcenJPanel;
	private JPanel					jdownpJPanel;
	private JLabel					nameLabel;
	private JLabel					dateLabel;
	private JLabel					markLabel;
	private JTextField				jName;
	private JTextField				jDate;
	private JTextField				jMark;
	private JButton					searchButton;
	private JButton					insertButton;
	private JTable					jTable;
	private JLabel					resultLabel;
	private JTextField				jID;
	private JButton					updateButton;
	
	public MainLoop()
	{
		this.setTitle("我的电影查询");
		this.setBounds(450, 150, 450, 600);
		Container container = this.getContentPane();
		jupJPanel = new JPanel(new GridLayout(2, 5));
		jcenJPanel = new JPanel();
		jdownpJPanel = new JPanel();
		
		// 上部
		nameLabel = new JLabel("名字");
		dateLabel = new JLabel("日期");
		markLabel = new JLabel("备注");
		
		jName = new JTextField();
		jDate = new JTextField();
		jMark = new JTextField();
		jID = new JTextField();
		searchButton = new JButton("搜索");
		insertButton = new JButton("插入");
		updateButton = new JButton("修改");
		jupJPanel.add(nameLabel);
		jupJPanel.add(dateLabel);
		jupJPanel.add(markLabel);
		jupJPanel.add(jID);
		jupJPanel.add(searchButton);
		jupJPanel.add(jName);
		jupJPanel.add(jDate);
		jupJPanel.add(jMark);
		jupJPanel.add(updateButton);
		jupJPanel.add(insertButton);
		
		// 中部
		title = new Vector<String>();
		title.add("序号");
		title.add("名字");
		title.add("日期");
		title.add("备注");
		content = new Vector<Vector<String>>();
		JScrollPane jScrollPane = new JScrollPane();
		jTable = new JTable(content, title);
		
		jScrollPane.setViewportView(jTable);
		jcenJPanel.add(jScrollPane);
		
		// 下部
		resultLabel = new JLabel();
		jdownpJPanel.add(resultLabel);
		
		// 整合
		container.add(jupJPanel, BorderLayout.NORTH);
		container.add(jcenJPanel, BorderLayout.CENTER);
		container.add(jdownpJPanel, BorderLayout.SOUTH);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
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
	}
	
	private void searchTrigger()
	{
		String queryName = jName.getText();
		String queryDate = jDate.getText();
		String queryMark = jMark.getText();
		if (queryName == null)
		{
			queryName = "";
		}
		if (queryDate == null)
		{
			queryDate = "";
		}
		if (queryMark == null)
		{
			queryMark = "";
		}
		QueryDatabase.queryFilm(queryName, queryMark, queryDate, content);
		String queryString = queryName + " : " + queryDate + " : " + queryMark;
		resultLabel.setText("查询“" + queryString + "”共有" + content.size()
				+ "条结果。");
		jTable.invalidate();
		this.pack();
	}
	
	private void insertTrigger()
	{
		String queryName = jName.getText();
		String queryDate = jDate.getText();
		String queryMark = jMark.getText();
		if (queryName == null)
		{
			queryName = "";
		}
		if (queryDate == null)
		{
			queryDate = "";
		}
		if (queryMark == null)
		{
			queryMark = "";
		}
		
		if (queryName.equals(""))
		{
			resultLabel.setText("严重警告：没有名字无法插入信息，请一定要输入名字！");
			this.pack();
			return;
		}
		if (queryDate.equals(""))
		{
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			queryDate = sdf.format(today);
		} else
		{
			if (queryDate.length() != 8)
			{
				resultLabel.setText("严重警告：日期输入错误，请一定要八位数字形式！");
				this.pack();
				return;
			} else
			{
				for (int i = 0; i < 8; i++)
				{
					char c = queryDate.charAt(i);
					if (!Character.isDigit(c))
					{
						resultLabel.setText("严重警告：日期输入错误，请一定要八位数字形式！");
						this.pack();
						return;
					}
				}
			}
		}
		
		boolean isSucc = QueryDatabase.addFilm(queryName, queryDate, queryMark);
		if (isSucc)
		{
			resultLabel.setText("增加成功！");
		} else
		{
			resultLabel.setText("增加失败！");
		}
		this.pack();
	}
	
	private void updateTrigger()
	{
		
		String queryName = jName.getText();
		String queryDate = jDate.getText();
		String queryMark = jMark.getText();
		String queryID=jID.getText();
		
		if (queryID==null|queryID.equals(""))
		{
			resultLabel.setText("严重警告：没有ID无法修改信息，请一定要输入ID！！！");
			this.pack();
			return;
		}else {
			for (int i = 0; i < queryID.length(); i++)
			{
				char c = queryID.charAt(i);
				if (!Character.isDigit(c))
				{
					resultLabel.setText("严重警告：ID输入错误，请一定要输入数字形式ID!!！");
					this.pack();
					return;
				}
			}
		}
		if (queryName == null)
		{
			queryName = "";
		}
		if (queryDate == null)
		{
			queryDate = "";
		}
		if (queryMark == null)
		{
			queryMark = "";
		}

		if (queryDate.equals(""))
		{
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			queryDate = sdf.format(today);
		} else
		{
			if (queryDate.length() != 8)
			{
				resultLabel.setText("严重警告：日期输入错误，请一定要八位数字形式!!！");
				this.pack();
				return;
			} else
			{
				for (int i = 0; i < 8; i++)
				{
					char c = queryDate.charAt(i);
					if (!Character.isDigit(c))
					{
						resultLabel.setText("严重警告：日期输入错误，请一定要八位数字形式!!！");
						this.pack();
						return;
					}
				}
			}
		}
		
		boolean isSucc = QueryDatabase.updateFilm(queryID, queryName, queryMark, queryDate);
		if (isSucc)
		{
			resultLabel.setText("修改成功！");
		} else
		{
			resultLabel.setText("修改失败！");
		}
		this.pack();
	}
	
	public static void main(String[] args)
	{
		MainLoop mLoop = new MainLoop();
		mLoop.setVisible(true);
	}
	
}

package org.sixlab.mymovies;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	
	private String					gloabID;
	private String					gloabName;
	private String					gloabMark;
	private String					gloabDate;
	
	private JPanel					jupJPanel;
	private JPanel					jcenJPanel;
	private JPanel					jdownpJPanel;
	private JTextField				jTextField1;
	private JTextField				jTextField2;
	private JTextField				jTextField3;
	private JTextField				jTextField4;
	private JButton					resetButton;
	private JButton					updateButton;
	private JButton					insertButton;
	private JButton					searchButton;
	private JButton					helpButton;
	private JTable					jTable;
	private JLabel					resultLabel;
	
	public MainLoop()
	{
		this.setTitle("我的电影查询");
		this.setBounds(450, 150, 450, 600);
		Container container = this.getContentPane();
		jupJPanel = new JPanel(new GridLayout(2, 4));
		jcenJPanel = new JPanel();
		jdownpJPanel = new JPanel(new BorderLayout());
		
		jTextField2 = new JTextField();
		jTextField3 = new JTextField();
		jTextField4 = new JTextField();
		jTextField1 = new JTextField();
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
		jupJPanel.add(searchButton);
		jupJPanel.add(resetButton);
		
		// 中部
		title = new Vector<String>();
		title.add("序号");
		title.add("名字");
		title.add("备注");
		title.add("日期");
		content = new Vector<Vector<String>>();
		JScrollPane jScrollPane = new JScrollPane();
		content = new Vector<>();
		QueryDatabase.queryFilm("", "", "", "", content);
		gloabID = "";
		gloabName = "";
		gloabMark = "";
		gloabDate = "";
		
		jTable = new JTable(content, title);
		
		jScrollPane.setViewportView(jTable);
		jcenJPanel.add(jScrollPane);
		
		// 下部
		resultLabel = new JLabel();
		helpButton = new JButton("帮助");
		jdownpJPanel.add(resultLabel, BorderLayout.CENTER);
		jdownpJPanel.add(helpButton, BorderLayout.EAST);
		resultLabel.setText("共有 " + content.size() + " 部电影");
		// 整合
		container.add(jupJPanel, BorderLayout.NORTH);
		container.add(jcenJPanel, BorderLayout.CENTER);
		container.add(jdownpJPanel, BorderLayout.SOUTH);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jTextField2.requestFocus();
		
		this.getContentPane().addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					searchTrigger();
				}
			}
		});
		
		jTextField1.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					searchTrigger();
				}
			}
		});
		
		jTextField2.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					searchTrigger();
				}
			}
		});
		
		jTextField3.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					searchTrigger();
				}
			}
		});
		
		jTextField4.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					searchTrigger();
				}
			}
		});
		
		jTable.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					searchTrigger();
				}
			}
		});
		
		helpButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				StringBuffer helpText = new StringBuffer();
				helpText.append("1、修改的时候必需有ID。\n");
				helpText.append("2、修改时日期为一个空格，则表示当前日期。\n");
				helpText.append("3、插入时必需有名字。\n");
				helpText.append("4、重置时，恢复最初状态。\n");
				helpText.append("5、所有文本框为空时，搜索所有记录。\n");
				helpText.append("6、在记录上右键点击，记录内容会填充到文本框。\n");
				JOptionPane.showMessageDialog(null, helpText, "帮助",
						JOptionPane.PLAIN_MESSAGE);
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
				
				gloabID = "";
				gloabName = "";
				gloabMark = "";
				gloabDate = "";
				jTextField1.setText("");
				jTextField2.setText("");
				jTextField4.setText("");
				jTextField3.setText("");
				jTextField2.requestFocus();
				QueryDatabase.queryFilm("", "", "", "", content);
				resultLabel.setText("共有 " + content.size() + " 部电影。");
				jTable.invalidate();
				jTable.repaint();
				pack();
			}
		});
	}
	
	private void searchTrigger()
	{
		String queryID = jTextField1.getText();
		String queryName = jTextField2.getText();
		String queryMark = jTextField3.getText();
		String queryDate = jTextField4.getText();
		
		if (queryID == null)
		{
			queryID = "";
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
		
		queryName = queryName.replace("'", "''");
		queryMark = queryMark.replace("'", "''");
		
		gloabID = queryID;
		gloabName = queryName;
		gloabMark = queryMark;
		gloabDate = queryDate;
		
		QueryDatabase.queryFilm(queryID, queryName, queryMark, queryDate,
				content);
		String queryString = queryID + ":" + queryName + " : " + queryMark
				+ " : " + queryDate;
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
		String queryDate = jTextField4.getText();
		
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
			jTextField2.requestFocus();
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
				jTextField4.requestFocus();
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
						jTextField4.requestFocus();
						this.pack();
						return;
					}
				}
			}
		}
		
		queryName = queryName.replace("'", "''");
		queryMark = queryMark.replace("'", "''");
		
		boolean isSucc = QueryDatabase.addFilm(queryName, queryMark, queryDate);
		if (isSucc)
		{
			resultLabel.setText("增加成功！");
			
			Vector<Vector<String>> content1 = new Vector<Vector<String>>();
			QueryDatabase.queryFilm("", queryName, queryMark, queryDate,
					content1);
			for (Vector<String> vector : content1)
			{
				Vector<String> newRecord = new Vector<String>();
				for (String string : vector)
				{
					newRecord.add(string);
				}
				content.add(newRecord);
			}
			jTable.invalidate();
			jTable.repaint();
		} else
		{
			resultLabel.setText("增加失败！");
		}
		jTextField1.requestFocus();
		this.pack();
	}
	
	private void updateTrigger()
	{
		String queryID = jTextField1.getText();
		String queryName = jTextField2.getText();
		String queryMark = jTextField3.getText();
		String queryDate = jTextField4.getText();
		
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
		if (queryDate == null)
		{
			queryDate = "";
		}
		if (queryMark == null)
		{
			queryMark = "";
		}
		
		if (queryDate.equals(" "))
		{
			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			queryDate = sdf.format(today);
		} else if (!queryDate.equals(""))
		{
			if (queryDate.length() != 8)
			{
				resultLabel.setText("严重警告：日期输入错误，请一定要八位数字形式!!！");
				jTextField4.requestFocus();
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
						jTextField4.requestFocus();
						this.pack();
						return;
					}
				}
			}
			jTextField2.requestFocus();
		}
		
		queryName = queryName.replace("'", "''");
		queryMark = queryMark.replace("'", "''");
		
		boolean isSucc = QueryDatabase.updateFilm(queryID, queryName,
				queryMark, queryDate);
		if (isSucc)
		{
			resultLabel.setText("修改成功！");
			QueryDatabase.queryFilm(gloabID, gloabName, gloabMark, gloabDate,
					content);
			jTable.invalidate();
			jTable.repaint();
		} else
		{
			resultLabel.setText("修改失败！");
		}
		this.pack();
	}
	
	private void tableRightClicked(MouseEvent e)
	{
		if (e.isMetaDown())
		{
			JTable clickTable = (JTable) e.getSource();
			int row = clickTable.rowAtPoint(e.getPoint());
			for (int i = 0; i < 4; i++)
			{
				int col = i;
				String cellVal = (String) clickTable.getValueAt(row, col);
				String fieldName = "jTextField" + (i + 1);
				try
				{
					Class<MainLoop> mainLoopClass = MainLoop.class;
					Field mlJTextField = mainLoopClass
							.getDeclaredField(fieldName);
					mlJTextField.setAccessible(true);
					JTextField jText = (JTextField) mlJTextField.get(this);
					jText.setText(cellVal);
				} catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		}
		jTextField2.requestFocus();
	}
	
	public static void main(String[] args)
	{
		MainLoop mLoop = new MainLoop();
		mLoop.setVisible(true);
	}
	
}

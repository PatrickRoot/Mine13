package org.sixlab.myshow;

import java.awt.BorderLayout;
import java.awt.GridLayout;
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

public class MainLoop extends JFrame
{
	private JPanel					jPanel1;
	private JTextField				jTextField1;
	private JTextField				jTextField2;
	private JTextField				jTextField3;
	private JTextField				jTextField4;
	private JTextField				jTextField5;
	private JTextField				jTextField6;
	private JTextField				jTextField7;
	
	private JButton					jButton1;
	private JButton					jButton2;
	private JButton					jButton3;
	private JButton					jButton4;
	private JButton					jButton5;
	
	private JScrollPane				jScrollPane1;
	private JTable					jTable1;
	private JPanel					jPanel;
	
	private JLabel					jLabel1;
	private Vector<String>			columnNames;
	private Vector<Vector<String>>	content;
	
	public MainLoop()
	{
		setLocationByPlatform(true);
		setResizable(false);
		jPanel1 = new JPanel(new GridLayout(2, 7));
		jTextField1 = new JTextField();
		jTextField2 = new JTextField();
		jTextField3 = new JTextField();
		jTextField4 = new JTextField();
		jTextField5 = new JTextField();
		jTextField6 = new JTextField();
		jTextField7 = new JTextField();
		
		jButton1 = new JButton();
		jButton2 = new JButton();
		jButton3 = new JButton();
		jButton4 = new JButton();
		jButton1.setText("\u91cd\u7f6e");
		jButton2.setText("\u641c\u7d22");
		jButton3.setText("\u4fee\u6539");
		jButton4.setText("\u63d2\u5165");
		
		jPanel1.add(jTextField1);
		jPanel1.add(jTextField2);
		jPanel1.add(jTextField3);
		jPanel1.add(jTextField4);
		jPanel1.add(jTextField5);
		jPanel1.add(jTextField6);
		jPanel1.add(jTextField7);
		jPanel1.add(jButton4);
		jPanel1.add(new JLabel());
		jPanel1.add(jButton2);
		jPanel1.add(new JLabel());
		jPanel1.add(jButton1);
		jPanel1.add(new JLabel());
		jPanel1.add(jButton3);
		
		jScrollPane1 = new JScrollPane();
		jScrollPane1.setSize(540, 550);
		columnNames = new Vector<String>();
		columnNames.add("ID");
		columnNames.add("名字");
		columnNames.add("季");
		columnNames.add("集");
		columnNames.add("出品");
		columnNames.add("Mark");
		columnNames.add("开始日期");
		content = new Vector<>();
		QueryDatabase.queryFilm("", "", "", "", "", "正看", "", content);
		jTable1 = new JTable(content, columnNames);
		jLabel1 = new JLabel("共有" + content.size() + "部正在看的电视剧。");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jPanel = new JPanel(new BorderLayout());
		jPanel.add(jLabel1, BorderLayout.CENTER);
		jButton5 = new JButton("帮助");
		jPanel.add(jButton5, BorderLayout.EAST);
		
		jScrollPane1.setViewportView(jTable1);
		
		this.add(jPanel1, BorderLayout.NORTH);
		this.add(jScrollPane1, BorderLayout.CENTER);
		this.add(jPanel, BorderLayout.SOUTH);
		
		// 表格双击
		jTable1.addMouseListener(new MouseAdapter()
		{
			
			@Override
			public void mouseClicked(MouseEvent e)
			{
				tableDoubleClicked(e);
			}
		});
		
		// 帮助按钮
		jButton5.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				StringBuffer helpText = new StringBuffer();
				helpText.append("1、默认显示Mark中含有‘正看’的记录。\n");
				helpText.append("2、插入记录时必需有名字。\n");
				helpText.append("3、所有文本框为空时搜索，则列出所有项。\n");
				helpText.append("4、重置时，恢复最初状态。\n");
				helpText.append("5、修改的时候必需有ID。\n");
				helpText.append("6、修改的时候如果‘集’为一个空格，则集数目加一。\n");
				helpText.append("7、在记录上右键点击，记录内容会填充到文本框。\n");
				helpText.append("8、修改的时候日期默认不变，但是如果是一个空格，则表示当前日期。\n");
				JOptionPane.showMessageDialog(null, helpText, "帮助",
						JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		// 重置
		jButton1.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				jTextField1.setText("");
				jTextField2.setText("");
				jTextField3.setText("");
				jTextField4.setText("");
				jTextField5.setText("");
				jTextField6.setText("");
				jTextField7.setText("");
				jTextField1.requestFocus();
				QueryDatabase.queryFilm("", "", "", "", "", "正看", "", content);
				jLabel1.setText("共有" + content.size() + "部正在看的电视剧。");
				jTable1.invalidate();
				pack();
			}
		});
		
		// 搜索
		jButton2.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				searchTrigger();
			}
		});
		
		// 修改
		jButton3.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				updateTrigger();
			}
		});
		
		// 插入
		jButton4.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				insertTrigger();
			}
		});
		
		this.pack();
	}
	
	private void tableDoubleClicked(MouseEvent e)
	{
		if (e.isMetaDown())
		{
			JTable clickTable = (JTable) e.getSource();
			int row = clickTable.rowAtPoint(e.getPoint());
			for (int i = 0; i < 7; i++)
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
	}
	
	public static void main(String[] args)
	{
		MainLoop ml = new MainLoop();
		ml.setVisible(true);
	}
	
	private void searchTrigger()
	{
		String queryID = jTextField1.getText();
		String queryName = jTextField2.getText();
		String querySeries = jTextField3.getText();
		String queryEpisode = jTextField4.getText();
		String queryTV = jTextField5.getText();
		String queryMark = jTextField6.getText();
		String queryDate = jTextField7.getText();
		if (queryID == null)
		{
			queryID = "";
		}
		if (queryName == null)
		{
			queryName = "";
		}
		if (querySeries == null)
		{
			querySeries = "";
		}
		if (queryEpisode == null)
		{
			queryEpisode = "";
		}
		if (queryTV == null)
		{
			queryTV = "";
		}
		if (queryMark == null)
		{
			queryMark = "";
		}
		if (queryDate == null)
		{
			queryDate = "";
		}
		QueryDatabase.queryFilm(queryID, queryName, querySeries, queryEpisode,
				queryTV, queryMark, queryDate, content);
		String queryString = queryID + " : " + queryName + " : " + querySeries
				+ "." + queryEpisode + " : " + " : " + queryMark + queryDate
				+ " : " + queryTV;
		jLabel1.setText("查询“" + queryString + "”共有" + content.size() + "条结果。");
		jTable1.invalidate();
		this.pack();
	}
	
	private void insertTrigger()
	{
		String queryName = jTextField2.getText();
		String querySeries = jTextField3.getText();
		String queryEpisode = jTextField4.getText();
		String queryTV = jTextField5.getText();
		String queryMark = jTextField6.getText();
		String queryDate = jTextField7.getText();
		if (queryName == null)
		{
			queryName = "";
		}
		if (querySeries == null | querySeries.equals(""))
		{
			querySeries = "1";
		}
		if (queryEpisode == null | queryEpisode.equals(""))
		{
			queryEpisode = "0";
		}
		if (queryTV == null)
		{
			queryTV = "";
		}
		if (queryMark == null)
		{
			queryMark = "";
		}
		if (queryDate == null)
		{
			queryDate = "";
		}
		if (queryName.equals(""))
		{
			jLabel1.setText("严重警告：没有名字无法插入信息，请一定要输入名字！");
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
				jLabel1.setText("严重警告：日期输入长度错误，请一定要八位数字形式！");
				this.pack();
				return;
			} else
			{
				for (int i = 0; i < 8; i++)
				{
					char c = queryDate.charAt(i);
					if (!Character.isDigit(c))
					{
						jLabel1.setText("严重警告：日期输入内容错误，请一定要八位数字形式！");
						this.pack();
						return;
					}
				}
			}
		}
		
		boolean isSucc = QueryDatabase.addFilm(queryName, querySeries,
				queryEpisode, queryTV, queryMark, queryDate);
		if (isSucc)
		{
			jLabel1.setText("增加成功！");
			QueryDatabase.queryFilm("", queryName, querySeries, queryEpisode,
					queryTV, queryMark, queryDate, content);
			jTable1.invalidate();
		} else
		{
			jLabel1.setText("增加失败！");
		}
		jTextField1.requestFocus();
		this.pack();
	}
	
	private void updateTrigger()
	{
		String queryID = jTextField1.getText();
		String queryName = jTextField2.getText();
		String querySeries = jTextField3.getText();
		String queryEpisode = jTextField4.getText();
		String queryTV = jTextField5.getText();
		String queryMark = jTextField6.getText();
		String queryDate = jTextField7.getText();
		
		if (queryID == null)
		{
			queryID = "";
		}
		if (queryName == null)
		{
			queryName = "";
		}
		if (querySeries == null)
		{
			querySeries = "";
		}
		if (queryEpisode == null)
		{
			queryEpisode = "";
		}
		if (queryTV == null)
		{
			queryTV = "";
		}
		if (queryMark == null)
		{
			queryMark = "";
		}
		if (queryDate == null)
		{
			queryDate = "";
		}
		if (queryID == null | queryID.equals(""))
		{
			jLabel1.setText("严重警告：没有ID无法修改信息，请一定要输入ID！！！");
			this.pack();
			return;
		} else
		{
			for (int i = 0; i < queryID.length(); i++)
			{
				char c = queryID.charAt(i);
				if (!Character.isDigit(c))
				{
					jLabel1.setText("严重警告：ID输入错误，请一定要输入数字形式ID!!！");
					this.pack();
					return;
				}
			}
		}
		if (!queryDate.equals(""))
		{
			if (queryDate.equals(" "))
			{
				Date today = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				queryDate = sdf.format(today);
			} else if (queryDate.length() != 8)
			{
				jLabel1.setText("严重警告：日期输入长度错误，请一定要八位数字形式！");
				this.pack();
				return;
			} else
			{
				for (int i = 0; i < 8; i++)
				{
					char c = queryDate.charAt(i);
					if (!Character.isDigit(c))
					{
						jLabel1.setText("严重警告：日期输入内容错误，请一定要八位数字形式！");
						this.pack();
						return;
					}
				}
			}
		}
		if (queryEpisode.equals(" "))
		{
			int ep = QueryDatabase.getEpisode(queryID) + 1;
			if (ep==0)
			{
				jLabel1.setText("严重警告：请检查ID！");
				this.pack();
				return;
			}
			queryEpisode = String.valueOf(ep);
		} else if (queryEpisode.equals(""))
		{
			int ep = QueryDatabase.getEpisode(queryID);
			if (ep==-1)
			{
				jLabel1.setText("严重警告：请检查ID！");
				this.pack();
				return;
			}
			queryEpisode = String.valueOf(ep);
		}
		boolean isSucc = QueryDatabase.updateFilm(queryID, queryName,
				querySeries, queryEpisode, queryTV, queryMark, queryDate);
		if (isSucc)
		{
			jLabel1.setText("修改成功！");
			QueryDatabase.queryFilm(queryID, queryName, querySeries,
					queryEpisode, queryTV, queryMark, queryDate, content);
			jTable1.invalidate();
		} else
		{
			jLabel1.setText("修改失败！");
		}
		this.pack();
	}
}

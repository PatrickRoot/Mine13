package org.sixlab.mycode;

import java.awt.BorderLayout;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.TabSet;
import javax.swing.text.TabStop;

public class MainLoop extends JFrame
{
	private JPanel				jPanel1;
	private JPanel				jPanel2;
	private JPanel				jPanel3;
	private JPanel				jPanel4;
	private JTextField			jTextField1;
	private JTextField			jTextField2;
	private JScrollPane			jScrollPane1;
	private JScrollPane			jScrollPane2;
	private JButton				jButton1;
	private JButton				jButton2;
	private JButton				jButton3;
	private JButton				jButton4;
	private JButton				jButton5;
	private JButton				jButton6;
	private JButton				jButton7;
	private JList<String>		jList;
	private JTextPane			jTextPane;
	private JLabel				jLabel;
	private ArrayList<String>	titleList	= new ArrayList<String>();
	
	public MainLoop()
	{
		DBHandle.getTitles(titleList);
		
		jPanel1 = new JPanel(new BorderLayout());
		jPanel2 = new JPanel(new BorderLayout());
		jScrollPane1 = new JScrollPane();
		jList = new JList<String>((String[]) titleList.toArray(new String[0]));
		jButton1 = new JButton("帮助");
		jPanel4 = new JPanel(new BorderLayout());
		jTextField2 = new JTextField();
		jButton6 = new JButton("搜索");
		jTextField1 = new JTextField();
		jScrollPane2 = new JScrollPane();
		jTextPane = new JTextPane();
		jPanel3 = new JPanel();
		jLabel = new JLabel();
		jButton2 = new JButton("清空");
		jButton3 = new JButton("删除");
		jButton7 = new JButton("修改");
		jButton4 = new JButton("插入");
		jButton5 = new JButton("复制");
		
		this.add(jPanel1, BorderLayout.WEST);
		this.add(jPanel2, BorderLayout.CENTER);
		jPanel1.add(jScrollPane1, BorderLayout.CENTER);
		jScrollPane1.setViewportView(jList);
		jPanel1.add(jButton1, BorderLayout.SOUTH);
		jPanel2.add(jPanel4, BorderLayout.NORTH);
		jPanel4.add(jTextField2, BorderLayout.CENTER);
		jPanel4.add(jButton6, BorderLayout.EAST);
		jPanel4.add(jTextField1, BorderLayout.SOUTH);
		jPanel2.add(jScrollPane2, BorderLayout.CENTER);
		jScrollPane2.setViewportView(jTextPane);
		jPanel3.add(jLabel);
		jPanel3.add(jButton2);
		jPanel3.add(jButton3);
		jPanel3.add(jButton7);
		jPanel3.add(jButton4);
		jPanel3.add(jButton5);
		jPanel2.add(jPanel3, BorderLayout.SOUTH);
		
		setLocationByPlatform(true);
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jList.setSize(100, 550);
		setTabs(jTextPane, 4);
		
		jList.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				selectTrigger();
			}
		});
		
		jButton1.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				StringBuffer message = new StringBuffer("00");
				JOptionPane.showMessageDialog(null, message);
			}
		});
		
		jButton2.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				jTextField1.setText("");
				jTextField2.setText("");
				jTextPane.setText("");
			}
		});
		
		jButton3.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				deleteTrigger();
			}
		});
		
		jButton4.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				insertTrigger();
			}
		});
		
		jButton5.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				copyTrigger();
			}
		});
		
		jButton6.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				searchTrigger();
			}
		});
		
		jButton7.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				upateTrigger();
			}
		});
	}
	
	private void setTabs(JTextPane textPane, int charactersPerTab)
	{
		FontMetrics fm = textPane.getFontMetrics(textPane.getFont());
		int charWidth = fm.charWidth('w');
		int tabWidth = charWidth * charactersPerTab;
		
		TabStop[] tabs = new TabStop[10];
		
		for (int j = 0; j < tabs.length; j++)
		{
			int tab = j + 1;
			tabs[j] = new TabStop(tab * tabWidth);
		}
		
		TabSet tabSet = new TabSet(tabs);
		SimpleAttributeSet attributes = new SimpleAttributeSet();
		StyleConstants.setTabSet(attributes, tabSet);
		textPane.getStyledDocument().setParagraphAttributes(0,
				textPane.getDocument().getLength(), attributes, true);
	}
	
	protected void upateTrigger()
	{
		if (null == titleList | titleList.size() == 0)
		{
			jLabel.setText("没有可修改内容！");
			return;
		}
		String selectString = jList.getSelectedValue();
		
		if (null == selectString | "".equals(selectString))
		{
			jLabel.setText("没有选择内容！");
			return;
		}
		
		String id = selectString.substring(0, selectString.indexOf(":"));
		String title = jTextField1.getText();
		String content = jTextPane.getText();
		
		boolean updateSuccess = DBHandle.update(id, title, content);
		
		if (updateSuccess)
		{
			jLabel.setText("更新信息：" + id + " 成功。");
			DBHandle.getTitles(titleList);
			jList.setListData((String[]) titleList.toArray(new String[0]));
		} else
		{
			jLabel.setText("更新信息：" + id + " 失败！");
		}
	}
	
	protected void selectTrigger()
	{
		String selectString = jList.getSelectedValue();
		String id = selectString.substring(0, selectString.indexOf(":"));
		String title = selectString.substring(1 + selectString.indexOf(":"),
				selectString.length());
		
		String context = DBHandle.getContext(id);
		if (context == null | "".equals(context))
		{
			jLabel.setText("获取信息：" + selectString + " 失败。");
		} else
		{
			jTextField1.setText(title);
			jTextPane.setText(context);
		}
		
	}
	
	protected void searchTrigger()
	{
		String searchContent = jTextField2.getText();
		boolean searchSuccess = DBHandle.search(titleList, searchContent);
		
		if (searchSuccess)
		{
			jLabel.setText("搜索信息：" + searchContent + " 成功。");
		} else
		{
			jLabel.setText("搜索信息：" + searchContent + " 失败！");
			DBHandle.getTitles(titleList);
		}
		jList.setListData((String[]) titleList.toArray(new String[0]));
	}
	
	protected void copyTrigger()
	{
		String content = jTextPane.getText();
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable theCode = new StringSelection(content);
		clipboard.setContents(theCode, null);
	}
	
	protected void insertTrigger()
	{
		String title = jTextField1.getText();
		String content = jTextPane.getText();
		
		boolean insertSuccess = DBHandle.insert(title, content);
		
		if (insertSuccess)
		{
			jLabel.setText("插入信息：" + title + " 成功。");
			DBHandle.getTitles(titleList);
			jList.setListData((String[]) titleList.toArray(new String[0]));
		} else
		{
			jLabel.setText("插入信息：" + title + " 失败！");
		}
		
	}
	
	protected void deleteTrigger()
	{
		if (null == titleList | titleList.size() == 0)
		{
			jLabel.setText("没有可删除内容！");
			return;
		}
		
		List<String> selectList = jList.getSelectedValuesList();
		
		if (null == selectList | selectList.size() == 0)
		{
			jLabel.setText("没有选择内容！");
			return;
		}
		
		for (String selectString : selectList)
		{
			String id = selectString.substring(0, selectString.indexOf(":"));
			boolean deleteSuccess = DBHandle.delete(id);
			
			if (deleteSuccess)
			{
				jLabel.setText("删除信息：" + id + " 成功。");
				DBHandle.getTitles(titleList);
				jList.setListData((String[]) titleList.toArray(new String[0]));
			} else
			{
				jLabel.setText("删除信息：" + id + " 失败！");
			}
		}
	}
	
	public static void main(String[] args)
	{
		MainLoop ml = new MainLoop();
		ml.setVisible(true);
		
	}
}

package org.sixlab.mycode;

import java.awt.BorderLayout;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.TabSet;
import javax.swing.text.TabStop;

public class MainLoop extends JFrame
{
	private JPanel				jPanel1;
	private JPanel				jPanel2;
	private JPanel				jPanel3;
	private JPanel				jPanel4;
	private JTextField			jTextField2;
	private JTextField			jTextField1;
	private JScrollPane			jScrollPane1;
	private JScrollPane			jScrollPane2;
	private JButton				jButton1;
	private JButton				jButton2;
	private JButton				jButton3;
	private JButton				jButton4;
	private JButton				jButton5;
	private JButton				jButton6;
	private JButton				jButton7;
	private JButton				jButton8;
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
		jTextField1 = new JTextField();
		jButton6 = new JButton("搜索");
		jButton8 = new JButton("字体");
		jTextField2 = new JTextField();
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
		jPanel4.add(jButton8, BorderLayout.WEST);
		jPanel4.add(jTextField1, BorderLayout.CENTER);
		jPanel4.add(jButton6, BorderLayout.EAST);
		jPanel4.add(jTextField2, BorderLayout.SOUTH);
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
		
		jTextField2.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					copyTrigger();
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
		
		jTextPane.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					copyTrigger();
				}
			}
		});
		
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
				jTextField2.setText("");
				jTextField1.setText("");
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
		
		jButton8.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				fontTrigger();
			}
		});
	}
	
	protected void fontTrigger()
	{
		String sizeString = jTextField1.getText();
		for (int i = 0; i < sizeString.length(); i++)
		{
			if (!Character.isDigit(sizeString.charAt(i)))
			{
				jLabel.setText("请输入数字再改变字体大小");
				jTextField1.requestFocus();
				return;
			}
		}
		int size = Integer.parseInt(sizeString);
		
		if ((size > 0) && (size < 512))
		{
			MutableAttributeSet attr = new SimpleAttributeSet();
			StyleConstants.setFontSize(attr, size);
			
			StyledDocument doc = (StyledDocument) jTextPane.getDocument();
			doc.setCharacterAttributes(0, jTextPane.getText().length(), attr,
					false);
			
			StyledEditorKit k = (StyledEditorKit) jTextPane.getEditorKit();
			MutableAttributeSet inputAttributes = k.getInputAttributes();
			inputAttributes.addAttributes(attr);
		} else
		{
			jLabel.setText("超出范围");
			
		}
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
		String title = jTextField2.getText();
		String content = jTextPane.getText();
		
		title = title.replace("'", "''");
		content = content.replace("'", "''");
		
		boolean updateSuccess = DBHandle.update(id, title, content);
		
		if (updateSuccess)
		{
			jLabel.setText("更新信息：" + id + " 成功。");
			DBHandle.getTitles(titleList);
			jList.setListData(titleList.toArray(new String[0]));
			jList.repaint();
		} else
		{
			jLabel.setText("更新信息：" + id + " 失败！");
		}
		jTextField2.requestFocus();
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
			jTextField2.setText(title);
			jTextPane.setText(context);
		}
		jTextField2.requestFocus();
	}
	
	protected void searchTrigger()
	{
		String searchContent = jTextField1.getText();
		boolean searchSuccess = DBHandle.search(titleList, searchContent);
		
		if (searchSuccess)
		{
			jLabel.setText("搜索信息：" + searchContent + " 成功。");
		} else
		{
			jLabel.setText("搜索信息：" + searchContent + " 失败！");
			DBHandle.getTitles(titleList);
		}
		jList.setListData(titleList.toArray(new String[0]));
		jList.repaint();
		jList.requestFocus();
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
		String title = jTextField2.getText();
		String content = jTextPane.getText();
		
		title = title.replace("'", "''");
		content = content.replace("'", "''");
		
		boolean insertSuccess = DBHandle.insert(title, content);
		
		if (insertSuccess)
		{
			jLabel.setText("插入信息：" + title + " 成功。");
			DBHandle.getTitles(titleList);
			jList.setListData(titleList.toArray(new String[0]));
			jList.repaint();
		} else
		{
			jLabel.setText("插入信息：" + title + " 失败！");
		}
		jTextField2.requestFocus();
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
				jList.setListData(titleList.toArray(new String[0]));
				jList.repaint();
			} else
			{
				jLabel.setText("删除信息：" + id + " 失败！");
			}
		}
		jTextField2.requestFocus();
	}
	
	public static void main(String[] args)
	{
		MainLoop ml = new MainLoop();
		ml.setVisible(true);
		if (ml.titleList.size() > 0)
		{
			ml.jList.setSelectedIndex(0);
			ml.selectTrigger();
		}
		ml.jTextField2.requestFocus();
	}
}

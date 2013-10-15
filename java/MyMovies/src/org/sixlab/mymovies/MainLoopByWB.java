package org.sixlab.mymovies;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import swing2swt.layout.BorderLayout;

public class MainLoopByWB extends Shell
{
	private Text					idText;
	private Text					nameText;
	private Text					markText;
	private Text					dateText;
	
	private Table					table;
	
	private String					gloabID;
	private String					gloabName;
	private String					gloabMark;
	private String					gloabDate;
	
	private Button					updateButton;
	private Button					insertButton;
	private Button					resetButton;
	private Button					searchButton;
	private Button					helpButton;
	private Label					resultLabel;
	private Vector<Vector<String>>	content;
	
	public static void main(String args[])
	{
		try
		{
			Display display = Display.getDefault();
			MainLoopByWB shell = new MainLoopByWB(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed())
			{
				if (!display.readAndDispatch())
				{
					display.sleep();
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public MainLoopByWB(Display display)
	{
		super(display, SWT.SHELL_TRIM);
		setLayout(new BorderLayout(0, 0));
		
		Group topGroup = new Group(this, SWT.NONE);
		topGroup.setLayoutData(BorderLayout.NORTH);
		topGroup.setLayout(new GridLayout(4, false));
		
		idText = new Text(topGroup, SWT.BORDER);
		idText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		
		nameText = new Text(topGroup, SWT.BORDER);
		nameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		
		markText = new Text(topGroup, SWT.BORDER);
		markText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		
		dateText = new Text(topGroup, SWT.BORDER);
		dateText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		
		updateButton = new Button(topGroup, SWT.NONE);
		updateButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				updateTrigger();
				super.mouseUp(e);
			}
		});
		updateButton.setText("修改");
		
		searchButton = new Button(topGroup, SWT.NONE);
		searchButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				searchTrigger();
				super.mouseUp(e);
			}
		});
		searchButton.setText("搜索");
		
		resetButton = new Button(topGroup, SWT.NONE);
		resetButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				idText.setText("");
				nameText.setText("");
				markText.setText("");
				dateText.setText("");
				super.mouseUp(e);
			}
		});
		resetButton.setText("重置");
		
		insertButton = new Button(topGroup, SWT.NONE);
		insertButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				insertTrigger();
				super.mouseUp(e);
			}
		});
		insertButton.setText("插入");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(this,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setLayoutData(BorderLayout.CENTER);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		table = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		scrolledComposite.setContent(table);
		scrolledComposite.setMinSize(table
				.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		TableColumn idColumn = new TableColumn(table, SWT.NONE);
		idColumn.setText("ID");
		TableColumn nameColumn = new TableColumn(table, SWT.NONE);
		nameColumn.setText("名字");
		TableColumn markColumn = new TableColumn(table, SWT.NONE);
		markColumn.setText("Mark");
		TableColumn dateColumn = new TableColumn(table, SWT.NONE);
		dateColumn.setText("日期");
		
		gloabID = "";
		gloabName = "";
		gloabMark = "";
		gloabDate = "";
		content = new Vector<>();
		QueryDatabase.queryFilm(gloabID, gloabName, gloabMark, gloabDate,
				content);
		setTableContent();
		
		table.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseDoubleClick(org.eclipse.swt.events.MouseEvent e)
			{
				tableDoubleClicked(e);
				super.mouseDoubleClick(e);
			}
		});
		
		Group bottomGroup = new Group(this, SWT.NONE);
		bottomGroup.setLayoutData(BorderLayout.SOUTH);
		bottomGroup.setLayout(new GridLayout(2, false));
		
		helpButton = new Button(bottomGroup, SWT.NONE);
		helpButton.setText("帮助");
		
		resultLabel = new Label(bottomGroup, SWT.NONE);
		resultLabel.setText("共 " + content.size() + " 部。");
		createContents();
	}
	
	protected void createContents()
	{
		setText("我的电影查询");
		setSize(450, 550);
	}
	
	@Override
	protected void checkSubclass()
	{
		// Disable the check that prevents subclassing of SWT components
	}
	
	private void setTableContent()
	{
		for (Vector<String> aVector : content)
		{
			TableItem aItem = new TableItem(table, SWT.NONE);
			String[] itemArray = new String[4];
			for (int i = 0; i < itemArray.length; i++)
			{
				itemArray[i] = aVector.get(i);
			}
			aItem.setText(itemArray);
		}
		for (int i = 0; i < table.getColumnCount(); i++)
		{
			table.getColumn(i).pack();
		}
	}
	
	private void searchTrigger()
	{
		String queryID = idText.getText();
		String queryName = nameText.getText();
		String queryMark = markText.getText();
		String queryDate = dateText.getText();
		
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
		table.redraw();
		nameText.forceFocus();
		this.pack();
		// jTable.invalidate();
		// jTable.repaint();
	}
	
	private void insertTrigger()
	{
		String queryName = nameText.getText();
		String queryMark = markText.getText();
		String queryDate = dateText.getText();
		
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
			nameText.forceFocus();
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
				dateText.forceFocus();
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
						dateText.forceFocus();
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
			table.redraw();
			// jTable.invalidate();
			// jTable.repaint();
		} else
		{
			resultLabel.setText("增加失败！");
		}
		idText.forceFocus();
		this.pack();
	}
	
	private void updateTrigger()
	{
		String queryID = idText.getText();
		String queryName = nameText.getText();
		String queryMark = markText.getText();
		String queryDate = dateText.getText();
		
		if (queryID == null | queryID.equals(""))
		{
			resultLabel.setText("严重警告：没有ID无法修改信息，请一定要输入ID！！！");
			idText.forceFocus();
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
					idText.forceFocus();
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
				dateText.forceFocus();
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
						dateText.forceFocus();
						this.pack();
						return;
					}
				}
			}
			nameText.forceFocus();
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
			table.redraw();
			// jTable.invalidate();
			// jTable.repaint();
		} else
		{
			resultLabel.setText("修改失败！");
		}
		this.pack();
	}
	
	private void tableDoubleClicked(org.eclipse.swt.events.MouseEvent e)
	{
		
		nameText.forceFocus();
	}
}

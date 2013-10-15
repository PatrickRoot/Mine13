package org.sixlab.mymovies;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

public class MainLoopBySwt
{
	private Display	display;
	private Shell	shell;
	private Button	modefyButton;
	private Button	searchButton;
	private Button	insertButton;
	private Button	resetButton;
	private Button	helpButton;
	
	private Text	idText;
	private Text	nameText;
	private Text	markText;
	private Text	dateText;
	
	private Label	label;
	
	private Table	table;
	
	public MainLoopBySwt()
	{
		display = new Display();
		shell = new Shell(display);
		shell.setText("看过的电影");
		shell.setLayout(new GridLayout(1, true));
		
		Group upGroup = new Group(shell, SWT.TOP);
		upGroup.setLayout(new GridLayout(4, true));
		
		idText = new Text(upGroup, SWT.NONE);
		nameText = new Text(upGroup, SWT.NONE);
		markText = new Text(upGroup, SWT.NONE);
		dateText = new Text(upGroup, SWT.NONE);
		
		modefyButton = new Button(upGroup, SWT.NONE);
		searchButton = new Button(upGroup, SWT.NONE);
		insertButton = new Button(upGroup, SWT.NONE);
		resetButton = new Button(upGroup, SWT.NONE);
		
		modefyButton.setText("修改");
		searchButton.setText("搜索");
		insertButton.setText("插入");
		resetButton.setText("重置");
		
		Group centerGroup = new Group(shell, SWT.CENTER);
		
		table = new Table(centerGroup, SWT.FULL_SELECTION);
		
		Group downGroup = new Group(shell, SWT.BOTTOM);
		downGroup.setLayout(new GridLayout(2, false));
		
		label = new Label(downGroup, SWT.NONE);
		label.setText("none");
		
		helpButton = new Button(downGroup, SWT.NONE);
		helpButton.setText("帮助");
		
		modefyButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				updateTrigger();
			}
		});
		
		insertButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				insertTrigger();
			}
		});
		
		searchButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				searchTrigger();
			}
		});
		
		resetButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				// TODO
			}
		});
		
		helpButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseUp(MouseEvent e)
			{
				// TODO
			}
		});
		
		// buttonGroup.layout();
		// textGroup.layout();
		shell.pack();
		shell.layout();
		shell.open();
	}
	
	protected void insertTrigger()
	{
		// TODO Auto-generated method stub
	}
	
	protected void updateTrigger()
	{
		// TODO Auto-generated method stub
	}
	
	protected void searchTrigger()
	{
		// TODO Auto-generated method stub
	}
	
	public static void main(String[] args)
	{
		MainLoopBySwt mlbs = new MainLoopBySwt();
		while (!mlbs.shell.isDisposed())
		{
			if (!mlbs.display.readAndDispatch())
			{
				mlbs.display.sleep();
			}
		}
		mlbs.display.dispose();
	}
}

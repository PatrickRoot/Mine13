package com.sixlab;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
/**
 * 
 * @author sixlab@nianqinianyi nianqinianyi@163.com
 * @version  2013-2-15,下午22:28:34
 *
 */
public class MainLoop extends JFrame {
	
	private static final long serialVersionUID = -3991473444098558558L;

	public MainLoop(){
		this.setTitle("我的电影查询");
		this.setBounds(450, 150, 400, 600);
		Container container=this.getContentPane();
		JPanel jupJPanel=new JPanel();
		JPanel jcenJPanel=new JPanel();
		JPanel jdownpJPanel=new JPanel();

		//上部
		JTextField jTextField=new JTextField();
		jTextField.setColumns(15);
		JButton jseabButton=new JButton("搜索");
		JButton jinButton=new JButton("插入");
		jupJPanel.add(jTextField, 0);
		jupJPanel.add(jseabButton,1);
		jupJPanel.add(jinButton,2);
		
		//中部
		String[] title = {"序号","名字","日期","备注"};
		String[][] taa=new String[30][4];
		JTable jTable=new JTable(taa, title);
		jcenJPanel.add(jTable);
		
		//下部
		JTextPane jTextPane=new JTextPane();
		jdownpJPanel.add(jTextPane);
		
		//整合
		container.add(jupJPanel, BorderLayout.NORTH);
		container.add(jcenJPanel,BorderLayout.CENTER);
		container.add(jdownpJPanel,BorderLayout.SOUTH);

	}
	
	public static void main(String[] args) {
		MainLoop mLoop=new MainLoop();
		mLoop.setVisible(true);
	}

}

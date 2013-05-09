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
 * @version  2013-2-15,����22:28:34
 *
 */
public class MainLoop extends JFrame {
	
	private static final long serialVersionUID = -3991473444098558558L;

	public MainLoop(){
		this.setTitle("�ҵĵ�Ӱ��ѯ");
		this.setBounds(450, 150, 400, 600);
		Container container=this.getContentPane();
		JPanel jupJPanel=new JPanel();
		JPanel jcenJPanel=new JPanel();
		JPanel jdownpJPanel=new JPanel();

		//�ϲ�
		JTextField jTextField=new JTextField();
		jTextField.setColumns(15);
		JButton jseabButton=new JButton("����");
		JButton jinButton=new JButton("����");
		jupJPanel.add(jTextField, 0);
		jupJPanel.add(jseabButton,1);
		jupJPanel.add(jinButton,2);
		
		//�в�
		String[] title = {"���","����","����","��ע"};
		String[][] taa=new String[30][4];
		JTable jTable=new JTable(taa, title);
		jcenJPanel.add(jTable);
		
		//�²�
		JTextPane jTextPane=new JTextPane();
		jdownpJPanel.add(jTextPane);
		
		//����
		container.add(jupJPanel, BorderLayout.NORTH);
		container.add(jcenJPanel,BorderLayout.CENTER);
		container.add(jdownpJPanel,BorderLayout.SOUTH);

	}
	
	public static void main(String[] args) {
		MainLoop mLoop=new MainLoop();
		mLoop.setVisible(true);
	}

}

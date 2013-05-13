package org.sixlab.calculator;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author sixlab@hundredkg nianqinianyi@163.com
 * @version 1.0.0 Apr 29, 2013 5:12:52 PM
 * 
 */
public class Calculator
{

	// 计算器的主界面
	private JFrame calculatorFrame;
	// 显示数字的面板
	private JTextField showField;
	// 放置按钮的JPanel
	private JPanel theJPanel;
	// calculatorButtons保存了所有的按钮
	private JButton[][] calculatorButtons;

	// 分别为前一个数字、计算结果
	private double preNum;
	private double resultNum;

	// 上一个运算符和其在输入列表的位置
	private String preOperator;
	private int operatorIndex = -1;

	// 判断是否是第一次按=
	private boolean notFirstEqual = false;
	private boolean isZeroDividend = false;

	// 上一次的按钮
	// private JButton preButton;

	// 输入序列，只保存数字、小数点、+-*/四个运算符和负号
	public StringBuffer inputList = new StringBuffer();

	/**
	 * Calculator的布局，调用此方法运行计算器
	 */
	public void runCalculator()
	{
		// 初始化组件
		calculatorFrame = new JFrame("计算器 by sixlab");
		showField = new JTextField("0");
		theJPanel = new JPanel(new GridLayout(5, 4, 5, 5));
		calculatorButtons = new JButton[5][4];

		// 文本框右对齐
		showField.setHorizontalAlignment(JTextField.RIGHT);

		// 设置Frame
		calculatorFrame.setResizable(false);
		calculatorFrame.getContentPane().add(showField, BorderLayout.NORTH);
		calculatorFrame.getContentPane().add(theJPanel, BorderLayout.SOUTH);
		calculatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		calculatorFrame.setSize(220,300);
		calculatorFrame.setLocationRelativeTo(null);

		// 除了1-9外的所有按钮
		calculatorButtons[0][0] = new JButton("C");
		calculatorButtons[0][1] = new JButton("CE");
		calculatorButtons[0][2] = new JButton("←");
		calculatorButtons[0][3] = new JButton("+/-");

		calculatorButtons[4][0] = new JButton("0");
		calculatorButtons[4][1] = new JButton(".");
		calculatorButtons[4][2] = new JButton("=");
		calculatorButtons[4][3] = new JButton("/");

		calculatorButtons[1][3] = new JButton("+");
		calculatorButtons[2][3] = new JButton("-");
		calculatorButtons[3][3] = new JButton("*");

		// 从1-9共9个数字的按钮 以及 将按钮添加到JPanel
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				if (i > 0 && i < 4 && j < 3)
				{
					calculatorButtons[i][j] = new JButton(
							String.valueOf((3 * (3 - i) + j) + 1));
				}
				theJPanel.add(calculatorButtons[i][j]);
				calculatorButtons[i][j].addMouseListener(new ClickListener());
			}
		}
		calculatorFrame.pack();
		calculatorFrame.setVisible(true);
	}

	class ClickListener extends MouseAdapter implements KeyListener
	{
		private void calculatorNum()
		{
			resultNum = (operatorIndex == 0) ? 0 : (Double
					.parseDouble(inputList.substring(0, operatorIndex)));
			if (notFirstEqual == false)
			{
				preNum = (operatorIndex == inputList.length() - 1) ? resultNum
						: Double.parseDouble(inputList.substring(
								operatorIndex + 1, inputList.length()));
			}
			String currOperator = inputList.substring(operatorIndex,
					operatorIndex + 1);
			if (currOperator.equals("+"))
			{
				resultNum = resultNum + preNum;
			}
			else if (currOperator.equals("-"))
			{
				resultNum = resultNum - preNum;
			}
			else if (currOperator.equals("*"))
			{
				resultNum = resultNum * preNum;
			}
			else if (currOperator.equals("/"))
			{
				if (preNum == 0)
				{
					isZeroDividend = true;
					resultNum = 0;
					preOperator = null;
					operatorIndex = -1;
					notFirstEqual = false;
					inputList = new StringBuffer();
				}
				else
				{
					resultNum = resultNum / preNum;
				}
			}

		}

		private void showNum(double theNum)
		{
			if (isZeroDividend == false)
			{
				if ((theNum % 1) == 0.0)
				{
					showField.setText(String.valueOf((int) theNum));
				}
				else
				{
					showField.setText(String.valueOf(theNum));
				}
			}
			else
			{
				showField.setText("被除数不能为零");
				isZeroDividend = false;
			}
		}

		private void showNum(String atheNum)
		{
			double theNum = Double.parseDouble(atheNum);
			showNum(theNum);
		}

		@Override
		public void mouseClicked(MouseEvent e)
		{
			// 获得事件源的按钮
			JButton clickedButton = (JButton) e.getSource();
			// 当点击按钮 C 时清空所有数据 ，文本框显示：0
			if (clickedButton.equals(calculatorButtons[0][0]))
			{
				preNum = 0;
				resultNum = 0;
				operatorIndex = -1;
				preOperator = null;
				inputList = new StringBuffer();
				notFirstEqual = false;
				isZeroDividend = false;
				showNum(0);
			}
			// 按钮 CE ，清空当前显示的数据
			else if (clickedButton.equals(calculatorButtons[0][1]))
			{
				showNum(0);
				inputList.delete(operatorIndex + 1, inputList.length());
			}
			// 按下按钮 退格键，删除最后一个字符
			else if (clickedButton.equals(calculatorButtons[0][2]))
			{
				if ((operatorIndex + 1) != inputList.length())
				{
					inputList.deleteCharAt(inputList.length() - 1);
					if (inputList.length() == (operatorIndex + 1))
					{
						showNum(0);
					}
					else
					{
						showNum(inputList.substring(operatorIndex + 1));
					}
				}
			}
			// 按下按钮 +/- 正负数转换
			else if (clickedButton.equals(calculatorButtons[0][3]))
			{
				if (operatorIndex != (inputList.length() - 1))
				{
					if ((inputList.substring(operatorIndex + 1,
							operatorIndex + 2)).equals("-"))
					{
						inputList.deleteCharAt(operatorIndex + 1);
					}
					else
					{
						inputList.insert(operatorIndex + 1, "-");
					}
					showNum(inputList.substring(operatorIndex + 1));
				}
			}
			// 对于等号的操作
			else if (clickedButton.equals(calculatorButtons[4][2]))
			{
				if (operatorIndex != -1)
				{
					calculatorNum();
					showNum(resultNum);
					if (isZeroDividend == false)
					{
						inputList = new StringBuffer(resultNum + preOperator);
						operatorIndex = inputList.length() - 1;
						notFirstEqual = true;
					}
				}
			}
			// 对于数字、操作符、小数点的操作
			else
			{
				// 如果是操作符
				for (int i = 1; i < 5; i++)
				{
					if (clickedButton.equals(calculatorButtons[i][3]))
					{
						preOperator = clickedButton.getText();
						// 两种情况，输入了数字还没有输入过运算符||什么都没有输入过
						if (operatorIndex == -1)
						{
							inputList.append(preOperator);
							operatorIndex = inputList.length() - 1;
						}
						// 除了上边的情况，只有上一个输入为运算符才等
						else if (inputList.length() - 1 == operatorIndex)
						{
							inputList.replace(inputList.length() - 1,
									inputList.length(), preOperator);
						}
						// 数字+运算符+数字 情况
						else
						{
							notFirstEqual = false;
							calculatorNum();
							showNum(resultNum);
							if (isZeroDividend == false)
							{
								inputList = new StringBuffer(resultNum
										+ preOperator);
								operatorIndex = inputList.length() - 1;
							}
						}
						return;
					}
				}
				// 如果是小数点
				if (clickedButton.equals(calculatorButtons[4][1]))
				{
					if (inputList.length() - 1 == operatorIndex)
					{
						inputList.append("0.");
					}
					else
					{
						inputList.append(clickedButton.getText());
					}
					showNum(inputList.substring(operatorIndex + 1));
				}
				// 剩下的只有数字的操作
				else
				{
					inputList.append(clickedButton.getText());
					showNum(inputList.substring(operatorIndex + 1));
				}
			}
		}

		@Override
		public void keyTyped(KeyEvent e)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent e)
		{
			// TODO Auto-generated method stub

		}

	}

	public static void main(String[] args)
	{
		Calculator myCalculator = new Calculator();

		myCalculator.runCalculator();
	}
}
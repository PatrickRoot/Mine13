package org.sixlab.algorithm40;

/**
 * 
 * @author loki
 * 
 *         题目：利用条件运算符的嵌套来完成此题：学习成绩> =90分的同学用A表示，60-89分之间的用B表示，60分以下的用C表示。
 */
public class No5Score {
	public static void main(String[] args) {
		try {
			System.out.println(giveScore(100));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static char giveScore(int num) throws Exception {
		if (num <= 100 && num >= 90) {
			return 'A';
		} else if (num < 90 && num >= 60) {
			return 'B';
		} else if (num >= 0 && num < 60) {
			return 'C';
		} else {
			throw new Exception("wrong score");
		}
	}
}

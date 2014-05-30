package org.sixlab.algorithm40;

import java.util.Scanner;

/**
 * 
 * @author loki
 * 
 *         题目：求s=a+aa+aaa+aaaa+aa…a的值，其中a是一个数字。
 * 
 *         例如2+22+222+2222+22222(此时共有5个数相加)，几个数相加有键盘控制。
 * 
 */
public class No8PlusNumber {

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		int exitCode = 1;
		while (exitCode != 0) {

			System.out.print("请输入a：");
			int a = sc.nextInt();
			while (a > 9 || a < 0) {
				System.out.print("请重新输入a：");
				a = sc.nextInt();
			}
			if (a == 0) {
				System.out.print("结束");
				System.exit(1);
			}
			System.out.print("请输入个数：");
			int count = sc.nextInt();

			System.out.println("结果为：" + plusNumber(a, count));
			System.out.print("输入0退出，输入其他数字继续：");
			exitCode = sc.nextInt();
		}

	}

	private static long eachNumber(int a, int count) {
		if (count == 1) {
			return a;
		} else {
			return a + 10 * eachNumber(a, count - 1);
		}
	}

	private static long plusNumber(int a, int count) {
		long bigest = eachNumber(a, count);
		if (count == 1) {
			return bigest;
		} else {
			return bigest + plusNumber(a, count - 1);
		}
	}
}

package org.sixlab.algorithm40;

/**
 * 
 * @author loki
 * 
 *         题目：一个数如果恰好等于它的因子之和，这个数就称为 "完数 "。
 * 
 *         例如6=1＋2＋3.编程 找出1000以内的所有完数。
 */
public class No9PerfectNumber {
	public static void main(String[] args) {
		for (int i = 0; i <= 1000; i++) {
			if (isPerfectNumber(i)) {
				System.out.println(i);
			}
		}
	}

	private static boolean isPerfectNumber(long number) {
		int sum = 0;

		for (int i = 1; i < number / 2 + 1; i++) {
			if (number % i == 0) {
				sum += i;
			}
		}

		if (number == sum) {
			return true;
		}
		return false;
	}
}

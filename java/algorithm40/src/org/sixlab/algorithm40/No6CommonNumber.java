package org.sixlab.algorithm40;

/**
 * 
 * @author loki
 * 
 *         题目：输入两个正整数m和n，求其最大公约数和最小公倍数。
 */
public class No6CommonNumber {
	public static void main(String[] args) {
		System.out.println(leastCommonMultiple(70, 100));
		System.out.println(greatestCommonDivisor(70, 100));
	}

	private static int greatestCommonDivisor(int x, int y) {
		int min = Math.min(x, y);
		for (int i = min; i > 1; i--) {
			if (x % i == 0 && y % i == 0) {
				return i;
			}
		}
		return 1;
	}

	private static int leastCommonMultiple(int x, int y) {
		int max = Math.max(x, y);

		int result = 1;
		for (int i = 2; i < max; i++) {
			if (x % i == 0 && y % i == 0) {
				result *= i;
				x /= i;
				y /= i;
				i--;
			} else if (x % i == 0) {
				result *= i;
				x /= i;
				i--;
			} else if (y % i == 0) {
				result *= i;
				y /= i;
				i--;
			}
		}
		return result;
	}
}

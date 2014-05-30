package org.sixlab.algorithm40;

/**
 * 
 * @author loki
 * 
 *         题目：古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第四个月后每个月又生一对兔子，假如兔子都不死，
 *         问每个月的兔子总数为多少？
 */
public class No1Rabbit {
	public static void main(String[] args) {
		for (int i = 1; i <= 10; i++) {
			System.out.println("第" + i + "个月的兔子数量：" + countRabbit(i));
		}
	}

	public static int countRabbit(int month) {
		if (month <= 0) {
			return 0;
		} else if (month < 3) {
			return 1;
		} else {
			return countRabbit(month - 1) + countRabbit(month - 2);
		}
	}
}
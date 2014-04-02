package org.sixlab.algorithm40;

public class Rabbit {
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
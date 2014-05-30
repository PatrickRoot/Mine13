package org.sixlab.algorithm40;

/**
 * 
 * @author loki
 * 
 *         题目：一球从100米高度自由落下，每次落地后反跳回原高度的一半；再落下，求它在 第10次落地时，共经过多少米？第10次反弹多高？
 * 
 */
public class No10Ball {
	public static void main(String[] args) {
		System.out.println("第十次反弹高度；" + eachLength(10));
		System.out.println("第十次总距离；" + totalLength(10));
	}
	
	private static double eachLength(int times) {
		if (times == 0) {
			return 100.0;
		} else {
			return eachLength(times - 1) / 2;
		}
	}
	
	private static double totalLength(int times) {
		if (times == 1) {
			return 100.0;
		} else if (times == 2) {
			return 200.0;
		} else {
			return eachLength(times - 1) * 2 + totalLength(times - 1);
		}
	}
}

package org.sixlab.algorithm40;

public class PrimeFactor {
	public static void main(String[] args) {
		int num = 90;
		System.out.println(decompose(num));
	}
	
	private static String decompose(int num) {
		String result = String.valueOf(num) + " = ";
		int endNum = (int) (Math.sqrt(num) + 1);
		for (int i = 2; i < endNum; i++) {
			if (isPrime(i) && isDivisible(num, i)) {
				result += (i + " * ");
				num /= i;
				i--;
			}
		}
		
		result = result.substring(0, result.length() - 3);
		
		return result;
	}
	
	private static boolean isPrime(int num) {
		if (num == 2) {
			return true;
		}
		for (int i = 2; i <= Math.sqrt(num) + 1; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean isDivisible(int num, int num2) {
		if (num2 != 0 && num % num2 == 0) {
			return true;
		} else {
			return false;
		}
	}
}

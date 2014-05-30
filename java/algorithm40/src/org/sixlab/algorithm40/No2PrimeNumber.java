package org.sixlab.algorithm40;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author loki
 * 
 *         题目：判断101-200之间有多少个素数，并输出所有素数。
 */
public class No2PrimeNumber {
	public static void main(String[] args) {

		List<Integer> primeNumbers = new ArrayList<Integer>();
		for (int i = 101; i < 201; i++) {
			if (isPrime(i)) {
				primeNumbers.add(i);
			}
		}

		System.out.println(primeNumbers.size());
		System.out.println(primeNumbers);
	}

	public static boolean isPrime(int num) {
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
}
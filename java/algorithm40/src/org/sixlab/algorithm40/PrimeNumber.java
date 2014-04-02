package org.sixlab.algorithm40;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumber {
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
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
}
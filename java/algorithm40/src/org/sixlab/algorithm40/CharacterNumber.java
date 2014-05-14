package org.sixlab.algorithm40;

import java.util.HashMap;
import java.util.Map;

public class CharacterNumber {
	public static void main(String[] args) {
		String testString="Hello World! Hello 123! >-<";
		System.out.println(countNumber(testString));
	}
	
	private static Map<String, Integer> countNumber(String input) {
		int englishNumber = 0;
		int numberNumber = 0;
		int spaceNumber = 0;
		int otherNumber = 0;
		
		int length = input.length();
		
		for (int i = 0; i < length; i++) {
			char tempChar = input.charAt(i);
			if (Character.isDigit(tempChar)) {
				numberNumber++;
			} else if (Character.isSpaceChar(tempChar)) {
				spaceNumber++;
			} else if (Character.isLetter(tempChar)) {
				englishNumber++;
			} else {
				otherNumber++;
			}
		}
		
		Map<String, Integer> map = new HashMap<>();
		map.put("english", englishNumber);
		map.put("number", numberNumber);
		map.put("space", spaceNumber);
		map.put("other", otherNumber);
		
		return map;
	}
}

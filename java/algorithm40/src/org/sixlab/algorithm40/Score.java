package org.sixlab.algorithm40;

public class Score {
	public static void main(String[] args) {
		try {
			System.out.println(giveScore(100));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static char giveScore(int num) throws Exception {
		if (num <= 100 && num >= 90) {
			return 'A';
		} else if (num < 90 && num >= 60) {
			return 'B';
		} else if (num >= 0 && num < 60) {
			return 'C';
		} else {
			throw new Exception("wrong score");
		}
	}
}

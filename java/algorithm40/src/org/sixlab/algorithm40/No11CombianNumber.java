package org.sixlab.algorithm40;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author loki
 * 
 *         题目：有1、2、3、4个数字，能组成多少个互不相同且无重复数字的三位数？都是多少？
 * 
 */
public class No11CombianNumber {
	public static void main(String[] args) {
		List<Integer> numberList = new ArrayList<Integer>();
		List<Integer> resultList = new ArrayList<Integer>();
		
		numberList.add(1);
		numberList.add(2);
		numberList.add(3);
		numberList.add(4);
		
		int result = 0;
		int temp1;
		int temp2;
		int temp3;
		
		for (int i = 4; i > 0; i--) {
			temp1 = numberList.get(i-1);
			numberList.remove(i-1);
			for (int j = 3; j > 0; j--) {
				temp2 = numberList.get(j-1);
				numberList.remove(j-1);
				for (int k = 0; k < 2; k++) {
					temp3 = numberList.get(k);
					result = temp1 * 100 + temp2 * 10 + temp3;
					resultList.add(result);
				}
				numberList.add(temp2);
			}
			numberList.clear();
			numberList.add(1);
			numberList.add(2);
			numberList.add(3);
			numberList.add(4);
		}
		
		System.out.println(resultList);
	}
}

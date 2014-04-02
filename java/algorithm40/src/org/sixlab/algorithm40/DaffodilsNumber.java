package org.sixlab.algorithm40;
 
public class DaffodilsNumber {
    public static void main(String[] args) {
        for (int i = 100; i < 1000; i++) {
            if (isDaffodilsNumber(i)) {
                System.out.println(i);
            }
        }
    }
     
    private static boolean isDaffodilsNumber(int num) {
        String numString = String.valueOf(num);
        int plus = 0;
        for (int i = 0; i < numString.length(); i++) {
            int a = (int) (num / (Math.pow(10, i)));
            a %= 10;
            plus += (int) Math.pow(a, 3);
        }
        if (num == plus) {
            return true;
        } else {
            return false;
        }
    }
}
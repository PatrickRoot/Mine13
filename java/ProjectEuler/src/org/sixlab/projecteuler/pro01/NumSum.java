package org.sixlab.projecteuler.pro01;

/**
 * Created by loki on 2014/7/17.
 */
public class NumSum {
    public static void main(String[] args) {
        System.out.println(countNum(1000));
    }

    public static int countNum(int num){
        int result = 0;



        for (int i = 1; i < 1000; i++) {
            if (is3or5(i)){
                result +=i;
            }
        }

        return result;
    }

    private static boolean is3or5(int i){
        if (i%3 == 0){
            System.out.println("3 * "+i/3+" = "+i);
            return true;
        }else if (i%5 == 0){
            System.out.println("5 * "+i/5+" = "+i);
            return true;
        }

        return false;
    }
}

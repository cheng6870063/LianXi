package com.cxx.test1;

import java.math.BigInteger;
import java.util.Scanner;

public class AddingReversedNumbers {

    public static void main(String[] args) {
        //将数字转换成字符然后用reverse方法倒转
        //StringBuilder比String要节省空间
        Scanner input = new Scanner(System.in);
        int numberOfTestCases = input.nextInt();
        while (numberOfTestCases != 0) {
            BigInteger first = input.nextBigInteger();
            BigInteger second = input.nextBigInteger();
            //直接通过空字符串+数字的形式转换为字符串（前后都可以用）
            StringBuilder firstString = new StringBuilder(first + "");
            StringBuilder secondString = new StringBuilder(second + "");
            BigInteger firstReversed = new BigInteger(firstString.reverse()
                    .toString());
            BigInteger secondReversed = new BigInteger(secondString.reverse()
                    .toString());
            BigInteger result = firstReversed.add(secondReversed);
            String resultReversed = new StringBuilder(result + "").reverse()
                    .toString();
            System.out.println(resultReversed.replaceFirst("^0*", ""));
            numberOfTestCases--;
        }
    }
}

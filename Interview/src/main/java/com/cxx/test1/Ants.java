package com.cxx.test1;

import java.util.Scanner;

public class Ants {
    public static void main(String args[]) throws Exception {
        //所有蚂蚁离开杆子的最短和最长时间
        Scanner sc = new Scanner(System.in);
        //多少组情况
        int testCases = sc.nextInt();
        //当前蚂蚁的位置

        int currentAnt;

        while(testCases > 0) {
            //杆子长度
            int length = sc.nextInt();
            //蚂蚁数量
            int numberOfAnts = sc.nextInt();

            int min = 0;
            int max = 0;
            while(numberOfAnts > 0) {
                currentAnt = sc.nextInt();
                currentAnt = currentAnt < length - currentAnt ? currentAnt : length - currentAnt;
                if(currentAnt > min) {
                    min = currentAnt;
                }
                if(length - currentAnt > max) {
                    max = length - currentAnt;
                }
                numberOfAnts--;
            }
            System.out.println(min + " " + max);
            testCases--;
        }
    }
}

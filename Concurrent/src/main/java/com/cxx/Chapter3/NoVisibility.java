package com.cxx.Chapter3;

public class NoVisibility {
    public static boolean ready = false;
    public static int number = 0;

    public static void main(String[] args) {
        new newThread().start();
        number = 42;
        System.out.println(ready);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ready = true;

    }

    private static class newThread extends Thread{
        public void run(){
            System.out.println(ready);
            while(!ready){
                Thread.yield();
                System.out.println(number);
            }
        }
    }
}

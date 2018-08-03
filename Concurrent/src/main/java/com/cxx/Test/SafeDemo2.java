package com.cxx.Test;

public class SafeDemo2 {
    private static volatile int count;

    public static void inc() {
        try {
            Thread.sleep(1);
        }catch (InterruptedException e){
            System.out.println(e.toString());
        }
        count ++;
    }

    public static void main(String[] args) throws InterruptedException{
        for(int i = 0;i < 1000;i ++){
            new Thread(()->{
                SafeDemo2.inc();
            }).start();
        }
        Thread.sleep(3000);
        System.out.println(count);
    }
}

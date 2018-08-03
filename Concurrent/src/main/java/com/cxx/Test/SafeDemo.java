package com.cxx.Test;

public class SafeDemo {
    private static int count;
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
                SafeDemo.inc();
            }).start();
        }
        Thread.sleep(1);
        System.out.println(count);
    }
}

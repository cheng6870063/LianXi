package com.cxx.Test;

import java.util.concurrent.TimeUnit;

public class InteruptDemo {
    private static int i;

    public static void main(String[] args) throws InterruptedException{
        Thread thread = new Thread(()->{
           System.out.println(Thread.currentThread().isInterrupted());
           while(!Thread.currentThread().isInterrupted()){
               i ++;
           }
           System.out.println("num:" + i);
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }
}

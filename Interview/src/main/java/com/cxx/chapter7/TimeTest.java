package com.cxx.chapter7;

import java.util.function.Function;

public class TimeTest {
    public static void main(String[] args) {

     /*   System.out.println("Sequential sum done in:" +
                measureSumPerf(ParallelStreams::sequentialSum, 10_000_000) + " msecs");

         *//*
           iterate生成的是装箱的对象，必须拆箱成数字才能求和；
           我们很难把iterate分成多个独立块来并行执行。
         *//*
        System.out.println("Sequential sum done in:" +
                measureSumPerf(ParallelStreams::parallelSum, 10_000_000) + " msecs");

        System.out.println("Sequential sum done in:" +
                measureSumPerf(ParallelStreams::iterativeSum, 10_000_000) + " msecs");*/

        System.out.println("Sequential sum done in:" +
                measureSumPerf(ParallelStreams::rangedSum, 10_000_000) + " msecs");

        System.out.println("Sequential sum done in:" +
                measureSumPerf(ParallelStreams::parallelRangedSum, 10_000_000) + " msecs");


    }

    /*
      可以用这个框架来测试某个函数的运行时间
    */
    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }

}

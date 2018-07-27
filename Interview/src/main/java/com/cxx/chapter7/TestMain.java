package com.cxx.chapter7;

import java.util.stream.Stream;

public class TestMain {
    public static void main(String[] args) {
        Long  value1 = Stream.iterate(1L, i -> i + 1)    //生成自然数无限流
                .limit(100)                     //限制到前n个数
                .reduce(0L, Long::sum); //对所有数字求和来归纳流

        Long  value2 = Stream.iterate(1L, i -> i + 1)
                .limit(100)
                .parallel()                    //将流转换为并行流
                .reduce(0L, Long::sum);

        int cpucount = Runtime.getRuntime().availableProcessors();//处理器数量

    }
}

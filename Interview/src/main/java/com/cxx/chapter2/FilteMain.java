package com.cxx.chapter2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilteMain {
    public static void main(String ... args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);

        List<Apple> redApples =
                filter(inventory, (Apple apple) -> "red".equals(apple.getColor()));
        List<Integer> evenNumbers =
                filter(numbers, (Integer i) -> i % 2 == 0);
    }


    //更抽象的方法
    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> result = new ArrayList<>();
        for(T e: list){
            if(p.test(e)){
                result.add(e);
            }
        }
        return result;
    }
}

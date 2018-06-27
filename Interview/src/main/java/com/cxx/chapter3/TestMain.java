package com.cxx.chapter3;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TestMain {
    static  Map<String, Function<Integer, Fruit>> map = new HashMap<>();

    static{
        map.put("apple", Apple::new);
        map.put("orange", Orange::new);
    }

    public static void main(String ... args){
        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = map(weights, Apple::new);

        List<Apple> applelist = maps(weights, Apple::new);


        Fruit fruit =  giveMeFruit("Apple",150);
        System.out.print(fruit.toString());
    }

    public static List<Apple> map(List<Integer> list,
                                  Function<Integer, Apple> f){
        List<Apple> result = new ArrayList<>();
        for(Integer e: list){
            result.add(f.apply(e));
        }
        return result;
    }

    public static List<Apple> maps(List<Integer> list,
                                   BiFunction<String, Integer, Apple> f){
        List<Apple> result = new ArrayList<>();
        for(Integer e: list){
            result.add(f.apply("green",e));
        }
        return result;
    }

    public static Fruit giveMeFruit(String fruit, Integer weight){
        return map.get(fruit.toLowerCase())
                .apply(weight);
    }
}

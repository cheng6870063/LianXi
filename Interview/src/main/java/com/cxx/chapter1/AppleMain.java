package com.cxx.chapter1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AppleMain {
    public static void main(String ... args){
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        List<Apple> greenApples1 = filterColorApples(inventory);
        System.out.println(greenApples1.size());

        List<Apple> weightApples1 = filterWeightApples(inventory,150);
        System.out.println(weightApples1.size());

        //行为参数化：方法引用
        List<Apple> greenApples2 = filterApples(inventory,AppleMain::isGreenApple);
        System.out.println(greenApples2.size());

        //行为参数化：方法引用
        List<Apple> weightApples2 = filterApples(inventory,AppleMain::isweightApple);
        System.out.println(weightApples2.size());

        //行为参数化：Lambda 表达式
        List<Apple> greenApples3 = filterApples(inventory,(Apple a) -> a.getColor().equals("green"));
        System.out.println(greenApples3.size());

        //行为参数化：Lambda 表达式
        List<Apple> weightApples3 = filterApples(inventory,(Apple a) -> a.getWeight() > 150);
        System.out.println(weightApples3.size());
        //行为参数化：Lambda 表达式
        List<Apple> weirdApples = filterApples(inventory, (Apple a) -> a.getWeight() < 80 ||
                "brown".equals(a.getColor()));
        System.out.println(weirdApples);

        //顺序处理：
        List<Apple> heavyApples1 = inventory.stream().filter((Apple a) -> a.getWeight() > 150)
                                            .collect(Collectors.toList());

        //并行处理：
        List<Apple> heavyApples2 = inventory.parallelStream().filter((Apple a) -> a.getWeight() > 150)
                .collect(Collectors.toList());
    }

    public static List<Apple> filterColorApples(List<Apple> inventory){
        List<Apple> appleList = new ArrayList<>();
        for(Apple apple:inventory){
            if(apple.getColor().equals("green")){
                appleList.add(apple);
            }
        }
        return appleList;
    }

    public static List<Apple> filterWeightApples(List<Apple> inventory,int weight){
        List<Apple> appleList = new ArrayList<>();
        for(Apple apple:inventory){
            if(apple.getWeight() > weight){
                appleList.add(apple);
            }
        }
        return appleList;
    }

    public static Boolean isGreenApple(Apple apple){
        return "green".equals(apple.getColor());
    }
    public static Boolean isweightApple(Apple apple){
        return apple.getWeight() > 150;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p){
        List<Apple> result = new ArrayList<>();
        for(Apple apple:inventory){
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }
}

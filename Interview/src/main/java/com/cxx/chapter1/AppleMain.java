package com.cxx.chapter1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class AppleMain {
    public static void main(String ... args){
        List<Apple> inventory = Arrays.asList(new Apple(80,"green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        List<Apple> greenApples1 = filterColorApples(inventory);
        System.out.println(greenApples1.size());

        List<Apple> weightApples1 = filterWeightApples(inventory,150);
        System.out.println(weightApples1.size());

        List<Apple> greenApples2 = filterApples(inventory,AppleMain::isGreenApple);
        System.out.println(greenApples2.size());


        List<Apple> weightApples2 = filterApples(inventory,AppleMain::isweightApple);
        System.out.println(weightApples2.size());


        List<Apple> greenApples3 = filterApples(inventory,(Apple a) -> a.getColor().equals("green"));
        System.out.println(greenApples3.size());


        List<Apple> weightApples3 = filterApples(inventory,(Apple a) -> a.getWeight() > 150);
        System.out.println(weightApples3.size());

        List<Apple> weirdApples = filterApples(inventory, (Apple a) -> a.getWeight() < 80 ||
                "brown".equals(a.getColor()));
        System.out.println(weirdApples);

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

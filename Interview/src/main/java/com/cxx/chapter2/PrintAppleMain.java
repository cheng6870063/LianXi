package com.cxx.chapter2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PrintAppleMain {
    public static void main(String ... args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));
        //行为参数化：类
        prettyPrintApple(inventory,new AppleFancyFormatter());
        //行为参数化：匿名内部类
        prettyPrintApple(inventory,new AppleFormatter(){
            public String accept(Apple apple) {
                return "An apple is " + apple.getColor() ;
            }
        });
        System.out.println("-----------------------------");
        //行为参数化：lamda表达式(Lambda没有return语句,因为已经隐含了return)
        prettyPrintApple(inventory,(Apple a)->{return "An apple is " + a.getColor();});
        prettyPrintApple(inventory,(Apple a)->"An apple is " + a.getColor());

        //行为参数化：匿名内部类(排序）
        inventory.sort(new Comparator<Apple>() {
            public int compare(Apple a1, Apple a2){
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });

        //函数式接口：只定义了一个抽象方法的接口
        //Lambda表达式可以被赋给一个变量，或传递给一个接受函数式接口作为参数的方法
        //行为参数化：lamda(排序）
        inventory.sort(
                (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

        //inventory.sort(comparing(Apple::getWeight));


        List<String> str = Arrays.asList("a","b","A","B");
        //lamda(排序）
        str.sort((s1,s2) -> s1.compareToIgnoreCase(s2));
        //方法引用(排序）
        str.sort(String::compareToIgnoreCase);

    }

    public static  void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter){
        for(Apple apple: inventory){
            String output = formatter.accept(apple);
            System.out.println(output);
        }
    }
}

package com.cxx.chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Comparator.*;

public class SortApplemain {
    public static void main(String ... args){
        List<Apple> inventory = Arrays.asList(new Apple("green",110),
                new Apple( "green",155),
                new Apple( "red",120));
        //第一种排序 类
        inventory.sort(new AppleComparator());

        //第二种排序，匿名内部类
        inventory.sort(new Comparator<Apple>() {
            public int compare(Apple a1, Apple a2){
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });

        //第三种排序：lamda
        inventory.sort((Apple a1, Apple a2)
                -> a1.getWeight().compareTo(a2.getWeight())
        );
        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));

        //第四种排序：方法引用
        inventory.sort(comparing((a) -> a.getWeight()));
        inventory.sort(Comparator.comparing(Apple::getWeight));//重量递增
        //比较器复合
        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());//重量递减
        inventory.sort(comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor));  //重量一样的情况下再按颜色排序
        //谓词复合
        List<Apple> weirdApples = filterApples(inventory, (Apple a) -> a.getWeight() < 80 ||
                "brown".equals(a.getColor()));
        Predicate<Apple> p1 = (Apple a) -> a.getWeight() < 80;
        Predicate<Apple> p2 = (Apple a) ->"brown".equals(a.getColor());
        List<Apple> apples1 = filterApples(inventory, p1.negate());
        List<Apple> apples2 = filterApples(inventory, p1.and(p2));
        List<Apple> apples3 = filterApples(inventory, p1.or(p2));


        Function<Integer, Integer> f1 = x -> x + 1;
        Function<Integer, Integer> g1 = x -> x * 2;
        Function<Integer, Integer> h1 = f1.andThen(g1);
        int result1 = h1.apply(1);

        Function<Integer, Integer> f2 = x -> x + 1;
        Function<Integer, Integer> g2 = x -> x * 2;
        Function<Integer, Integer> h2 = f2.compose(g2);
        int result2 = h2.apply(1);

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

class AppleComparator implements Comparator<Apple> {
    public int compare(Apple a1, Apple a2){
        return a1.getWeight().compareTo(a2.getWeight());
    }
}
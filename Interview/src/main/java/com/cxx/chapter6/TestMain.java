package com.cxx.chapter6;

import com.cxx.chapter5.Dish;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestMain {
    public static void main(String ... args){
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );
        List<String> means = menu.stream().map(Dish::getName).collect(Collectors.toList());
        String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining());
        System.out.println(shortMenu);

        String shortMenu1 = menu.stream().map(Dish::getName).collect(Collectors.joining(", "));


        //计算总热量
        int totalCalories1 = menu.stream().map(Dish::getCalories).
                             reduce(0,(a,b)-> a + b);
        int totalCalories2 = menu.stream().collect(Collectors.summingInt(Dish::getCalories));

        int totalCalories3 =  menu.stream().collect(Collectors.reducing(
                              0, Dish::getCalories, (i, j) -> i + j));
    }

}

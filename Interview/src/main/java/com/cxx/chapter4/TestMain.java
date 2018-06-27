package com.cxx.chapter4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.*;

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
        List<String> dishname = menu.stream().filter((Dish d) -> d.getCalories()>300)
                                .sorted(comparing(Dish::getCalories)).map(Dish::getName).limit(3).collect(Collectors.toList());

        //集合：用for-each循环外部迭代
        List<String> names = new ArrayList<>();
        for(Dish d: menu){
            names.add(d.getName());
        }
        //集合：用背后的迭代器做外部迭代
        List<String> name = new ArrayList<>();
        Iterator<Dish> iterator = menu.iterator();
        while(iterator.hasNext()) {
            Dish d = iterator.next();
            name.add(d.getName());
        }

        // 流：内部迭代
        List<String> name1 = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());


        List<String> namess =
                menu.stream()
                        .filter(d -> {
                            System.out.println("filtering:" + d.getName());
                            return d.getCalories() > 300;
                        })
                        .map(d -> {
                            System.out.println("mapping:" + d.getName());
                            return d.getName();
                        })
                        .limit(3)
                        .collect(Collectors.toList());
        System.out.println(namess);

        menu.stream().forEach(System.out::println);

        long count = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .distinct()
                .limit(3)
                .count();

        List<String> title = Arrays.asList("Java8", "In", "Action");
        Stream<String> s = title.stream();
        s.forEach(System.out::println);
        //流只能消费一次
        //s.forEach(System.out::println);//java.lang.IllegalStateException:流已被操作或关闭

    }
}

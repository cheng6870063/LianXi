package com.cxx.chapter6;

import com.cxx.chapter5.Dish;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class TestMain {
    private enum CaloricLevel { DIET, NORMAL, FAT }
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
        int totalCalories0 = menu.stream().map(Dish::getCalories).
                            reduce(Integer::sum).get();

        int totalCalories1 = menu.stream().map(Dish::getCalories).
                             reduce(0,(a,b)-> a + b);

        int totalCalories2 = menu.stream().collect(Collectors.summingInt(Dish::getCalories));

        int totalCalories3 = menu.stream().collect(Collectors.reducing(
                              0, Dish::getCalories, Integer::sum));

        int totalCalories4 =  menu.stream().collect(Collectors.reducing(
                              0, Dish::getCalories, (i, j) -> i + j));

        int totalCalories5 = menu.stream().mapToInt(Dish::getCalories).sum();

        //找到热量最高的菜
        Optional<Dish> mostCaloriesDish =
                menu.stream().collect(Collectors.reducing(
                (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));

        //分组
        Map<Dish.Type, List<Dish>> dishesByType =
                menu.stream().collect(groupingBy(Dish::getType));

        System.out.println(dishesByType.size());
        //根据热量分组
        Map<Integer, List<Dish>> dishesByCalories =
                menu.stream().collect(groupingBy(Dish::getCalories));
        //根据热量分组1
        Map<Integer, List<Dish>> dishesByCalories1 =
                menu.stream().collect(groupingBy(dish ->{
                               if(dish.getCalories() < 400) return 1;
                               else if(dish.getCalories() <= 700) return 2;
                               else return 3;}
        ));

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(
                                groupingBy(dish ->{
                                    if(dish.getCalories() < 400) return CaloricLevel.DIET;
                                    else if(dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                    else return CaloricLevel.FAT;
                                }
        ));

        //多级分组
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel =
                menu.stream().collect(groupingBy(Dish::getType,
                        groupingBy(dish -> {
                            if(dish.getCalories() < 400) return CaloricLevel.DIET;
                            else if(dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        })));

        //根据类型分类，然后计算个数
        Map<Dish.Type,Long> typesCount = menu.stream().collect(
                groupingBy(Dish::getType,Collectors.counting()));

        //根据类型分类，然后取出热量最大的菜单
        Map<Dish.Type, Optional<Dish>> mostCaloricByType =
                menu.stream()
                        .collect(groupingBy(Dish::getType,
                                Collectors.maxBy(comparingInt(Dish::getCalories))));
        Map<Dish.Type, Dish> mostCaloricByType1 =
                menu.stream().collect(groupingBy(Dish::getType,
                        Collectors.collectingAndThen(Collectors.maxBy(comparingInt(Dish::getCalories)),
                                Optional::get)));
        //根据类型分类然后计算总热量
        Map<Dish.Type,Integer> totalCaloriesByType =
                menu.stream().collect(groupingBy(Dish::getType,
                        Collectors.summingInt(Dish::getCalories)));

        //过滤出类型为other且热量为350的以及其他类型的菜单
        List<Dish> dishList = menu.stream().filter(dish->{
            if(dish.getCalories() ==350 && dish.getType().equals(Dish.Type.OTHER)) return true;
            if(!dish.getType().equals(Dish.Type.OTHER)) return true;
            else return false;
        }).collect(Collectors.toList());

        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
                menu.stream().collect(
                        groupingBy(Dish::getType, mapping(
                                dish -> { if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                                else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                                else return CaloricLevel.FAT; },
                                toSet() )));
       System.out.println(caloricLevelsByType.size());


       //分区
        Map<Boolean, List<Dish>> partitionedMenu =
                menu.stream().collect(partitioningBy(Dish::isVegetarian));

        List<Dish> vegetarianDishes = partitionedMenu.get(true);

        List<Dish> vegetarianDishes1 =
                menu.stream().filter(Dish::isVegetarian).collect(toList());

        Map<Boolean, List<Dish>> partitionedMenu1 =
                menu.stream().collect(partitioningBy(dish ->{
                    if (dish.getCalories() <= 700) return true;
                    else return false;
                }));

        //将数字按质数和非质数分区
        Map<Boolean, List<Integer>> partitionPrimes =
                IntStream.rangeClosed(2, 100).boxed()
                        .collect(
                                partitioningBy(candidate -> isPrime(candidate)));
    }

    public static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

}

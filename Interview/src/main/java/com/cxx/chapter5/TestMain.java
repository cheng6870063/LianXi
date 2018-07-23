package com.cxx.chapter5;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

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
        //截短流
        List<Dish> vegetarianMenu = menu.stream()
                .filter(Dish::isVegetarian)
                .limit(3)
                .collect(Collectors.toList());
        //跳过删选出元素的前N个
        List<Dish> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());
        System.out.println(dishes);

        List<Integer> dishNameLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());



        //筛选各异的元素
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        List<Integer> number = numbers.stream().filter(d -> d % 2 == 0)
                .distinct().collect(Collectors.toList());
        System.out.println(numbers);
        System.out.println(number);



        List<String> words = Arrays.asList("hello", "world");
        List<String[]> word = words.stream()
                .map((String s) -> s.split(""))
                .distinct()
                .collect(Collectors.toList());
        List<Stream<String>> word1 = words.stream()
                .map((String s) -> s.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        //flatMap各个数组并不是分别映射成一个流，而是映射成流的内容
        List<String> word2 = words.stream()
                .map((String s) -> s.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs =
                numbers1.stream()
                        .flatMap(i -> numbers2.stream()
                                .map(j -> new int[]{i, j})
                        )
                        .collect(Collectors.toList());
        List<int[]> pairs1 =
                numbers1.stream()
                        .flatMap(i ->
                                numbers2.stream()
                                        .filter(j -> (i + j) % 3 == 0)
                                        .map(j -> new int[]{i, j})
                        )
                        .collect(Collectors.toList());

        //检查谓词是否至少匹配一个元素,anyMatch方法返回一个boolean，因此是一个终端操作
        if(menu.stream().anyMatch(Dish::isVegetarian)){
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }

        //检查谓词是否匹配所有元素
       boolean isHealthy = menu.stream()
                           .allMatch(d -> d.getCalories() < 1000);
        //以确保流中没有任何元素与给定的谓词匹配
        boolean isHealthy1 = menu.stream()
                .noneMatch(d -> d.getCalories() >= 1000);

        //从符合条件的列表中取出任意一个(findAny可能什么元素都没找到)
        //Optional<T>类（java.util.Optional）是一个容器类
        Optional<Dish> dish =
                menu.stream()
                        .filter(Dish::isVegetarian)
                        .findAny();

          menu.stream()
                  .filter(Dish::isVegetarian)
                  .findAny()
                  .ifPresent(d -> System.out.println(d.getName()));



          //查找第一个元素
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNumbers.stream()
                        .map(x -> x * x)
                        .filter(x -> x % 3 == 0)
                        .findFirst();



        int product = someNumbers.stream().reduce(1, (a, b) -> a * b);
        int sum = someNumbers.stream().reduce(0, (a, b) -> a + b);
        int sum1 = someNumbers.stream().reduce(0, Integer::sum);
        //reduce还有一个重载的变体，它不接受初始值，但是会返回一个Optional对象：
        //考虑流中没有任何元素的情况。reduce操作无法返回其和，因为它没有初始值。
        Optional<Integer> sum2 = someNumbers.stream().reduce((a, b) -> (a + b));
        //用reduce来计算流中的最大值
        Optional<Integer> max = someNumbers.stream().reduce(Integer::max);
        Optional<Integer> min = numbers.stream().reduce(Integer::min);

        //用map和reduce方法数一数流中有多少个菜呢
        int count = menu.stream()
                .map(d -> 1)
                .reduce(0, (a, b) -> a + b);
        long counts = menu.stream().count();


        int calories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        int calories1 = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, (a, b) -> a + b);

        int totalCalories = menu.stream().collect(Collectors.summingInt(Dish::getCalories));

        double avgCalories = menu.stream().collect(Collectors.averagingInt(Dish::getCalories));

        IntSummaryStatistics menuStatistics =
                menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));

        menuStatistics.getCount();
        menuStatistics.getMax();
        menuStatistics.getMin();
        menuStatistics.getSum();
        menuStatistics.getAverage();
        menuStatistics.toString();
        System.out.println(menuStatistics);
    }

}

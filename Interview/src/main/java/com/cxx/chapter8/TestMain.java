package com.cxx.chapter8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class TestMain {
    public static void main(String[] args) {
        Runnable r1 = new Runnable(){
            public void run(){
                System.out.println("Hello1");
            }
        };
        Runnable r2 = () -> System.out.println("Hello2");
        r1.run();
        r2.run();

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


        int totalCalories1 = menu.stream().map(Dish::getCalories).reduce(0,
                (a,b) -> a + b);

        int totalCalories2 = menu.stream().collect(Collectors.summingInt(Dish::getCalories));

        System.out.println(totalCalories1 == totalCalories2);



        Validator numericValidator = new Validator(new IsNumeric());
        boolean b1 = numericValidator.validate("aaaa");
        Validator lowerCaseValidator = new Validator(new IsAllLowerCase ());
        boolean b2 = lowerCaseValidator.validate("bbbb");
    }



}

package com.cxx.Chapter10;

import java.util.Optional;


/*
人可能有车，也可能没
有车，因此将这个字段
声明为Optional
 */
public class Person {
    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = Optional.of(car);
    }
}

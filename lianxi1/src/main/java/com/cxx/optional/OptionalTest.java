package com.cxx.optional;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class OptionalTest {
    @Test
    public void emptyCarTest(){
        Optional<Car> car = Optional.empty();
        assertFalse(car.isPresent());
    }

    @Test
    public void nonNullCarTest(){
        Optional<Car> car = Optional.of(new Car());
        assertTrue("非空车辆：",car.isPresent());
    }

    @Test
    public void nullCarTest(){
        Optional<Car> car = Optional.ofNullable(null);
        assertFalse(car.isPresent());
    }

    @Test
    public void mapConversionValue(){
        Optional<Insurance> optInsurance = Optional.empty();
        Optional<String> nameOpt = optInsurance.map(Insurance::getName);
        /*Insurance Insurance = null;
        String name = Insurance.getName();*/
        assertFalse(nameOpt.isPresent());
    }

    @Test
    public void flatMapConversionValue(){
        Optional<Person> person = Optional.empty();
        Optional<String> nameOptional = person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName);
        assertFalse(nameOptional.isPresent());
    }

    @Test
    public void ifPresentInsurance(){
        Insurance insurance = new Insurance();
        //insurance.setName("zhangsan");
        Optional<String> name = Optional.ofNullable(insurance).map(Insurance::getName);
        //Optional<Insurance> insuranceopt = Optional.of(insurance);
        Optional<Insurance> insuranceopt = Optional.empty();
        insuranceopt.ifPresent(in -> System.out.println(in.getName()));

    }

    @Test
    public void ifPresentCar(){
        Person person = new Person();
        //Optional<Car> cars = person.getCar();
        Optional<Car> car = Optional.ofNullable(person).map(Person::getCar).orElse(null);
        //Optional<Car> car1 = Optional.ofNullable(person).flatMap(Person::getCar);
        Optional<Optional<Car>> car2 = Optional.ofNullable(person).map(Person::getCar);


        Insurance insurance = new Insurance();
        //insurance.setName("zhangsan");
        //String names = insurance.getName().toString();//报空指针
        String name = Optional.ofNullable(insurance).map(Insurance::getName).orElse("");
    }

    @Test
    public void ifPresentCar1(){
        Person1 person = new Person1();

        Car car = Optional.ofNullable(person).map(Person1::getCar).orElse(null);
        Optional<Car> cars = Optional.ofNullable(person).map(Person1::getCar);

        //car.setInsurance();

        Optional<Insurance> insurance = Optional.ofNullable(car).map(Car::getInsurance).orElse(null);

       }
}
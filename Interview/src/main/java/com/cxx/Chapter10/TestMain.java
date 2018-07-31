package com.cxx.Chapter10;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestMain {
    public static void main(String[] args) {
       /*
       //空指针
       Insurance insurance = null;
        String name = insurance.getName();*/
       /*
       //name值为空
       Insurance insurance = new Insurance();
        String name = insurance.getName();*/

     /*   Insurance insurance1 = new Insurance();
        insurance1.setName("cxx");

        Optional<Insurance> insurance = Optional.ofNullable(null);
        Optional<String> name = insurance.map(Insurance::getName);
        System.out.println(name);*/

        Person person2 = new Person();
        List<Person> personList = new ArrayList<>();
        personList.add(null);
        //personList.get(0).getCar();  报空指针
        Optional<Person> person = Optional.ofNullable(personList.get(0));
        Person person1 = person.orElse(person2);
        Optional<Car> car= person.flatMap(Person::getCar);
    }
}

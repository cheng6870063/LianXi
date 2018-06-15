package com.bean;

import java.util.concurrent.atomic.AtomicLong;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class Person {

    private static AtomicLong COUNTER = new AtomicLong(0L);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;

    public Person() {
        this.id = COUNTER.incrementAndGet();
    }
}
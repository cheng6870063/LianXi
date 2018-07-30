package com.cxx.Chapter8Strategy;

import com.cxx.chapter8.ValidationStrategy;

public class Validator {

    private final ValidationStrategy strategy;

    public Validator(ValidationStrategy v) {
        this.strategy = v;
    }
    public boolean validate(String s){
        return strategy.execute(s);
    }
}

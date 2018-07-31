package com.cxx.Chapter10;

import java.util.Optional;


/*
车可能进行了保险，也可
能没有保险，所以将这个
字段声明为Optional
 */

public class Car {
    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = Optional.of(insurance);
    }
}

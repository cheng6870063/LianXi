package com.cxx.chapter3;

public class Orange extends Fruit{
    private int weight = 0;

    public Orange(int weight){
        this.weight = weight;
    }


    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String toString() {
        return "Orange{" +
                "weight=" + weight +
                '}';
    }
}

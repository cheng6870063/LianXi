package com.cxx.chapter3;

public class Apple extends Fruit {
    private int weight = 0;
    private String color = "";

    public Apple(String color,int weight){
        this.weight = weight;
        this.color = color;
    }
    public Apple(int weight){
        this.weight = weight;
    }


    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                '}';
    }
}

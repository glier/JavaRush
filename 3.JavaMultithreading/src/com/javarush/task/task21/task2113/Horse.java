package com.javarush.task.task21.task2113;

/**
 * Created by Борозденец on 23.06.2017.
 */
public class Horse {

    private String name;
    private double speed;
    private double distance;

    public Horse(String name, double speed, double distance) {
        setName(name);
        setSpeed(speed);
        setDistance(distance);
    }

    public void move() {
        distance += speed * Math.random();
    }

    public void print() {
        int pointCount = (int) distance;
        for (int i = 0; i < pointCount; i++) System.out.print(".");
        System.out.println(name);
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDistance() {
        return distance;
    }
}

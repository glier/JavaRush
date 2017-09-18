package com.javarush.task.task27.task2712.kitchen;

public enum  Dish {
    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);

    private int duration;



    Dish(int i) {
        duration = i;
    }

    public int getDuration() {
        return duration;
    }

    public static String allDishesToString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < Dish.values().length; i++) {
            if (i < Dish.values().length - 1) {
                sb.append(Dish.values()[i]).append(", ");
            } else {
                sb.append(Dish.values()[i]);
            }
        }
        return sb.toString();
    }
}

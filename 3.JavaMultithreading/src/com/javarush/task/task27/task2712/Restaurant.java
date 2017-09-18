package com.javarush.task.task27.task2712;


import com.javarush.task.task27.task2712.kitchen.Cook;

import com.javarush.task.task27.task2712.kitchen.Waiter;


public class Restaurant {

    public static void main(String[] args) {

        Cook amigo = new Cook("Amigo");
        Waiter waiter = new Waiter();
        Tablet tablet = new Tablet(5);

        tablet.addObserver(amigo);
        amigo.addObserver(waiter);

        System.out.println(tablet.createOrder());





    }
}

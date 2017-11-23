package com.javarush.task.task27.task2712;


import com.javarush.task.task27.task2712.kitchen.Cook;

import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;


public class Restaurant {

    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {

        Cook amigo = new Cook("Amigo");
        amigo.setOrderQueue(orderQueue);
        Cook bander = new Cook("Bander");
        bander.setOrderQueue(orderQueue);


        Waiter waiter = new Waiter();
        amigo.addObserver(waiter);
        bander.addObserver(waiter);


        List<Tablet> tablets = new ArrayList<>(5);
        for (int i = 1; i <= 5; i++)
        {
            Tablet tablet = new Tablet(i);
            tablet.setOrderQueue(orderQueue);
            tablets.add(tablet);
        }

        RandomOrderGeneratorTask task = new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL);
        Thread orderThread = new Thread(task);
        orderThread.start();

        Thread amigoThread = new Thread(amigo);
        amigoThread.start();
        Thread banderThread = new Thread(bander);
        banderThread.start();

        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
        }

        orderThread.interrupt();

        boolean isNotDone = true;
        while (isNotDone)
        {
            if (orderQueue.isEmpty())
            {
                amigoThread.interrupt();
                banderThread.interrupt();
                isNotDone = false;
            }
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }


        DirectorTablet directorTablet = new DirectorTablet();

        directorTablet.printAdvertisementProfit();
        directorTablet.printCookWorkloading();
        directorTablet.printArchivedVideoSet();
        directorTablet.printActiveVideoSet();


    }
}

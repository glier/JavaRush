package com.javarush.task.task27.task2712;


import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;

import java.io.IOException;
import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet{

    final int number;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());
    private LinkedBlockingQueue<Order> queue;

    public Tablet(int number) {
        this.number = number;
    }

    public Order createOrder() {
        try {
            Order order = new Order(this);

            if (!order.isEmpty()) {
                printOrderAndShowAds(order);
                return order;
            }

        }
        catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
        return null;
    }

    private void printOrderAndShowAds(Order order) {
        try {
            new AdvertisementManager(order.getTotalCookingTime() * 60).processVideos();
        }
        catch (NoVideoAvailableException e) {
            logger.log(Level.INFO, "No video is available for the order " + order);
        }
        queue.add(order);
    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }

    public void createTestOrder() {
        try {
            TestOrder order = new TestOrder(this);

            if (!order.isEmpty()) {
                printOrderAndShowAds(order);
            }

        }
        catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }

    }

    public void setOrderQueue(LinkedBlockingQueue<Order> orderQueue) {
        this.queue = orderQueue;
    }
}

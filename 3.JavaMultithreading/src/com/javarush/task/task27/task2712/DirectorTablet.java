package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.Map;

public class DirectorTablet {

    public void printAdvertisementProfit() {

        Map<String, Double> map = StatisticManager.getInstance().getVideoStatistic();
        double totalAmount = 0;

        for (Map.Entry<String, Double> entry : map.entrySet())
        {
            totalAmount += entry.getValue();
            System.out.println(entry.getKey() + " - " + String.format("%.2f", entry.getValue()));
        }
        System.out.println(String.format("Total - %.2f", totalAmount));
    }

    public void printCookWorkloading() {}

    public void printActiveVideoSet() {}

    public void printArchivedVideoSet() {}

}

package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.Collections;
import java.util.List;
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

    public void printCookWorkloading() {
        Map<String, Map<String, Integer>> result = StatisticManager.getInstance().getCooksWorkStatistic();


        for (Map.Entry<String, Map<String, Integer>> entry1 : result.entrySet())
        {
            System.out.println(entry1.getKey());
            for (Map.Entry<String, Integer> entry2 : entry1.getValue().entrySet())
            {
                System.out.println(entry2.getKey() + " - " + entry2.getValue() + " min");
            }
        }

    }

    public void printActiveVideoSet() {
        StatisticAdvertisementManager statisticAdvertisementManager = StatisticAdvertisementManager.getInstance();
        List<Advertisement> listAd = statisticAdvertisementManager.getVideoSet(true);

        for (Advertisement ad: listAd) {
            ConsoleHelper.writeMessage(ad.getName() + " - " + ad.getHits());
        }
    }

    public void printArchivedVideoSet() {
        StatisticAdvertisementManager statisticAdvertisementManager = StatisticAdvertisementManager.getInstance();
        List<Advertisement> listAd = statisticAdvertisementManager.getVideoSet(false);

        for (Advertisement ad: listAd) {
            ConsoleHelper.writeMessage(ad.getName());
        }
    }

}

package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;

public class StatisticManager {
    private static StatisticManager instance = null;
    private StatisticStorage statisticStorage = new StatisticStorage();
    private Set<Cook> cooks = new HashSet<>();

    public static StatisticManager getInstance() {
        if (instance == null) {
            instance = new StatisticManager();
        }
        return instance;
    }

    private StatisticManager() {

    }

    private static class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public StatisticStorage() {
            for (EventType eventType : EventType.values())
            {
                storage.put(eventType, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data) {
            storage.get(data.getType()).add(data);
        }

        private Map<EventType, List<EventDataRow>> getStorage() {
            return storage;
        }
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    public void register(Cook cook) {
        cooks.add(cook);
    }

    public Map<String, Double> getVideoStatistic() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Map<EventType, List<EventDataRow>> storageMap = statisticStorage.getStorage();
        List<EventDataRow> list = storageMap.get(EventType.SELECTED_VIDEOS);

        Map<String, Double> map = new TreeMap<>(Collections.reverseOrder());

        for (EventDataRow event : list)
        {
            VideoSelectedEventDataRow videoSelectedEvent = (VideoSelectedEventDataRow) event;
            String date = dateFormat.format(videoSelectedEvent.getDate());
            double amount = (double) videoSelectedEvent.getAmount() / 100;

            if (map.containsKey(date))
            {
                map.put(date, map.get(date) + amount);
            } else
            {
                map.put(date, amount);
            }
        }
        return map;
    }

    public Map<String, Map<String, Integer>> getCooksWorkStatistic() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Map<EventType, List<EventDataRow>> storageMap = statisticStorage.getStorage();
        List<EventDataRow> list = storageMap.get(EventType.COOKED_ORDER);

        Map<String, Map<String, Integer>> result = new TreeMap<>(Collections.reverseOrder());
        Map<String, Integer> cooksTime = new TreeMap<>();

        for (EventDataRow event : list)
        {
            CookedOrderEventDataRow cookedOrderEvent = (CookedOrderEventDataRow) event;
            String date = dateFormat.format(cookedOrderEvent.getDate());
            String cookName = cookedOrderEvent.getCookName();
            int cookingTime = cookedOrderEvent.getTime();
            int cookingTimeMin = (cookingTime % 60 == 0) ? (cookingTime / 60) : (cookingTime / 60 + 1);

            if (result.containsKey(date))
            {
                Map<String, Integer> temp = result.get(date);
                if (temp.containsKey(cookName))
                {
                    temp.put(cookName, temp.get(cookName) + cookingTimeMin);
                } else
                {
                    temp.put(cookName, cookingTimeMin);
                }
                result.put(date, temp);
            } else
            {
                Map<String, Integer> temp = new TreeMap<>();
                temp.put(cookName, cookingTimeMin);
                result.put(date, temp);
            }
        }

        return result;
    }

}

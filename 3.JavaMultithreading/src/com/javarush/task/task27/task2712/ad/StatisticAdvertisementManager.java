package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StatisticAdvertisementManager {

    private static StatisticAdvertisementManager instance = null;

    private final AdvertisementStorage adStorege = AdvertisementStorage.getInstance();

    private StatisticAdvertisementManager() {}

    public static StatisticAdvertisementManager getInstance() {
        if (instance == null) {
            instance = new StatisticAdvertisementManager();
        }
        return instance;
    }

    public List<Advertisement> getVideoSet(boolean isActive)
    {
        List<Advertisement> videoSet = new ArrayList<>();
        for (Advertisement ad : adStorege.list())
        {
            if (!isActive && ad.getHits() == 0)
            {
                videoSet.add(ad);
            }
            if (isActive && ad.getHits() != 0)
            {
                videoSet.add(ad);
            }
        }
        Collections.sort(videoSet, (o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        return videoSet;
    }
}

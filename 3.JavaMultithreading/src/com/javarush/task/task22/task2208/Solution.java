package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
        Map map = new HashMap<>();
        map.put("name", "Ivanov");
        map.put("country", "Ukraine");
        map.put("city", "Kiev");
        map.put("age", null);
        System.out.println("map " + getQuery(map));

        Map map1 = new HashMap<>();
        System.out.println("map1 " + getQuery(map1));

        Map map2 = new HashMap<>();
        map2.put("age", null);
        System.out.println("map2 " + getQuery(map2));

        Map map3 = new HashMap<>();
        map3.put(null, null);
        map3.put("name", "Ivanov");
        System.out.println("map3 " + getQuery(map3));

        Map map4 = new HashMap<>();
        map4.put(null, null);
        map4.put("", "");
        System.out.println("map4 " + getQuery(map4));

    }
    public static String getQuery(Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry entry : params.entrySet()) {
            if (entry.getValue() != null) {
                if (stringBuilder.toString().equals(""))
                    stringBuilder.append(entry.getKey()).append(" = '").append(entry.getValue()).append("'");
                else
                    stringBuilder.append(" and ").append(entry.getKey()).append(" = '").append(entry.getValue()).append("'");
            }

        }
        return stringBuilder.toString();
    }
}

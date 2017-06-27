package com.javarush.task.task22.task2210;

import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {
      //  System.out.println(getTokens("level22.lesson13.task01", ".")[3]);

    }
    public static String [] getTokens(String query, String delimiter) {
        StringTokenizer stringTokenizer = new StringTokenizer(query, delimiter);
        String[] out = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < out.length; i++) {
            out[i] = stringTokenizer.nextToken();
        }
        return out;
    }
}

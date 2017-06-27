package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) throws TooShortStringException {
            if (string == null || string.split(" ").length - 1 < 4 )
                throw new TooShortStringException();
            String[] s = string.split(" ");
            String substring = s[1] + " " + s[2] + " " + s[3] + " " + s[4];
            return substring;
    }

    public static class TooShortStringException extends RuntimeException {
    }
}

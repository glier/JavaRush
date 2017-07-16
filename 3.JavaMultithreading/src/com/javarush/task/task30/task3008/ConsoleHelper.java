package com.javarush.task.task30.task3008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by barabashka on 13.07.17.
 */
public class ConsoleHelper {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        boolean repeater = true;
        while (repeater) {
            try {
                String line = bufferedReader.readLine();
                repeater = false;
                return line;
            } catch (IOException e) {
                System.out.println("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
            }
        }
        return null;
    }

    public static int readInt() {
        boolean repeater = true;
        while (repeater) {
            try {
                int number = Integer.parseInt(readString());
                repeater = false;
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            }
        }
        return 0;
    }


}

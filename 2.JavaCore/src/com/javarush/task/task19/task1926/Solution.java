package com.javarush.task.task19.task1926;

/* 
Перевертыши
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fr = new BufferedReader(new FileReader(reader.readLine()));
        reader.close();

        while(fr.ready()) {
            String str = fr.readLine();
            char[] ch = str.toCharArray();

            for (int i = ch.length - 1; i > -1; i--) {
                System.out.print(ch[i]);
            }

            System.out.println();
        }

        fr.close();
    }
}

package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fr = new BufferedReader(new FileReader(reader.readLine()));
        reader.close();
        while (fr.ready()) {
            String str = fr.readLine();
            String[] splitstr = str.split(" ");
            int count = 0;

            for (int i = 0; i < words.size(); i++) {
                for (int j = 0; j < splitstr.length; j++) {
                    if (words.get(i).equals(splitstr[j])) {
                        count++;
                    }
                }
            }

            if (count == 2) {
                System.out.println(str);
            }

            count = 0;
        }


        fr.close();
    }
}

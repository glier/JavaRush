package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader fr = new BufferedReader(new FileReader(args[0]));
        FileWriter fw = new FileWriter(args[1]);

        while (fr.ready()) {
            String str = fr.readLine();
            String[] splitstr = str.split(" ");

            for (int i = 0; i < splitstr.length; i++) {
                if (splitstr[i].matches("(.*[0-9]+.*)")) {
                    fw.write(splitstr[i] + " ");
                }
            }
        }

        fr.close();
        fw.close();
    }
}

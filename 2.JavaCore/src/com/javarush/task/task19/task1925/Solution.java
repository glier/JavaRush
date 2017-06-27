package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader fr = new BufferedReader(new FileReader(args[0]));
        FileWriter fw = new FileWriter(args[1]);
        ArrayList<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        while (fr.ready()) {
            String str = fr.readLine();
            String[] splitstr = str.split(" ");

            for (int i = 0; i < splitstr.length; i++) {
                if (splitstr[i].length() > 6) {
                    list.add(splitstr[i]);
                }
            }
        }

        for (int i = 0; i < list.size(); i++) {
            if (i < list.size() - 1) {
                sb.append(list.get(i));
                sb.append(",");
            } else {
                sb.append(list.get(i));
            }
        }

        fw.write(sb.toString());

        fr.close();
        fw.close();
    }
}

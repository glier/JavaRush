package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));

        while (reader.ready()) {
            StringBuilder name = new StringBuilder();
            StringBuilder stringDate = new StringBuilder();
            String[] splitLine = reader.readLine().split(" ");

            for (int i = 0; i < splitLine.length - 3; i++) {
                name.append(splitLine[i]);
                if (i < splitLine.length - 4 ) {
                    name.append(" ");
                }
            }

            int year = Integer.parseInt(splitLine[splitLine.length - 1]);
            int month = Integer.parseInt(splitLine[splitLine.length - 2]);
            int day = Integer.parseInt(splitLine[splitLine.length - 3]);

            PEOPLE.add(new Person(name.toString(), new Date(year-1900,month-1,day)));
        }

        reader.close();

        for (Person pr: PEOPLE) {
            System.out.println(pr.getName() + pr.getBirthday());
        }

    }
}

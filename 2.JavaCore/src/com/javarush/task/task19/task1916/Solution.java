package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        FileReader fr1 = new FileReader(reader.readLine());
//        FileReader fr2 = new FileReader(reader.readLine());
//
//        BufferedReader fileLineReader1 = new BufferedReader(fr1);
//        BufferedReader fileLineReader2 = new BufferedReader(fr2);
//        String fileLine1 = null;
//        String fileLine2 = null;
//        while(((fileLine1 = fileLineReader1.readLine()) != null) || (((fileLine2 = fileLineReader2.readLine()) != null))) {
//
//            if (fileLine1 != null && fileLine1.equals(fileLine2)) lines.add(new LineItem(Type.SAME, fileLine2));
//            else if (fileLine1 == null) lines.add(new LineItem(Type.ADDED, fileLine2));
//            else lines.add(new LineItem(Type.REMOVED, fileLine1));
//        }
//
//        for (LineItem li: lines) {
//            System.out.println(li.type.toString() + " " + li.line);
//        }
//
//        reader.close();
//        fileLineReader1.close();
//        fileLineReader2.close();
//        fr1.close();
//        fr2.close();


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1Name = reader.readLine();
        String file2Name = reader.readLine();
//        FileReader fileReader1 = new FileReader(file1Name);
//        FileReader fileReader2 = new FileReader(file2Name);
        ArrayList<String> line1 = new ArrayList<>();
        ArrayList<String> line2 = new ArrayList<>();
        BufferedReader fr1 = new BufferedReader(new FileReader(file1Name));
        BufferedReader fr2 = new BufferedReader(new FileReader(file2Name));
        while (fr1.ready())
        {
            line1.add(fr1.readLine());
        }
        while (fr2.ready())
        {
            line2.add(fr2.readLine());
        }
        for (int index = 0; index < line1.size(); index++)
        {
            if (line2.size() > 0 && line1.get(index).equals(line2.get(0)) )
            {
                LineItem item = new LineItem(Type.SAME, line1.get(index));
                lines.add(item);
                line2.remove(0);
            }else if (line2.size() > 0 && !(line1.get(index).equals(line2.get(0))))
            {
                if (line2.size() > 1 && line1.get(index).equals(line2.get(1)))
                {
                    LineItem item = new LineItem(Type.ADDED, line2.get(0));
                    lines.add(item);
                    line2.remove(0);
                    index--;
                }else
                {
                    LineItem item = new LineItem(Type.REMOVED, line1.get(index));
                    lines.add(item);
                }
            }
        }
        if (line2.size() > 0)
        {
            for (String longerLines : line2)
            {
                lines.add(new LineItem(Type.ADDED, longerLines));
            }
        }

//        for (LineItem x : lines)
//        {
//            System.out.println(x.type + " " + x.line);
//        }
        reader.close();
        fr1.close();
        fr2.close();
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}

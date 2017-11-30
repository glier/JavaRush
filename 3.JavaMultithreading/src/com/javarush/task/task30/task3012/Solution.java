package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
    }

    public void createExpression(int number) {

        boolean cycle = true;
        ArrayList<Integer> list = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        builder.append(number).append(" =");


        while (cycle) {
            int ost = number % 3;
            number = number / 3;

            if (ost == 2) {
                list.add(-1);
                number++;
            }



            if (number < 3) {
                cycle = false;
                list.add(number);
                break;
            }
        }

        System.out.println(list);

        for (int i = 0; i < list.size() ; i++) {


            if (list.get(i) == 1) builder.append(" + ").append(list.get(i)* (int)Math.pow(3,i));

            if (list.get(i) == 2) builder.append(" - ").append((list.get(i)-3) * -(int)Math.pow(3,i));

        }

        System.out.println(builder.toString());



    }
}
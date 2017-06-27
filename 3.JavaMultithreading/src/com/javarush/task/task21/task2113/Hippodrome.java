package com.javarush.task.task21.task2113;


import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Борозденец on 23.06.2017.
 */
public class Hippodrome {
    private List<Horse> horses;
    public static Hippodrome game;

    public static void main(String[] args) {
        game = new Hippodrome(new ArrayList<>());
        game.getHorses().add(new Horse("Horse1", 3, 0));
        game.getHorses().add(new Horse("Horse2", 3, 0));
        game.getHorses().add(new Horse("Horse3", 3, 0));
        game.getHorses().add(new Horse("Horse4", 3, 0));
        game.getHorses().add(new Horse("Horse5", 3, 0));
        game.getHorses().add(new Horse("Horse6", 3, 0));
        game.getHorses().add(new Horse("Horse7", 3, 0));
        game.getHorses().add(new Horse("Horse8", 3, 0));
        game.getHorses().add(new Horse("Horse9", 3, 0));
        game.getHorses().add(new Horse("Horse10", 3, 0));
        game.getHorses().add(new Horse("Horse11", 3, 0));
        game.getHorses().add(new Horse("Horse12", 3, 0));
        game.getHorses().add(new Horse("Horse13", 3, 0));
        game.run();
        game.printWinner();
    }
    
    public Hippodrome (List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void move() {
        for (int i = 0; i < getHorses().size(); i++) {
            getHorses().get(i).move();
        }
    }

    public void print() {
        for (int i = 0; i <getHorses().size(); i++) {
            getHorses().get(i).print();
        }

        for (int i = 0; i < 10; i++) System.out.println();
    }

    public void run() {
        for (int i = 0; i < 95; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i < 94) clearScreen();
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public Horse getWinner() {
        Horse winner = null;
        double maxDistance = 0;

        for (int i = 0; i < getHorses().size(); i++) {
            if (getHorses().get(i).getDistance() > maxDistance) {
                maxDistance = getHorses().get(i).getDistance();
                winner = getHorses().get(i);
            }
        }

        return winner;
    }

    public void printWinner() {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }

}

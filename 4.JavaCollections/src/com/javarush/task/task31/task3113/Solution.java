package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что внутри папки?
*/
public class Solution {
    static long dirCount = 0;
    static long fileCount = 0;
    static long size = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Path path = Paths.get(reader.readLine());

        if (Files.isDirectory(path)) {

            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    size += attrs.size();
                    fileCount += 1;
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    dirCount += 1;
                    size += attrs.size();
                    return FileVisitResult.CONTINUE;
                }
            });

            System.out.println("Всего папок - " + (dirCount-1));
            System.out.println("Всего файлов - " + fileCount);
            System.out.println("Общий размер - " + size);
        }
        else {
            System.out.println(path.toString() + " - не папка");
        }


    }
}

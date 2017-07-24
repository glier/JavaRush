package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.ZipFileManager;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Борозденец on 21.07.2017.
 */
public abstract class ZipCommand implements Command {

    public ZipFileManager getZipFileManager() throws Exception {

        ConsoleHelper.writeMessage("Полный путь расположения архива:");
        String zipFile = ConsoleHelper.readString();
        Path zipPath = Paths.get(zipFile);

        ZipFileManager zipFileManager = new ZipFileManager(zipPath);



        return zipFileManager;
    }
}

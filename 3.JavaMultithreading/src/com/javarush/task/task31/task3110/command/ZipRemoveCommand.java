package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.ZipFileManager;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Борозденец on 21.07.2017.
 * Команда удаления файла из архива 
 */
public class ZipRemoveCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("Файл архива:");

        ZipFileManager zipFileManager = getZipFileManager();

        ConsoleHelper.writeMessage("Файл, который удалить из архива:");
        Path deleteFilePath = Paths.get(ConsoleHelper.readString());
        zipFileManager.removeFile(deleteFilePath);

        ConsoleHelper.writeMessage("Операция завершена.");
    }
}

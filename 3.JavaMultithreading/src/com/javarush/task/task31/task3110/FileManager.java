package com.javarush.task.task31.task3110;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Борозденец on 21.07.2017.
 */
public class FileManager {

    private Path rootPath;
    private List<Path> fileList;

    public FileManager(Path rootPath) throws IOException {
        this.rootPath = rootPath;
        this.fileList = new LinkedList<>();
    }

    public List<Path> getFileList() {
        return fileList;
    }

    public void collectFileList(Path path) throws IOException {
        if (Files.isRegularFile(path)) {
            fileList.add(path.toAbsolutePath());
        } else if (Files.isDirectory(path)) {
            DirectoryStream directoryStream = Files.newDirectoryStream(path);

            //for (Path path1 : directoryStream)
        }
    }
}

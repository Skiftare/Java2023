package edu.hw9.task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

public class FileSearchTask extends RecursiveTask<List<File>> {
    private final File directory;
    private final Predicate<File> predicate;
    private final boolean searchDirectories;

    public FileSearchTask(File directory, Predicate<File> predicate, boolean searchDirectories) {
        this.directory = directory;
        this.predicate = predicate;
        this.searchDirectories = searchDirectories;
    }

    @Override
    protected List<File> compute() {
        List<File> foundFiles = new ArrayList<>();

        if (directory.isDirectory()) {
            List<FileSearchTask> subTasks = new ArrayList<>();
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        handleDirectoryFile(file, subTasks);
                    }
                    handleNonDirectoryFile(file, foundFiles);

                }
            }

            joinSubTasks(subTasks, foundFiles);
        }

        return foundFiles;
    }

    private void handleDirectoryFile(File file, List<FileSearchTask> subTasks) {
        FileSearchTask subTask = new FileSearchTask(file, predicate, searchDirectories);
        subTasks.add(subTask);
        subTask.fork();
    }

    private void handleNonDirectoryFile(File file, List<File> foundFiles) {
        if (predicate.test(file)) {
            foundFiles.add(file);
        }
    }

    private void joinSubTasks(List<FileSearchTask> subTasks, List<File> foundFiles) {
        for (FileSearchTask subTask : subTasks) {
            foundFiles.addAll(subTask.join());
        }
    }
}

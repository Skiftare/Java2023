package edu.hw9;

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
                    if (file.isDirectory() && searchDirectories) {
                        FileSearchTask subTask = new FileSearchTask(file, predicate, searchDirectories);
                        subTasks.add(subTask);
                        subTask.fork();

                    } else if (file.isDirectory()) {

                        FileSearchTask subTask =
                            new FileSearchTask(file, predicate, searchDirectories);
                        subTasks.add(subTask);
                        subTask.fork();

                    }


                    if (predicate.test(file)) {
                        foundFiles.add(file);
                    }
                }
            }

            for (FileSearchTask subTask : subTasks) {
                foundFiles.addAll(subTask.join());
            }
        }

        return foundFiles;
    }
}

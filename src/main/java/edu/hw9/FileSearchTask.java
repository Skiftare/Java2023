package edu.hw9;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

public class FileSearchTask extends RecursiveTask<List<File>> {
    private final File directory;
    private final Predicate<File> predicate;

    public FileSearchTask(File directory, Predicate<File> predicate) {
        this.directory = directory;
        this.predicate = predicate;
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
                        FileSearchTask subTask = new FileSearchTask(file, predicate);
                        subTasks.add(subTask);
                        subTask.fork();
                    } else {
                        if (predicate.test(file)) {
                            foundFiles.add(file);
                        }
                    }
                }
            }

            for (FileSearchTask subTask : subTasks) {
                foundFiles.addAll(subTask.join());
            }
        }

        return foundFiles;
    }

    public static void main(String[] args) {
        File directory = new File("/путь/к/файловой/системе");

        // Поиск директорий, содержащих более 1000 файлов
        FileSearchTask countFilesTask = new FileSearchTask(directory, file -> file.isDirectory() && file.listFiles().length > 1000);
        ForkJoinPool.commonPool().invoke(countFilesTask);
        List<File> directoriesWithMoreThan1000Files = countFilesTask.join();
        System.out.println("Директории, содержащие более 1000 файлов:");
        for (File dir : directoriesWithMoreThan1000Files) {
            System.out.println(dir.getAbsolutePath());
        }

        // Поиск файлов по предикату (размер, расширение)
        FileSearchTask findFilesTask = new FileSearchTask(directory, file -> file.isFile() && file.length() > 1000000 && file.getName().endsWith(".txt"));
        ForkJoinPool.commonPool().invoke(findFilesTask);
        List<File> filesWithPredicate = findFilesTask.join();
        System.out.println("Файлы, удовлетворяющие предикату:");
        for (File file : filesWithPredicate) {
            System.out.println(file.getAbsolutePath());
        }
    }
}

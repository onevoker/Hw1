package edu.hw9.Task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

public class FileSearcher extends RecursiveTask<List<File>> {
    private final File directory;
    private final Predicate<File> predicate;

    public FileSearcher(File directory, Predicate<File> predicate) {
        this.directory = directory;
        this.predicate = predicate;
    }

    @Override
    protected List<File> compute() {
        List<File> result = new ArrayList<>();
        File[] files = directory.listFiles();

        if (files != null) {
            List<FileSearcher> subTasks = new ArrayList<>();

            for (var file : files) {
                if (file.isDirectory()) {
                    FileSearcher subTask = new FileSearcher(file, predicate);
                    subTask.fork();
                    subTasks.add(subTask);
                } else {
                    if (predicate.test(file)) {
                        result.add(file);
                    }
                }
            }

            for (var subTask : subTasks) {
                var filesOFSubTask = subTask.join();
                result.addAll(filesOFSubTask);
            }
        }

        return result;
    }
}

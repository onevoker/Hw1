package edu.hw9.Task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DirectorySearcher extends RecursiveTask<List<File>> {
    private final File directory;
    private static final int NEEDED_FILE_SIZE = 1000;

    public DirectorySearcher(File directory) {
        this.directory = directory;
    }

    @Override
    protected List<File> compute() {
        List<File> result = new ArrayList<>();
        File[] files = directory.listFiles();

        if (files != null) {
            List<DirectorySearcher> subTasks = new ArrayList<>();

            for (var file : files) {
                if (file.isDirectory()) {
                    DirectorySearcher subTask = new DirectorySearcher(file);
                    subTask.fork();
                    subTasks.add(subTask);
                }
            }

            if (files.length > NEEDED_FILE_SIZE) {
                result.add(directory);
            }

            for (var subTask : subTasks) {
                var filesOFSubTask = subTask.join();
                result.addAll(filesOFSubTask);
            }
        }

        return result;
    }
}

package edu.hw9.Task2;

import java.io.File;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Predicate;

public class Task2 {
    private Task2() {
    }

    public static List<File> getDirectoriesWithMoreThan1000Files(File directory) {
        DirectorySearcher searcher = new DirectorySearcher(directory);
        try (ForkJoinPool pool = new ForkJoinPool()) {
            return pool.invoke(searcher);
        }
    }

    public static List<File> getFilesByPredicate(File directory, Predicate<File> predicate) {
        FileSearcher searcher = new FileSearcher(directory, predicate);
        try (ForkJoinPool pool = new ForkJoinPool()) {
            return pool.invoke(searcher);
        }
    }
}

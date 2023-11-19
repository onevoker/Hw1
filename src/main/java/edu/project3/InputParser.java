package edu.project3;

import java.time.LocalDate;

public class InputParser {
    private InputParser() {
    }

    public static LocalDate getDateFrom(String date) {
        if (date.isEmpty()) {
            return LocalDate.EPOCH;
        }

        return LocalDate.parse(date);
    }

    public static LocalDate getDateTo(String date) {
        if (date.isEmpty()) {
            return LocalDate.now();
        }

        return LocalDate.parse(date);
    }

    public static String getPath(String path) {
        String directory = path;

        if (path.endsWith("*")) {
            directory = path.substring(0, path.length() - 1);
        }

        return directory;
    }
}

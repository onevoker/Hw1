package edu.hw5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Task3 {
    private static final LocalDate CURRENT_DATE = LocalDate.now();

    public Optional<LocalDate> parseDate(String string) {
        DateTimeFormatter[] formatters = {
            DateTimeFormatter.ofPattern("yyyy-MM-d"),
            DateTimeFormatter.ofPattern("yyyy-M-d"),
            DateTimeFormatter.ofPattern("d/M/yyyy"),
            DateTimeFormatter.ofPattern("d/M/yy")
        };

        for (DateTimeFormatter formatter : formatters) {
            try {
                LocalDate localDate = LocalDate.parse(string, formatter);
                return Optional.of(localDate);
            } catch (Exception ignored) {
            }
        }

        return parseWordDate(string);
    }

    @SuppressWarnings({"ReturnCount"})
    private Optional<LocalDate> parseWordDate(String string) {
        switch (string.toLowerCase()) {
            case "tomorrow" -> {
                return Optional.of(CURRENT_DATE.plusDays(1));
            }
            case "today" -> {
                return Optional.of(CURRENT_DATE);
            }
            case "yesterday" -> {
                return Optional.of(CURRENT_DATE.minusDays(1));
            }
            case "next week" -> {
                return Optional.of(CURRENT_DATE.plusWeeks(1));
            }
            case "next month" -> {
                return Optional.of(CURRENT_DATE.plusMonths(1));
            }
            case "next year" -> {
                return Optional.of(CURRENT_DATE.plusYears(1));
            }
            default -> {
            }
        }

        return parseSomeDaysAgoOrLater(string);
    }

    private Optional<LocalDate> parseSomeDaysAgoOrLater(String string) {
        if (string.toLowerCase().matches("\\d+ days? ago")) {
            long days = Integer.parseInt(string.split(" ")[0]);
            return Optional.of(CURRENT_DATE.minusDays(days));

        } else if (string.toLowerCase().matches("\\d+ days? later")) {
            long days = Integer.parseInt(string.split(" ")[0]);
            return Optional.of(CURRENT_DATE.plusDays(days));
        }

        return Optional.empty();
    }
}

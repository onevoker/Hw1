package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Task1 {
    private static final int SIXTY = 60;

    private long getDurationOfSession(String session) {
        String regex = "\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2} - \\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}";

        if (!session.matches(regex)) {
            throw new IllegalArgumentException("Invalid sessions");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
        String[] splitTimes = session.split(" - ");

        LocalDateTime start = LocalDateTime.parse(splitTimes[0], formatter);
        LocalDateTime end = LocalDateTime.parse(splitTimes[1], formatter);
        Duration time = Duration.between(start, end);

        return time.toSeconds();
    }

    public String getAverageDurationOfSessions(List<String> allSessions) {
        if (allSessions == null) {
            throw new NullPointerException("Null list of sessions");
        }
        if (allSessions.isEmpty()) {
            throw new IllegalArgumentException("Empty list of sessions");
        }

        long result = 0;

        for (String session : allSessions) {
            result += getDurationOfSession(session);
        }

        result /= allSessions.size();

        long minutes = (result / SIXTY) % SIXTY;
        long hours = result / (SIXTY * SIXTY);

        return "%sч %sм".formatted(hours, minutes);
    }
}

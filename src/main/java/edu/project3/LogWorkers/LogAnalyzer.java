package edu.project3.LogWorkers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LogAnalyzer {
    private static final int MAX_HOURS = 23;
    private static final int MAX_MINUTES = 59;
    private static final int MAX_SECONDS = 59;

    private LogAnalyzer() {
    }

    public static long countTotalRequests(List<LogRecord> logs, LocalDate dateFrom, LocalDate dateTo) {
        return logs.stream()
            .filter(logRecord ->
                logRecord.timeLocal().isAfter(LocalDateTime.of(dateFrom, LocalTime.of(0, 0, 0)))
                    && logRecord.timeLocal()
                    .isBefore(LocalDateTime.of(dateTo, LocalTime.of(MAX_HOURS, MAX_MINUTES, MAX_SECONDS))))
            .count();
    }

    public static Map<String, Integer> getTeMostFrequencyRequests(
        List<LogRecord> logs,
        LocalDate dateFrom,
        LocalDate dateTo
    ) {
        return logs.stream()
            .filter(logRecord ->
                logRecord.timeLocal().isAfter(LocalDateTime.of(dateFrom, LocalTime.of(0, 0, 0)))
                    && logRecord.timeLocal()
                    .isBefore(LocalDateTime.of(dateTo, LocalTime.of(MAX_HOURS, MAX_MINUTES, MAX_SECONDS))))
            .collect(
                Collectors.groupingBy(
                    log -> log.request().replaceAll("(HEAD |GET | HTTP/1.1)", ""),
                    Collectors.summingInt(count -> 1)
                ));
    }

    public static Map<Integer, Integer> getMostFrequencyResponseCodes(
        List<LogRecord> logs,
        LocalDate dateFrom,
        LocalDate dateTo
    ) {
        return logs.stream()
            .filter(logRecord ->
                logRecord.timeLocal().isAfter(LocalDateTime.of(dateFrom, LocalTime.of(0, 0, 0)))
                    && logRecord.timeLocal()
                    .isBefore(LocalDateTime.of(dateTo, LocalTime.of(MAX_HOURS, MAX_MINUTES, MAX_SECONDS))))
            .collect(
                Collectors.groupingBy(LogRecord::status, Collectors.summingInt(count -> 1)));
    }

    public static long getMediumSizeOfAnswer(List<LogRecord> logs, LocalDate dateFrom, LocalDate dateTo) {
        return logs.stream()
            .filter(logRecord ->
                logRecord.timeLocal().isAfter(LocalDateTime.of(dateFrom, LocalTime.of(0, 0, 0)))
                    && logRecord.timeLocal()
                    .isBefore(LocalDateTime.of(dateTo, LocalTime.of(MAX_HOURS, MAX_MINUTES, MAX_SECONDS))))
            .mapToLong(LogRecord::bodyBytesSend)
            .sum() / logs.size();
    }

    public static String getTheBestUser(List<LogRecord> logs, LocalDate dateFrom, LocalDate dateTo) {
        Map<String, Integer> mapOfUsers = logs.stream()
            .filter(logRecord ->
                logRecord.timeLocal().isAfter(LocalDateTime.of(dateFrom, LocalTime.of(0, 0, 0)))
                    && logRecord.timeLocal()
                    .isBefore(LocalDateTime.of(dateTo, LocalTime.of(MAX_HOURS, MAX_MINUTES, MAX_SECONDS))))
            .collect(
                Collectors.groupingBy(LogRecord::remoteAddr, Collectors.summingInt(count -> 1)));

        int activityOfBestUser = 0;

        for (var entry : mapOfUsers.entrySet()) {
            if (activityOfBestUser < entry.getValue()) {
                activityOfBestUser = entry.getValue();
            }
        }

        for (var entry : mapOfUsers.entrySet()) {
            if (entry.getValue() == activityOfBestUser) {
                return entry.getKey() + " " + activityOfBestUser;
            }
        }
        return "";
    }

    public static LocalDate getTheBusiestDay(List<LogRecord> logs, LocalDate dateFrom, LocalDate dateTo) {
        LocalDateTime result = logs.stream()
            .parallel()
            .filter(logRecord ->
                logRecord.timeLocal().isAfter(LocalDateTime.of(dateFrom, LocalTime.of(0, 0, 0)))
                    && logRecord.timeLocal()
                    .isBefore(LocalDateTime.of(dateTo, LocalTime.of(MAX_HOURS, MAX_MINUTES, MAX_SECONDS))))
            .map(LogRecord::timeLocal)
            .reduce((localDateTime, localDateTime2) ->
                countTotalRequests(logs, localDateTime.toLocalDate(), localDateTime.toLocalDate())
                    >= countTotalRequests(logs, localDateTime2.toLocalDate(), localDateTime2.toLocalDate())
                    ? localDateTime : localDateTime2)
            .orElse(LocalDateTime.of(dateFrom, LocalTime.of(0, 0, 0)));

        return result.toLocalDate();
    }

    public static Map<String, Integer> getAllHttpUserBrowsers(
        List<LogRecord> logs,
        LocalDate dateFrom,
        LocalDate dateTo
    ) {
        return logs.stream()
            .filter(logRecord ->
                logRecord.timeLocal().isAfter(LocalDateTime.of(dateFrom, LocalTime.of(0, 0, 0)))
                    && logRecord.timeLocal()
                    .isBefore(LocalDateTime.of(dateTo, LocalTime.of(MAX_HOURS, MAX_MINUTES, MAX_SECONDS))))
            .collect(
                Collectors.groupingBy(log -> {
                    int lastIndex = 0;
                    for (int i = 0; i < log.httpUserAgent().length(); ++i) {
                        if (log.httpUserAgent().charAt(i) == ' ') {
                            lastIndex = i;
                            break;
                        }
                    }
                    return log.httpUserAgent().substring(0, lastIndex);
                }, Collectors.summingInt(count -> 1)));
    }
}

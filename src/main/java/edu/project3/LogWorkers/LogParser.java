package edu.project3.LogWorkers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {
    private LogParser() {
    }

    private static final int AVAILABLE_RESPONSE_CODE = 200;
    private static final String TXT_FORMAT = ".txt";
    private static final DateTimeFormatter FORMATTER =
        DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
    private static final String LOG_PATTERN =
        "^(\\S+) - (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"([^\"]+)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"([^\"]+)\"$";

    @SuppressWarnings("MagicNumber")
    private static LogRecord parseLogLine(String logLine) {
        Pattern pattern = Pattern.compile(LOG_PATTERN);
        Matcher matcher = pattern.matcher(logLine);

        if (matcher.find()) {
            return new LogRecord(
                matcher.group(1),
                matcher.group(2),
                LocalDateTime.parse(matcher.group(3), FORMATTER),
                matcher.group(4),
                Integer.parseInt(matcher.group(5)),
                Long.parseLong(matcher.group(6)),
                matcher.group(7),
                matcher.group(8)
            );
        }

        return null;
    }

    public static List<LogRecord> parseAllLogLines(List<String> logLines) {
        List<LogRecord> logs = new ArrayList<>(List.of());

        for (String logLine : logLines) {
            logs.add(parseLogLine(logLine));
        }

        return logs;
    }

    public static boolean isURI(String path) throws URISyntaxException {

        URI uri = new URI(path);

        return (uri.getScheme() != null && uri.getHost() != null);
    }

    public static List<LogRecord> getLogs(String path) throws URISyntaxException {

        if (isURI(path)) {
            return getLogsFromURI(path);
        }

        List<LogRecord> list = new LinkedList<>();

        if (path.endsWith(TXT_FORMAT)) {
            return getLogsFromFile(path);
        } else {
            File directory = new File(path);
            File[] files = directory.listFiles();

            for (File file : files) {
                String name = file.getName();
                if (name.endsWith(TXT_FORMAT)) {
                    list.addAll(getLogsFromFile(file.getPath()));
                }
            }
        }

        return list;
    }

    private static List<LogRecord> getLogsFromURI(String path) throws URISyntaxException {
        List<LogRecord> logs = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(new URI(path)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == AVAILABLE_RESPONSE_CODE) {
                ByteArrayInputStream inputStream = new ByteArrayInputStream(response.body().getBytes());
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = reader.readLine()) != null) {
                    logs.add(parseLogLine(line));
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Can't read from this URI");
        }

        return logs;
    }

    private static List<LogRecord> getLogsFromFile(String path) {
        List<LogRecord> logs = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logs.add(parseLogLine(line));
            }

            return logs;
        } catch (IOException e) {
            throw new IllegalArgumentException("Incorrect path");
        }
    }
}

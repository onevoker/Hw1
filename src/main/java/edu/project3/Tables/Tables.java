package edu.project3.Tables;

import edu.project3.LogWorkers.LogAnalyzer;
import edu.project3.LogWorkers.LogParser;
import edu.project3.LogWorkers.LogRecord;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tables {
    private Tables() {
    }

    public static List<String> generalInformation(
        String path,
        List<LogRecord> logs,
        LocalDate dateFrom,
        LocalDate dateTo,
        String separator
    ) throws URISyntaxException {
        String typeOfPath;

        int index = path.lastIndexOf("/") + 1;
        String fileName = path.substring(index);

        if (LogParser.isURI(path)) {
            typeOfPath = "URI";
        } else {
            typeOfPath = "Файл(ы)";
        }

        List<String> table = new ArrayList<>();

        table.add("|  %-21s|  %-12s|".formatted("Метрика", "Значение"));
        if (!separator.isEmpty()) {
            table.add(separator);
        }
        table.add("|  %-21s| `%-10s` |".formatted(typeOfPath, fileName));
        table.add("|  Начальная дата       | %12s |".formatted(dateFrom));
        table.add("|  Конечная дата        | %12s |".formatted(dateTo));
        table.add("|  Количество запросов  | %12s |".formatted(LogAnalyzer.countTotalRequests(
            logs,
            dateFrom,
            dateTo
        )));
        table.add("|  Средний размер ответа|%13s |".formatted(LogAnalyzer.getMediumSizeOfAnswer(
            logs,
            dateFrom,
            dateTo
        )));

        return table;
    }

    public static List<String> requestedResources(
        List<LogRecord> logs,
        LocalDate dateFrom,
        LocalDate dateTo,
        String separator
    ) {
        Map<String, Integer> requests = LogAnalyzer.getTeMostFrequencyRequests(logs, dateFrom, dateTo);

        List<String> table = new ArrayList<>();

        table.add("|  %-24s| %-10s |".formatted("Ресурс", "Количество"));
        if (!separator.isEmpty()) {
            table.add(separator);
        }

        for (var entry : requests.entrySet()) {
            table.add("|  `%s`  | %10s |".formatted(entry.getKey(), entry.getValue()));
        }

        return table;
    }

    @SuppressWarnings({"MagicNumber", "InnerAssignment"})
    public static List<String> responseCodes(
        List<LogRecord> logs, LocalDate dateFrom, LocalDate dateTo, String separator
    ) {
        Map<Integer, Integer> codes = LogAnalyzer.getMostFrequencyResponseCodes(logs, dateFrom, dateTo);

        List<String> table = new ArrayList<>();

        table.add("| Код |          Имя          | Количество |");
        if (!separator.isEmpty()) {
            table.add(separator);
        }

        String statusCode = "";

        for (var entry : codes.entrySet()) {
            switch (entry.getKey()) {
                case 200 -> statusCode = "OK";
                case 206 -> statusCode = "Partial content";
                case 304 -> statusCode = "Not Modified";
                case 403 -> statusCode = "Forbidden";
                case 404 -> statusCode = "Not Found ";
                case 416 -> statusCode = "Range Not Satisfiable";
                case 500 -> statusCode = "Internal Server Error";
                default -> {
                }
            }
            table.add("| %s | %-21s |%11s |".formatted(
                entry.getKey(),
                statusCode,
                entry.getValue()
            ));
        }

        return table;
    }

    public static List<String> bestUser(
        List<LogRecord> logs, LocalDate dateFrom, LocalDate dateTo, String separator
    ) {
        String userInfo = LogAnalyzer.getTheBestUser(logs, dateFrom, dateTo);
        String userRemoteAddr = "";
        int userActivity = 0;
        if (!userInfo.isEmpty()) {
            var parts = userInfo.split(" ");
            userRemoteAddr = parts[0];
            userActivity = Integer.parseInt(parts[1]);
        }

        List<String> table = new ArrayList<>();

        table.add("|   Пользователь   | Активность |");
        if (!separator.isEmpty()) {
            table.add(separator);
        }
        table.add("|%17s |%11s |".formatted(userRemoteAddr, userActivity));

        return table;
    }

    public static List<String> busiestDay(
        List<LogRecord> logs, LocalDate dateFrom, LocalDate dateTo, String separator
    ) {
        LocalDate busiestDay = LogAnalyzer.getTheBusiestDay(logs, dateFrom, dateTo);

        List<String> table = new ArrayList<>();

        table.add("|    Дата     |  День недели  |");
        if (!separator.isEmpty()) {
            table.add(separator);
        }
        table.add("|  %s |    %-8s   |".formatted(busiestDay, busiestDay.getDayOfWeek()));

        return table;
    }

    public static List<String> httpUserAgents(
        List<LogRecord> logs,
        LocalDate dateFrom,
        LocalDate dateTo,
        String separator
    ) {
        Map<String, Integer> agents = LogAnalyzer.getAllHttpUserBrowsers(logs, dateFrom, dateTo);

        List<String> table = new ArrayList<>();

        table.add("|  %-24s| %-19s |".formatted("Агент", "Количество запросов"));
        if (!separator.isEmpty()) {
            table.add(separator);
        }

        for (var agent : agents.entrySet()) {
            String agentName = agent.getKey();
            if (agentName.isEmpty()) {
                agentName = "-";
            }
            table.add("| %-25s| %19s |".formatted(
                agentName,
                agent.getValue()
            ));
        }

        return table;
    }
}

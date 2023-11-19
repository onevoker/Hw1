package edu.project3.Tables;

import edu.project3.LogWorkers.LogRecord;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import static edu.project3.Tables.Tables.bestUser;
import static edu.project3.Tables.Tables.busiestDay;
import static edu.project3.Tables.Tables.generalInformation;
import static edu.project3.Tables.Tables.httpUserAgents;
import static edu.project3.Tables.Tables.requestedResources;
import static edu.project3.Tables.Tables.responseCodes;

public class AdocTable {
    private static final String OPTIONS_HEADER = "[options=\"header\"]\n";
    private static final String TABLE_BOUNDARIES = "|===";

    public void printReport(String path, List<LogRecord> logs, LocalDate dateFrom, LocalDate dateTo)
        throws URISyntaxException, IOException {
        List<List<String>> report = List.of(
            List.of(OPTIONS_HEADER + ".Общая информация"),
            List.of(TABLE_BOUNDARIES),
            generalInformation(path, logs, dateFrom, dateTo, ""),
            List.of(TABLE_BOUNDARIES),

            List.of(OPTIONS_HEADER + ".Запрашиваемые ресурсы"),
            List.of(TABLE_BOUNDARIES),
            requestedResources(logs, dateFrom, dateTo, ""),
            List.of(TABLE_BOUNDARIES),

            List.of(OPTIONS_HEADER + ".Коды ответа"),
            List.of(TABLE_BOUNDARIES),
            responseCodes(logs, dateFrom, dateTo, ""),
            List.of(TABLE_BOUNDARIES),

            List.of(OPTIONS_HEADER + ".Лучший пользователь"),
            List.of(TABLE_BOUNDARIES),
            bestUser(logs, dateFrom, dateTo, ""),
            List.of(TABLE_BOUNDARIES),

            List.of(OPTIONS_HEADER + ".Самый нагруженнный день"),
            List.of(TABLE_BOUNDARIES),
            busiestDay(logs, dateFrom, dateTo, ""),
            List.of(TABLE_BOUNDARIES),

            List.of(OPTIONS_HEADER + ".Агенты"),
            List.of(TABLE_BOUNDARIES),
            httpUserAgents(logs, dateFrom, dateTo, ""),
            List.of(TABLE_BOUNDARIES)
        );

        TableWriter.writeToFile("./src/main/java/edu/project3/reports/report.adoc", report);
    }
}

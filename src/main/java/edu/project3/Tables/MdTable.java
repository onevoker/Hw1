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

public class MdTable extends TableWriter {

    public void printReport(String path, List<LogRecord> logs, LocalDate dateFrom, LocalDate dateTo)
        throws URISyntaxException, IOException {
        List<List<String>> report = List.of(
            List.of("#### Общая информация"),
            generalInformation(path, logs, dateFrom, dateTo, "|:---------------------:|-------------:|"),

            List.of("#### Запрашиваемые ресурсы"),
            requestedResources(logs, dateFrom, dateTo, "|:------------------------:|-----------:|"),

            List.of("#### Коды ответа"),
            responseCodes(logs, dateFrom, dateTo, "|:----|----------------------:|-----------:|"),

            List.of("#### Лучший пользователь"),
            bestUser(logs, dateFrom, dateTo, "|:----------------:|-----------:|"),

            List.of("#### Самый нагруженнный день"),
            busiestDay(logs, dateFrom, dateTo, "|:-----------:|--------------:|"),

            List.of("#### Агенты"),
            httpUserAgents(logs, dateFrom, dateTo, "|:------------------------:|--------------------:|")
        );

        writeToFile("./src/main/java/edu/project3/reports/report.md", report);
    }
}

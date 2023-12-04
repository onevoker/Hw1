package edu.project3;

import edu.project3.LogWorkers.LogParser;
import edu.project3.Tables.AdocTable;
import edu.project3.Tables.MdTable;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import static edu.project3.InputParser.getDateFrom;
import static edu.project3.InputParser.getDateTo;
import static edu.project3.InputParser.getPath;

public class Main {
    private Main() {
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        getReport(args);
    }

    @SuppressWarnings("InnerAssignment")
    public static void getReport(String[] args) throws URISyntaxException, IOException {
        String path = "";
        String from = "";
        String to = "";
        String format = "";

        for (int i = 0; i < args.length; ++i) {
            switch (args[i]) {
                case "--path" -> path = args[i + 1];
                case "--from" -> from = args[i + 1];
                case "--to" -> to = args[i + 1];
                case "--format" -> format = args[i + 1];
                default -> {
                }
            }
        }

        path = getPath(path);
        LocalDate dateFrom = getDateFrom(from);
        LocalDate dateTo = getDateTo(to);

        printReport(path, dateFrom, dateTo, format);
    }

    public static void printReport(String path, LocalDate dateFrom, LocalDate dateTo, String format)
        throws URISyntaxException, IOException {
        if (format.equals("adoc")) {
            printAdocReport(path, dateFrom, dateTo);
        } else {
            printMdReport(path, dateFrom, dateTo);
        }
    }

    private static void printMdReport(String path, LocalDate dateFrom, LocalDate dateTo)
        throws URISyntaxException, IOException {

        MdTable mdTable = new MdTable();
        mdTable.printReport(path, LogParser.getLogs(path), dateFrom, dateTo);
    }

    private static void printAdocReport(String path, LocalDate dateFrom, LocalDate dateTo)
        throws URISyntaxException, IOException {

        AdocTable adocTable = new AdocTable();
        adocTable.printReport(path, LogParser.getLogs(path), dateFrom, dateTo);
    }
}

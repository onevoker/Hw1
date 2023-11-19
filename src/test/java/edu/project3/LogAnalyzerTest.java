package edu.project3;

import edu.project3.LogWorkers.LogAnalyzer;
import edu.project3.LogWorkers.LogParser;
import edu.project3.LogWorkers.LogRecord;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
public class LogAnalyzerTest {

    private static final List<String> LOG_LINES = List.of(
        "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"",
        "93.180.71.3 - - [17/May/2015:08:05:23 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"",
        "93.180.71.3 - - [17/May/2015:08:05:26 +0000] \"GET /downloads/product_1 HTTP/1.1\" 404 324 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"",
        "80.91.33.133 - - [17/May/2015:08:05:55 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.16)\""
    );

    private static final List<LogRecord> LOGS = LogParser.parseAllLogLines(LOG_LINES);

    @Test
    void testCountTotalCountRequests() {
        assertThat(LogAnalyzer.countTotalRequests(LOGS, LocalDate.EPOCH, LocalDate.now())).isEqualTo(4);
    }

    @Test
    void testGetTeMostFrequencyRequests() {
        Map<String, Integer> expected = Map.of(
            "/downloads/product_1", 4
        );

        assertThat(LogAnalyzer.getTeMostFrequencyRequests(LOGS, LocalDate.EPOCH, LocalDate.now())).isEqualTo(expected);
    }

    @Test
    void testGetMostFrequencyResponseCodes() {
        Map<Integer, Integer> expected = Map.of(
            304, 3,
            404, 1
        );

        assertThat(LogAnalyzer.getMostFrequencyResponseCodes(
            LOGS,
            LocalDate.EPOCH,
            LocalDate.now()
        )).isEqualTo(expected);
    }

    @Test
    void testGetMediumSizeOfAnswer() {
        assertThat(LogAnalyzer.getMediumSizeOfAnswer(LOGS, LocalDate.EPOCH, LocalDate.now())).isEqualTo(81L);
    }

    @Test
    void testGetTheBestUser() {
        assertThat(LogAnalyzer.getTheBestUser(LOGS, LocalDate.EPOCH, LocalDate.now())).isEqualTo("93.180.71.3 3");
    }

    @Test
    void testGetTheBusiestDay() {
        LocalDate expected = LocalDate.of(2015, 5, 17);

        assertThat(LogAnalyzer.getTheBusiestDay(LOGS, LocalDate.EPOCH, LocalDate.now())).isEqualTo(expected);
    }

    @Test
    void testGetAllHttpUserAgents() {
        Map<String, Integer> expected = Map.of(
            "Debian", 4
        );

        assertThat(LogAnalyzer.getAllHttpUserBrowsers(LOGS, LocalDate.EPOCH, LocalDate.now())).isEqualTo(expected);
    }
}

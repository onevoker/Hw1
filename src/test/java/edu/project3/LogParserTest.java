package edu.project3;

import edu.project3.LogWorkers.LogParser;
import org.junit.jupiter.api.Test;
import java.net.URISyntaxException;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class LogParserTest {
    private static final List<String> LOG_LINES = List.of(
        "93.180.71.3 - - [17/May/2015:08:05:32 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"",
        "93.180.71.3 - - [17/May/2015:08:05:23 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"",
        "93.180.71.3 - - [17/May/2015:08:05:26 +0000] \"GET /downloads/product_1 HTTP/1.1\" 404 324 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"",
        "80.91.33.133 - - [17/May/2015:08:05:55 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.16)\""
    );

    @Test
    void testGetLogs() throws URISyntaxException {
        assertThat(LogParser.getLogs("./src/main/java/edu/project3/logData/logs2.txt").size()).isEqualTo(7);
    }

    @Test
    void testIsURI() {
        assertAll(
            () -> assertThat(LogParser.isURI("./src/main/java/edu/project3/logData/logs.txt")).isFalse(),
            () -> assertThat(LogParser.isURI(
                "https://raw.githubusercontent.com/elastic/examples" +
                    "/master/Common%20Data%20Formats/nginx_logs/nginx_logs"))
                .isTrue()
        );
    }

    @Test
    void testParseAllLogLines() {
        assertThat(LogParser.parseAllLogLines(LOG_LINES).size()).isEqualTo(4);
    }
}

package edu.project3;

import org.junit.jupiter.api.Test;
import static edu.project3.Main.main;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MainTest {
    @Test
    void testMainDoesNotTrowWithUri() {
        assertDoesNotThrow(() -> main(
            ("java -jar nginx-log-stats.jar --path https://raw.githubusercontent.com/elastic/examples/master"
                + "/Common%20Data%20Formats/nginx_logs/nginx_logs"
                + " --from 2015-05-19")
                .split(" ")));
    }

    @Test
    void testMainDoesNotThrowWithPath() {
        assertDoesNotThrow(() -> main(
            ("java -jar nginx-log-stats.jar --path ./src/main/java/edu/project3/logData/logs.txt"
                + " --from 2015-05-17 --to 2015-05-21"
                + " --format adoc")
                .split(" ")));
    }
}

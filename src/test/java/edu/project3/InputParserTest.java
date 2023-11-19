package edu.project3;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static edu.project3.InputParser.getPath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class InputParserTest {
    @Test
    void testGetDateFrom() {
        String date1 = "";
        String date2 = "2023-08-31";

        assertAll(
            () -> assertThat(InputParser.getDateFrom(date1)).isEqualTo(LocalDate.EPOCH),
            () -> assertThat(InputParser.getDateFrom(date2)).isEqualTo(LocalDate.parse(date2))
        );
    }

    @Test
    void testGetDateTo() {
        String date1 = "";
        String date2 = "2023-08-31";

        assertAll(
            () -> assertThat(InputParser.getDateTo(date1)).isEqualTo(LocalDate.now()),
            () -> assertThat(InputParser.getDateTo(date2)).isEqualTo(LocalDate.parse(date2))
        );
    }

    @Test
    void testGetPath() {
        String directory = "./src/main/java/edu/project3/logData*";
        String path = "./src/main/java/edu/project3/logData/logs.txt";

        assertAll(
            () -> assertThat(getPath(directory)).isEqualTo("./src/main/java/edu/project3/logData"),
            () -> assertThat(getPath(path)).isEqualTo(path)
        );
    }
}

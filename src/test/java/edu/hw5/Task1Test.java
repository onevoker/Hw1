package edu.hw5;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task1Test {
    private static final Task1 task1 = new Task1();

    @Test
    void testValidInput() {
        List<String> allSessions1 = List.of(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        );

        List<String> allSessions2 = List.of(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20",
            "2022-05-10, 12:00 - 2022-05-10, 21:10"
        );

        List<String> allSessions3 = List.of(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20",
            "2022-05-10, 12:00 - 2022-05-10, 21:10",
            "2022-01-10, 13:00 - 2022-01-10, 13:40",
            "2022-03-14, 12:00 - 2022-03-15, 12:30",
            "2022-12-31, 18:05 - 2023-01-01, 01:25"
        );

        assertAll(
            () -> assertThat(task1.getAverageDurationOfSessions(allSessions1)).isEqualTo("3ч 40м"),
            () -> assertThat(task1.getAverageDurationOfSessions(allSessions2)).isEqualTo("5ч 30м"),
            () -> assertThat(task1.getAverageDurationOfSessions(allSessions3)).isEqualTo("8ч 10м")

        );
    }

    @Test
    void testInvalidInput() {
        List<String> allSessions1 = List.of(
            "2022-03-12, 1:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        );

        List<String> allSessions2 = List.of(
            "2022-03-12 - 2022-03-13",
            "2022-04, 21:30 - 2022-04, 01:20"
        );

        List<String> allSessions3 = List.of();

        assertAll(
            () -> {
                var exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> task1.getAverageDurationOfSessions(allSessions1)
                );

                assertThat(exception.getMessage()).isEqualTo("Invalid sessions");
            },
            () -> {
                var exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> task1.getAverageDurationOfSessions(allSessions2)
                );

                assertThat(exception.getMessage()).isEqualTo("Invalid sessions");
            },
            () -> {
                var exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> task1.getAverageDurationOfSessions(allSessions3)
                );

                assertThat(exception.getMessage()).isEqualTo("Empty list of sessions");
            },
            () -> {
                var exception = assertThrows(
                    NullPointerException.class,
                    () -> task1.getAverageDurationOfSessions(null)
                );

                assertThat(exception.getMessage()).isEqualTo("Null list of sessions");
            }
        );
    }
}

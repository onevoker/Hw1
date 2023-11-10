package edu.hw5;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Task2Test {
    private static final Task2 task2 = new Task2();

    @Test
    void testGetNextFridayThirteen() {
        LocalDate date1 = LocalDate.of(1925, 2, 12);
        LocalDate expected1 = LocalDate.of(1925, 2, 13);

        LocalDate date2 = LocalDate.of(2023, 11, 8);
        LocalDate expected2 = LocalDate.of(2024, 9, 13);

        LocalDate date3 = LocalDate.of(2024, 9, 13);
        LocalDate expected3 = LocalDate.of(2024, 12, 13);

        assertAll(
            () -> assertThat(task2.getNextFridayThirteen(date1)).isEqualTo(expected1),
            () -> assertThat(task2.getNextFridayThirteen(date2)).isEqualTo(expected2),
            () -> assertThat(task2.getNextFridayThirteen(date3)).isEqualTo(expected3)
        );
    }

    @Test
    void testGetAllFridaysThirteenInYear() {
        List<LocalDate> expectedFor1925thYear = List.of(
            LocalDate.of(1925, 2, 13),
            LocalDate.of(1925, 3, 13),
            LocalDate.of(1925, 11, 13)
        );

        List<LocalDate> expectedFor2024thYear = List.of(
            LocalDate.of(2024, 9, 13),
            LocalDate.of(2024, 12, 13)

        );

        List<LocalDate> expectedFor2023thYear = List.of(
            LocalDate.of(2023, 1, 13),
            LocalDate.of(2023, 10, 13)
        );

        assertAll(
            () -> assertThat(task2.getAllFridaysThirteenInYear(1925)).isEqualTo(expectedFor1925thYear),
            () -> assertThat(task2.getAllFridaysThirteenInYear(2023)).isEqualTo(expectedFor2023thYear),
            () -> assertThat(task2.getAllFridaysThirteenInYear(2024)).isEqualTo(expectedFor2024thYear)
        );
    }
}

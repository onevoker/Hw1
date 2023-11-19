package edu.hw5;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Task3Test {
    private static final Task3 task3 = new Task3();

    @Test
    void testValidDates() {
        assertAll(
            () -> assertThat(task3.parseDate("2020-10-10")).isEqualTo(Optional.of(LocalDate.of(2020, 10, 10))),
            () -> assertThat(task3.parseDate("2020-3-14")).isEqualTo(Optional.of(LocalDate.of(2020, 3, 14))),
            () -> assertThat(task3.parseDate("2020-3-4")).isEqualTo(Optional.of(LocalDate.of(2020, 3, 4))),
            () -> assertThat(task3.parseDate("2020-12-2")).isEqualTo(Optional.of(LocalDate.of(2020, 12, 2))),
            () -> assertThat(task3.parseDate("1/3/1976")).isEqualTo(Optional.of(LocalDate.of(1976, 3, 1))),
            () -> assertThat(task3.parseDate("1/3/20")).isEqualTo(Optional.of(LocalDate.of(2020, 3, 1)))
        );
    }

    @Test
    void testValidWordsForDates() {
        assertAll(
            () -> assertThat(task3.parseDate("tomorrow")).isEqualTo(Optional.of(LocalDate.now().plusDays(1))),
            () -> assertThat(task3.parseDate("Tomorrow")).isEqualTo(Optional.of(LocalDate.now().plusDays(1))),
            () -> assertThat(task3.parseDate("today")).isEqualTo(Optional.of(LocalDate.now())),
            () -> assertThat(task3.parseDate("Today")).isEqualTo(Optional.of(LocalDate.now())),
            () -> assertThat(task3.parseDate("yesterday")).isEqualTo(Optional.of(LocalDate.now().minusDays(1))),
            () -> assertThat(task3.parseDate("Yesterday")).isEqualTo(Optional.of(LocalDate.now().minusDays(1))),
            () -> assertThat(task3.parseDate("next week")).isEqualTo(Optional.of(LocalDate.now().plusWeeks(1))),
            () -> assertThat(task3.parseDate("next month")).isEqualTo(Optional.of(LocalDate.now().plusMonths(1))),
            () -> assertThat(task3.parseDate("next year")).isEqualTo(Optional.of(LocalDate.now().plusYears(1)))
        );
    }

    @Test
    void testSomeDaysAgo() {
        assertAll(
            () -> assertThat(task3.parseDate("1 day ago")).isEqualTo(Optional.of(LocalDate.now().minusDays(1))),
            () -> assertThat(task3.parseDate("2234 days ago")).isEqualTo(Optional.of(LocalDate.now().minusDays(2234))),
            () -> assertThat(task3.parseDate("3 days ago")).isEqualTo(Optional.of(LocalDate.now().minusDays(3))),
            () -> assertThat(task3.parseDate("6 Days ago")).isEqualTo(Optional.of(LocalDate.now().minusDays(6))),
            () -> assertThat(task3.parseDate("314 Days Ago")).isEqualTo(Optional.of(LocalDate.now().minusDays(314)))
        );
    }

    @Test
    void testSomeDaysLater() {
        assertAll(
            () -> assertThat(task3.parseDate("1 day later")).isEqualTo(Optional.of(LocalDate.now().plusDays(1))),
            () -> assertThat(task3.parseDate("2234 days later")).isEqualTo(Optional.of(LocalDate.now().plusDays(2234))),
            () -> assertThat(task3.parseDate("3 days later")).isEqualTo(Optional.of(LocalDate.now().plusDays(3))),
            () -> assertThat(task3.parseDate("444 Days later")).isEqualTo(Optional.of(LocalDate.now().plusDays(444))),
            () -> assertThat(task3.parseDate("314 Days later")).isEqualTo(Optional.of(LocalDate.now().plusDays(314)))
        );
    }

    @Test
    void testInvalidInput() {
        assertAll(
            () -> assertThat(task3.parseDate("")).isEqualTo(Optional.empty()),
            () -> assertThat(task3.parseDate("haha 5 days ago")).isEqualTo(Optional.empty()),
            () -> assertThat(task3.parseDate("")).isEqualTo(Optional.empty()),
            () -> assertThat(task3.parseDate("zavtra")).isEqualTo(Optional.empty()),
            () -> assertThat(task3.parseDate("1001010")).isEqualTo(Optional.empty()),
            () -> assertThat(task3.parseDate("never")).isEqualTo(Optional.empty()),
            () -> assertThat(task3.parseDate("sometimes")).isEqualTo(Optional.empty()),
            () -> assertThat(task3.parseDate("202023-1-1")).isEqualTo(Optional.empty()),
            () -> assertThat(task3.parseDate("202023/1/1")).isEqualTo(Optional.empty())
        );
    }
}
